package fr.umlv.dragonflyEJB.services.account.information;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.managers.account.AccountManager;
import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;


@RemoteBinding(jndiBinding = "AccountInformation/remote")
public @Stateless class AccountInformationBean implements AccountInformation {

	@EJB
	private AccountManager managerLocal;

	public String getUserNickname(String mail)throws DragonflyBddException{
		return managerLocal.getUserNickname(mail);
	}

	public boolean isPasswordCorrect(String mail, String passwd) throws DragonflyBddException{
		return managerLocal.isPasswordCorrect(mail,passwd);
	}

	public List<String> getUserRoles(String user) throws DragonflyBddException{
		return managerLocal.getUserRoles(user);
	}
	
	public List<MessageEJB> getMessages(String UserID)throws DragonflyBddException{
		return managerLocal.getMessages(UserID);
	}
}
