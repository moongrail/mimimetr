package com.example.mimimimetr.dto;

import com.example.mimimimetr.validation.ValidAvatar;
import com.example.mimimimetr.validation.ValidPassword;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUpForm {
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Size(min = 7, max = 40, message = "email must be between 7 and 40 characters")
    @NotBlank(message = "email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;
    @Size(min = 7, max = 20, message = "password must be between 7 and 20 characters")
    @NotBlank(message = "password cannot be blank")
    @ValidPassword
    private String password;
    @ValidAvatar(message = "Avatar must be valid. Only .png and .jpg files are allowed.")
    @NotNull(message = "Avatar cannot be null")
    private MultipartFile avatar;
}
