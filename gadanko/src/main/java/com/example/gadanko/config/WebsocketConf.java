package com.example.gadanko.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConf implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/sent/1").withSockJS();
        registry.addEndpoint("/sent/2").withSockJS();
        registry.addEndpoint("/sent/3").withSockJS();
        registry.addEndpoint("/sent/4").withSockJS();
        registry.addEndpoint("/sent/5").withSockJS();
        registry.addEndpoint("/sent/6").withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/chat");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
