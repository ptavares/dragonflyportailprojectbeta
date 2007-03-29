package fr.umlv.dragonflyEJB.locals.modification;

import java.util.Date;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@LocalBinding(jndiBinding = "ModificationManager/local")
public class ModificationManagerBean implements ModificationManager {
	private final static fr.umlv.dragonflyBdd.AccountManager AM_MANAGER;

	static {
		AM_MANAGER = fr.umlv.dragonflyBdd.AccountManager.getAccountManagerInstance();
	}

	private final static fr.umlv.dragonflyBdd.ProjectManager PM_MANAGER;

	static {
		PM_MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}

	public boolean activateAccount(String email) throws DragonflyBddException {
		return AM_MANAGER.activateAccount(email);
	}
	public boolean activateProject(String name) throws DragonflyBddException {
		return PM_MANAGER.activateProject(name);
	}
	public boolean changeEndDate(String name, Date newEndDate) throws DragonflyBddException {
		return PM_MANAGER.changeEndDate(name, newEndDate);
	}
	public boolean changeNickmane(String email, String newNickName) throws DragonflyBddException {
		return AM_MANAGER.changeNickmane(email, newNickName);
	}
	public boolean changePassword(String email, String newPassword) throws DragonflyBddException {
		return AM_MANAGER.changePassword(email, newPassword);
	}
	public boolean editQuestionResponse(String name, String qr_id, String question, String response) throws DragonflyBddException {
		return PM_MANAGER.editQuestionResponse(name, qr_id, question, response);
	}
	public boolean removeMeeting(String name, String meet_id) throws DragonflyBddException {
		return PM_MANAGER.removeMeeting(name,meet_id);
	}
	public boolean removeMessage(String user_id, long id) throws DragonflyBddException {
		return AM_MANAGER.RemoveMessage(user_id, id);
	}
	public boolean removeNews(String project, String news_id) throws DragonflyBddException {
		return PM_MANAGER.removeNews(project,news_id);
	}
	public boolean removeQuestionResponse(String name, String qr_id) throws DragonflyBddException {
		return PM_MANAGER.removeQuestionResponse(name, qr_id);
	}
	public boolean removeTask(String name, String task_id) throws DragonflyBddException {
		return PM_MANAGER.removeTask(name, task_id);
	}
	public boolean removeUserFromProject(String project, String mail) throws DragonflyBddException {
		return PM_MANAGER.removeUserFromProject(project, mail);
	}
}
