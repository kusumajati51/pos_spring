package com.tree.pos.validators.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.tree.pos.validators.constraint.ExistConstraintValidator;
import com.tree.pos.validators.intrefaces.FieldValueExists;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistConstraintValidator.class)
public @interface Exist {
    String message() default "not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    Class<? extends FieldValueExists> service();
    String serviceQualifier() default"";
    String fieldName();
}