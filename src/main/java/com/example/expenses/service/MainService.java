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
                    1) Profilni kurish: firstname, lastname, username, income, outcome, createdAt
                    2) Profil malumotlarni yangilash
                }
                case "EXPENSE" -> {
                    1) Xarajat turini yozing;
                    2) Xrajat miqdorini yozing(O'zbek so'mida)
                    3) Izoh yozing(Ixtiyoriy)
                    Hammasini qayta tashlab , tasdiqlash/rad etish buyrug'i junatiladi,
                                }
                case "REPORT" -> {
                    1) Yillik hisobot(yillar tanlanadi)
                    2) Oylik Hisobot(Oy tanlanadi)
                    3) Excelda saqlangan malumotlarni junatsin.
                }
                case "INCOME" -> {
                     1) Daromat manbaini kiriting.
                     2) Daromat miqdorini kiriting.
                     3) Izoh(Ixtiyoriy)
                     4) Tasdiqlash.
                     }
            }
        }
         */
        return sendMessage;
    }
}