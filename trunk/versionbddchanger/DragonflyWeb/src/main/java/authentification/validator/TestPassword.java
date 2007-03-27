package authentification.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.ValidatorContext;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class TestPassword extends FieldValidatorSupport {


	private String password;
	private String confirmPassword;


	public void validate(Object object) throws ValidationException {
		
		String fieldname = getFieldName();
		
		password = (String) getFieldValue("password", object);
		confirmPassword = (String) getFieldValue("confirmPassword", object);
	
		String message = getMessage(getMessageKey());
		
		ValidatorContext ctxt = getValidatorContext();
		
		if(password == null || confirmPassword == null)
			return;
		
		if(!password.equals(confirmPassword)){
			ctxt.addFieldError(fieldname, message);
			return;
		}
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
