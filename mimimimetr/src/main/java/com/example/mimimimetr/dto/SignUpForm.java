package com.example.mimimimetr.dto;

import com.example.mimimimetr.validation.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUpForm {
    @Size(min = 2, max = 20)
    @NotBlank
    private String name;
    @Size(min = 7, max = 20)
    @NotBlank
    @ValidPassword
    private String password;
    private byte[] image;
}
