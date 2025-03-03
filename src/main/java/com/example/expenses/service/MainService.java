package com.example.expenses.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {
    public SendMessage mainBot(Update update) throws IOException, InterruptedException {
        SendMessage sendMessage = new SendMessage();
        return sendMessage;
    }
}
