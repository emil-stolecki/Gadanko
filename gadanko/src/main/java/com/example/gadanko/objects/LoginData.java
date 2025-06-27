package com.example.gadanko.objects;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {
    @NonNull
    @Size(min=3, max=24)
    private String username;
    @Size(min=8)
    @NonNull
    private String password;
}
