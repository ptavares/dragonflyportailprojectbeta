package fr.umlv.dragonflyEJB.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.MessageBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;
import fr.umlv.dragonflyEJB.locals.adds.AddsManager;
import fr.umlv.dragonflyEJB.locals.authentification.AuthentificationManager;
import fr.umlv.dragonflyEJB.locals.creation.CreationManager;
import fr.umlv.dragonflyEJB.locals.dropper.DropperManager;
import fr.umlv.dragonflyEJB.locals.information.InformationManager;
import fr.umlv.dragonflyEJB.locals.modification.ModificationManager;

@RemoteBinding(jndiBinding = "DragonflyEJB/remote")
public class DragonflyEJBBean implements DragonflyEJB {

	@EJB
	private AddsManager addsManager;
	@EJB
	private AuthentificationManager authentificationManager;
	@EJB
	private CreationManager creationManager;
	@EJB
	private DropperManager dropperManager;
	@EJB
	private InformationManager informationManager;
	@EJB
	private ModificationManager modificationManager;

	public boolean activateProject(String project) throws DragonflyBddException {
		return modificationManager.activateProject(project);
	}
	public boolean activateUser(String email) throws DragonflyBddException {
		return modificationManager.activateAccount(email);
	}
	public long addMeeting(String project, String author, String subj, String description, Date post, Date date) throws DragonflyBddException {
		return addsManager.addMeeting(project, author, subj, description, post, date);
	}
	public long addNews(String project, String author, Date post, String subj, String descr) throws DragonflyBddException {
		return addsManager.addNews(project, author, post, subj, descr);
	}
	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException {
		return addsManager.addQuestionResponse(proj, question, response);
	}
	public boolean addRole(String user, String role) throws DragonflyBddException {
		return addsManager.addRole(user, role);
	}
	public long addTask(String project, String author, String descr, String subj, Date post, Date start, Date end) throws DragonflyBddException {
		return addsManager.addTask(project, author, descr, subj, post, start, end);
	}
	public boolean addUserToProject(String project, String mail) throws DragonflyBddException {
		return addsManager.addUser(project, mail);
	}
	public boolean changeEndDate(String name, Date newEndDate) throws DragonflyBddException {
		return modificationManager.changeEndDate(name, newEndDate);
	}
	public boolean changeNickmane(String email, String newNickName) throws DragonflyBddException {
		return modificationManager.changeNickmane(email, newNickName);
	}
	public boolean changePassword(String email, String newPassword) throws DragonflyBddException {
		return modificationManager.changePassword(email, newPassword);
	}
	public int createAccount(String email, String nickname, String password) throws DragonflyBddException {
		return creationManager.createAccount(email, nickname, password);
	}
	public boolean createMessage(String sender, String addressee, String name, String content) throws DragonflyBddException {
		return addsManager.createMessage(sender, addressee, name, content);
	}
	public int createProject(String name, String projectLeader, String resume) throws DragonflyBddException {
		return creationManager.createProject(name, projectLeader, resume);
	}
	public int createProject(String name, String projectLeader, String resume, Date endDate) throws DragonflyBddException {
		return creationManager.createProject(name, projectLeader, resume, endDate);
	}
	public int createProject(String name, String projectLeader, Date endDate, String resume, String description) throws DragonflyBddException {
		return creationManager.createProject(name, projectLeader, endDate, resume, description);
	}
	public boolean doesUserExist(String mail) throws DragonflyBddException {
		return authentificationManager.doesUserExist(mail);
	}
	public boolean editQuestionResponse(String name, String qr_id, String question, String response) throws DragonflyBddException {
		return modificationManager.editQuestionResponse(name, qr_id, question, response);
	}
	public List<Project> getAllProject() throws DragonflyBddException {
		return informationManager.getAllProject();
	}
	
	public List<MessageBean> getMessages(String UserID) throws DragonflyBddException {
		return informationManager.getMessages(UserID);
	}
	public List<QuestionResponseBean> getProjectFAQ(String project) throws DragonflyBddException {
		return informationManager.getProjectFAQ(project);
	}
	public ProjectInformationsBean getProjectInformations(String project) throws DragonflyBddException {
		return informationManager.getProjectInformations(project);
	}
	public List<MeetingBean> getProjectMeetings(String project) throws DragonflyBddException {
		return informationManager.getProjectMeetings(project);
	}
	public List<NewsBean> getProjectNews(String name) throws DragonflyBddException {
		return informationManager.getProjectNews(name);
	}
	public List<TaskBean> getProjectTasks(String project) throws DragonflyBddException {
		return informationManager.getProjectTasks(project);
	}
	public String getUserNickname(String mail) throws DragonflyBddException {
		return informationManager.getUserNickname(mail);
	}
	public List<String> getUserRoles(String user) throws DragonflyBddException {
		return informationManager.getUserRoles(user);
	}
	public boolean isAuthentificationCorrect(String email, String password) throws DragonflyBddException {
		return authentificationManager.isAuthentificationCorrect(email, password);
	}
	public boolean isPasswordCorrect(String mail, String passwd) throws DragonflyBddException {
		return authentificationManager.isAuthentificationCorrect(mail, passwd);
	}
	public boolean removeAccount(String email) throws DragonflyBddException {
		return dropperManager.removeAccount(email);
	}
	public boolean removeMeeting(String name, String meet_id) throws DragonflyBddException {
		return modificationManager.removeMeeting(name, meet_id);
	}
	public boolean removeMessage(String user_id, long id) throws DragonflyBddException {
		return modificationManager.removeMessage(user_id, id);
	}
	public boolean removeNews(String project, String news_id) throws DragonflyBddException {
		return modificationManager.removeNews(project, news_id);
	}
	public boolean removeProject(String name) throws DragonflyBddException {
		return dropperManager.removeProject(name);
	}
	public boolean removeQuestionResponse(String name, String qr_id) throws DragonflyBddException {
		return modificationManager.removeQuestionResponse(name, qr_id);
	}
	public boolean removeTask(String name, String task_id) throws DragonflyBddException {
		return modificationManager.removeTask(name, task_id);
	}
	public boolean removeUserFromProject(String project, String mail) throws DragonflyBddException {
		return modificationManager.removeUserFromProject(project, mail);
	}
	public List<String> getActiveUsers() throws DragonflyBddException {
		return informationManager.getActiveUsers();
	}
	public List<String> getNotActiveUsers() throws DragonflyBddException {
		return informationManager.getNotActiveUsers();
	}


}
