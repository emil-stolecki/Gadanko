package com.example.gadanko.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterData implements Serializable {
    private String username;
    private String email;
    private String password;
    private String repeat;
}
