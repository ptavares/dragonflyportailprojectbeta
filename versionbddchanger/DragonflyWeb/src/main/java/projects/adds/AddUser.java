package projects.adds;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.services.account.adds.AccountAdds;
import fr.umlv.dragonflyEJB.services.account.authentification.AccountAuthentification;
import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;

public class AddUser extends ActionSupport {
	private String userMail;

	public String execute() {
		String name = (String) ServletActionContext.getRequest().getSession().getAttribute("project");

		InitialContext ctx;
		try {
			ctx = new InitialContext();

			final AccountAuthentification account=(AccountAuthentification) ctx.lookup("AccountAuthentification/remote");

			if(account.doesUserExist(userMail)==false){
				addActionError(getText("administration.addUser.accountError"));
				return "Error";
			}

			final ProjectAdds add=(ProjectAdds) ctx.lookup("ProjectAdds/remote");
			
			//Return false -> User already in Project (available for admin)
			if(!add.addUser(name, getUserMail())){
				addActionError(getText("administration.addUser.userAlreadyInProject"));
				return "Error";
			}
			
			final AccountAdds add2=(AccountAdds) ctx.lookup("AccountAdds/remote");
			//TODO : TESTER Si l'AJOUT EST OK sinon le supprimer du projet !!
			add2.addRole(getUserMail(), name+"user");
			
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
