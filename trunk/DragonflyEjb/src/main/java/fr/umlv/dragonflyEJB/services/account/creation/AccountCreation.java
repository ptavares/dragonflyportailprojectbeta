package fr.umlv.dragonflyEJB.services.account.creation;

import javax.ejb.Remote;

@Remote
public interface AccountCreation {

	public int createAccount(String email, String nickname, String password);
	
}
