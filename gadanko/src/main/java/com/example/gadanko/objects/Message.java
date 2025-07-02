package com.example.gadanko.objects;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.streams.kstream.internals.KStreamImpl;


@AllArgsConstructor
@Getter
@Setter
public class Message {
    private String username;
    private String date;
    private String text;


}
