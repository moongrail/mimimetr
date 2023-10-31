package com.example.mimimimetr.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AvatarValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAvatar {
    String message() default "Invalid file format. Only .png and .jpg files are allowed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
