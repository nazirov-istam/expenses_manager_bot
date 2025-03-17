package com.example.expenses.service;

import com.example.expenses.enums.Language;
import com.example.expenses.model.Expense;
import com.example.expenses.model.Income;
import com.example.expenses.model.User;
import com.example.expenses.repository.ExpenseRepository;
import com.example.expenses.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public byte[] generateIncomeReport(Long chatId, String yearMonth) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            User user = userService.getCurrentUser(chatId);
            Language language = user.getLanguage(); // Foydalanuvchining tilini olish

            Sheet sheet;
            Row header;
            String totalLabel;

            switch (language) {
                case UZBEK -> {
                    sheet = workbook.createSheet("Daromat Hisoboti");
                    header = sheet.createRow(0);
                    header.createCell(0).setCellValue("Daromat manbai");
                    header.createCell(1).setCellValue("Miqdori");
                    header.createCell(2).setCellValue("Izoh");
                    header.createCell(3).setCellValue("Sana");
                    totalLabel = "Umumiy daromat:";
                }
                case RUSSIAN -> {
                    sheet = workbook.createSheet("Отчет о доходах");
                    header = sheet.createRow(0);
                    header.createCell(0).setCellValue("Источник дохода");
                    header.createCell(1).setCellValue("Сумма");
                    header.createCell(2).setCellValue("Описание");
                    header.createCell(3).setCellValue("Дата");
                    totalLabel = "Общий доход:";
                }
                default -> { // Default English
                    sheet = workbook.createSheet("Income Report");
                    header = sheet.createRow(0);
                    header.createCell(0).setCellValue("Source");
                    header.createCell(1).setCellValue("Amount");
                    header.createCell(2).setCellValue("Description");
                    header.createCell(3).setCellValue("Created At");
                    totalLabel = "Total Income:";
                }
            }

            List<Income> incomes = getMonthlyIncome(chatId, yearMonth);
            int rowNum = 1;
            double totalIncome = 0;

            for (Income income : incomes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(income.getIncomeSource());
                row.createCell(1).setCellValue(income.getIncomeAmount());
                row.createCell(2).setCellValue(income.getDescription());
                row.createCell(3).setCellValue(income.getCreatedAt().toString());
                totalIncome += income.getIncomeAmount();
            }

            Row totalRow = sheet.createRow(rowNum);
            totalRow.createCell(0).setCellValue(totalLabel);
            totalRow.createCell(1).setCellValue(totalIncome);

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating Income report", e);
        }
    }

    public InputFile sendIncomeReportDirectly(Long chatId, String yearMonth) {
        byte[] excelData = generateIncomeReport(chatId, yearMonth);

        InputStream inputStream = new ByteArrayInputStream(excelData);
        String fileName = String.format("income_report_%s.xlsx", yearMonth);

        return new InputFile(inputStream, fileName);
    }

    public List<Income> getMonthlyIncome(Long chatId, String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDateTime startDate = ym.atDay(1).atStartOfDay();
        LocalDateTime endDate = ym.atEndOfMonth().atTime(23, 59, 59);

        return incomeRepository.findByUser_ChatIdAndCreatedAtBetween(chatId, startDate, endDate);
    }

    public byte[] generateExpenseReport(Long chatId, String yearMonth) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            User user = userService.getCurrentUser(chatId);
            Language language = user.getLanguage();

            Sheet sheet;
            Row header;
            String totalLabel;

            switch (language) {
                case UZBEK -> {
                    sheet = workbook.createSheet("Xarajat Hisoboti");
                    header = sheet.createRow(0);
                    header.createCell(0).setCellValue("Xarajat turi");
                    header.createCell(1).setCellValue("Miqdori");
                    header.createCell(2).setCellValue("Izoh");
                    header.createCell(3).setCellValue("Sana");
                    totalLabel = "Umumiy xarajat:";
                }
                case RUSSIAN -> {
                    sheet = workbook.createSheet("Отчет о расходах");
                    header = sheet.createRow(0);
                    header.createCell(0).setCellValue("Тип расхода");
                    header.createCell(1).setCellValue("Сумма");
                    header.createCell(2).setCellValue("Описание");
                    header.createCell(3).setCellValue("Дата");
                    totalLabel = "Общий расход:";
                }
                default -> {
                    sheet = workbook.createSheet("Expense Report");
                    header = sheet.createRow(0);
                    header.createCell(0).setCellValue("Expense Type");
                    header.createCell(1).setCellValue("Amount");
                    header.createCell(2).setCellValue("Description");
                    header.createCell(3).setCellValue("Created At");
                    totalLabel = "Total Expense:";
                }
            }

            List<Expense> expenses = getMonthlyExpense(chatId, yearMonth);
            int rowNum = 1;
            double totalExpense = 0;

            for (Expense expense : expenses) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(expense.getExpenseSource());
                row.createCell(1).setCellValue(expense.getExpenseAmount());
                row.createCell(2).setCellValue(expense.getDescription());
                row.createCell(3).setCellValue(expense.getCreatedAt().toString());
                totalExpense += expense.getExpenseAmount();
            }

            Row totalRow = sheet.createRow(rowNum);
            totalRow.createCell(0).setCellValue(totalLabel);
            totalRow.createCell(1).setCellValue(totalExpense);

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating Expense report", e);
        }
    }

    public InputFile sendExpenseReportDirectly(Long chatId, String yearMonth) {
        byte[] excelData = generateExpenseReport(chatId, yearMonth);
        InputStream inputStream = new ByteArrayInputStream(excelData);
        String fileName = String.format("expense_report_%s.xlsx", yearMonth);

        return new InputFile(inputStream, fileName);
    }

    public List<Expense> getMonthlyExpense(Long chatId, String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDateTime startDate = ym.atDay(1).atStartOfDay();
        LocalDateTime endDate = ym.atEndOfMonth().atTime(23, 59, 59);

        return expenseRepository.findByUser_ChatIdAndCreatedAtBetween(chatId, startDate, endDate);
    }
}
