package com.example.gadanko.objects.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterData implements Serializable {
    @NonNull
    @Size(min=3, max=24)
    private String username;
    @NonNull
    @Email
    private String email;
    @NonNull
    @Size(min=8)
    private String password;
    @Size(min=8)
    private String repeat;
}
