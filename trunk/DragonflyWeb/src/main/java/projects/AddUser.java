package projects;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.account.adds.AccountAdds;
import fr.umlv.dragonflyEJB.services.account.authentification.AccountAuthentification;
import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;
import fr.umlv.dragonflyEJB.services.project.information.ProjectInformation;

public class AddUser extends ActionSupport {
	private String userMail;

	public String execute() throws Exception{
		String name = (String) ServletActionContext.getRequest().getSession().getAttribute("project");
		String nameAdmin = (String) ServletActionContext.getRequest().getSession().getAttribute("nom");

		System.out.println("Project Name AddUser.class : "+name);
		System.out.println("User Mail : "+ getUserMail());

		InitialContext ctx = new InitialContext();
		final AccountAuthentification account=(AccountAuthentification) ctx.lookup("AccountAuthentification/remote");
		final ProjectInformation ejb=(ProjectInformation) ctx.lookup("ProjectInformation/remote");

		if(account.doesUserExist(userMail)==false){
			addFieldError("adduserError", "Account does not exist");
			return "Error";
		}
		else{
			final ProjectAdds add=(ProjectAdds) ctx.lookup("ProjectAdds/remote");
			add.addUser(name, getUserMail());
			
			if(!nameAdmin.equals(name)){
				final AccountAdds add2=(AccountAdds) ctx.lookup("AccountAdds/remote");
				add2.addRole(getUserMail(), name+"user");
			}
			else{
				addFieldError("adduserError", "User could not be added");
				return "Error";
			}
		}
		addFieldError("adduserError", "User Successfully Add");
		return SUCCESS;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

}
