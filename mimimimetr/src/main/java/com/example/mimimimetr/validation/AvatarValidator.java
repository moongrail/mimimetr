package com.example.mimimimetr.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AvatarValidator implements ConstraintValidator<ValidAvatar, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value.getOriginalFilename().endsWith(".jpg") || value.getOriginalFilename().endsWith(".png");
    }
}
