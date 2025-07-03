package com.example.gadanko.objects.config;

import com.example.gadanko.objects.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsMessageController {

    @MessageMapping("/sent/1")
    @SendTo("/chat/1")
    public Message sendMessage1(Message message) throws Exception{
        return message;
    }

    @MessageMapping("/sent/2")
    @SendTo("/chat/2")
    public Message sendMessage2(Message message) throws Exception{
        return message;
    }

    @MessageMapping("/sent/3")
    @SendTo("/chat/3")
    public Message sendMessage3(Message message) throws Exception{
        return message;
    }

    @MessageMapping("/sent/4")
    @SendTo("/chat/4")
    public Message sendMessage4(Message message) throws Exception{
        return message;
    }

    @MessageMapping("/sent/5")
    @SendTo("/chat/5")
    public Message sendMessage5(Message message) throws Exception{
        return message;
    }

    @MessageMapping("/sent/6")
    @SendTo("/chat/6")
    public Message sendMessage6(Message message) throws Exception{
        return message;
    }


}
