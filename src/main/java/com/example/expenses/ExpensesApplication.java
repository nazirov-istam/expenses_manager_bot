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

}
