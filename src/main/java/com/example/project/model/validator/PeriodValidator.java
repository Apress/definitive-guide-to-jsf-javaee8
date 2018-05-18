package com.example.project.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.project.model.Period;

public class PeriodValidator implements ConstraintValidator<PeriodConstraint, Period> {

	@Override
	public boolean isValid(Period period, ConstraintValidatorContext context) {
        return period.getStartDate().isBefore(period.getEndDate());
    }

}
