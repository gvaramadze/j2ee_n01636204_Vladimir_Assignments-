package com.example.finallabvladimir.controller;


import com.example.finallabvladimir.model.ChatMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageSenderController {

    private final JmsTemplate jmsTemplate;

    public MessageSenderController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/send")
    public String sendMessageGet(@RequestParam String name, @RequestParam String message) {
        ChatMessage chatMessage = new ChatMessage(name, message);
        jmsTemplate.convertAndSend("lab-queue", chatMessage);
        return "Message sent from " + name + ": " + message;
    }


    @PostMapping("/send")
    public String sendMessage(@RequestBody ChatMessage chatMessage) {
        jmsTemplate.convertAndSend("lab-queue", chatMessage);
        return "Message sent from " + chatMessage;
    }
}
