package com.tree.pos.validators.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tree.pos.repository.UserRepositoryJpa;
import com.tree.pos.validators.intrefaces.FieldValueExists;
import com.tree.pos.validators.anotation.Unique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private ApplicationContext context;

    private FieldValueExists valueExist;

    public FieldValueExists getValueExist() {
        return this.valueExist;
    }

    @Autowired
    public void setValueExist(FieldValueExists valueExist) {
        this.valueExist = valueExist;
    }
    
    private String fieldName;

    @Override
    public void initialize(final Unique unique) {
        final Class<? extends FieldValueExists> clazz =  unique.service();
        this.fieldName = unique.fieldName();
        final String serviceQualifier = unique.serviceQualifier();
        if(!serviceQualifier.equals("")){
            this.valueExist = this.context.getBean(serviceQualifier, clazz);
        }else{
            this.valueExist = this.context.getBean(clazz);
        }
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return !this.valueExist.fieldValueExist(value, fieldName);
    }
    
}