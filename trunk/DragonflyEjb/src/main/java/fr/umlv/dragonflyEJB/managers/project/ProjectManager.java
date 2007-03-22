package fr.umlv.dragonflyEJB.managers.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.tables.Meeting;
import fr.umlv.dragonflyBdd.tables.News;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.Task;
import fr.umlv.dragonflyBdd.tables.User;

@Local
public interface ProjectManager {

	public boolean addUserToProject(String name, User user);

	public boolean changeEndDate(String name, Date newEndDate);

	public int createProject(String name,String projectLeader, String resume);

	public int createProject(String name, String projectLeader, String resume,Date endDate);

	public int createProject(String name, String projectLeader,Date endDate, String resume, String description);

	public List<Project> getAllProject();

	public boolean removeProject(String name);

	public long addTask(String project, String author,String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end);

	public List<List<String>> getProjectTasks(String project);

	public long addMeeting(String project, String author, String subj, String description, java.util.Date post, java.util.Date date);

	public List<List<String>> getProjectMeetings(String project);

	public long addNews(String project,  String author, java.util.Date post, String subj, String descr);

	public List<List<String>> getProjectNews(String name);

	public boolean removeTask(String name, String task_id);

	public boolean removeMeeting(String name, String meet_id);

	public boolean addUserToProject(String project, String mail);

	public String[][] getProjectUsers(String project);

	public Map<String, String> getProjectInformations(String project);

	public boolean removeNews(String project, String news_id);

}
