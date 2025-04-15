package com.example.finallabvladimir.listener;

import com.example.finallabvladimir.model.ChatMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverListener {

    @JmsListener(destination = "lab-queue")
    public void receiveMessage(ChatMessage chatMessage) {
        System.out.println("Received message from " + chatMessage);
    }
}
