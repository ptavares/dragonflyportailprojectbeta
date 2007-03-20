package fr.umlv.dragonflyEJB.services.account.modification;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyEJB.managers.account.AccountManager;
import fr.umlv.dragonflyEJB.services.account.modification.AccountModification;



@RemoteBinding(jndiBinding = "AccountModification/remote")
public @Stateless class AccountModificationBean implements AccountModification {

	@EJB
	private AccountManager managerLocal;
	
	public boolean changeNickmane(String email, String newNickName) {
		return managerLocal.changeNickmane(email, newNickName);
	}

	public boolean changePassword(String email, String newPassword) {
		return managerLocal.changePassword(email, newPassword);
	}

}
