package fr.umlv.dragonflyEJB.locals.authentification;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Local
public interface AuthentificationManager {

	public boolean isAuthentificationCorrect(String email, String password)throws DragonflyBddException;
	
	public boolean doesUserExist(String mail)throws DragonflyBddException;
}
