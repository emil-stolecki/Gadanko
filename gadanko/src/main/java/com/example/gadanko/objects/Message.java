package com.example.gadanko.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.kafka.streams.kstream.internals.KStreamImpl;
@AllArgsConstructor
@Getter
public class Message {
    private String username;
    private String text;

}
