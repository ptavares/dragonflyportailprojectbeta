package fr.umlv.dragonflyEJB.services.account.dropper;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.managers.account.AccountManager;


@RemoteBinding(jndiBinding = "AccountDropper/remote")
public @Stateless class AccountDropperBean implements AccountDropper {
	
	@EJB
	private AccountManager managerLocal;
	
	public boolean removeAccount(String email) throws DragonflyBddException{
		return managerLocal.removeAccount(email);
	}

}
