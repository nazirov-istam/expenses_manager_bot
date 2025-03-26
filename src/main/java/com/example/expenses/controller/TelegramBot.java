package com.example.expenses.controller;

import com.example.expenses.application.BotProperties;
import com.example.expenses.configuration.DefaultConfiguration;
import com.example.expenses.service.MainService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final BotProperties botProperties;
    private final MainService mainService;
    private final DefaultConfiguration options;

    public TelegramBot(DefaultConfiguration options, BotProperties botProperties, MainService mainService) {
        this.options = options;
        this.botProperties = botProperties;
        this.mainService = mainService;
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (options == null) {
                log.error("DefaultBotOptions is null");
                return; // или выбросьте исключение
            }
            PartialBotApiMethod<?> response = mainService.mainBot(update);
            if (response != null) {
                if (response instanceof SendMessage sendMessage) {
                    execute(sendMessage);
                } else if (response instanceof SendDocument sendDocument) {
                    execute(sendDocument);
                }
            } else {
                log.warn("main service returned null");
                System.err.println("main service returned null");
            }
        } catch (Exception e) {
            log.error("Error occurred while processing update: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }
}
