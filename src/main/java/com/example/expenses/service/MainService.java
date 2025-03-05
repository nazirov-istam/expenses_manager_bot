package com.example.expenses.service;

import com.example.expenses.application.Commands;
import com.example.expenses.application.Messages;
import com.example.expenses.enums.Language;
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
            switch (userText) {
                case Commands.START -> {
                    if (user == null) {
                        userService.registerUser(update.getMessage());
                        sendMessage.setText(Messages.language);
                        sendMessage.setReplyMarkup(GeneralService.ThreeButtons(Messages.Uz, Messages.Ru, Messages.En));
                    } else {
                        switch (user.getLanguage()) {
                            case UZBEK -> sendMessage.setText(Messages.startUzIsRegistered);
                            case RUSSIAN -> sendMessage.setText(Messages.startRuIsRegistered);
                            case ENGLISH -> sendMessage.setText(Messages.startEnIsRegistered);
                            default -> sendMessage.setText("Iltimos tilni tanlang !");
                        }
                    }
                }
                case Messages.Uz, Messages.Ru, Messages.En -> {
                    switch (userText) {
                        case Messages.Uz:
                            userService.saveLanguage(Language.UZBEK, chatId);
                            sendMessage.setText(Messages.startUz);
                            break;
                        case Messages.Ru:
                            userService.saveLanguage(Language.RUSSIAN, chatId);
                            sendMessage.setText(Messages.startRu);
                            break;
                        case Messages.En:
                            userService.saveLanguage(Language.ENGLISH, chatId);
                            sendMessage.setText(Messages.startEn);
                            break;
                        default:
                    }
                }
                case Commands.HELP -> {
                    switch (user.getLanguage()) {
                        case UZBEK -> sendMessage.setText(Messages.helpUz);
                        case RUSSIAN -> sendMessage.setText(Messages.helpRu);
                        case ENGLISH -> sendMessage.setText(Messages.helpEn);
                    }
                }
                case Commands.INFO -> {
                    switch (user.getLanguage()) {
                        case UZBEK -> sendMessage.setText(Messages.infoUz);
                        case RUSSIAN -> sendMessage.setText(Messages.infoRu);
                        case ENGLISH -> sendMessage.setText(Messages.infoEn);
                    }
                }
                case Commands.MAIN -> {
                    sendMessage.setText(GeneralService.mainMenu(user.getLanguage()));
                    sendMessage.setReplyMarkup(GeneralService.MainMenuWithCallbacks(user.getLanguage()));
                }
                case Commands.LANGUAGE -> { // ishlamayapti
                    sendMessage.setText(Messages.language);
                    sendMessage.setReplyMarkup(GeneralService.ThreeButtons(Messages.Uz, Messages.Ru, Messages.En));
                }
            }
        }
        /*else if (update.hasCallbackQuery()) {
            switch (update.getCallbackQuery().getData()) {
                case "PROFILE" -> {
                    sendMessage.setText("");
                }
                case "EXPENSE" -> {

                }
                case "REPORT" -> {

                }
                case "EXTRA_INFO" -> {

                }
            }
        }
         */
        return sendMessage;
    }
}


/*profileButton.setCallbackData("PROFILE");
        incomeButton.setCallbackData("INCOME");
        expenseButton.setCallbackData("EXPENSE");
        reportButton.setCallbackData("REPORT");
        extraInfoButton.setCallbackData("EXTRA_INFO");

 */