package projects;

import com.opensymphony.xwork2.ActionSupport;

public class LoginError extends ActionSupport {
	public String execute(){
		if(1==1){
		addFieldError("NoLogin", "Please Login First");
		return ("error");
		}
		return ("error");
	}
}
