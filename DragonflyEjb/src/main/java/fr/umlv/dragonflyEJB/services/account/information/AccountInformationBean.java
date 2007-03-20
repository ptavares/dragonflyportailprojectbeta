package fr.umlv.dragonflyEJB.services.account.information;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyEJB.managers.account.AccountManager;
import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;
import fr.umlv.dragonflyEJB.services.account.modification.AccountModification;


@RemoteBinding(jndiBinding = "AccountInformation/remote")
public @Stateless class AccountInformationBean implements AccountInformation {

	@EJB
	private AccountManager managerLocal;

	public String getUserNickname(String mail){
		return managerLocal.getUserNickname(mail);
	}

	public boolean isPasswordCorrect(String mail, String passwd) {
		return managerLocal.isPasswordCorrect(mail,passwd);
	}

	public List<String> getUserRoles(String user) {
		return managerLocal.getUserRoles(user);
	}
	
	public List<MessageEJB> getMessages(String UserID){
		return managerLocal.getMessages(UserID);
	}
}
