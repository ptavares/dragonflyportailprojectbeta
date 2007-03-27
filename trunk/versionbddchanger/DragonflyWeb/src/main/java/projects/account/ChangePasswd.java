package projects.account;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.account.information.AccountInformation;
import fr.umlv.dragonflyEJB.services.account.modification.AccountModification;

public class ChangePasswd extends ActionSupport {
	private String oldPasswd;
	private String newPasswd;
	private String confirmPasswd;
	
	
	public String execute() throws Exception{
		System.out.println("test");
		String mail = (String) ServletActionContext.getRequest().getSession().getAttribute("nom");
		if(isValid(oldPasswd)==false) {
			addFieldError("changeError","oldPasswd Invalid");
			System.out.println("yes la dedans");
			return "changFaild";
			}
		if(isValid(newPasswd)==false) {
			addFieldError("changeError","newPasswd Invalid");
			
			return "changFaild";
			}
		if(isValid(confirmPasswd)==false) {
			addFieldError("changeError","confirmPasswd Invalid");
			
			return "changFaild";
			}
		
		System.out.println("old password : "+ getOldPasswd());
		System.out.println("new password : "+ getNewPasswd());
		System.out.println("confirm password : "+ getConfirmPasswd());
		final InitialContext ctx = new InitialContext();
		final AccountInformation info = (AccountInformation)ctx.lookup("AccountInformation/remote");
		boolean comp = info.isPasswordCorrect(mail, oldPasswd);
		if(comp == false){
			addFieldError("changeError",getText("passwdChange.incorrect"));
			return "changFaild";
		}
		if(newPasswd.compareTo(confirmPasswd)!=0){
			addFieldError("changeError",getText("passwdChange.notMatch"));
			return "changFaild";
		}
		final AccountModification modif = (AccountModification)ctx.lookup("AccountModification/remote");
		boolean b = modif.changePassword(mail, newPasswd);
		if(b==false){
			addFieldError("changeError",getText("passwdChange.probleme"));
			return "changFaild";
		}
		addActionMessage(getText("passwdChange.success"));		
		return "changeOk";
	}

	public String goPasswd(){
		return SUCCESS;
	}
	
	private boolean isValid(String pass){
		if((pass == null) || (pass.length()==0))
			return false;
		return true;
	}
	
	
	public String getConfirmPasswd() {
		return confirmPasswd;
	}


	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}


	public String getNewPasswd() {
		return newPasswd;
	}


	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}


	public String getOldPasswd() {
		return oldPasswd;
	}


	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}
	
}
