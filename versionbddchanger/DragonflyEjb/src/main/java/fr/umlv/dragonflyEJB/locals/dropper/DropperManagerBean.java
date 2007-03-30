package fr.umlv.dragonflyEJB.locals.dropper;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import javax.ejb.Stateless;

@LocalBinding(jndiBinding = "DropperManager/local")
public @Stateless class DropperManagerBean implements DropperManager {
	private final static fr.umlv.dragonflyBdd.AccountManager AM_MANAGER;

	static {
		AM_MANAGER = fr.umlv.dragonflyBdd.AccountManager.getAccountManagerInstance();
	}

	private final static fr.umlv.dragonflyBdd.ProjectManager PM_MANAGER;
	
	static {
		PM_MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}
	
	public boolean removeAccount(String email) throws DragonflyBddException {
		return AM_MANAGER.removeAccount(email);
	}
	public boolean removeProject(String name) throws DragonflyBddException {
		return PM_MANAGER.removeProject(name);
	}
}
