package fr.umlv.dragonflyEJB.services.account.creation;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface AccountCreation {

	public int createAccount(String email, String nickname, String password)throws DragonflyBddException;
	
}
