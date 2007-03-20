package projects;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.account.modification.AccountModification;

public class ChangeEmail extends ActionSupport {
	private String newMail = null;
	
	public String execute() throws Exception{
		System.out.println("Nickname :"+getNewMail());
		if((newMail == null) || (newMail.length()==0))
			return INPUT;
		String oldMail = (String) ServletActionContext.getRequest().getSession().getAttribute("nom");
		final InitialContext ctx = new InitialContext();
		AccountModification modif = (AccountModification)ctx.lookup("AccountModification/remote");
		
		//TODO : Probleme avec le changement de mail. C'est peut �tre parce-que le mail constitue la cl�
		//primaire de la table. En tout cas, il n'arrive pas � changer le mail. Soit remplacer par un syst�me
		//qui copie les information de ce compte, puis l'efface et cr�e un nouveau, soit
		//virer completement l'option de changement de mail.
		
		/*boolean val = modif.changeMail(oldMail, getNewMail());
		if(val==false){
			System.out.println("Probleme changing e-mail");
			return ERROR;
		}*/			
		return SUCCESS;
	}

	public String goEmail(){
		System.out.println("Entering goEmail");
		return SUCCESS;
	}
	
	public String getNewMail() {
		return newMail;
	}

	public void setNewMail(String newMail) {
		this.newMail = newMail;
	}
}
