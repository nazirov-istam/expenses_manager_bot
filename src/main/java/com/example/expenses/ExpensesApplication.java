package com.example.expenses;

import com.example.expenses.application.BotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(BotProperties.class)
public class ExpensesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpensesApplication.class, args);
    }

    // TODO
    /*
    1) Phone Number ishlaydigan qilish kerak ✅
    2) Delete account ✅
    3) Osonlashtirish (tugmalar uta kup.)
    4) Reset Balance ✅

    userni telefon raqamini faqat "Contact share" qilib olsa bo'ladi
    o'shanda o'zini chat id simi yo'qmi tekshirib qo'yasiz
    bunda yana bitta jihat bor: boshqa odamni kontaktini share qilishi ham mumkin
    o'shanchun chat id lar mosligini tekshirish kerak
     */
}
