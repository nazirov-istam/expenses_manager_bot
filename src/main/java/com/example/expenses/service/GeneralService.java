package com.example.expenses.service;

import com.example.expenses.application.Messages;
import com.example.expenses.enums.Language;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class GeneralService {
    public static String mainMenu(Language language) {
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

    public static ReplyKeyboardMarkup ThreeButtons(String btn1, String btn2, String btn3) {
        KeyboardButton button1 = new KeyboardButton();
        button1.setText(btn1);

        KeyboardButton button2 = new KeyboardButton();
        button2.setText(btn2);

        KeyboardButton button3 = new KeyboardButton();
        button3.setText(btn3);

        KeyboardRow row1 = new KeyboardRow();
        row1.add(button1);
        row1.add(button2);
        row1.add(button3);

        ArrayList<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup profileWithCallbacks(Language language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton userInfo = new InlineKeyboardButton();
        InlineKeyboardButton userInfoChange = new InlineKeyboardButton();
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup MainMenuWithCallbacks(Language language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        // Creating buttons with callback data
        InlineKeyboardButton profileButton = new InlineKeyboardButton();
        InlineKeyboardButton incomeButton = new InlineKeyboardButton();
        InlineKeyboardButton expenseButton = new InlineKeyboardButton();
        InlineKeyboardButton reportButton = new InlineKeyboardButton();
        InlineKeyboardButton extraInfoButton = new InlineKeyboardButton();

        switch (language) {
            case UZBEK -> {
                profileButton.setText(Messages.menuProfilUz + " \uD83D\uDC64");
                incomeButton.setText(Messages.menuDaromadUz + " \uD83D\uDCB5");
                expenseButton.setText(Messages.menuXarajatUz + " \uD83D\uDED2");
                reportButton.setText(Messages.menuHisobotUz + " \uD83D\uDCC1");
                extraInfoButton.setText(Messages.menuQoshimchaUz + " \uD83D\uDCCC");
            }
            case RUSSIAN -> {
                profileButton.setText(Messages.menuProfilRu + " \uD83D\uDC64");
                incomeButton.setText(Messages.menuDaromadRu + " \uD83D\uDCB5");
                expenseButton.setText(Messages.menuXarajatRu + " \uD83D\uDED2");
                reportButton.setText(Messages.menuHisobotRu + " \uD83D\uDCC1");
                extraInfoButton.setText(Messages.menuQoshimchaRu + " \uD83D\uDCCC");
            }
            case ENGLISH -> {
                profileButton.setText(Messages.menuProfilEn + " \uD83D\uDC64");
                incomeButton.setText(Messages.menuDaromadEn + " \uD83D\uDCB5");
                expenseButton.setText(Messages.menuXarajatEn + " \uD83D\uDED2");
                reportButton.setText(Messages.menuHisobotEn + " \uD83D\uDCC1");
                extraInfoButton.setText(Messages.menuQoshimchaEn + " \uD83D\uDCCC");
            }
        }

        // Setting callback data for handling button clicks
        profileButton.setCallbackData("PROFILE");
        incomeButton.setCallbackData("INCOME");
        expenseButton.setCallbackData("EXPENSE");
        reportButton.setCallbackData("REPORT");
        extraInfoButton.setCallbackData("EXTRA_INFO");

        // Arranging buttons into rows
        List<InlineKeyboardButton> row1 = List.of(profileButton, incomeButton);
        List<InlineKeyboardButton> row2 = List.of(expenseButton, reportButton);
        List<InlineKeyboardButton> row3 = List.of(extraInfoButton);

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }
}
