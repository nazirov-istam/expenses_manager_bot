package com.example.expenses.service;

import com.example.expenses.enums.Language;
import com.example.expenses.model.Expense;
import com.example.expenses.model.Income;
import com.example.expenses.model.User;
import com.example.expenses.repository.ExpenseRepository;
import com.example.expenses.repository.IncomeRepository;
import com.example.expenses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
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
    private final UserRepository userRepository;

    public byte[] generateMonthlyIncomeReport(Long chatId, String yearMonth) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            User user = userService.getCurrentUser(chatId);
            Language language = user.getLanguage();

            Sheet sheet;
            Row header;
            String totalLabel;

            CellStyle boldStyle = workbook.createCellStyle();
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            boldStyle.setFont(boldFont);

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
                row.createCell(3).setCellValue(income.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                totalIncome += income.getIncomeAmount();
            }

            Row totalRow = sheet.createRow(rowNum);
            createHeaderCell(totalRow, 0, totalLabel, boldStyle);
            totalRow.createCell(1).setCellValue(totalIncome);

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating Income report", e);
        }
    }

    public List<Income> getMonthlyIncome(Long chatId, String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDateTime startDate = ym.atDay(1).atStartOfDay();
        LocalDateTime endDate = ym.atEndOfMonth().atTime(23, 59, 59);

        return incomeRepository.findAllByUserChatIdAndCreatedAtBetween(chatId, startDate, endDate);
    }

    public List<Expense> getMonthlyExpense(Long chatId, String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDateTime startDate = ym.atDay(1).atStartOfDay();
        LocalDateTime endDate = ym.atEndOfMonth().atTime(23, 59, 59);

        return expenseRepository.findAllByUserChatIdAndCreatedAtBetween(chatId, startDate, endDate);
    }


    public InputFile sendMonthlyIncomeReportDirectly(Long chatId, String yearMonth) {
        byte[] excelData = generateMonthlyIncomeReport(chatId, yearMonth);

        InputStream inputStream = new ByteArrayInputStream(excelData);
        String fileName = String.format("income_report_%s.xlsx", yearMonth);

        return new InputFile(inputStream, fileName);
    }


    public byte[] generateMonthlyExpenseReport(Long chatId, String yearMonth) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            User user = userService.getCurrentUser(chatId);
            Language language = user.getLanguage();

            Sheet sheet;
            Row header;
            String totalLabel;

            CellStyle boldStyle = workbook.createCellStyle();
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            boldStyle.setFont(boldFont);

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
                row.createCell(3).setCellValue(expense.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                totalExpense += expense.getExpenseAmount();
            }

            Row totalRow = sheet.createRow(rowNum);
            createHeaderCell(totalRow, 0, totalLabel, boldStyle);
            totalRow.createCell(1).setCellValue(totalExpense);

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating Expense report", e);
        }
    }

    public InputFile sendMonthlyExpenseReportDirectly(Long chatId, String yearMonth) {
        byte[] excelData = generateMonthlyExpenseReport(chatId, yearMonth);
        InputStream inputStream = new ByteArrayInputStream(excelData);
        String fileName = String.format("expense_report_%s.xlsx", yearMonth);

        return new InputFile(inputStream, fileName);
    }

    public List<Expense> getYearlyExpense(Long chatId, String year) {
        int parsedYear = Integer.parseInt(year);

        LocalDateTime startDate = LocalDateTime.of(parsedYear, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(parsedYear, 12, 31, 23, 59, 59);

        return expenseRepository.findAllByUserChatIdAndCreatedAtBetween(chatId, startDate, endDate);
    }

    public List<Income> getYearlyIncome(Long chatId, String year) {
        int parsedYear = Integer.parseInt(year);

        LocalDateTime startDate = LocalDateTime.of(parsedYear, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(parsedYear, 12, 31, 23, 59, 59);

        return incomeRepository.findAllByUserChatIdAndCreatedAtBetween(chatId, startDate, endDate);
    }

    public byte[] generateYearlyIncomeReport(Long chatId, String year) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            User user = userService.getCurrentUser(chatId);
            Language language = user.getLanguage();

            Sheet sheet;
            Row header;
            String totalLabel;

            CellStyle boldStyle = workbook.createCellStyle();
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            boldStyle.setFont(boldFont);

            switch (language) {
                case UZBEK -> {
                    sheet = workbook.createSheet("Yillik Daromat Hisoboti");
                    header = sheet.createRow(0);
                    createHeaderCell(header, 0, "Daromat manbai", boldStyle);
                    createHeaderCell(header, 1, "Miqdori", boldStyle);
                    createHeaderCell(header, 2, "Izoh", boldStyle);
                    createHeaderCell(header, 3, "Sana", boldStyle);
                    totalLabel = "Umumiy daromat:";
                }
                case RUSSIAN -> {
                    sheet = workbook.createSheet("Годовой отчет о доходах");
                    header = sheet.createRow(0);
                    createHeaderCell(header, 0, "Источник дохода", boldStyle);
                    createHeaderCell(header, 1, "Сумма", boldStyle);
                    createHeaderCell(header, 2, "Описание", boldStyle);
                    createHeaderCell(header, 3, "Дата", boldStyle);
                    totalLabel = "Общий доход:";
                }
                default -> {
                    sheet = workbook.createSheet("Yearly Income Report");
                    header = sheet.createRow(0);
                    createHeaderCell(header, 0, "Source", boldStyle);
                    createHeaderCell(header, 1, "Amount", boldStyle);
                    createHeaderCell(header, 2, "Description", boldStyle);
                    createHeaderCell(header, 3, "Created At", boldStyle);
                    totalLabel = "Total Income:";
                }
            }

            List<Income> incomes = getYearlyIncome(chatId, year);
            int rowNum = 1;
            double totalIncome = 0;

            for (Income income : incomes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(income.getIncomeSource());
                row.createCell(1).setCellValue(income.getIncomeAmount());
                row.createCell(2).setCellValue(income.getDescription());
                row.createCell(3).setCellValue(income.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                totalIncome += income.getIncomeAmount();
            }

            Row totalRow = sheet.createRow(rowNum);
            createHeaderCell(totalRow, 0, totalLabel, boldStyle);
            totalRow.createCell(1).setCellValue(totalIncome);

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating Yearly Income report", e);
        }
    }

    private void createHeaderCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    public byte[] generateYearlyExpenseReport(Long chatId, String year) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            User user = userService.getCurrentUser(chatId);
            Language language = user.getLanguage();

            Sheet sheet;
            Row header;
            String totalLabel;

            CellStyle boldStyle = workbook.createCellStyle();
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            boldStyle.setFont(boldFont);

            switch (language) {
                case UZBEK -> {
                    sheet = workbook.createSheet("Yillik Xarajat Hisoboti");
                    header = sheet.createRow(0);
                    createHeaderCell(header, 0, "Xarajat turi", boldStyle);
                    createHeaderCell(header, 1, "Miqdori", boldStyle);
                    createHeaderCell(header, 2, "Izoh", boldStyle);
                    createHeaderCell(header, 3, "Sana", boldStyle);
                    totalLabel = "Umumiy xarajat:";
                }
                case RUSSIAN -> {
                    sheet = workbook.createSheet("Годовой отчет о расходах");
                    header = sheet.createRow(0);
                    createHeaderCell(header, 0, "Тип расхода", boldStyle);
                    createHeaderCell(header, 1, "Сумма", boldStyle);
                    createHeaderCell(header, 2, "Описание", boldStyle);
                    createHeaderCell(header, 3, "Дата", boldStyle);
                    totalLabel = "Общий расход:";
                }
                default -> {
                    sheet = workbook.createSheet("Yearly Expense Report");
                    header = sheet.createRow(0);
                    createHeaderCell(header, 0, "Expense Type", boldStyle);
                    createHeaderCell(header, 1, "Amount", boldStyle);
                    createHeaderCell(header, 2, "Description", boldStyle);
                    createHeaderCell(header, 3, "Created At", boldStyle);
                    totalLabel = "Total Expense:";
                }
            }

            List<Expense> expenses = getYearlyExpense(chatId, year);
            int rowNum = 1;
            double totalExpense = 0;

            for (Expense expense : expenses) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(expense.getExpenseSource());
                row.createCell(1).setCellValue(expense.getExpenseAmount());
                row.createCell(2).setCellValue(expense.getDescription());
                row.createCell(3).setCellValue(expense.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                totalExpense += expense.getExpenseAmount();
            }

            Row totalRow = sheet.createRow(rowNum);
            createHeaderCell(totalRow, 0, totalLabel, boldStyle);
            totalRow.createCell(1).setCellValue(totalExpense);

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating Yearly Expense report", e);
        }
    }

    public InputFile sendYearlyExpenseReportDirectly(Long chatId, String year) {
        byte[] excelData = generateYearlyExpenseReport(chatId, year);
        InputStream inputStream = new ByteArrayInputStream(excelData);
        String fileName = String.format("expense_report_%s.xlsx", year);

        return new InputFile(inputStream, fileName);
    }

    public InputFile sendYearlyIncomeReportDirectly(Long chatId, String year) {
        byte[] excelData = generateYearlyIncomeReport(chatId, year);
        InputStream inputStream = new ByteArrayInputStream(excelData);
        String fileName = String.format("income_report_%s.xlsx", year);

        return new InputFile(inputStream, fileName);
    }

    public byte[] getAllUserInfoForOwner() {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet;
            Row header;
            String totalLabel;

            CellStyle boldStyle = workbook.createCellStyle();
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            boldStyle.setFont(boldFont);

            sheet = workbook.createSheet("Foydalanuvchilar ma'lumotlari");
            header = sheet.createRow(0);

            createHeaderCell(header, 0, "Ismi", boldStyle);
            createHeaderCell(header, 1, "Familiyasi", boldStyle);
            createHeaderCell(header, 2, "Username", boldStyle);
            createHeaderCell(header, 3, "Telefon raqami", boldStyle);
            createHeaderCell(header, 4, "Ro'yxatdan o'tgan sana", boldStyle);
            createHeaderCell(header, 5, "Hozirgi balans", boldStyle);

            totalLabel = "Umumiy soni:";

            List<User> users = userRepository.findAll();
            int rowNum = 1;

            for (User user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getFirstname());
                row.createCell(1).setCellValue(user.getLastname());
                row.createCell(2).setCellValue("@" + user.getUsername());
                row.createCell(3).setCellValue(user.getPhoneNumber());
                row.createCell(4).setCellValue(user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                row.createCell(5).setCellValue(user.getTotalBalance());
            }

            Row emptyRow = sheet.createRow(rowNum++);

            Row totalRow = sheet.createRow(rowNum);
            createHeaderCell(totalRow, 0, totalLabel, boldStyle);
            totalRow.createCell(1).setCellValue(userRepository.getTotalUserCount());

            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating All Users report", e);
        }
    }

    public InputFile sendAllUserInfo(Long chatId) {
        if (chatId == 1386819485L) {
            byte[] excelData = getAllUserInfoForOwner();

            InputStream inputStream = new ByteArrayInputStream(excelData);
            String fileName = "all_users_info.xlsx";

            return new InputFile(inputStream, fileName);
        }
        return null;
    }
}
