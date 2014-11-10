/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.books.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.convert.ConverterException;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.books.application.exception.PaymentFailedException;

import org.books.util.MessageFactory;

/**
 *
 * @author AWy
 */
@FacesValidator("org.books.converter.CreditCardNumberValidator")
public class CreditCardNumberValidator implements Validator {

    public static final String VALIDATOR_ID = "org.books.converter.CreditCardNumberValidator";
    public static final String VALIDATOR_INVALID_NUMBER = "org.books.converter.CreditCardNumberValidator.INVALID_NUMBER";
    public static final String VALIDATOR_NON_EXISTING_NUMBER = "org.books.converter.CreditCardNumberValidator.NOT_EXISTING_NUMBER";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	if (!(value instanceof String)) {
	    throwError(VALIDATOR_INVALID_NUMBER);
	}
	String val = (String) value;

	checkNumberFormat(val);
	checkLuhnDigit(val);
    }

    private void checkNumberFormat(String number) {
	Pattern pattern = Pattern.compile("^\\d{4}\\s?\\d{4}\\s?\\d{4}\\s?\\d{4}");
	Matcher matcher = pattern.matcher(number);
	if (!matcher.matches()) {
	    throwError(VALIDATOR_INVALID_NUMBER);
	}
    }

    private void checkLuhnDigit(String number) {
	int sum = 0;
	for (int i = 0; i < number.length(); i++) {
	    int d = Character.digit(number.charAt(i), 10);
	    if (i % 2 == number.length() % 2) {
		d += d < 5 ? d : (d - 9);
	    }
	    sum += d;
	}
	if (sum % 10 != 0) {
	    throwError(VALIDATOR_NON_EXISTING_NUMBER);
	}
    }

    private void throwError(String messageId) throws ConverterException {
	FacesMessage facesMessage = MessageFactory.getMessage(FacesMessage.SEVERITY_ERROR, messageId);
	throw new ValidatorException(facesMessage);
    }
}
