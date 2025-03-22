package com.example.expenses.service;

import com.example.expenses.application.Commands;
import com.example.expenses.application.Messages;
import com.example.expenses.enums.Steps;
import com.example.expenses.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {
    private final UserService userService;
    private final GeneralService generalService;
    private final ReportService reportService;

    public PartialBotApiMethod<Message> mainBot(Update update) {
        SendMessage sendMessage = new SendMessage();
        SendDocument sendDocument = new SendDocument();

        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String userText = update.getMessage().getText();
            sendDocument.setChatId(chatId);
            sendMessage.setChatId(chatId);
            User user = userService.getCurrentUser(chatId);
            if (userText.equals(Commands.START)) {
                if (user == null) {
                    userService.registerUser(update.getMessage());
                    sendMessage.setText(Messages.language);
                    sendMessage.setReplyMarkup(generalService.threeButtons(Messages.Uz, Messages.Ru, Messages.En));
                } else {
                    sendMessage.setText(userService.getStartMessage(user));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                }
                generalService.updateStep(chatId, Steps.START);
            } else if (userText.equals(Messages.Uz) || userText.equals(Messages.Ru) || userText.equals(Messages.En)) {
                if (user.getStep() == Steps.START) {
                    userService.setLanguage(userText, chatId);
                    generalService.updateStep(chatId, Steps.ENTER_FIRSTNAME);
                    sendMessage.setText(userService.getFirstName(userText));
                } else if (user.getStep() == Steps.HOME) {
                    generalService.updateStep(chatId, Steps.LANGUAGE);
                    sendMessage.setText(generalService.language(userText, chatId));
                }
            } else if (user.getStep() == Steps.ENTER_FIRSTNAME && userText != null) {
                if (userService.setFirstName(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ENTER_LASTNAME);
                    sendMessage.setText(userService.getLastName(user));
                } else {
                    generalService.updateStep(chatId, Steps.ENTER_FIRSTNAME);
                    sendMessage.setText(userService.getFirstNameAgain(user));
                }
            } else if (user.getStep() == Steps.ENTER_LASTNAME && userText != null) {
                if (userService.setLastName(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ENTER_PHONE_NUMBER);
                    sendMessage.setText(userService.getPhoneNumber(user));
                } else {
                    generalService.updateStep(chatId, Steps.ENTER_LASTNAME);
                    sendMessage.setText(userService.getLastNameAgain(user));
                }
            } else if (user.getStep() == Steps.ENTER_PHONE_NUMBER && userText != null) {
                if (userService.setPhoneNumber(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ENTER_BALANCE);
                    sendMessage.setText(userService.getBalance(user));
                } else {
                    generalService.updateStep(chatId, Steps.ENTER_PHONE_NUMBER);
                    sendMessage.setText(userService.getPhoneNumberAgain(user));
                }
            } else if (user.getStep() == Steps.ENTER_BALANCE && userText != null) {
                if (userService.setBalance(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.HOME);
                    sendMessage.setText(userService.saveUser(user));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                } else {
                    generalService.updateStep(chatId, Steps.ENTER_BALANCE);
                    sendMessage.setText(userService.getBalanceAgain(user));
                }
            } else if (userText.equals(Commands.LANGUAGE)) {
                generalService.updateStep(chatId, Steps.HOME);
                sendMessage.setText(Messages.language);
                sendMessage.setReplyMarkup(generalService.threeButtons(Messages.Uz, Messages.Ru, Messages.En));
            } else if (userText.equals(Commands.HELP)) {
                generalService.updateStep(chatId, Steps.HOME);
                sendMessage.setText(generalService.help(user.getLanguage()));
            } else if (userText.equals(Commands.INFO)) {
                generalService.updateStep(chatId, Steps.HOME);
                sendMessage.setText(generalService.info(user.getLanguage()));
            } else if (userText.equals(Commands.STATS)) {
                generalService.updateStep(chatId, Steps.HOME);
                sendMessage.setText(generalService.stats(chatId));
            } else if (userText.equals(Commands.OWNER_STATS)) {
                generalService.updateStep(chatId, Steps.HOME);
                sendMessage.setText(userService.getUserStatistics(chatId));
            } else if (userText.equals(Commands.MAIN)) {
                generalService.updateStep(chatId, Steps.HOME);
                sendMessage.setText(generalService.mainMenuWelcome(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
            } else if (userText.equals(Messages.menuProfileUz) || userText.equals(Messages.menuProfileRu) || userText.equals(Messages.menuProfileEn)) {
                generalService.updateStep(chatId, Steps.PROFILE);
                sendMessage.setReplyMarkup(generalService.threeButtonProfile(user.getLanguage()));
                sendMessage.setText(generalService.mainProfile(user.getLanguage()));
            } else if (userText.equals(Messages.menuProfileViewUz) || userText.equals(Messages.menuProfileViewRu) || userText.equals(Messages.menuProfileViewEn)) {
                generalService.updateStep(chatId, Steps.PROFILE);
                sendMessage.setText(userService.getInfo(chatId, user.getLanguage()));
            } else if (userText.equals(Messages.menuIncomeUz) || userText.equals(Messages.menuIncomeRu) || userText.equals(Messages.menuIncomeEn)) {
                generalService.registerIncome(chatId);
                generalService.updateStep(chatId, Steps.INCOME);
                sendMessage.setText(generalService.askIncomeSource(user.getLanguage()));
            } else if (generalService.getStep(chatId) == Steps.INCOME && userText != null) {
                if (generalService.enterIncomeSource(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ASK_INCOME_AMOUNT);
                    sendMessage.setText(generalService.askIncomeAmount(user.getLanguage()));
                } else {
                    generalService.updateStep(chatId, Steps.INCOME);
                    sendMessage.setText(generalService.askIncomeSourceAgain(user.getLanguage()));
                }
            } else if (generalService.getStep(chatId) == Steps.ASK_INCOME_AMOUNT && userText != null) {
                if (generalService.enterIncomeAmount(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ASK_INCOME_DESCRIPTION);
                    sendMessage.setText(generalService.askIncomeDescription(user.getLanguage()));
                } else {
                    generalService.updateStep(chatId, Steps.ASK_INCOME_AMOUNT);
                    sendMessage.setText(generalService.askIncomeAmountAgain(user.getLanguage()));
                }
            } else if (generalService.getStep(chatId) == Steps.ASK_INCOME_DESCRIPTION && userText != null) {
                if (generalService.enterIncomeDescription(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ASK_CONFIRMATION_INCOME);
                    sendMessage.setText(generalService.confirmationIncome(chatId));
                    sendMessage.setReplyMarkup(generalService.twoButtons(user.getLanguage()));
                } else {
                    generalService.updateStep(chatId, Steps.ASK_INCOME_DESCRIPTION);
                    sendMessage.setText(generalService.askIncomeDescriptionAgain(user.getLanguage()));
                }
            } else if (generalService.getStep(chatId) == Steps.ASK_CONFIRMATION_INCOME && userText != null) {
                if (userText.equals(Messages.confirmMessageUz) || userText.equals(Messages.confirmMessageRu) || userText.equals(Messages.confirmMessageEn)) {
                    generalService.updateStep(chatId, Steps.HOME);
                    sendMessage.setText(generalService.confirmIncome(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                } else {
                    generalService.declineIncomeMethod(chatId);
                    generalService.updateStep(chatId, Steps.HOME);
                    sendMessage.setText(generalService.declineIncome(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                }
            } else if (userText.equals(Messages.menuExpenseUz) || userText.equals(Messages.menuExpenseEn) || userText.equals(Messages.menuExpenseRu)) {
                generalService.registerExpense(chatId);
                generalService.updateStep(chatId, Steps.EXPENSE);
                sendMessage.setText(generalService.askExpenseSource(user.getLanguage()));
            } else if (generalService.getStep(chatId) == Steps.EXPENSE && userText != null) {
                if (generalService.enterExpenseSource(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ASK_EXPENSE_AMOUNT);
                    sendMessage.setText(generalService.askExpenseAmount(user.getLanguage()));
                } else {
                    generalService.updateStep(chatId, Steps.EXPENSE);
                    sendMessage.setText(generalService.askExpenseSourceAgain(user.getLanguage()));
                }
            } else if (generalService.getStep(chatId) == Steps.ASK_EXPENSE_AMOUNT && userText != null) {
                if (generalService.enterExpenseAmount(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ASK_EXPENSE_DESCRIPTION);
                    sendMessage.setText(generalService.askExpenseDescription(user.getLanguage()));
                } else {
                    generalService.updateStep(chatId, Steps.ASK_EXPENSE_AMOUNT);
                    sendMessage.setText(generalService.askExpenseAmountAgain(user.getLanguage()));
                }
            } else if (generalService.getStep(chatId) == Steps.ASK_EXPENSE_DESCRIPTION && userText != null) {
                if (generalService.enterExpenseDescription(chatId, userText)) {
                    generalService.updateStep(chatId, Steps.ASK_CONFIRMATION_EXPENSE);
                    sendMessage.setText(generalService.confirmationExpense(chatId));
                    sendMessage.setReplyMarkup(generalService.twoButtons(user.getLanguage()));
                } else {
                    generalService.updateStep(chatId, Steps.ASK_EXPENSE_DESCRIPTION);
                    sendMessage.setText(generalService.askExpenseDescriptionAgain(user.getLanguage()));
                }
            } else if (generalService.getStep(chatId) == Steps.ASK_CONFIRMATION_EXPENSE && userText != null) {
                if (userText.equals(Messages.confirmMessageUz) || userText.equals(Messages.confirmMessageRu) || userText.equals(Messages.confirmMessageEn)) {
                    generalService.updateStep(chatId, Steps.HOME);
                    sendMessage.setText(generalService.confirmExpense(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                } else {
                    generalService.declineExpenseMethod(chatId);
                    generalService.updateStep(chatId, Steps.HOME);
                    sendMessage.setText(generalService.declineExpense(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                }
            } else if (userText.equals(Messages.menuProfileEditUz) || userText.equals(Messages.menuProfileEditRu) || userText.equals(Messages.menuProfileEditEn)) {
                generalService.updateStep(chatId, Steps.PROFILE_EDIT);
                sendMessage.setText(generalService.editProfile(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.fourButtonProfileEdit(user.getLanguage()));
            } else if (userText.equals(Messages.editFirstNameButtonUz) || userText.equals(Messages.editFirstNameButtonRu) || userText.equals(Messages.editFirstNameButtonEn)) {
                generalService.updateStep(chatId, Steps.EDIT_FIRSTNAME);
                sendMessage.setText(generalService.askNewFirstName(user.getLanguage()));
            } else if (generalService.getStep(chatId) == Steps.EDIT_FIRSTNAME && userText != null) {
                generalService.updateFirstName(chatId, userText);
                sendMessage.setText(generalService.editFirstNameSuccess(user.getLanguage()));
                generalService.updateStep(chatId, Steps.PROFILE_EDIT);
                sendMessage.setReplyMarkup(generalService.fourButtonProfileEdit(user.getLanguage()));
            } else if (userText.equals(Messages.editLastNameButtonUz) || userText.equals(Messages.editLastNameButtonRu) || userText.equals(Messages.editLastNameButtonEn)) {
                generalService.updateStep(chatId, Steps.EDIT_LASTNAME);
                sendMessage.setText(generalService.askNewLastName(user.getLanguage()));
            } else if (generalService.getStep(chatId) == Steps.EDIT_LASTNAME && userText != null) {
                generalService.updateLastName(chatId, userText);
                sendMessage.setText(generalService.editLastNameSuccess(user.getLanguage()));
                generalService.updateStep(chatId, Steps.PROFILE_EDIT);
                sendMessage.setReplyMarkup(generalService.fourButtonProfileEdit(user.getLanguage()));
            } else if (userText.equals(Messages.editPhoneNumberButtonUz) || userText.equals(Messages.editPhoneNumberButtonRu) || userText.equals(Messages.editPhoneNumberButtonEn)) {
                generalService.updateStep(chatId, Steps.EDIT_PHONE_NUMBER);
                sendMessage.setText(generalService.askNewPhoneNumber(user.getLanguage()));
            } else if (generalService.getStep(chatId) == Steps.EDIT_PHONE_NUMBER && userText != null) {
                generalService.updatePhoneNumber(chatId, userText);
                sendMessage.setText(generalService.editPhoneNumberSuccess(user.getLanguage()));
                generalService.updateStep(chatId, Steps.PROFILE_EDIT);
                sendMessage.setReplyMarkup(generalService.fourButtonProfileEdit(user.getLanguage()));
            } else if (userText.equals(Messages.menuReportUz) || userText.equals(Messages.menuReportRu) || userText.equals(Messages.menuReportEn)) {
                generalService.updateStep(chatId, Steps.REPORT);
                sendMessage.setReplyMarkup(generalService.threeButtonReport(user.getLanguage()));
                sendMessage.setText(generalService.mainReport(user.getLanguage()));
            }
            else if (userText.equals(Messages.askMonthlyReportUz) || userText.equals(Messages.askMonthlyReportRu) || userText.equals(Messages.askMonthlyReportEn)) {
                generalService.updateStep(chatId, Steps.MONTHLY_REPORT);
                sendMessage.setText(generalService.mainReportIncomeOrExpense(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.threeButtonMonthlyAndYearlyReport(user.getLanguage()));
            }
            else if (user.getStep() == Steps.MONTHLY_REPORT && (userText.equals(Messages.askIncomeUz) || userText.equals(Messages.askIncomeRu) || userText.equals(Messages.askIncomeEn))) {
                generalService.updateStep(chatId, Steps.MONTHLY_INCOME_REPORT);
                sendMessage.setText(generalService.askYearOfMonthlyReport(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.fiveButtons("2025", "2026", "2027", "2028", "2029"));
            } else if (user.getStep() == Steps.MONTHLY_INCOME_REPORT) {
                userService.saveYearOfMonthlyReport(chatId, userText);
                generalService.updateStep(chatId, Steps.MONTHLY_INCOME_REPORT_MONTH);
                sendMessage.setText(generalService.askMonthOfMonthlyReport(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.twelveButtonsForMonths(user.getLanguage()));
            } else if (user.getStep() == Steps.MONTHLY_INCOME_REPORT_MONTH) {
                generalService.updateStep(chatId, Steps.HOME);
                String var = userService.saveMonthOfMonthlyReport(chatId, userText);
                if (!userService.checkIfIncomeExistsByDate(var, chatId)) {
                    userService.deleteYearAndDateOfMonthlyReport(chatId);
                    sendMessage.setText(generalService.noReportResponseByDate(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    return sendMessage;
                } else {
                    sendDocument.setDocument(reportService.sendMonthlyIncomeReportDirectly(chatId, var));
                    sendDocument.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    userService.deleteYearAndDateOfMonthlyReport(chatId);
                    return sendDocument;
                }
            }
            else if (user.getStep() == Steps.MONTHLY_REPORT && (userText.equals(Messages.askExpenseUz) || userText.equals(Messages.askExpenseRu) || userText.equals(Messages.askExpenseEn))) {
                generalService.updateStep(chatId, Steps.MONTHLY_EXPENSE_REPORT);
                sendMessage.setText(generalService.askYearOfMonthlyReport(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.fiveButtons("2025", "2026", "2027", "2028", "2029"));
            } else if (user.getStep() == Steps.MONTHLY_EXPENSE_REPORT) {
                userService.saveYearOfMonthlyReport(chatId, userText);
                generalService.updateStep(chatId, Steps.MONTHLY_EXPENSE_REPORT_MONTH);
                sendMessage.setText(generalService.askMonthOfMonthlyReport(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.twelveButtonsForMonths(user.getLanguage()));
            } else if (user.getStep() == Steps.MONTHLY_EXPENSE_REPORT_MONTH) {
                generalService.updateStep(chatId, Steps.HOME);
                String var = userService.saveMonthOfMonthlyReport(chatId, userText);
                if (!userService.checkIfExpenseExistsByDate(var, chatId)) {
                    userService.deleteYearAndDateOfMonthlyReport(chatId);
                    sendMessage.setText(generalService.noReportResponseByDate(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    return sendMessage;
                } else {
                    sendDocument.setDocument(reportService.sendMonthlyExpenseReportDirectly(chatId, var));
                    sendDocument.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    userService.deleteYearAndDateOfMonthlyReport(chatId);
                    return sendDocument;
                }
            }
            else if (userText.equals(Messages.askYearlyReportUz) || userText.equals(Messages.askYearlyReportRu) || userText.equals(Messages.askYearlyReportEn)) {
                generalService.updateStep(chatId, Steps.YEARLY_REPORT);
                sendMessage.setText(generalService.mainReportIncomeOrExpense(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.threeButtonMonthlyAndYearlyReport(user.getLanguage()));
            } else if (user.getStep() == Steps.YEARLY_REPORT && (userText.equals(Messages.askIncomeUz) || userText.equals(Messages.askIncomeRu) || userText.equals(Messages.askIncomeEn))) {
                generalService.updateStep(chatId, Steps.YEARLY_INCOME_REPORT);
                sendMessage.setText(generalService.askFormatOfYearlyReport(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.fiveButtons("2025", "2026", "2027", "2028", "2029"));
            } else if (user.getStep() == Steps.YEARLY_INCOME_REPORT && userText != null) {
                generalService.updateStep(chatId, Steps.HOME);
                if (!userService.checkIfIncomeExistsByYear(userText, chatId)) {
                    sendMessage.setText(generalService.noReportResponseByYear(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    return sendMessage;
                } else {
                    sendDocument.setDocument(reportService.sendYearlyIncomeReportDirectly(chatId, userText));
                    sendDocument.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    return sendDocument;
                }
            } else if (user.getStep() == Steps.YEARLY_REPORT && (userText.equals(Messages.askExpenseUz) || userText.equals(Messages.askExpenseRu) || userText.equals(Messages.askExpenseEn))) {
                generalService.updateStep(chatId, Steps.YEARLY_EXPENSE_REPORT);
                sendMessage.setText(generalService.askFormatOfYearlyReport(user.getLanguage()));
                sendMessage.setReplyMarkup(generalService.fiveButtons("2025", "2026", "2027", "2028", "2029"));
            } else if (user.getStep() == Steps.YEARLY_EXPENSE_REPORT && userText != null) {
                generalService.updateStep(chatId, Steps.HOME);
                if (!userService.checkIfExpenseExistsByYear(userText, chatId)) {
                    sendMessage.setText(generalService.noReportResponseByYear(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    return sendMessage;
                } else {
                    sendDocument.setDocument(reportService.sendYearlyExpenseReportDirectly(chatId, userText));
                    sendDocument.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    return sendDocument;
                }
            } else if (userText.equals(Messages.backUz) || userText.equals(Messages.backRu) || userText.equals(Messages.backEn)) {
                if (generalService.getStep(chatId) == Steps.PROFILE_EDIT) {
                    generalService.updateStep(chatId, Steps.PROFILE);
                    sendMessage.setReplyMarkup(generalService.threeButtonProfile(user.getLanguage()));
                    sendMessage.setText(generalService.mainProfile(user.getLanguage()));
                } else if (generalService.getStep(chatId) == Steps.PROFILE) {
                    generalService.updateStep(chatId, Steps.HOME);
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    sendMessage.setText(generalService.mainMenuWelcome(user.getLanguage()));
                } else if (generalService.getStep(chatId) == Steps.REPORT) {
                    generalService.updateStep(chatId, Steps.HOME);
                    sendMessage.setReplyMarkup(generalService.mainMenu(user.getLanguage()));
                    sendMessage.setText(generalService.mainMenuWelcome(user.getLanguage()));
                } else if (generalService.getStep(chatId) == Steps.MONTHLY_REPORT) {
                    generalService.updateStep(chatId, Steps.REPORT);
                    sendMessage.setText(generalService.mainReport(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.threeButtonReport(user.getLanguage()));
                } else if (generalService.getStep(chatId) == Steps.MONTHLY_INCOME_REPORT) {
                    generalService.updateStep(chatId, Steps.MONTHLY_REPORT);
                    sendMessage.setText(generalService.mainReportIncomeOrExpense(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.threeButtonMonthlyAndYearlyReport(user.getLanguage()));
                } else if (generalService.getStep(chatId) == Steps.MONTHLY_EXPENSE_REPORT) {
                    generalService.updateStep(chatId, Steps.MONTHLY_REPORT);
                    sendMessage.setText(generalService.mainReportIncomeOrExpense(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.threeButtonMonthlyAndYearlyReport(user.getLanguage()));
                } else if (generalService.getStep(chatId) == Steps.YEARLY_REPORT) {
                    generalService.updateStep(chatId, Steps.REPORT);
                    sendMessage.setText(generalService.mainReport(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.threeButtonReport(user.getLanguage()));
                } else if (generalService.getStep(chatId) == Steps.YEARLY_INCOME_REPORT) {
                    generalService.updateStep(chatId, Steps.YEARLY_REPORT);
                    sendMessage.setText(generalService.mainReportIncomeOrExpense(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.threeButtonMonthlyAndYearlyReport(user.getLanguage()));
                } else if (generalService.getStep(chatId) == Steps.YEARLY_EXPENSE_REPORT) {
                    generalService.updateStep(chatId, Steps.YEARLY_REPORT);
                    sendMessage.setText(generalService.mainReportIncomeOrExpense(user.getLanguage()));
                    sendMessage.setReplyMarkup(generalService.threeButtonMonthlyAndYearlyReport(user.getLanguage()));
                }
            }
        }
        return sendMessage;
    }
}