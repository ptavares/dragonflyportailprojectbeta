package fr.umlv.dragonflyEJB.services.account.dropper;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyEJB.managers.account.AccountManager;
import fr.umlv.dragonflyEJB.services.account.dropper.AccountDropper;


@RemoteBinding(jndiBinding = "AccountDropper/remote")
public @Stateless class AccountDropperBean implements AccountDropper {
	
	@EJB
	private AccountManager managerLocal;
	
	public boolean removeAccount(String email) {
		return managerLocal.removeAccount(email);
	}

}
