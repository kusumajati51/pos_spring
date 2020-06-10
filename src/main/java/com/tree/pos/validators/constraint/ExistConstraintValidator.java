package com.tree.pos.validators.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tree.pos.validators.anotation.Exist;
import com.tree.pos.validators.intrefaces.FieldValueExists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ch.qos.logback.core.Context;
import lombok.val;

public class ExistConstraintValidator implements ConstraintValidator<Exist, String> {

    @Autowired
    private ApplicationContext context;

    private FieldValueExists valueExist;

    public FieldValueExists getValueExist() {
        return this.valueExist;
    }

    private String fieldName;

    @Autowired
    public void setValueExist(FieldValueExists valueExist) {
        this.valueExist = valueExist;
    }

    @Override
    public void initialize(Exist exist) {
      final Class< ? extends FieldValueExists> clazz = exist.service();
      fieldName = exist.fieldName();
      final String serviceQualifier = exist.serviceQualifier();
      if(!serviceQualifier.equals("")){
            this.valueExist = this.context.getBean(serviceQualifier, clazz);
        }else{
            this.valueExist = this.context.getBean(clazz);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return valueExist.fieldValueExist(value, fieldName);
    }

}