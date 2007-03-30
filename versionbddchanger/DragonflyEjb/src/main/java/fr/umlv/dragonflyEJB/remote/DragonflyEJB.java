package fr.umlv.dragonflyEJB.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.MessageBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;

@Remote
public interface DragonflyEJB {

	/*******************************************************************/
	/**           		         Account                              **/
	/********************************************************************/

	public int createAccount(String email, String nickname, String password)throws DragonflyBddException;

	public boolean removeAccount(String email)throws DragonflyBddException;

	public boolean changeNickmane(String email, String newNickName)throws DragonflyBddException;

	public boolean changePassword(String email, String newPassword)throws DragonflyBddException;

	public boolean isAuthentificationCorrect(String email, String password)throws DragonflyBddException;

	public boolean doesUserExist(String mail)throws DragonflyBddException;

	public String getUserNickname(String mail)throws DragonflyBddException;

	public boolean isPasswordCorrect(String mail, String passwd)throws DragonflyBddException;

	public boolean addRole(String user, String role)throws DragonflyBddException;

	public List<String> getUserRoles(String user)throws DragonflyBddException;

	public List<MessageBean> getMessages(String UserID)throws DragonflyBddException;

	public boolean createMessage(String sender, String addressee, String name, String content)throws DragonflyBddException;

	public boolean removeMessage(String user_id, long id)throws DragonflyBddException;

	public List<String> getActiveUsers() throws DragonflyBddException;

	public List<String> getNotActiveUsers() throws DragonflyBddException;

	public boolean activateUser(String email)throws DragonflyBddException;

	/*******************************************************************/
	/**           		         Project                              **/
	/********************************************************************/

	public boolean changeEndDate(String name, Date newEndDate)throws DragonflyBddException;

	public int createProject(String name,String projectLeader, String resume)throws DragonflyBddException;

	public int createProject(String name, String projectLeader, String resume,Date endDate)throws DragonflyBddException;

	public int createProject(String name, String projectLeader,Date endDate, String resume, String description)throws DragonflyBddException;

	public List<Project> getAllProject()throws DragonflyBddException;

	public boolean removeProject(String name)throws DragonflyBddException;

	public long addTask(String project, String author,String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end)throws DragonflyBddException;

	public List<TaskBean> getProjectTasks(String project)throws DragonflyBddException;

	public long addMeeting(String project, String author, String subj, String description, java.util.Date post, java.util.Date date)throws DragonflyBddException;

	public List<MeetingBean> getProjectMeetings(String project)throws DragonflyBddException;

	public long addNews(String project,  String author, java.util.Date post, String subj, String descr)throws DragonflyBddException;

	public List<NewsBean> getProjectNews(String name)throws DragonflyBddException;

	public boolean removeTask(String name, String task_id)throws DragonflyBddException;

	public boolean removeMeeting(String name, String meet_id)throws DragonflyBddException;

	public boolean addUserToProject(String project, String mail)throws DragonflyBddException;

	public boolean removeUserFromProject(String project, String mail)throws DragonflyBddException;

	public ProjectInformationsBean getProjectInformations(String project)throws DragonflyBddException;

	public boolean removeNews(String project, String news_id)throws DragonflyBddException;

	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException;

	public boolean removeQuestionResponse(String name, String qr_id) throws DragonflyBddException;

	public boolean editQuestionResponse(String name, String qr_id,String question, String response) throws DragonflyBddException;

	public List<QuestionResponseBean> getProjectFAQ(String project) throws DragonflyBddException;

	public boolean activateProject(String project) throws DragonflyBddException;

	public List<ProjectInformationsBean> getMyProject(String UserName) throws DragonflyBddException;

	public List<ProjectInformationsBean> getProjectNamesWithDescriptions() throws DragonflyBddException;

	public List<String> getActiveProject() throws DragonflyBddException;

	public List<String> getNotActiveProject() throws DragonflyBddException;

}
