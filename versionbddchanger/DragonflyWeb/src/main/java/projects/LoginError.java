package projects;

import com.opensymphony.xwork2.ActionSupport;

public class LoginError extends ActionSupport {
	public String execute(){
		if(1==1){
			addActionError(getText("login.nologin"));
			return ("error");
		}
		return ("error");
	}
}
