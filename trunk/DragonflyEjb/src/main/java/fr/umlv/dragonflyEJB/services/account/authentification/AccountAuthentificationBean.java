package fr.umlv.dragonflyEJB.services.account.authentification;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyEJB.managers.account.AccountManager;
import fr.umlv.dragonflyEJB.services.account.authentification.AccountAuthentification;



@RemoteBinding(jndiBinding = "AccountAuthentification/remote")
public @Stateless class AccountAuthentificationBean implements AccountAuthentification {

	@EJB
	private AccountManager managerLocal;
	
	public boolean isAuthentificationCorrect(String email, String password) {
		System.out.println("*****************");
		return managerLocal.isAuthentificationCorrect(email, password);
	}
	
	public boolean doesUserExist(String mail) {
		return managerLocal.doesUserExist(mail);
	}

}
