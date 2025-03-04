package com.example.expenses.application;

import org.springframework.stereotype.Component;

@Component
public final class MessageUz {
    public static final String startUz = """
            👋 Salom! My Expenses Manager Bot-ga xush kelibsiz!💰
            
            Bu bot yordamida xarajatlaringizni kuzatishingiz, byudjetingizni nazorat qilishingiz va moliyaviy maqsadlaringizga erishishingiz mumkin.
            
            📌 Asosiy buyruqlar:
            ✅ /add – Xarajat qo‘shish
            """;

    public static final String startUzIsRegistered = """
            👋 Salom! Siz allaqachon ro‘yxatdan o‘tgansiz. 🎉
            
            📌 Asosiy buyruqlar:
            ✅ /add – Xarajat qo‘shish
            """;
}
