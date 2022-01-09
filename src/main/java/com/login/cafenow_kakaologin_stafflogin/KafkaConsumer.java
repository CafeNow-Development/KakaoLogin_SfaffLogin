package com.login.cafenow_kakaologin_stafflogin;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "exam", groupId = "foo")
    public void consume(Object message) throws IOException {
        System.out.println(message);
    }
}