package fr.umlv.dragonflyEJB.services.account.authentification;

import javax.ejb.Remote;

@Remote
public interface AccountAuthentification {

	public boolean isAuthentificationCorrect(String email, String password);
	
	public boolean doesUserExist(String mail);
}
