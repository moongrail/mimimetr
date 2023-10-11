package com.example.mimimimetr.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignInForm {
    @Size(min = 2, max = 20)
    @NotBlank
    @Email
    private String email;
    @Size(min = 7, max = 20)
    @NotBlank
    private String password;
}
