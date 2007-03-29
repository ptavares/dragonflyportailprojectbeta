package fr.umlv.dragonflyEJB.locals.information;

import java.util.List;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.MessageBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;

@Local
public interface InformationManager {

	/*
	 * Account
	 */
	public String getUserNickname(String mail)throws DragonflyBddException;

	public boolean isPasswordCorrect(String mail, String passwd)throws DragonflyBddException;

	public List<String> getUserRoles(String user)throws DragonflyBddException;

	public List<MessageBean> getMessages(String UserID)throws DragonflyBddException;

	public List<String> getActiveUsers()throws DragonflyBddException;

	public List<String> getNotActiveUsers()throws DragonflyBddException;

	/*
	 * Project
	 */
	public List<Project> getAllProject()throws DragonflyBddException;

	public List<String> getAllProjectsNames()throws DragonflyBddException;

	public List<ProjectInformationsBean> getProjectNamesWithDescriptions()throws DragonflyBddException;

	public List<TaskBean> getProjectTasks(String project)throws DragonflyBddException;

	public List<MeetingBean> getProjectMeetings(String project)throws DragonflyBddException;

	public List<NewsBean> getProjectNews(String name)throws DragonflyBddException;

	public ProjectInformationsBean getProjectInformations(String proName)throws DragonflyBddException;

	public List<QuestionResponseBean> getProjectFAQ(String project) throws DragonflyBddException;

	public List<String> getActiveProject() throws DragonflyBddException;

	public List<String> getNotActiveProject() throws DragonflyBddException;

}
