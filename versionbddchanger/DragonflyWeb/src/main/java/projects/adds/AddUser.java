package projects.adds;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class AddUser extends ActionSupport {
	private String userMail;

	public String execute() {
		String name = (String) ServletActionContext.getRequest().getSession().getAttribute("project");

		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");

			if(dEJB.doesUserExist(userMail)==false){
				addActionError(getText("administration.addUser.accountError"));
				return "Error";
			}

			//Return false -> User already in Project (available for admin)
			if(!dEJB.addUserToProject(name, getUserMail())){
				addActionError(getText("administration.addUser.userAlreadyInProject"));
				return "Error";
			}
			
			if(!dEJB.addRole(getUserMail(), name+"user"))
				if(!dEJB.removeUserFromProject(name, getUserMail())){
					addActionError(getText("administration.addUser.addError"));
					return "Error";
				}
			
			addActionMessage(getText("administration.addUser.succes"));

		} catch (NamingException e) {
			// TODO REDIRECTION ERREUR EJB
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			// TODO REDIRECTION ERREUR BDD
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

}
