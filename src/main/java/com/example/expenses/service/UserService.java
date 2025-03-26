package com.example.expenses.service;

import com.example.expenses.application.Messages;
import com.example.expenses.enums.Language;
import com.example.expenses.model.User;
import com.example.expenses.repository.ExpenseRepository;
import com.example.expenses.repository.IncomeRepository;
import com.example.expenses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public boolean setFirstName(Long chatId, String userText) {
        User user = getCurrentUser(chatId);
        if (userText.matches("[A-Za-zА-Яа-яЁёЎўҚқҒғҲҳЧчШшЪъЬьІіЄєҐґ’ʼ ]+")) {
            user.setFirstname(userText);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean setLastName(Long chatId, String userText) {
        User user = getCurrentUser(chatId);
        if (userText.matches("[A-Za-zА-Яа-яЁёЎўҚқҒғҲҳЧчШшЪъЬьІіЄєҐґ’ʼ ]+")) {
            user.setLastname(userText);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean setPhoneNumber(Long chatId, String userText) {
        User user = getCurrentUser(chatId);
        if (userText.replaceAll("\\s+", "").matches("\\+\\d+")) {
            user.setPhoneNumber(userText);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean setPhoneNumber2(Long chatId, String userText) {
        User user = getCurrentUser(chatId);
        if (userText.matches("\\+\\d+")) {
            user.setPhoneNumber(userText);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean setBalance(Long chatId, String userText) {
        User user = getCurrentUser(chatId);
        if (userText.matches("\\d+(\\.\\d+)?")) {
            double value = Double.parseDouble(userText);
            user.setTotalBalance(value);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public String getStartMessage(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.startUzIsRegistered;
            case RUSSIAN -> Messages.startRuIsRegistered;
            case ENGLISH -> Messages.startEnIsRegistered;
            default -> "Iltimos, tilni tanlang!";
        };
    }

    public String getFirstName(String userText) {
        return switch (userText) {
            case Messages.Uz -> Messages.askFirstNameUz;
            case Messages.Ru -> Messages.askFirstNameRu;
            case Messages.En -> Messages.askFirstNameEn;
            default -> throw new IllegalStateException("Unexpected value: " + userText);
        };
    }

    public String getFirstNameAgain(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.getAskFirstNameAgainUz;
            case RUSSIAN -> Messages.getAskFirstNameAgainRu;
            case ENGLISH -> Messages.getAskFirstNameAgainEn;
        };
    }

    public String getLastName(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.askLastNameUz;
            case RUSSIAN -> Messages.askLastNameRu;
            case ENGLISH -> Messages.askLastNameEn;
        };
    }

    public String getLastNameAgain(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.getAskLastNameAgainUz;
            case RUSSIAN -> Messages.getAskLastNameAgainRu;
            case ENGLISH -> Messages.getAskLastNameAgainEn;
        };
    }

    public String getPhoneNumber(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.askPhoneNumberUz;
            case RUSSIAN -> Messages.askPhoneNumberRu;
            case ENGLISH -> Messages.askPhoneNumberEn;
        };
    }

    public String getPhoneNumberAgain(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.getAskPhoneNumberAgainUz;
            case RUSSIAN -> Messages.getAskPhoneNumberAgainRu;
            case ENGLISH -> Messages.getAskPhoneNumberAgainEn;
        };
    }

    public String getBalance(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.askCurrentBalanceUz;
            case RUSSIAN -> Messages.askCurrentBalanceRu;
            case ENGLISH -> Messages.askCurrentBalanceEn;
        };
    }

    public String getBalanceAgain(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.getAskCurrentBalanceAgainUz;
            case RUSSIAN -> Messages.getAskCurrentBalanceAgainRu;
            case ENGLISH -> Messages.getAskCurrentBalanceAgainEn;
        };
    }

    public String saveUser(User user) {
        return switch (user.getLanguage()) {
            case UZBEK -> Messages.registerSuccessUz;
            case RUSSIAN -> Messages.registerSuccessRu;
            case ENGLISH -> Messages.registerSuccessEn;
        };
    }


    public void registerUser(Message message) {
        try {
            User user = new User();
            user.setChatId(message.getChatId());
            user.setCreatedAt(LocalDateTime.now());
            user.setIncome(0.0);
            user.setExpense(0.0);
            user.setUsername(Optional.ofNullable(message.getChat())
                    .map(Chat::getUserName)
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

    public void setLanguage(String language, long chatId) {
        User user = getCurrentUser(chatId);
        switch (language) {
            case Messages.Uz -> user.setLanguage(Language.UZBEK);
            case Messages.Ru -> user.setLanguage(Language.RUSSIAN);
            case Messages.En -> user.setLanguage(Language.ENGLISH);
        }
        userRepository.save(user);
    }

    public Language getLanguage(Long chatId) {
        return userRepository.findById(chatId).get().getLanguage();
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
                                        📅 Ro'yxatdan o'tgan sana: %s
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
                                        📅 Registration date: %s
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

    public boolean checkIfIncomeExistsByYear(String year, Long userId) {
        int parsedYear = Integer.parseInt(year); // String -> int
        return incomeRepository.existsByYearAndUserId(parsedYear, userId);
    }

    public boolean checkIfExpenseExistsByYear(String year, Long userId) {
        int parsedYear = Integer.parseInt(year); // String -> int
        return expenseRepository.existsByYearAndUserId(parsedYear, userId);
    }

    public boolean checkIfIncomeExistsByDate(String userText, Long userId) {
        String[] parts = userText.split("-");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Noto‘g‘ri sana formati. To‘g‘ri format: YYYY-MM yoki YYYY-M");
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23, 59, 59, 999999999);

        return incomeRepository.existsByUserChatIdAndCreatedAtBetween(userId, startDate, endDate);
    }

    public boolean checkIfExpenseExistsByDate(String userText, Long userId) {
        String[] parts = userText.split("-");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Noto‘g‘ri sana formati. To‘g‘ri format: YYYY-MM yoki YYYY-M");
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23, 59, 59, 999999999);

        return expenseRepository.existsByUserChatIdAndCreatedAtBetween(userId, startDate, endDate);
    }

    public String getUserStatistics(Long chatId) {
        if (chatId == 1386819485L) {
            long totalUsers = userRepository.getTotalUserCount();

            Object[] mostActiveMonth = new String[]{userRepository.findMonthWithMostUsers()
                    .orElse(Arrays.toString(new Object[]{"Ma'lumot yo'q", 0}))};
            Object[] leastActiveMonth = new String[]{userRepository.findMonthWithLeastUsers()
                    .orElse(Arrays.toString(new Object[]{"Ma'lumot yo'q", 0}))};

            String mostActiveMonthFormatted = formatMonthData(mostActiveMonth);
            String leastActiveMonthFormatted = formatMonthData(leastActiveMonth);

            return String.format("""
                    📊 Bot User Statistics:
                    
                    👥 Total number of users: %d
                    📅 Month with the most new users: %s
                    📅 Month with the fewest new users: %s
                    """, totalUsers, mostActiveMonthFormatted, leastActiveMonthFormatted);
        }

        return switch (getCurrentUser(chatId).getLanguage()) {
            case UZBEK -> "Bunday buyruq mavjud emas yoki noto‘g‘ri formatda kiritilgan.";
            case RUSSIAN -> "Такой команды не существует или она введена в неверном формате.";
            case ENGLISH -> "Such a command does not exist or has been entered in an incorrect format.";
        };
    }

    private String formatMonthData(Object[] monthData) {
        if (monthData == null || monthData.length < 2 || monthData[0] == null || monthData[1] == null) {
            return "Ma'lumot topilmadi";
        }
        return String.format("%s , %s kishi", monthData[0], monthData[1]);
    }

    public static Map<Long, String> map = new HashMap<>();

    public void saveYearOfMonthlyReport(Long chatId, String userText) {
        map.put(chatId, userText);
    }

    public String saveMonthOfMonthlyReport(Long chatId, String userText) {
        switch (userText) {
            case "Yanvar", "January", "Январь" -> map.put(chatId, map.get(chatId) + "-01");
            case "Fevral", "February", "Февраль" -> map.put(chatId, map.get(chatId) + "-02");
            case "Mart", "March", "Март" -> map.put(chatId, map.get(chatId) + "-03");
            case "Aprel", "April", "Апрель" -> map.put(chatId, map.get(chatId) + "-04");
            case "May", "Май" -> map.put(chatId, map.get(chatId) + "-05");
            case "Iyun", "June", "Июнь" -> map.put(chatId, map.get(chatId) + "-06");
            case "Iyul", "July", "Июль" -> map.put(chatId, map.get(chatId) + "-07");
            case "Avgust", "August", "Август" -> map.put(chatId, map.get(chatId) + "-08");
            case "Sentabr", "September", "Сентябрь" -> map.put(chatId, map.get(chatId) + "-09");
            case "Oktabr", "October", "Октябрь" -> map.put(chatId, map.get(chatId) + "-10");
            case "Noyabr", "November", "Ноябрь" -> map.put(chatId, map.get(chatId) + "-11");
            case "Dekabr", "December", "Декабрь" -> map.put(chatId, map.get(chatId) + "-12");
        }
        return map.get(chatId);
    }

    public void deleteYearAndDateOfMonthlyReport(Long chatId) {
        map.put(chatId, "");
    }

    public ReplyKeyboardMarkup sendRequestForPhoneNumber(Long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        KeyboardButton button = new KeyboardButton();
        var row = new KeyboardRow();
        switch (getCurrentUser(chatId).getLanguage()) {
            case UZBEK -> button.setText("📱 Telefon raqamni yuborish");
            case RUSSIAN -> button.setText("📱 Отправить номер телефона");
            case ENGLISH -> button.setText("📱 Send phone number");
        }
        button.setRequestContact(true);
        row.add(button);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }
}
