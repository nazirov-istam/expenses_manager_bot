package com.example.expenses.application;

import org.springframework.stereotype.Component;

@Component
public final class Messages {
    public static final String language = """
            \uD83C\uDDFA\uD83C\uDDFF Tilni tanlang !!!
            \uD83C\uDDF7\uD83C\uDDFA Выберите язык !!!
            \uD83C\uDDFA\uD83C\uDDF8 Select a language !!!
            """;
    public static final String Uz = "\uD83C\uDDFA\uD83C\uDDFF Uz";
    public static final String Ru = "\uD83C\uDDF7\uD83C\uDDFA Ru";
    public static final String En = "\uD83C\uDDFA\uD83C\uDDF8 En";

    public static final String welcomeMenuUz = "Siz 'Asosiy buyruqlar' qismidasiz.";
    public static final String welcomeMenuRu = "Вы находитесь в разделе 'Основные команды'.";
    public static final String welcomeMenuEn = "You are in the 'Main commands' section.";


    public static final String startUz = """
            👋 Salom! My Expenses Manager Bot-ga xush kelibsiz!💰
            
            Bu bot yordamida xarajatlaringizni kuzatishingiz, byudjetingizni nazorat qilishingiz va moliyaviy maqsadlaringizga erishishingiz mumkin.
            
            📌 Asosiy buyruqlar:
            ✅ /main – Asosiy buyruqlar
            ✅ /info – Bot haqida ma'lumot olish
            ✅ /help – Yordam
            ✅ /langauge - Tilni o'zgartirish
            """;

    public static final String startRu = """
            👋 Привет! Добро пожаловать в My Expenses Manager Bot!💰
            
            С помощью этого бота вы можете отслеживать свои расходы, контролировать бюджет и достигать финансовых целей.
            
            📌 Основные команды:
            ✅ /main – Основные команды
            ✅ /info – Получить информацию о боте
            ✅ /help – Помощь
            ✅ /langauge - Изменить язык
            """;

    public static final String startEn = """
            👋 Hello! Welcome to My Expenses Manager Bot!💰
            
            With this bot, you can track your expenses, manage your budget, and achieve your financial goals.
            
            📌 Main commands:
            ✅ /main – Main commands
            ✅ /info – Get information about the bot
            ✅ /help – Help
            ✅ /langauge - Change language
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
    public static final String menuProfilUz = "Profil";
    public static final String menuProfilRu = "Профиль";
    public static final String menuProfilEn = "Profile";

    // Daromad
    public static final String menuDaromadUz = "Daromad";
    public static final String menuDaromadRu = "Доход";
    public static final String menuDaromadEn = "Income";

    // Xarajat
    public static final String menuXarajatUz = "Xarajat";
    public static final String menuXarajatRu = "Расход";
    public static final String menuXarajatEn = "Expense";

    // Hisobot
    public static final String menuHisobotUz = "Hisobot";
    public static final String menuHisobotRu = "Отчёт";
    public static final String menuHisobotEn = "Report";

    // Qo'shimcha ma'lumotlar
    public static final String menuQoshimchaUz = "Qo'shimcha ma'lumotlar";
    public static final String menuQoshimchaRu = "Дополнительная информация";
    public static final String menuQoshimchaEn = "Additional Information";


}
