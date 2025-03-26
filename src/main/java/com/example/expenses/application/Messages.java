package com.example.expenses.application;

import org.springframework.stereotype.Component;

@Component
public final class Messages {
    public static final String language = """
            \uD83C\uDDFA\uD83C\uDDFF Tilni tanlang !
            \uD83C\uDDF7\uD83C\uDDFA Выберите язык !
            \uD83C\uDDFA\uD83C\uDDF8 Select a language !
            """;

    public static final String languageChangeSuccessUz = "Foydalanuvchi tili muvaffaqiyatli o'zgartirildi✅";
    public static final String languageChangeSuccessRu = "Язык пользователя успешно изменен✅";
    public static final String languageChangeSuccessEn = "User language successfully changed✅";

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

    public static final String askIncomeOrExpenseUz = "Iltimos, daromat yoki xarajat hisobotlarini ko'rishni xohlayotganingizni tanlang.";
    public static final String askIncomeOrExpenseEn = "Please select whether you want to view the income or expense reports.";
    public static final String askIncomeOrExpenseRu = "Пожалуйста, выберите, хотите ли вы просмотреть отчёты о доходах или расходах.";

    public static final String getSuccessFirstNameEditUz = "Foydalanuvchi ismi yangilandi.✅";
    public static final String getSuccessFirstNameEditRu = "Имя пользователя обновлено.✅";
    public static final String getSuccessFirstNameEditEn = "User's first name has been updated.✅";

    public static final String getSuccessLastNameEditUz = "Foydalanuvchi familiyasi yangilandi.✅";
    public static final String getSuccessLastNameEditRu = "Фамилия пользователя обновлена.✅";
    public static final String getSuccessLastNameEditEn = "User's last name has been updated.✅";

    public static final String getSuccessPhoneNumberEditUz = "Foydalanuvchi telefon raqami yangilandi.✅";
    public static final String getSuccessPhoneNumberEditRu = "Номер телефона пользователя обновлен.✅";
    public static final String getSuccessPhoneNumberEditEn = "User's phone number has been updated.✅";

    public static final String registerSuccessUz = """
            👋 My Expenses Manager Bot-ga xush kelibsiz!💰
            
            Endi xarajat va daromatlaringizni kuzatib borish, byudjetingizni boshqarish va moliyaviy maqsadlaringizga erishish ancha oson bo‘ladi.
            
             📌 Quyidagi qismlar yordamida botdan foydalanishingiz mumkin:
            ✅ Profil - shaxsiy malumotlarni ko'rish va o'zgartirish
            ✅ Daromat - daromatni ro'yxatdan o'tkazish
            ✅ Xarajat - xarajatni ro'yxatdan o'tkazish
            ✅ Hisobot -  umumiy hisobotlarni olish
            
            🗓 Menu -> Qo'shimcha buyruqlar
            
            🚀 Moliyaviy intizomga ega bo‘lish va mablag‘laringizni samarali boshqarish uchun hozirdanoq botdan foydalanishni boshlang!
            """;

    public static final String registerSuccessRu = """
            👋 Добро пожаловать в My Expenses Manager Bot!💰
            
            Теперь отслеживать свои доходы и расходы, управлять бюджетом и достигать финансовых целей стало намного проще.
            
            📌 Вы можете использовать бот с помощью следующих разделов:
            ✅ Профиль – просмотр и изменение личных данных
            ✅ Доход – регистрация дохода
            ✅ Расход – регистрация расхода
            ✅ Отчет – получение общих отчетов
            
            🗓 Меню -> Дополнительные команды
            
            🚀 Начните пользоваться ботом прямо сейчас, чтобы развить финансовую дисциплину и эффективно управлять своими средствами!
            """;

    public static final String registerSuccessEn = """
            👋 Welcome to My Expenses Manager Bot!💰
            
            Now tracking your expenses and income, managing your budget, and achieving your financial goals is easier than ever.
            
            📌 You can use the bot through the following sections:
            ✅ Profile – View and edit personal information
            ✅ Income – Register income
            ✅ Expense – Register expenses
            ✅ Report – Get general reports
            
            🗓 Menu -> Additional commands
            
            🚀 Start using the bot now to develop financial discipline and manage your money effectively!
            """;

    public static final String startUzIsRegistered = """
            Siz avval ro‘yxatdan o‘tgansiz. 📌
            """;

    public static final String startRuIsRegistered = """
            Вы ранее зарегистрировались. 📌
            """;

    public static final String startEnIsRegistered = """
            You have previously registered. 📌
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
    public static final String menuProfileViewRu = "Профиль Инфо \uD83D\uDC64";
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
            Ro'yxatdan to'liq o'tish uchun shaxsiy ma'lumotlaringizni birma-bir kiriting.📌
            
            \uD83D\uDCCB Iltimos, ismingizni kiriting:
            Namuna: Sardor
            """;

    public static final String askFirstNameRu = """
            Для завершения регистрации введите свои личные данные по очереди.📌
            
            \uD83D\uDCCB Пожалуйста, введите ваше имя:
            Пример: Сергей
            """;

    public static final String askFirstNameEn = """
            To complete the registration, please enter your personal details one by one.📌
            
            \uD83D\uDCCB Please enter your first name:
            Example: John
            """;
    public static final String askLastNameUz = """
            \uD83D\uDCCB Iltimos, familiyangizni kiriting:
            Namuna: Sardorov
            """;
    public static final String askCurrentBalanceUz = """
            \uD83D\uDCB5 Iltimos, hozirgi balans miqdoringizni kiriting:
            Namuna: 10000000
            """;

    public static final String askPhoneNumberUz = """
            📲 Iltimos, telefon raqamingizni kiriting:
            Namuna: +998919876543
            """;

    public static final String askPhoneNumberRu = """
            📲 Пожалуйста, введите свой номер телефона:
            Пример: +998919876543
            """;

    public static final String askPhoneNumberEn = """
            📲 Please, enter your phone number:
            Example: +998919876543
            """;
    public static final String askLastNameRu = """
            \uD83D\uDCCB Пожалуйста, введите вашу фамилию:
            Пример: Сардоров
            """;
    public static final String askCurrentBalanceRu = """
            \uD83D\uDCB5 Пожалуйста, введите ваш текущий Баланс:
            Пример: 10000000
            """;
    public static final String askLastNameEn = """
            \uD83D\uDCCB Please enter your last name:
            Example: Way
            """;
    public static final String askCurrentBalanceEn = """
            \uD83D\uDCB5 Please enter your current balance amount:
            Example: 10000000
            """;

    public static final String askMonthlyReportUz = "Oylik hisobotlar 📊";
    public static final String askMonthlyReportRu = "Ежемесячные отчёты 📊";
    public static final String askMonthlyReportEn = "Monthly reports 📊";

    public static final String askYearlyReportUz = "Yillik hisobotlar 📊";
    public static final String askYearlyReportRu = "Годовые отчёты 📊";
    public static final String askYearlyReportEn = "Yearly reports 📊";

    public static final String askIncomeUz = "Daromad hisobotlari 📊";
    public static final String askIncomeRu = "Доход отчёты 📊";
    public static final String askIncomeEn = "Income reports 📊";

    public static final String askYearOfMonthlyFormatUz = "Yilni tanlang: ";
    public static final String askYearOfMonthlyFormatRu = "Выберите год: ";
    public static final String askYearOfMonthlyFormatEn = "Select the year: ";

    public static final String askMonthOfMonthlyFormatUz = "Oyni tanlang: ";
    public static final String askMonthOfMonthlyFormatRu = "Выберите месяц: ";
    public static final String askMonthOfMonthlyFormatEn = "Select the month: ";


    public static final String askYearlyFormatUz = "Qaysi yilga oid hisobotni olishni xohlaysiz?";

    public static final String askYearlyFormatRu = "За какой годовой отчет вы хотели бы получить информацию?";

    public static final String askYearlyFormatEn = "For which year would you like to receive the report?";

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

    public static final String askExpenseSourceUz = """
            Iltimos, xarajat joyini kiriting:
            🏬 Namuna: Supermarket
            """;

    public static final String askExpenseSourceRu = """
            Пожалуйста, введите место расхода:
            🏬 Пример: Supermarket
            """;

    public static final String askExpenseSourceEn = """
            Please enter the expense location:
            🏬 Example: Supermarket
            """;


    public static final String askExpenseSourceAgainUz = """
            ❌ Xatolik: Faqat harflar va raqamlardan foydalaning!
            
            Iltimos, xarajat joyini qayta kiriting:
            """;

    public static final String askExpenseSourceAgainRu = """
            ❌ Ошибка: Используйте только буквы и цифры!
            
            Пожалуйста, введите место расхода заново:
            """;

    public static final String askExpenseSourceAgainEn = """
            ❌ Error: Use only letters and numbers!
            
            Please re-enter the expense location:
            """;

    public static final String askIncomeSourceAgainUz = """
            ❌ Xatolik: Faqat harflar va raqamlardan foydalaning!
            
            Iltimos, daromad manbasini qayta kiriting:
            """;

    public static final String askIncomeSourceAgainRu = """
            ❌ Ошибка: Используйте только буквы и цифры!
            
            Пожалуйста, введите источник дохода заново:
            """;

    public static final String askIncomeSourceAgainEn = """
            ❌ Error: Use only letters and numbers!
            
            Please re-enter the income source:
            """;

    public static final String askIncomeDescriptionAgainUz = """
            ❌ Xatolik: Faqat harflar va raqamlardan foydalaning!
            
            Iltimos, daromad izohini qayta kiriting:
            """;

    public static final String askIncomeDescriptionAgainRu = """
            ❌ Ошибка: Используйте только буквы и цифры!
            
            Пожалуйста, введите примечание к доходу заново:
            """;

    public static final String askIncomeDescriptionAgainEn = """
            ❌ Error: Use only letters and numbers!
            
            Please re-enter the income description:
            """;

    public static final String askExpenseDescriptionAgainUz = """
            ❌ Xatolik: Faqat harflar va raqamlardan foydalaning!
            
            Iltimos, xarajat izohini qayta kiriting:
            """;

    public static final String askExpenseDescriptionAgainRu = """
            ❌ Ошибка: Используйте только буквы и цифры!
            
            Пожалуйста, введите примечание к расходу заново:
            """;

    public static final String askExpenseDescriptionAgainEn = """
            ❌ Error: Use only letters and numbers!
            
            Please re-enter the expense description:
            """;


    public static final String askExpenseAmountUz = """
            Iltimos, xarajat miqdorini kiriting (so‘mda):
            💰 Namuna: 150000
            """;

    public static final String askExpenseAmountRu = """
            Пожалуйста, введите сумму расхода (в сумах):
            💰 Пример: 150000
            """;

    public static final String askExpenseAmountEn = """
            Please enter the expense amount (in UZS):
            💰 Example: 150000
            """;

    public static final String askIncomeAmountAgainUz = """
            ❌ Xatolik:
            Daromad miqdori faqat raqam va nuqtadan iborat bo‘lishi kerak.
            Masalan: 12345.67 yoki 100
            
            Iltimos, daromad miqdorini qayta kiriting!
            """;

    public static final String askIncomeAmountAgainRu = """
            ❌ Ошибка:
            Сумма дохода должна содержать только цифры и точку.
            Например: 12345.67 или 100
            
            Пожалуйста, введите сумму дохода заново!
            """;

    public static final String askIncomeAmountAgainEn = """
            ❌ Error:
            The income amount must contain only numbers and a dot.
            Example: 12345.67 or 100
            
            Please re-enter the income amount!
            """;

    public static final String askExpenseAmountAgainUz = """
            ❌ Xatolik:
            Xarajat miqdori faqat raqam va nuqtadan iborat bo‘lishi kerak.
            Masalan: 12345.67 yoki 100
            
            Iltimos, xarajat miqdorini qayta kiriting!
            """;

    public static final String askExpenseAmountAgainRu = """
            ❌ Ошибка:
            Сумма расхода должна содержать только цифры и точку.
            Например: 12345.67 или 100
            
            Пожалуйста, введите сумму расхода заново!
            """;

    public static final String askExpenseAmountAgainEn = """
            ❌ Error:
            The expense amount must contain only numbers and a dot.
            Example: 12345.67 or 100
            
            Please re-enter the expense amount!
            """;


    public static final String askExpenseDescriptionUz = """
            Iltimos, xarajat uchun izoh kiriting:
            📝 Namuna: Non va sut uchun
            """;

    public static final String askExpenseDescriptionRu = """
            Пожалуйста, введите примечание к расходу:
            📝 Пример: Хлеб и молоко
            """;

    public static final String askExpenseDescriptionEn = """
            Please enter a note for the expense:
            📝 Example: Bread and milk
            """;

    public static final String askIncomeSourceUz = "Iltimos, daromad manbasini kiriting:\nNamuna: Ish haqi";
    public static final String askIncomeSourceRu = "Пожалуйста, введите источник дохода:\nПример: Зарплата";
    public static final String askIncomeSourceEn = "Please enter the income source:\nExample: Salary";

    public static final String askIncomeAmountUz = "Iltimos, daromad miqdorini kiriting (so‘mda):\nNamuna: 2000000";
    public static final String askIncomeAmountRu = "Пожалуйста, введите сумму дохода (в сумах):\nПример: 2000000";
    public static final String askIncomeAmountEn = "Please enter the income amount (in UZS):\nExample: 2000000";

    public static final String askIncomeDescriptionUz = "Iltimos, daromad uchun izoh kiriting:\nNamuna: Bonus yoki qo‘shimcha daromad";
    public static final String askIncomeDescriptionRu = "Пожалуйста, введите примечание к доходу:\nПример: Бонус или дополнительный доход";
    public static final String askIncomeDescriptionEn = "Please enter a note for the income:\nExample: Bonus or additional income";

    public static final String successSaveIncomeUz = "Daromad ma'lumotlaringiz muvaffaqiyatli saqlandi.✅";
    public static final String successSaveIncomeRu = "Ваши данные о доходах успешно сохранены.✅";
    public static final String successSaveIncomeEn = "Your income details have been successfully saved.✅";

    public static final String declineSaveIncomeUz = "Daromad ma'lumotlaringiz saqlanmadi.❌";
    public static final String declineSaveIncomeRu = "Ваши данные о доходах не были сохранены.❌";
    public static final String declineSaveIncomeEn = "Your income details were not saved.❌";

    public static final String successSaveExpenseUz = "Xarajat ma'lumotlaringiz muvaffaqiyatli saqlandi.✅";
    public static final String successSaveExpenseRu = "Ваши данные о расходах успешно сохранены.✅";
    public static final String successSaveExpenseEn = "Your expense details have been successfully saved.✅";

    public static final String declineSaveExpenseUz = "Xarajat ma'lumotlaringiz saqlanmadi.❌";
    public static final String declineSaveExpenseRu = "Ваши данные о расходах не были сохранены.❌";
    public static final String declineSaveExpenseEn = "Your expense details were not saved.❌";


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

    public static final String noReportByGivenYearUz = """
            Berilgan yil bo‘yicha hisobot ma’lumotlari mavjud emas.⚠️
            """;

    public static final String noReportByGivenYearRu = """
            Отчетные данные за указанный год отсутствуют⚠️.
            """;

    public static final String noReportByGivenYearEn = """
            No report data available for the specified year⚠️.
            """;

    public static final String noReportByGivenYearAndMonthUz = """
            Berilgan oy bo‘yicha hisobot ma’lumotlari mavjud emas.⚠️
            """;

    public static final String noReportByGivenYearAndMonthRu = """
            Отчетные данные за указанный месяц отсутствуют.⚠️
            """;

    public static final String noReportByGivenYearAndMonthEn = """
            No report data available for the specified month.⚠️
            """;

    public static final String getAskFirstNameAgainUz = """
            ❌ Xatolik: Faqat harflardan foydalaning!
            
            Iltimos, ismingizni qayta kiriting!
            """;

    public static final String getAskFirstNameAgainRu = """
            ❌ Ошибка: Используйте только буквы!
            
            Пожалуйста, введите ваше имя заново!
            """;

    public static final String getAskFirstNameAgainEn = """
            ❌ Error: Use only letters!
            
            Please re-enter your name!
            """;

    public static final String getAskLastNameAgainUz = """
            ❌ Xatolik: Faqat harflardan foydalaning!
            
            Iltimos, familiyangizni qayta kiriting!
            """;

    public static final String getAskLastNameAgainRu = """
            ❌ Ошибка: Используйте только буквы!
            
            Пожалуйста, введите ваше фамилию!
            """;

    public static final String getAskLastNameAgainEn = """
            ❌ Error: Use only letters!
            
            Please,, re-enter your surname!
            """;

    public static final String getAskPhoneNumberAgainUz = """
            ❌ Xatolik:
            Telefon raqamini quyidagi formatda kiriting:
            +998911234567
            
            Iltimos, telefon raqamingizni qayta kiriting!
            """;

    public static final String getAskPhoneNumberAgainEn = """
            ❌ Error:
            Please enter your phone number in the following format:
            +998911234567
            
            Please re-enter your phone number!
            """;
    public static final String getAskPhoneNumberAgainRu = """
            ❌ Ошибка:
            Пожалуйста, введите номер телефона в следующем формате:
            +998911234567
            
            Пожалуйста, введите номер телефона заново!
            """;

    public static final String getAskCurrentBalanceAgainUz = """
            ❌ Xatolik:
            Balans faqat raqam va nuqtadan iborat bo‘lishi kerak.
            Masalan: 12345.67 yoki 100
            
            Iltimos, balansni qayta kiriting!
            """;
    public static final String getAskCurrentBalanceAgainRu = """
            ❌ Ошибка:
            Баланс должен содержать только цифры и точку.
            Например: 12345.67 или 100
            
            Пожалуйста, введите баланс заново!
            """;

    public static final String getAskCurrentBalanceAgainEn = """
            ❌ Error:
            The balance must contain only numbers and a dot.
            Example: 12345.67 or 100
            
            Please enter the balance again!
            """;
}
