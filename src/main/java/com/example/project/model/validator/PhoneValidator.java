package com.example.project.model.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private static final Pattern SPECIAL_CHARS = Pattern.compile("[\\s().+-]|ext", Pattern.CASE_INSENSITIVE);
	private static final Pattern DIGITS = Pattern.compile("[0-9]{7,15}");

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		if (phone == null || phone.isEmpty()) {
			return true; // Let @NotNull/@NotEmpty handle this.
		}

		return isValid(phone);
	}

	public static boolean isValid(String phone) {
		String digits = SPECIAL_CHARS.matcher(phone).replaceAll("");
		return DIGITS.matcher(digits).matches();
	}

}
