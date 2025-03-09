package com.example.expenses.service;

import com.example.expenses.application.Messages;
import com.example.expenses.enums.Language;
import com.example.expenses.enums.Steps;
import com.example.expenses.model.Expense;
import com.example.expenses.model.User;
import com.example.expenses.repository.ExpenseRepository;
import com.example.expenses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.example.expenses.enums.Language.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

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

    public String mainMenuWelcome(Language language) {
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

    public String editFirstNameSuccess(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.getSuccessFirstNameEditUz;
            }
            case RUSSIAN -> {
                return Messages.getSuccessFirstNameEditRu;
            }
            case ENGLISH -> {
                return Messages.getSuccessFirstNameEditEn;
            }
        }
        return "";
    }

    public String editLastNameSuccess(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.getSuccessLastNameEditUz;
            }
            case RUSSIAN -> {
                return Messages.getSuccessLastNameEditRu;
            }
            case ENGLISH -> {
                return Messages.getSuccessLastNameEditEn;
            }
        }
        return "";
    }

    public String editPhoneNumberSuccess(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.getSuccessPhoneNumberEditUz;
            }
            case RUSSIAN -> {
                return Messages.getSuccessPhoneNumberEditRu;
            }
            case ENGLISH -> {
                return Messages.getSuccessPhoneNumberEditEn;
            }
        }
        return "";
    }

    public String askNewFirstName(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.askNewFirstNameUz;
            }
            case RUSSIAN -> {
                return Messages.askNewFirstNameRu;
            }
            case ENGLISH -> {
                return Messages.askNewFirstNameEn;
            }
        }
        return "";
    }

    public String askNewLastName(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.askNewLastNameUz;
            }
            case RUSSIAN -> {
                return Messages.askNewLastNameRu;
            }
            case ENGLISH -> {
                return Messages.askNewLastNameEn;
            }
        }
        return "";
    }

    public String askNewPhoneNumber(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.askNewPhoneNumberUz;
            }
            case RUSSIAN -> {
                return Messages.askNewPhoneNumberRu;
            }
            case ENGLISH -> {
                return Messages.askNewPhoneNumberEn;
            }
        }
        return "";
    }

    public String askExpenseSource(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.askExpenseSourceUz;
            }
            case RUSSIAN -> {
                return Messages.askExpenseSourceRu;
            }
            case ENGLISH -> {
                return Messages.askExpenseSourceEn;
            }
        }
        return "";
    }

    public String askExpenseAmount(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.askExpenseAmountUz;
            }
            case RUSSIAN -> {
                return Messages.askExpenseAmountRu;
            }
            case ENGLISH -> {
                return Messages.askExpenseAmountEn;
            }
        }
        return "";
    }

    public String askExpenseDescription(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.askExpenseDescriptionUz;
            }
            case RUSSIAN -> {
                return Messages.askExpenseDescriptionRu;
            }
            case ENGLISH -> {
                return Messages.askExpenseDescriptionEn;
            }
        }
        return "";
    }

    public String confirmExpense(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.successSaveExpenseUz;
            }
            case RUSSIAN -> {
                return Messages.successSaveExpenseRu;
            }
            case ENGLISH -> {
                return Messages.successSaveExpenseEn;
            }
        }
        return "";
    }

    public String declineExpense(Language language) {
        switch (language) {
            case UZBEK -> {
                return Messages.failedSaveExpenseUz;
            }
            case RUSSIAN -> {
                return Messages.failedSaveExpenseRu;
            }
            case ENGLISH -> {
                return Messages.failedSaveExpenseEn;
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

    public ReplyKeyboardMarkup threeButtons(String btn1, String btn2, String btn3) {
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

    public ReplyKeyboardMarkup twoButtons(Language language) {
        KeyboardButton confirmButton = new KeyboardButton();
        KeyboardButton declineButton = new KeyboardButton();

        switch (language) {
            case UZBEK -> {
                confirmButton.setText(Messages.confirmMessageUz);
                declineButton.setText(Messages.declineMessageUz);
            }
            case RUSSIAN -> {
                confirmButton.setText(Messages.confirmMessageRu);
                declineButton.setText(Messages.declineMessageRu);
            }
            case ENGLISH -> {
                confirmButton.setText(Messages.confirmMessageEn);
                declineButton.setText(Messages.declineMessageEn);
            }
        }


        KeyboardRow row1 = new KeyboardRow();
        row1.add(confirmButton);
        row1.add(declineButton);
        ArrayList<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup mainMenu(Language language) {
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

    public ReplyKeyboardMarkup threeButtonProfile(Language language) {
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

    public ReplyKeyboard fourButtonProfileEdit(Language language) {
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

    public Steps getStep(Long chatId) {
        return userRepository.getStepByChatId(chatId).getStep();
    }

    public void updateStep(Long chatId, Steps step) {
        userRepository.findById(chatId).ifPresentOrElse(
                user -> {
                    user.setStep(step);
                    userRepository.save(user);
                },
                () -> log.warn("Bunday foydalanuvchi mavjud emas")
        );
    }

    public void updateFirstName(Long chatId, String firstName) {
        userRepository.findById(chatId).ifPresentOrElse(
                user -> {
                    user.setFirstname(firstName);
                    userRepository.save(user);
                }, () -> log.warn("Bunday foydalanuvchi mavjud emas")
        );
    }

    public void updateLastName(Long chatId, String lastName) {
        userRepository.findById(chatId).ifPresentOrElse(
                user -> {
                    user.setLastname(lastName);
                    userRepository.save(user);
                }, () -> log.warn("Bunday foydalanuvchi mavjud emas")
        );
    }

    public void updatePhoneNumber(Long chatId, String phoneNumber) {
        userRepository.findById(chatId).ifPresentOrElse(
                user -> {
                    user.setPhoneNumber(phoneNumber);
                    userRepository.save(user);
                }, () -> log.warn("Bunday foydalanuvchi mavjud emas")
        );
    }

    public void registerExpense(Long chatId) {
        try {
            Expense expense = new Expense();
            expense.setUser(userService.getCurrentUser(chatId));
            expense.setExpenseSource("Noma'lum");
            expense.setExpenseAmount(0.0);
            expense.setDescription("Izoh yo'q");
            expense.setCreatedAt(LocalDateTime.now());

            expenseRepository.save(expense);
            log.info("Xarajat muvaffaqiyatli ro'yxatdan o'tdi: {}", expense);
        } catch (Exception e) {
            log.error("Xarajatni ro'yxatdan o'tkazishda xatolik ro'y berdi: {}", e.getMessage());
            throw new RuntimeException("Xarajat ro'yxatdan o'tkazilmadi.", e);
        }
    }

    public void enterExpenseSource(Long chatId, String expenseSource) {
        User user = userService.getCurrentUser(chatId);
        if (user != null) {
            Expense expense = expenseRepository.findTopByUserOrderByCreatedAtDesc(user);
            if (expense != null) {
                expense.setExpenseSource(expenseSource);
                expenseRepository.save(expense);
                log.info("Xarajat joyi yangilandi: {}", expenseSource);
            } else {
                log.warn("Foydalanuvchiga tegishli hech qanday xarajat topilmadi: chatId={}", chatId);
            }
        } else {
            log.warn("Bunday foydalanuvchi mavjud emas: chatId={}", chatId);
        }
    }


    public void enterExpenseAmount(Long chatId, String expenseAmount) {
        User user = userService.getCurrentUser(chatId);
        if (user != null) {
            Expense expense = expenseRepository.findTopByUserOrderByCreatedAtDesc(user);
            if (expense != null) {
                try {
                    expense.setExpenseAmount(Double.parseDouble(expenseAmount));
                    expenseRepository.save(expense);
                    log.info("Xarajat miqdori yangilandi: {}", expenseAmount);
                } catch (NumberFormatException e) {
                    log.warn("Noto‘g‘ri raqam formati: {}", expenseAmount);
                }
            } else {
                log.warn("Foydalanuvchiga tegishli hech qanday xarajat topilmadi: chatId={}", chatId);
            }
        } else {
            log.warn("Bunday foydalanuvchi mavjud emas: chatId={}", chatId);
        }
    }

    public void enterExpenseDescription(Long chatId, String description) {
        User user = userService.getCurrentUser(chatId);
        if (user != null) {
            Expense expense = expenseRepository.findTopByUserOrderByCreatedAtDesc(user);
            if (expense != null) {
                expense.setDescription(description);
                expenseRepository.save(expense);
                log.info("Xarajat tavsifi yangilandi: {}", description);
            } else {
                log.warn("Foydalanuvchiga tegishli hech qanday xarajat topilmadi: chatId={}", chatId);
            }
        } else {
            log.warn("Bunday foydalanuvchi mavjud emas: chatId={}", chatId);
        }
    }

    public String confirmationExpense(Long chatId) {
        User user = userService.getCurrentUser(chatId);
        if (user != null) {
            Expense expense = expenseRepository.findTopByUserOrderByCreatedAtDesc(user);
            if (expense != null) {
                return switch (user.getLanguage()) {
                    case UZBEK -> Messages.expenseInfoUz.formatted(
                            expense.getExpenseSource(),
                            expense.getExpenseAmount(),
                            expense.getDescription()
                    );
                    case ENGLISH -> Messages.expenseInfoEn.formatted(
                            expense.getExpenseSource(),
                            expense.getExpenseAmount(),
                            expense.getDescription()
                    );
                    case RUSSIAN -> Messages.expenseInfoRu.formatted(
                            expense.getExpenseSource(),
                            expense.getExpenseAmount(),
                            expense.getDescription()
                    );
                };
            } else {
                log.warn("Foydalanuvchiga tegishli hech qanday xarajat topilmadi: chatId={}", chatId);
            }
        } else {
            log.warn("Bunday foydalanuvchi mavjud emas: chatId={}", chatId);
        }
        return "Xarajat ma'lumotlari topilmadi."; // Agar hech narsa topilmasa, default xabar qaytariladi
    }

    public void declineExpenseMethod(Long chatId) {
        User user = userService.getCurrentUser(chatId);
        if (user != null) {
            Expense lastExpense = expenseRepository.findTopByUserOrderByCreatedAtDesc(user);
            if (lastExpense != null) {
                expenseRepository.delete(lastExpense);
                log.info("Oxirgi qo‘shilgan xarajat o‘chirildi: {}", lastExpense);
            } else {
                log.warn("Foydalanuvchiga tegishli hech qanday xarajat topilmadi: chatId={}", chatId);
            }
        } else {
            log.warn("Bunday foydalanuvchi mavjud emas: chatId={}", chatId);
        }
    }
}
