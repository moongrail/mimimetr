package com.example.mimimimetr.dto;

import com.example.mimimimetr.validation.ValidPassword;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
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
    @Size(min = 2, max = 20)
    @NotBlank
    @Email
    private String email;
    @Size(min = 7, max = 20)
    @NotBlank
    @ValidPassword
    private String password;
    private MultipartFile avatar;
}
