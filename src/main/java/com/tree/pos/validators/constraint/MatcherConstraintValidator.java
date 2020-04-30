package com.tree.pos.validators.constraint;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tree.pos.validators.anotation.MatcherValidator;

import org.apache.commons.beanutils.BeanUtils;

public class MatcherConstraintValidator implements ConstraintValidator<MatcherValidator, Object> {

    private String firstField;
    private String secondField;
    private String message;

    @Override
    public void initialize(MatcherValidator matcherValidator) {
        firstField = matcherValidator.first();
        secondField = matcherValidator.second();
        message = matcherValidator.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstField);
            final Object secondObj = BeanUtils.getProperty(value, secondField);
            valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }

}