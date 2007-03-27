package projects.account;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.account.modification.AccountModification;

public class ChangeNickname extends ActionSupport {
	private String newLogin=null;
	
	public String execute() throws Exception{
		System.out.println("Nickname :"+getNewLogin());
		if((newLogin == null) || (newLogin.length()==0))
		{
			return ERROR;
			
		}
		String oldNickname = (String) ServletActionContext.getRequest().getSession().getAttribute("nom");
		final InitialContext ctx = new InitialContext();
		AccountModification modif = (AccountModification)ctx.lookup("AccountModification/remote");
		boolean val = modif.changeNickmane(oldNickname, newLogin);
		if(val==false){
			addFieldError("changeError","");
			return ERROR;
		}
		addActionMessage(getText("nickname.success"));	
		return "changeOk";
	}

	public String goNickname(){
		return SUCCESS;
	}
	
	public String getNewLogin() {
		return newLogin;
	}


	public void setNewLogin(String newLogin) {
		this.newLogin = newLogin;
	}
	
	public String init(){
		return SUCCESS;
	}
	
}
