package fr.umlv.dragonflyEJB.services.account.authentification;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface AccountAuthentification {

	public boolean isAuthentificationCorrect(String email, String password)throws DragonflyBddException;
	
	public boolean doesUserExist(String mail)throws DragonflyBddException;
}
