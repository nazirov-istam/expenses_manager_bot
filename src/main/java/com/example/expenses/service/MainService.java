package com.example.expenses.service;

import com.example.expenses.application.Commands;
import com.example.expenses.application.MessageUz;
import com.example.expenses.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {
    private final UserService userService;

    public SendMessage mainBot(Update update) {
        SendMessage sendMessage = new SendMessage();

        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String userText = update.getMessage().getText();
            sendMessage.setChatId(chatId);
            User user = userService.getCurrentUser(chatId);
            if (userText.equals(Commands.START)) {
                if (user == null) {
                    userService.registerUser(update.getMessage());
                    sendMessage.setText(MessageUz.startUz);
                } else {
                    sendMessage.setText(MessageUz.startUzIsRegistered);
                }
            }
        }
        return sendMessage;
    }
}
