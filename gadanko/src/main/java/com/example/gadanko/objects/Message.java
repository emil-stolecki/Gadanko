package com.example.gadanko.objects;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.streams.kstream.internals.KStreamImpl;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    private String username;
    private String date;
    private String text;


}
