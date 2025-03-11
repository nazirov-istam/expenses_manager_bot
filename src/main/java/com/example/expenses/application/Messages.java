package com.example.expenses.application;

import org.springframework.stereotype.Component;

@Component
public final class Messages {
    public static final String language = """
            \uD83C\uDDFA\uD83C\uDDFF Tilni tanlang !
            \uD83C\uDDF7\uD83C\uDDFA Выберите язык !
            \uD83C\uDDFA\uD83C\uDDF8 Select a language !
            """;
    public static final String Uz = "\uD83C\uDDFA\uD83C\uDDFF Uz";
    public static final String Ru = "\uD83C\uDDF7\uD83C\uDDFA Ru";
    public static final String En = "\uD83C\uDDFA\uD83C\uDDF8 En";

    public static final String welcomeMenuUz = "Siz 'Asosiy buyruqlar' bo'limidasiz.";
    public static final String welcomeMenuRu = "Вы находитесь в разделе 'Основные команды'.";
    public static final String welcomeMenuEn = "You are in the 'Main commands' section.";

    public static final String welcomeProfileUz = "Siz 'Profil' bo'limidasiz.";
    public static final String welcomeProfileRu = "Вы находитесь в разделе 'Профиль'.";
    public static final String welcomeProfileEn = "You are in the 'Profile' section.";

    public static final String welcomeEditProfileUz = "Siz 'Foydalanuvchi ma'lumotlarini o'zgartirish' bo'limidasiz.";
    public static final String welcomeEditProfileRu = "Вы находитесь в разделе 'Изменение данных пользователя'.";
    public static final String welcomeEditProfileEn = "You are in the 'Edit User Information' section.";

    public static final String welcomeReportsUz = "Siz 'Hisobotlar' bo‘limidasiz.";
    public static final String welcomeReportsRu = "Вы находитесь в разделе 'Отчёты'.";
    public static final String welcomeReportsEn = "You are in the 'Reports' section.";

    public static final String askMonthlyOrYearlyReportUz = "Iltimos, oylik yoki yillik hisoblarni ko'rishni xohlayotganingizni tanlang.";
    public static final String askMonthlyOrYearlyReportEn = "Please select whether you want to view the monthly or yearly reports.";
    public static final String askMonthlyOrYearlyReportRu = "Пожалуйста, выберите, хотите ли вы просмотреть ежемесячные или год";

    public static final String askIncomeOrExpenseUz = "Iltimos, daromat yoki xarajat hisobotlarini ko'rishni xohlayotganingizni tanlang.";
    public static final String askIncomeOrExpenseEn = "Please select whether you want to view the income or expense reports.";
    public static final String askIncomeOrExpenseRu = "Пожалуйста, выберите, хотите ли вы просмотреть отчёты о доходах или расходах.";

    public static final String askReportFormatUz = "Iltimos, hisobot formatini tanlang: Excel yoki Matn (Text).";
    public static final String askReportFormatEn = "Please select the report format: Excel or Text.";
    public static final String askReportFormatRu = "Пожалуйста, выберите формат отчёта: Excel или Текст.";


    public static final String getSuccessFirstNameEditUz = "Foydalanuvchi ismi yangilandi.";
    public static final String getSuccessFirstNameEditRu = "Имя пользователя обновлено.";
    public static final String getSuccessFirstNameEditEn = "User's first name has been updated.";

    public static final String getSuccessLastNameEditUz = "Foydalanuvchi familiyasi yangilandi.";
    public static final String getSuccessLastNameEditRu = "Фамилия пользователя обновлена.";
    public static final String getSuccessLastNameEditEn = "User's last name has been updated.";

    public static final String getSuccessPhoneNumberEditUz = "Foydalanuvchi telefon raqami yangilandi.";
    public static final String getSuccessPhoneNumberEditRu = "Номер телефона пользователя обновлен.";
    public static final String getSuccessPhoneNumberEditEn = "User's phone number has been updated.";


    public static final String startUz = """
            👋 Salom! My Expenses Manager Bot-ga xush kelibsiz!💰
            
            Bu bot yordamida xarajatlaringizni kuzatishingiz, byudjetingizni nazorat qilishingiz va moliyaviy maqsadlaringizga erishishingiz mumkin.
            
            📌 Asosiy buyruqlar:
            ✅ /main – Asosiy buyruqlar
            ✅ /info – Bot haqida ma'lumot olish
            ✅ /help – Yordam
            ✅ /language - Tilni o'zgartirish
            """;

    public static final String startRu = """
            👋 Привет! Добро пожаловать в My Expenses Manager Bot!💰
            
            С помощью этого бота вы можете отслеживать свои расходы, контролировать бюджет и достигать финансовых целей.
            
            📌 Основные команды:
            ✅ /main – Основные команды
            ✅ /info – Получить информацию о боте
            ✅ /help – Помощь
            ✅ /language - Изменить язык
            """;

    public static final String startEn = """
            👋 Hello! Welcome to My Expenses Manager Bot!💰
            
            With this bot, you can track your expenses, manage your budget, and achieve your financial goals.
            
            📌 Main commands:
            ✅ /main – Main commands
            ✅ /info – Get information about the bot
            ✅ /help – Help
            ✅ /language - Change language
            """;


    public static final String startUzIsRegistered = """
            Siz allaqachon ro‘yxatdan o‘tgansiz. 📌
            """;

    public static final String startRuIsRegistered = """
            Вы уже зарегистрированы. 📌
            """;

    public static final String startEnIsRegistered = """
            You are already registered. 📌
            """;


    public static final String helpUz = """
            🆘 Yordam kerakmi?
            
            Agar botdan foydalanishda savollaringiz bo‘lsa yoki texnik muammolar yuzaga kelsa, biz bilan bog‘lanishingiz mumkin.
            
            📩 Biz bilan bog‘lanish:
            👉 Telegram: @nazirov_istam
            
            📢 Eslatma:
            Savollarga javob berish vaqti: 09:00 - 18:00 (Toshkent vaqti).
            Xarajatlarni boshqarish bo‘yicha savollaringizga tezkor va to‘liq javob berishga harakat qilamiz!
            
            🌟 Omad tilaymiz va sizning murojaatingizni kutamiz! 💰
            """;

    public static final String helpRu = """
            🆘 Нужна помощь?
            
            Если у вас есть вопросы по использованию бота или возникли технические проблемы, вы можете связаться с нами.
            
            📩 Связаться с нами:
            👉 Telegram: @nazirov_istam
            
            📢 Напоминание:
            Время ответа на вопросы: 09:00 - 18:00 (по ташкентскому времени).
            Мы постараемся быстро и полно ответить на ваши вопросы по управлению расходами!
            
            🌟 Желаем удачи и ждем вашего обращения! 💰
            """;

    public static final String helpEn = """
            🆘 Need help?
            
            If you have any questions about using the bot or encounter technical issues, you can contact us.
            
            📩 Contact us:
            👉 Telegram: @nazirov_istam
            
            📢 Note:
            Response time for questions: 09:00 - 18:00 (Tashkent time).
            We will do our best to provide quick and complete answers to your expense management questions!
            
            🌟 Good luck, and we look forward to assisting you! 💰
            """;


    public static final String infoUz = """
            ℹ️ My Expenses Manager Bot
            
            @my_expenses_manager_bot – bu shaxsiy moliyangizni boshqarish uchun qulay bot.
            Siz xarajatlaringizni qo‘shish, kuzatish va tahlil qilish orqali pulingizni samarali boshqarishingiz mumkin.
            
            📌 Asosiy xususiyatlar:
            ✔️ Xarajatlarni qo‘shish va kategoriyalar bo‘yicha ajratish
            ✔️ Oylik budjetni belgilash va nazorat qilish
            ✔️ Xarajatlar statistikasini ko‘rish
            
            🆘 Yordam uchun: /help
            📊 Statistika: /stats
            
            📢 Moliyangizni nazorat qiling va ko‘proq jamg‘aring! 🚀
            """;

    public static final String infoRu = """
            ℹ️ My Expenses Manager Bot
            
            @my_expenses_manager_bot – удобный бот для управления вашими личными финансами.
            Вы можете добавлять, отслеживать и анализировать свои расходы, чтобы эффективно управлять своими деньгами.
            
            📌 Основные функции:
            ✔️ Добавление расходов и их категоризация
            ✔️ Установка и контроль месячного бюджета
            ✔️ Просмотр статистики расходов
            
            🆘 Помощь: /help
            📊 Статистика: /stats
            
            📢 Контролируйте свои финансы и экономьте больше! 🚀
            """;

    public static final String infoEn = """
            ℹ️ My Expenses Manager Bot
            
            @my_expenses_manager_bot – a convenient bot for managing your personal finances.
            You can add, track, and analyze your expenses to manage your money efficiently.
            
            📌 Key features:
            ✔️ Add expenses and categorize them
            ✔️ Set and control a monthly budget
            ✔️ View expense statistics
            
            🆘 Help: /help
            📊 Statistics: /stats
            
            📢 Take control of your finances and save more! 🚀
            """;

    // Profil
    public static final String menuProfileUz = "Profil" + " \uD83D\uDC64";
    public static final String menuProfileRu = "Профиль" + " \uD83D\uDC64";
    public static final String menuProfileEn = "Profile" + " \uD83D\uDC64";

    public static final String backUz = "Orqaga" + " ⬅️";
    public static final String backRu = "Назад" + " ⬅️";
    public static final String backEn = "Back" + " ⬅️";


    public static final String menuProfileViewUz = "Profil ma'lumotlari \uD83D\uDC64";
    public static final String menuProfileViewRu = "Профиль \uD83D\uDC64";
    public static final String menuProfileViewEn = "Profile Info \uD83D\uDC64";

    public static final String menuProfileEditUz = "Profilni tahrirlash ⚙️";
    public static final String menuProfileEditRu = "Редактировать профиль ⚙️";
    public static final String menuProfileEditEn = "Edit Profile ⚙️";


    // Daromad
    public static final String menuIncomeUz = "Daromad" + " \uD83D\uDCB5";
    public static final String menuIncomeRu = "Доход" + " \uD83D\uDCB5";
    public static final String menuIncomeEn = "Income" + " \uD83D\uDCB5";

    // Xarajat
    public static final String menuExpenseUz = "Xarajat" + " \uD83D\uDED2";
    public static final String menuExpenseRu = "Расход" + " \uD83D\uDED2";
    public static final String menuExpenseEn = "Expense" + " \uD83D\uDED2";

    // Hisobot
    public static final String menuReportUz = "Hisobot" + " \uD83D\uDCC1";
    public static final String menuReportRu = "Отчёт" + " \uD83D\uDCC1";
    public static final String menuReportEn = "Report" + " \uD83D\uDCC1";

    // User ma'lumotlarini surash
    public static final String askFirstNameUz = """
            \uD83D\uDCCB Iltimos, ismingizni kiriting:
            Namuna: Sardor
            """;
    public static final String askLastNameUz = """
            \uD83D\uDCCB Iltimos, familiyangizni kiriting:
            Namuna: Sardorov
            """;
    public static final String askCurrentIncomeUz = """
            \uD83D\uDCB5 Iltimos, hozirgi daromat miqdoringizni kiriting:
            Namuna: 10000000
            """;

    public static final String askFirstNameRu = """
            \uD83D\uDCCB Пожалуйста, введите ваше имя:
            Пример: Сардор
            """;
    public static final String askLastNameRu = """
            \uD83D\uDCCB Пожалуйста, введите вашу фамилию:
            Пример: Сардоров
            """;
    public static final String askCurrentIncomeRu = """
            \uD83D\uDCB5 Пожалуйста, введите ваш текущий доход:
            Пример: 10000000
            """;

    public static final String askFirstNameEn = """
            \uD83D\uDCCB Please enter your first name:
            Example: Sardor
            """;
    public static final String askLastNameEn = """
            \uD83D\uDCCB Please enter your last name:
            Example: Sardorov
            """;
    public static final String askCurrentIncomeEn = """
            \uD83D\uDCB5 Please enter your current income amount:
            Example: 10000000
            """;

    public static final String successSaveProfileInfoUz = "Shaxsiy ma'lumotlaringiz muvaffaqiyatli saqlandi. \nUlarni 'Profil' bo'limidan ko'rishingiz va o'zgartirishingiz mumkin.";
    public static final String successSaveProfileInfoRu = "Ваши личные данные успешно сохранены. \nВы можете просмотреть и изменить их в разделе 'Профиль'.";
    public static final String successSaveProfileInfoEn = "Your personal information has been successfully saved. \nYou can view and edit it in the 'Profile' section.";

    public static final String askMonthlyReportUz = "Oylik hisobotlar 📊";
    public static final String askMonthlyReportRu = "Ежемесячные отчёты 📊";
    public static final String askMonthlyReportEn = "Monthly reports 📊";

    public static final String askYearlyReportUz = "Yillik hisobotlar 📊";
    public static final String askYearlyReportRu = "Годовые отчёты 📊";
    public static final String askYearlyReportEn = "Yearly reports 📊";

    public static final String askIncomeUz = "Daromad hisobotlari 📊";
    public static final String askIncomeRu = "Доход отчёты 📊";
    public static final String askIncomeEn = "Income reports 📊";

    public static final String askTextFormatUz = "Text holatida \uD83D\uDCDD";
    public static final String askTextFormatRu = "В текстовом формате \uD83D\uDCDD";
    public static final String askTextFormatEn = "In text format \uD83D\uDCDD";

    public static final String askExcelFormatUz = "Excel formatida \uD83D\uDDC2";
    public static final String askExcelFormatRu = "В формате Excel \uD83D\uDDC2";
    public static final String askExcelFormatEn = "In Excel format \uD83D\uDDC2";

    // Xarajat
    public static final String askExpenseUz = "Xarajat hisobotlari 📊";
    public static final String askExpenseRu = "Расход отчёты 📊";
    public static final String askExpenseEn = "Expense reports 📊";


    public static final String editFirstNameButtonUz = "Ismni tahrirlash ✏️";
    public static final String editFirstNameButtonRu = "Имя ✏️";
    public static final String editFirstNameButtonEn = "Edit Name ✏️";

    public static final String editLastNameButtonUz = "Familiyani tahrirlash ✏️";
    public static final String editLastNameButtonRu = "Фамилия ✏️";
    public static final String editLastNameButtonEn = "Edit Surname ✏️";

    public static final String editPhoneNumberButtonUz = "Telefon raqamni tahrirlash ✏️";
    public static final String editPhoneNumberButtonRu = "Телефон ✏️";
    public static final String editPhoneNumberButtonEn = "Edit Phone ✏️";

    public static final String askNewFirstNameUz = "Iltimos, yangi ismni yozing:\nNamuna: Jasur";
    public static final String askNewFirstNameRu = "Пожалуйста, введите новое имя:\nПример: Jasur";
    public static final String askNewFirstNameEn = "Please enter a new first name:\nExample: Jasur";

    public static final String askNewLastNameUz = "Iltimos, yangi familiyangizni yozing:\nNamuna: Karimov";
    public static final String askNewLastNameRu = "Пожалуйста, введите новую фамилию:\nПример: Karimov";
    public static final String askNewLastNameEn = "Please enter a new surname:\nExample: Karimov";

    public static final String askNewPhoneNumberUz = "Iltimos, yangi telefon raqamingizni yozing:\nNamuna: +998901234567";
    public static final String askNewPhoneNumberRu = "Пожалуйста, введите новый номер телефона:\nПример: +998901234567";
    public static final String askNewPhoneNumberEn = "Please enter a new phone number:\nExample: +998901234567";

    public static final String askExpenseSourceUz = "Iltimos, xarajat joyini kiriting:\nNamuna: Supermarket";
    public static final String askExpenseSourceRu = "Пожалуйста, введите место расхода:\nПример: Supermarket";
    public static final String askExpenseSourceEn = "Please enter the expense location:\nExample: Supermarket";

    public static final String askExpenseAmountUz = "Iltimos, xarajat miqdorini kiriting (so‘mda):\nNamuna: 150000";
    public static final String askExpenseAmountRu = "Пожалуйста, введите сумму расхода (в сумах):\nПример: 150000";
    public static final String askExpenseAmountEn = "Please enter the expense amount (in UZS):\nExample: 150000";

    public static final String askExpenseDescriptionUz = "Iltimos, xarajat uchun izoh kiriting:\nNamuna: Non va sut uchun";
    public static final String askExpenseDescriptionRu = "Пожалуйста, введите примечание к расходу:\nПример: Non va sut uchun";
    public static final String askExpenseDescriptionEn = "Please enter a note for the expense:\nExample: Non va sut uchun";

    public static final String successSaveExpenseUz = "Xarajat ma'lumotlaringiz muvaffaqiyatli saqlandi.";
    public static final String successSaveExpenseRu = "Ваши данные о расходах успешно сохранены.";
    public static final String successSaveExpenseEn = "Your expense details have been successfully saved.";

    public static final String failedSaveExpenseUz = "Xarajat ma'lumotlaringiz saqlanmadi.";
    public static final String failedSaveExpenseRu = "Ваши данные о расходах не были сохранены.";
    public static final String failedSaveExpenseEn = "Your expense details were not saved.";

    public static final String askIncomeSourceUz = "Iltimos, daromad manbasini kiriting:\nNamuna: Ish haqi";
    public static final String askIncomeSourceRu = "Пожалуйста, введите источник дохода:\nПример: Зарплата";
    public static final String askIncomeSourceEn = "Please enter the income source:\nExample: Salary";

    public static final String askIncomeAmountUz = "Iltimos, daromad miqdorini kiriting (so‘mda):\nNamuna: 2000000";
    public static final String askIncomeAmountRu = "Пожалуйста, введите сумму дохода (в сумах):\nПример: 2000000";
    public static final String askIncomeAmountEn = "Please enter the income amount (in UZS):\nExample: 2000000";

    public static final String askIncomeDescriptionUz = "Iltimos, daromad uchun izoh kiriting:\nNamuna: Bonus yoki qo‘shimcha daromad";
    public static final String askIncomeDescriptionRu = "Пожалуйста, введите примечание к доходу:\nПример: Бонус или дополнительный доход";
    public static final String askIncomeDescriptionEn = "Please enter a note for the income:\nExample: Bonus or additional income";

    public static final String successSaveIncomeUz = "Daromad ma'lumotlaringiz muvaffaqiyatli saqlandi.";
    public static final String successSaveIncomeRu = "Ваши данные о доходах успешно сохранены.";
    public static final String successSaveIncomeEn = "Your income details have been successfully saved.";

    public static final String failedSaveIncomeUz = "Daromad ma'lumotlaringiz saqlanmadi.";
    public static final String failedSaveIncomeRu = "Ваши данные о доходах не были сохранены.";
    public static final String failedSaveIncomeEn = "Your income details were not saved.";


    public static final String expenseInfoUz = """
            ✅ Xarajat ma'lumotlari:
            
            📍 Xarajat joyi: %s
            💰 Xarajat miqdori: %.2f so'm
            📝 Izoh: %s
            
            Ushbu ma'lumotlarni tasdiqlaysizmi?
            """;

    public static final String expenseInfoRu = """
            ✅ Данные о расходе:
            
            📍 Место расхода: %s
            💰 Сумма расхода: %.2f сум
            📝 Описание: %s
            
            Вы подтверждаете эти данные?
            """;

    public static final String expenseInfoEn = """
            ✅ Expense details:
            
            📍 Expense location: %s
            💰 Expense amount: %.2f UZS
            📝 Description: %s
            
            Do you confirm these details?
            """;

    public static final String incomeInfoUz = """
            ✅ Daromad ma'lumotlari:
            
            📍 Daromad manbasi: %s
            💰 Daromad miqdori: %.2f so'm
            📝 Izoh: %s
            
            Ushbu ma'lumotlarni tasdiqlaysizmi?
            """;

    public static final String incomeInfoRu = """
            ✅ Данные о доходе:
            
            📍 Источник дохода: %s
            💰 Сумма дохода: %.2f сум
            📝 Описание: %s
            
            Вы подтверждаете эти данные?
            """;

    public static final String incomeInfoEn = """
            ✅ Income details:
            
            📍 Income source: %s
            💰 Income amount: %.2f UZS
            📝 Description: %s
            
            Do you confirm these details?
            """;


    public static final String confirmMessageUz = "Tasdiqlash ✅";
    public static final String declineMessageUz = "Rad etish ❌";

    public static final String confirmMessageRu = "Подтвердить ✅";
    public static final String declineMessageRu = "Отклонить ❌";

    public static final String confirmMessageEn = "Confirm ✅";
    public static final String declineMessageEn = "Decline ❌";

}
