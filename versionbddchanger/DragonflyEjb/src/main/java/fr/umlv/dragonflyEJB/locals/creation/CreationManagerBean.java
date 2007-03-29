package fr.umlv.dragonflyEJB.locals.creation;

import java.util.Date;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@LocalBinding(jndiBinding = "CreationManager/local")
public class CreationManagerBean implements CreationManager {
	private final static fr.umlv.dragonflyBdd.AccountManager AM_MANAGER;

	static {
		AM_MANAGER = fr.umlv.dragonflyBdd.AccountManager.getAccountManagerInstance();
	}

	private final static fr.umlv.dragonflyBdd.ProjectManager PM_MANAGER;
	
	static {
		PM_MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}
	
	public int createAccount(String email, String nickname, String password) throws DragonflyBddException {
		return AM_MANAGER.createAccount(email, nickname, password);
	}
	public int createProject(String name, String projectLeader, String resume) throws DragonflyBddException {
		return PM_MANAGER.createProject(name,projectLeader,resume);
	}
	public int createProject(String name, String projectLeader, String resume, Date endDate) throws DragonflyBddException {
		return PM_MANAGER.createProject(name, projectLeader,resume,endDate);
	}
	public int createProject(String name, String projectLeader, Date endDate, String resume, String description) throws DragonflyBddException {
		return PM_MANAGER.createProject(name, projectLeader,endDate,resume,description);
	}
}
