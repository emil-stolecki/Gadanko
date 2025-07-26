package com.example.gadanko.config;

import com.example.gadanko.objects.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.kafka.core.KafkaTemplate;
@Controller
public class WsMessageController {


    //make cyston serializer for message
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @MessageMapping("/sent/1")
    @SendTo("/chat/1")
    public Message sendMessage1(Message message) throws Exception{
        kafkaTemplate.send("general", message);
        return message;
    }

    @MessageMapping("/sent/2")
    @SendTo("/chat/2")
    public Message sendMessage2(Message message) throws Exception{
        kafkaTemplate.send("art", message);
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
