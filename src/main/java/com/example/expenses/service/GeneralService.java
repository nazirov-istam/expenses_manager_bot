package com.example.expenses.service;

import com.example.expenses.application.Messages;
import com.example.expenses.enums.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

import static com.example.expenses.enums.Language.*;

@Service
@RequiredArgsConstructor
public class GeneralService {

    private final UserService userService;

    public String language(String userText, long chatId) {
        switch (userText) {
            case Messages.Uz -> {
                userService.saveLanguage(UZBEK, chatId);
                return Messages.startUz;
            }
            case Messages.Ru -> {
                userService.saveLanguage(RUSSIAN, chatId);
                return Messages.startRu;
            }
            case Messages.En -> {
                userService.saveLanguage(ENGLISH, chatId);
                return Messages.startEn;
            }
        }
        return "";
    }

    public String mainMenu(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.welcomeMenuUz;
            }
            case RUSSIAN -> {
                return Messages.welcomeMenuRu;
            }
            case ENGLISH -> {
                return Messages.welcomeMenuEn;
            }
        }
        return "";
    }

    public String info(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.infoUz;
            }
            case RUSSIAN -> {
                return Messages.infoRu;
            }
            case ENGLISH -> {
                return Messages.infoEn;
            }
        }
        return "";
    }

    public String help(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.helpUz;
            }
            case RUSSIAN -> {
                return Messages.helpRu;
            }
            case ENGLISH -> {
                return Messages.helpEn;
            }
        }
        return "";
    }

    public String editProfile(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.welcomeEditProfileUz;
            }
            case RUSSIAN -> {
                return Messages.welcomeEditProfileRu;
            }
            case ENGLISH -> {
                return Messages.welcomeEditProfileEn;
            }
        }
        return "";
    }

    /*switch (userText) {
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

     */

    public String mainProfile(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.welcomeProfileUz;
            }
            case RUSSIAN -> {
                return Messages.welcomeProfileRu;
            }
            case ENGLISH -> {
                return Messages.welcomeProfileEn;
            }
        }
        return "";
    }

    public ReplyKeyboardMarkup ThreeButtons(String btn1, String btn2, String btn3) {
        KeyboardButton button1 = new KeyboardButton();
        button1.setText(btn1);

        KeyboardButton button2 = new KeyboardButton();
        button2.setText(btn2);

        KeyboardButton button3 = new KeyboardButton();
        button3.setText(btn3);

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        row1.add(button1);
        row1.add(button2);
        row2.add(button3);

        ArrayList<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup MainMenu(Language language) {
        KeyboardButton profileButton = new KeyboardButton();
        KeyboardButton incomeButton = new KeyboardButton();
        KeyboardButton expenseButton = new KeyboardButton();
        KeyboardButton reportButton = new KeyboardButton();

        switch (language) {
            case UZBEK -> {
                profileButton.setText(Messages.menuProfileUz);
                incomeButton.setText(Messages.menuIncomeUz);
                expenseButton.setText(Messages.menuExpenseUz);
                reportButton.setText(Messages.menuReportUz);
            }
            case RUSSIAN -> {
                profileButton.setText(Messages.menuProfileRu);
                incomeButton.setText(Messages.menuIncomeRu);
                expenseButton.setText(Messages.menuExpenseRu);
                reportButton.setText(Messages.menuReportRu);
            }
            case ENGLISH -> {
                profileButton.setText(Messages.menuProfileEn);
                incomeButton.setText(Messages.menuIncomeEn);
                expenseButton.setText(Messages.menuExpenseEn);
                reportButton.setText(Messages.menuReportEn);
            }
        }

        KeyboardRow row1 = new KeyboardRow();
        row1.add(profileButton);
        row1.add(incomeButton);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(expenseButton);
        row2.add(reportButton);

        ArrayList<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup TwoButtonProfile(Language language) {
        KeyboardButton profileView = new KeyboardButton();
        KeyboardButton profileEdit = new KeyboardButton();
        KeyboardButton back = new KeyboardButton();

        switch (language) {
            case UZBEK -> {
                profileView.setText(Messages.menuProfileViewUz);
                profileEdit.setText(Messages.menuProfileEditUz);
                back.setText(Messages.backUz);
            }
            case RUSSIAN -> {
                profileView.setText(Messages.menuProfileViewRu);
                profileEdit.setText(Messages.menuProfileEditRu);
                back.setText(Messages.backRu);
            }
            case ENGLISH -> {
                profileView.setText(Messages.menuProfileViewEn);
                back.setText(Messages.backEn);
                profileEdit.setText(Messages.menuProfileEditEn);
            }
        }

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        row1.add(profileView);
        row1.add(profileEdit);
        row2.add(back);

        ArrayList<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboard TwoButtonProfileEdit(Language language) {
        KeyboardButton firstNameEdit = new KeyboardButton();
        KeyboardButton lastNameEdit = new KeyboardButton();
        KeyboardButton phoneNumberEdit = new KeyboardButton();
        KeyboardButton back = new KeyboardButton();


        switch (language) {
            case UZBEK -> {
                firstNameEdit.setText(Messages.editFirstNameButtonUz);
                lastNameEdit.setText(Messages.editLastNameButtonUz);
                phoneNumberEdit.setText(Messages.editPhoneNumberButtonUz);
                back.setText(Messages.backUz);
            }
            case RUSSIAN -> {
                firstNameEdit.setText(Messages.editFirstNameButtonRu);
                lastNameEdit.setText(Messages.editLastNameButtonRu);
                phoneNumberEdit.setText(Messages.editPhoneNumberButtonRu);
                back.setText(Messages.backRu);
            }
            case ENGLISH -> {
                firstNameEdit.setText(Messages.editFirstNameButtonEn);
                lastNameEdit.setText(Messages.editLastNameButtonEn);
                phoneNumberEdit.setText(Messages.editPhoneNumberButtonEn);
                back.setText(Messages.backEn);

            }
        }

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        row1.add(firstNameEdit);
        row1.add(lastNameEdit);
        row1.add(phoneNumberEdit);
        row2.add(back);

        ArrayList<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        return replyKeyboardMarkup;
    }
}
