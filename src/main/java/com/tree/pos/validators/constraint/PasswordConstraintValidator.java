package com.tree.pos.validators.constraint;


import java.util.Arrays;
import java.util.List;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tree.pos.validators.anotation.ValidPassword;
import com.tree.pos.validators.intrefaces.FieldValueConfirmation;

import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {


    @Autowired
    private FieldValueConfirmation confirmation;

   

    @Override
    public boolean isValid( String value,  ConstraintValidatorContext context) {
        List<Rule> rules = Arrays.asList(
            new LengthRule(8, 30),

                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),

                // no whitespace
                new WhitespaceRule()
        );
        PasswordValidator validator = new PasswordValidator(rules);
        RuleResult result = validator.validate(new PasswordData(value));
        if (!result.isValid()) {
            List<String> messages = validator.getMessages(result);
            String messageTemplate = messages.iterator().next();
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();     
        }

       
        return result.isValid();
    }
}