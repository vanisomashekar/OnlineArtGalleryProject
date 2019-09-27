package com.profile.login.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.profile.login.beans.Message;

public class MessageValidation  implements Validator{
	public boolean supports(Class<?> arg0) {
		return Message.class.equals(arg0);
	}


	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
	}
}
