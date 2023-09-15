package com.example.mimimimetr.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @NotEmpty
    @Size(min = 7, max = 20)
    private String password;
}
