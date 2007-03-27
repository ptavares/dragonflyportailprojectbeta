package fr.umlv.dragonflyEJB.managers.project;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;

@Local
public interface ProjectManager {

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

	public ProjectInformationsBean getProjectInformations(String project)throws DragonflyBddException;

	public boolean removeNews(String project, String news_id)throws DragonflyBddException;

	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException;

	public boolean removeQuestionResponse(String name, String qr_id) throws DragonflyBddException;
	
	public boolean editQuestionResponse(String name, String qr_id,String question, String response) throws DragonflyBddException;

	public List<QuestionResponseBean> getProjectFAQ(String project) throws DragonflyBddException;
        
        public boolean activateProject(String project) throws DragonflyBddException;

}
