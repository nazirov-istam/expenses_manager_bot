package com.example.expenses.service;

import com.example.expenses.model.User;
import com.example.expenses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(Message message) {
        try {
            Optional<User> optionalUser = userRepository.findById(message.getChatId());
            if (optionalUser.isPresent()) {
                log.info("Foydalanuvchi avval ro'yxatdan o'tgan.");
            }
            User user = new User();
            user.setChatId(message.getChatId());
            user.setSuccess("true");
            user.setCreatedAt(LocalDateTime.now());
            log.info("Foydalanuvchi muvaffaqiyatli ro'yxatdan o'tdi.");
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Foydalanuvchini ro'yxatdan o'tkazishda xatolik ro'y berdi.");
            throw new RuntimeException();
        }
    }

    public User getCurrentUser(Long chatId) {
        return userRepository.findById(chatId).orElse(null);
    }

    public void saveUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error saving user with id {}", user.getChatId(), e);
        }
    }
}
