package com.example.gadanko.objects.config;

import com.example.gadanko.objects.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsMessageController {

    @MessageMapping("/sent")
    @SendTo("/chat/messages")
    public Message sendMessage(Message message) throws Exception{
        return message;
    }
}
