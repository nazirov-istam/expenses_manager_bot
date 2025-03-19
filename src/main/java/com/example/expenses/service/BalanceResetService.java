package com.example.expenses.service;

import com.example.expenses.model.User;
import com.example.expenses.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BalanceResetService {
    private final UserRepository userRepository;
    private final TelegramService telegramService;

    public BalanceResetService(UserRepository userRepository, TelegramService telegramService) {
        this.userRepository = userRepository;
        this.telegramService = telegramService;
    }

    //TODO Test qilib kurish kerak

    @Scheduled(cron = "0 0 0 1 * ?")
    @Transactional
    public void resetUserBalances() {
        List<User> users = userRepository.findAll();

        // 1️⃣ Userlarning balansini 0 ga tushirish
        userRepository.resetAllUserBalances();

        // 2️⃣ Har bir userga Telegram orqali xabar yuborish
        for (User user : users) {
            if (user.getChatId() != null) {
                String message = switch (user.getLanguage()) {
                    case RUSSIAN -> "Здравствуйте, " + user.getFirstname() + " " + user.getLastname() + "!\n" +
                            "Ваш баланс был обнулен. Следите за своими финансовыми операциями в новом месяце!";
                    case ENGLISH -> "Hello, " + user.getFirstname() + " " + user.getLastname() + "!\n" +
                            "Your balance has been reset. Keep track of your financial transactions this month!";
                    default -> "Assalomu alaykum, " + user.getFirstname() + " " + user.getLastname() + "!\n" +
                            "Sizning balansingiz nollandi. Yangi oydagi moliyaviy hisob-kitoblaringizni kuzatib boring!";
                };
                telegramService.sendTelegramMessage(user.getChatId(), message);
            }
        }
        System.out.println("Barcha userlarning balanslari 0 ga tushirildi va xabar yuborildi.");
    }
}
