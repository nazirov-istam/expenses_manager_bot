package com.example.expenses.service;

import com.example.expenses.application.BotProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
            String url = "https://api.telegram.org/bot" + botProperties.getToken() + "/sendMessage";

            Map<String, Object> request = new HashMap<>();
            request.put("chat_id", chatId);
            request.put("text", message);
            request.put("parse_mode", "MarkdownV2");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

            restTemplate.postForObject(url, entity, String.class);
        } catch (Exception e) {
            System.err.println("Xatolik: Telegram xabarini yuborishda muammo yuz berdi. " + e.getMessage());
        }
    }

    public void sendWeeklyIncomeReport(Long chatId, File reportFile, LocalDate startDate, LocalDate endDate) {
        try {
            String url = "https://api.telegram.org/bot" + botProperties.getToken() + "/sendDocument";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // 📅 Haftalik hisobot haqida caption
            String caption = String.format(
                    "📊 *Haftalik Daromad Hisoboti* \n\n" +
                            "📅 *Boshlanish sanasi:* %s\n" +
                            "📅 *Tugash sanasi:* %s\n" +
                            "📄 Hisobot fayli ilova qilindi.",
                    startDate, endDate
            );

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("chat_id", chatId);
            body.add("document", new FileSystemResource(reportFile)); // Fayl yuklash
            body.add("caption", caption); // 📅 Hisobot haqida matn

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            restTemplate.postForObject(url, requestEntity, String.class);

            System.out.println("📄 Haftalik daromad hisobot hujjati jo'natildi!");
        } catch (Exception e) {
            System.err.println("Xatolik: Telegram document yuborishda muammo yuz berdi. " + e.getMessage());
        }
    }
}
