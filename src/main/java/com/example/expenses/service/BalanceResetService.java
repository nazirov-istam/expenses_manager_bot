package com.example.expenses.service;

import com.example.expenses.enums.Language;
import com.example.expenses.model.User;
import com.example.expenses.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BalanceResetService {
    private final UserRepository userRepository;
    private final TelegramService telegramService;

    public BalanceResetService(UserRepository userRepository, TelegramService telegramService) {
        this.userRepository = userRepository;
        this.telegramService = telegramService;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void resetUserBalances() {
        List<User> users = userRepository.findAll();

        userRepository.resetAllUserBalances();

        Map<Language, String> messages = Map.of(
                Language.RUSSIAN, "Здравствуйте, %s %s!\n\nВаш баланс доходов и расходов был обнулен. ✅\nСледите за своими финансовыми операциями в новом месяце! 🎯",
                Language.ENGLISH, "Hello, %s %s!\n\nYour income and expense balance has been reset. ✅\nKeep track of your financial transactions this month! 🎯",
                Language.UZBEK, "Assalomu alaykum, %s %s!\n\nSizning daromad va xarajat balansingiz nollandi. ✅\nYangi oydagi moliyaviy hisob-kitoblaringizni kuzatib boring! 🎯"
        );

        for (User user : users) {
            if (user.getChatId() != null) {
                String messageTemplate = messages.getOrDefault(user.getLanguage(), messages.get(Language.ENGLISH));
                String message = escapeMarkdownV2(String.format(messageTemplate, user.getFirstname(), user.getLastname()));
                telegramService.sendTelegramMessage(user.getChatId(), message);
            }
        }

        System.out.println("Barcha userlarning balanslari 0 ga tushirildi va xabar yuborildi.");
    }

    public String escapeMarkdownV2(String text) {
        return text.replaceAll("([_\\-*\\[\\]()~`>#+=|{}.!])", "\\\\$1");
    }
}
