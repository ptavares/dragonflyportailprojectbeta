package fr.umlv.dragonflyEJB.services.account.modification;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.managers.account.AccountManager;



@RemoteBinding(jndiBinding = "AccountModification/remote")
public @Stateless class AccountModificationBean implements AccountModification {

	@EJB
	private AccountManager managerLocal;
	
	public boolean changeNickmane(String email, String newNickName) throws DragonflyBddException{
		return managerLocal.changeNickmane(email, newNickName);
	}

	public boolean changePassword(String email, String newPassword) throws DragonflyBddException{
		return managerLocal.changePassword(email, newPassword);
	}

}
