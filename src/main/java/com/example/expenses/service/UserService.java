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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            User user = new User();
            user.setChatId(message.getChatId());
            user.setCreatedAt(LocalDateTime.now().format(formatter));
            user.setIncome(0.0);
            user.setOutcome(0.0);
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
        switch (language) {
            case UZBEK -> {
                return userRepository.findById(chatId)
                        .map(user -> String.format("""
                                        👤 Ism: %s
                                        🏷️ Familiya: %s
                                        📞 Telefon raqam: %s
                                        💰 Daromad miqdori: %s
                                        💸 Xarajatlar miqdori: %s
                                        📅 Qo'shilgan sana: %s
                                        """, user.getFirstname(), user.getLastname(), user.getPhoneNumber(),
                                user.getIncome().toString(), user.getOutcome().toString(), user.getCreatedAt().toString()))
                        .orElse("Bunday foydalanuvchi mavjud emas");
            }

            case RUSSIAN -> {
                return userRepository.findById(chatId)
                        .map(user -> String.format("""
                                        👤 Имя: %s
                                        🏷️ Фамилия: %s
                                        📞 Номер телефона: %s
                                        💰 Доход: %s
                                        💸 Расходы: %s
                                        📅 Дата регистрации: %s
                                        """, user.getFirstname(), user.getLastname(), user.getPhoneNumber(),
                                user.getIncome().toString(), user.getOutcome().toString(), user.getCreatedAt().toString()))
                        .orElse("Такой пользователь не существует");
            }
            case ENGLISH -> {
                return userRepository.findById(chatId)
                        .map(user -> String.format("""
                                        👤 First Name: %s
                                        🏷️ Last Name: %s
                                        📞 Phone Number: %s
                                        💰 Income Amount: %s
                                        💸 Outcome Amount: %s
                                        📅 Joined Date: %s
                                        """, user.getFirstname(), user.getLastname(), user.getPhoneNumber(),
                                user.getIncome().toString(), user.getOutcome().toString(), user.getCreatedAt().toString()))
                        .orElse("Such a user does not exist");
            }
        }
        return "";
    }

}
