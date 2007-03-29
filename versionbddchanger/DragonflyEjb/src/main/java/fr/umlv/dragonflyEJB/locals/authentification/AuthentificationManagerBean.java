package fr.umlv.dragonflyEJB.locals.authentification;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@LocalBinding(jndiBinding = "AuthentificationManager/local")
public class AuthentificationManagerBean implements AuthentificationManager {

	private final static fr.umlv.dragonflyBdd.AccountManager AM_MANAGER;

	static {
		AM_MANAGER = fr.umlv.dragonflyBdd.AccountManager.getAccountManagerInstance();
	}


	public boolean doesUserExist(String mail) throws DragonflyBddException {
		if(AM_MANAGER.getUser(mail) == null)
            return false;
        return true;
	}
	public boolean isAuthentificationCorrect(String email, String password) throws DragonflyBddException {
		return AM_MANAGER.isAuthentificationCorrect(email, password);
	}
}
