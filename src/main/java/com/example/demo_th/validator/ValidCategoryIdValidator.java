package com.example.demo_th.validator;

import com.example.demo_th.entity.Category;
import com.example.demo_th.validator.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return category != null && category.getId() != null;
    }
}
