package com.example.mimimimetr.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    @NotEmpty
    @NotBlank
    private String username;
    @NotBlank
    @NotEmpty
    private String password;
}
