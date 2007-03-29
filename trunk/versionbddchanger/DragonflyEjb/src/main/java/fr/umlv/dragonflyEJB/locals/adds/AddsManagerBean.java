package fr.umlv.dragonflyEJB.locals.adds;

import java.util.Date;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@LocalBinding(jndiBinding = "AddsManager/local")
public class AddsManagerBean implements AddsManager {

	private final static fr.umlv.dragonflyBdd.AccountManager AM_MANAGER;

	static {
		AM_MANAGER = fr.umlv.dragonflyBdd.AccountManager.getAccountManagerInstance();
	}

	private final static fr.umlv.dragonflyBdd.ProjectManager PM_MANAGER;
	
	static {
		PM_MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}
	
	
	public long addMeeting(String project, String author, String subj, String description, Date post, Date date) throws DragonflyBddException {
		return PM_MANAGER.addMeeting(project, author, subj, description, post, date);
	}
	public long addNews(String project, String author, Date post, String subj, String descr) throws DragonflyBddException {
		return PM_MANAGER.addNews(project, author, post, subj, descr);
	}
	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException {
		return PM_MANAGER.addQuestionResponse(proj, question, response);
	}
	public boolean addRole(String user, String role) throws DragonflyBddException {
		return AM_MANAGER.addRole(user,role);
	}
	public boolean addUser(String project, String mail) throws DragonflyBddException {
		return PM_MANAGER.addUserToProject(project, mail);
	}
	public long addTask(String project, String author, String descr, String subj, Date post, Date start, Date end) throws DragonflyBddException {
		return PM_MANAGER.addTask(project, author,descr,subj,post,start,end);
	}
	public boolean createMessage(String sender, String addressee, String name, String content) throws DragonflyBddException {
		return AM_MANAGER.CreateMessage(sender, addressee, name, content);
	}
	

}
