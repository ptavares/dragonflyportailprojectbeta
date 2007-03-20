package fr.umlv.dragonflyEJB.managers.project;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.User;

@Local
public interface ProjectManager {

	public int createProject(String name,String projectLeader, String resume);
	
	public int createProject(String name, String projectLeader, String resume,Date endDate);

	public int createProject(String name, String projectLeader,Date endDate, String resume, String description);
	
	public boolean removeProject(String name);
	
	public boolean changeEndDate(String name, Date newEndDate);
	
	public List<Project> getAllProject();
	
	public boolean addTask(String name);
	
	public boolean addMeeting(String name);
	
	public boolean addNews(String name);
	
	public boolean addUserToProject(String name, User user);

	public long addTask(String project, String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end);

	public List<List<String>> getProjectTasks(String project);

	public boolean addMeeting(String project, String subj, String description, java.util.Date date);

	public List<List<String>> getProjectMeetings(String project);

	public boolean addNews(String project, String subj, String descr);

	public List<List<String>> getProjectNews(String name);

	public boolean removeTask(String name, String task_id);

	public boolean removeMeeting(String name, String meet_id);
	
	public boolean addUserToProject(String project, String mail);

	public String[][] getProjectUsers(String project);

	public Map<String, String> getProjectInformations(String project);

}
