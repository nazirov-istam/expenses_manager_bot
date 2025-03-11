package com.example.expenses.service;

import com.example.expenses.application.Messages;
import com.example.expenses.enums.Language;
import com.example.expenses.model.User;
import com.example.expenses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String getStartMessage(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.startUzIsRegistered;
            case RUSSIAN -> Messages.startRuIsRegistered;
            case ENGLISH -> Messages.startEnIsRegistered;
            default -> "Iltimos, tilni tanlang!";
        };
    }


    public void registerUser(Message message) {
        try {
            User user = new User();
            user.setChatId(message.getChatId());
            user.setCreatedAt(LocalDateTime.now());
            user.setIncome(0.0);
            user.setExpense(0.0);
            user.setTotalBalance(0.0);
            user.setPhoneNumber("Ma'lumot kiritilmagan.");
            user.setUsername(Optional.ofNullable(message.getChat())
                    .map(Chat::getUserName)
                    .orElse("Ma'lumot kiritilmagan."));
            user.setFirstname(Optional.ofNullable(message.getChat())
                    .map(Chat::getFirstName)
                    .orElse("Ma'lumot kiritilmagan."));
            user.setLastname(Optional.ofNullable(message.getChat())
                    .map(Chat::getLastName)
                    .orElse("Ma'lumot kiritilmagan."));
            log.info("Foydalanuvchi muvaffaqiyatli ro'yxatdan o'tdi.");
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Foydalanuvchini ro'yxatdan o'tkazishda xatolik ro'y berdi.");
            throw new RuntimeException();
        }
    }

    public void saveLanguage(Language language, long chatId) {
        User user = getCurrentUser(chatId);
        user.setLanguage(language);
        userRepository.save(user);
    }

    public User getCurrentUser(Long chatId) {
        return userRepository.findById(chatId).orElse(null);
    }

    public String getInfo(Long chatId, Language language) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return userRepository.findById(chatId)
                .map(user -> {
                    return switch (language) {
                        case UZBEK -> String.format("""
                                        👤 Ism: %s
                                        🏷️ Familiya: %s
                                        📞 Telefon raqam: %s
                                        💰 Daromad miqdori: %s
                                        💸 Xarajatlar miqdori: %s
                                        💵 Umumiy balans: %s
                                        📅 Qo'shilgan sana: %s
                                        """, user.getFirstname(), user.getLastname(), user.getPhoneNumber(),
                                user.getIncome(), user.getExpense(), user.getTotalBalance(), user.getCreatedAt().format(formatter));
                        case RUSSIAN -> String.format("""
                                        👤 Имя: %s
                                        🏷️ Фамилия: %s
                                        📞 Номер телефона: %s
                                        💰 Доход: %s
                                        💸 Расходы: %s
                                        💵 Общий баланс: %s
                                        📅 Дата регистрации: %s
                                        """, user.getFirstname(), user.getLastname(), user.getPhoneNumber(),
                                user.getIncome(), user.getExpense(), user.getTotalBalance(), user.getCreatedAt().format(formatter));
                        case ENGLISH -> String.format("""
                                        👤 First Name: %s
                                        🏷️ Last Name: %s
                                        📞 Phone Number: %s
                                        💰 Income Amount: %s
                                        💸 Expense Amount: %s
                                        💵 Total Balance: %s
                                        📅 Joined Date: %s
                                        """, user.getFirstname(), user.getLastname(), user.getPhoneNumber(),
                                user.getIncome(), user.getExpense(), user.getTotalBalance(), user.getCreatedAt().format(formatter));
                    };
                })
                .orElse(switch (language) {
                    case UZBEK -> "Bunday foydalanuvchi mavjud emas";
                    case RUSSIAN -> "Такой пользователь не существует";
                    case ENGLISH -> "Such a user does not exist";
                });
    }

    // TODO: Userni usha oydagi barcha xarajatlarini chiqarish

    // TODO: Userni usha oydagi barcha daromatlarini chiqarish

    // TODO: Userni usha yildagi barcha xarajatlarini chiqarish

    // TODO: Userni usha yildagi barcha daromatlarini chiqarish

    /*

    public File generateExpenseReport(Long userId, String month) throws Exception {
        List<Expense> expenses = expenseRepository.findAllByUserAndMonth(userId, month);

        User currentUser = getCurrentUser(userId);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Expenses");

        // Define column headers based on the selected language
        String[] columns;
        switch (currentUser.getLanguage()) {
            case RUSSIAN:
                columns = new String[]{"Сумма расхода", "Источник расхода", "Описание", "Дата создания"};
                break;
            case UZBEK:
                columns = new String[]{"Xarajat summasi", "Xarajat manbai", "Tavsif", "Yaratilgan sana"};
                break;
            default: // English
                columns = new String[]{"Expense Amount", "Expense Source", "Description", "Created At"};
                break;
        }

        // Create Header Row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            headerRow.createCell(i).setCellValue(columns[i]);
        }

        // Fill Data
        int rowNum = 1;
        for (Expense expense : expenses) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(expense.getExpenseAmount()); // Expense Amount
            row.createCell(1).setCellValue(expense.getExpenseSource()); // Expense Source
            row.createCell(2).setCellValue(expense.getDescription());   // Description
            row.createCell(3).setCellValue(expense.getCreatedAt().toString()); // Created At
        }

        // Save to File
        File file = new File("expenses_" + userId + "_" + month + ".xlsx");
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        }
        workbook.close();

        return file;
    }

    SendDocument document = new SendDocument();
    document.setChatId(userChatId);
    document.setDocument(new InputFile(file));
    telegramBot.execute(document);

    file.delete();
     */
}
