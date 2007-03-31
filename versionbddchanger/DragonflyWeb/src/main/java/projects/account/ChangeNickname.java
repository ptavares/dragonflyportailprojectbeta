package projects.account;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class ChangeNickname extends ActionSupport {
	private String newLogin=null;

	public String execute() {
		//System.out.println("Nickname :"+getNewLogin());
		if((newLogin == null) || (newLogin.length()==0)){
			return ERROR;
		}

		String oldNickname = (String) ServletActionContext.getRequest().getSession().getAttribute("nom");

		final InitialContext ctx;
		try {
			ctx = new InitialContext();
			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			
			boolean val = dEJB.changeNickmane(oldNickname, newLogin);
			
			if(val==false){
				addActionError(getText("change.changeNicknameError"));
				return ERROR;
			}
			addActionMessage(getText("change.changeNicknameErrorSuccess"));
			
		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		}
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
