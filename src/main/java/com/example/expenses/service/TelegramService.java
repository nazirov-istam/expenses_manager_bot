package com.example.expenses.service;

import com.example.expenses.application.BotProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TelegramService {

    //TODO Test qilib kurish kerak


    private final RestTemplate restTemplate;
    private final BotProperties botProperties;

    public TelegramService(RestTemplate restTemplate, BotProperties botProperties) {
        this.restTemplate = restTemplate;
        this.botProperties = botProperties;
    }

    public void sendTelegramMessage(Long chatId, String message) {
        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
            String url = "https://api.telegram.org/bot" + botProperties.getToken() +
                    "/sendMessage?chat_id=" + chatId + "&text=" + encodedMessage;
            restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            System.err.println("Xatolik: Telegram xabarini yuborishda muammo yuz berdi. " + e.getMessage());
        }
    }
}
