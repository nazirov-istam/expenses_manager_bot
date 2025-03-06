package com.example.expenses.service;

import com.example.expenses.application.Commands;
import com.example.expenses.application.Messages;
import com.example.expenses.enums.Steps;
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
    private final GeneralService generalService;

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
                    sendMessage.setText(Messages.language);
                    sendMessage.setReplyMarkup(generalService.ThreeButtons(Messages.Uz, Messages.Ru, Messages.En));
                } else {
                    sendMessage.setText(userService.getStartMessage(user));
                }
            } else if (userText.equals(Messages.Uz) || userText.equals(Messages.Ru) || userText.equals(Messages.En)) {
                user.setStep(Steps.LANGUAGE);
                sendMessage.setText(generalService.language(userText, chatId));
            } else if (userText.equals(Commands.HELP)) {
                sendMessage.setText(generalService.help(user.getLanguage()));
            } else if (userText.equals(Commands.INFO)) {
                sendMessage.setText(generalService.info(user.getLanguage()));
            } else if (userText.equals(Commands.MAIN)) {
                sendMessage.setText(generalService.mainMenu(user.getLanguage()));
            } else if (userText.equals(Messages.menuProfileUz) || userText.equals(Messages.menuProfileRu) || userText.equals(Messages.menuProfileEn)) {
                user.setStep(Steps.PROFILE);
                sendMessage.setReplyMarkup(generalService.TwoButtonProfile(user.getLanguage()));
                sendMessage.setText(generalService.mainProfile(user.getLanguage()));
            } else if (userText.equals(Messages.menuProfileViewUz) || userText.equals(Messages.menuProfileViewRu) || userText.equals(Messages.menuProfileViewEn)) {
                user.setStep(Steps.PROFILE);
                sendMessage.setText(userService.getInfo(chatId, user.getLanguage()));
            } else if (userText.equals(Messages.menuProfileEditUz) || userText.equals(Messages.menuProfileEditRu) || userText.equals(Messages.menuProfileEditEn)) {
                sendMessage.setText(generalService.editProfile(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.TwoButtonProfileEdit(user.getLanguage()));
                user.setStep(Steps.PROFILE_EDIT);
            } else if (userText.equals(Commands.LANGUAGE)) {
                sendMessage.setText(Messages.language);
                sendMessage.setReplyMarkup(generalService.ThreeButtons(Messages.Uz, Messages.Ru, Messages.En));
            } else if ((userText.equals(Messages.backUz) || userText.equals(Messages.backRu) || userText.equals(Messages.backEn)) && user.getStep() == Steps.PROFILE_EDIT) {
                user.setStep(Steps.PROFILE);
                sendMessage.setReplyMarkup(generalService.TwoButtonProfile(user.getLanguage()));
                sendMessage.setText(generalService.mainProfile(user.getLanguage()));
            } else if ((userText.equals(Messages.backUz) || userText.equals(Messages.backRu) || userText.equals(Messages.backEn)) && user.getStep() == Steps.PROFILE) {
                sendMessage.setText(generalService.mainMenu(user.getLanguage()));
            }
        }
        /*
                case "PROFILE" -> {
                    1) Profilni kurish: firstname, lastname, username, income, outcome, createdAt -> DONE
                    2) Profil malumotlarni yangilash (FirstName, LastName, PhoneNumber)
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
        }*/
        return sendMessage;
    }
}