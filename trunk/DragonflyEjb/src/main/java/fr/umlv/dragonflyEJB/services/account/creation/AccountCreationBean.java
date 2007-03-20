package fr.umlv.dragonflyEJB.services.account.creation;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyEJB.managers.account.AccountManager;

@RemoteBinding(jndiBinding = "AccountCreation/remote")
public @Stateless class AccountCreationBean implements AccountCreation {

	@EJB
	private AccountManager managerLocal;
	
	public int createAccount(String email, String nickname, String password) {
		return managerLocal.createAccount(email, nickname, password);
	}

}
