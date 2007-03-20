package fr.umlv.dragonflyEJB.managers.project;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.User;
import fr.umlv.dragonflyEJB.managers.project.ProjectManager;



@LocalBinding(jndiBinding = "ProjectManager/local")
public @Stateless class ProjectManagerBean implements ProjectManager {

	private final static fr.umlv.dragonflyBdd.ProjectManager MANAGER;
	
	static {
		MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}
	
	public boolean addMeeting(String name) {
		return MANAGER.addMeeting(name);
	}

	public boolean addNews(String name) {
		return MANAGER.addNews(name);
	}

	public boolean addTask(String name) {
		return MANAGER.addTask(name);
	}

	public boolean addUserToProject(String name, User user) {
		return MANAGER.addUserToProject(name, user);
	}

	public boolean changeEndDate(String name, Date newEndDate) {
		return MANAGER.changeEndDate(name, newEndDate);
	}

	public int createProject(String name,String projectLeader, String resume) {
		return MANAGER.createProject(name,projectLeader,resume);
	}

	public int createProject(String name, String projectLeader, String resume,Date endDate) {
		return MANAGER.createProject(name, projectLeader,resume,endDate);
	}

	public int createProject(String name, String projectLeader,Date endDate, String resume, String description) {
		return MANAGER.createProject(name, projectLeader,endDate,resume,description);
	}

	public List<Project> getAllProject() {
		return MANAGER.getAllProject();
	}

	public boolean removeProject(String name) {
		return MANAGER.removeProject(name);
	}

	public long addTask(String project, String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end) {
		return MANAGER.addTask(project,descr,subj,post,start,end);
	}

	public List<List<String>> getProjectTasks(String project) {
		return MANAGER.getProjectTasks(project);
	}

	public boolean addMeeting(String project, String subj, String description, java.util.Date date) {
		return MANAGER.addMeeting(project,subj,description,date);
	}

	public List<List<String>> getProjectMeetings(String project) {
		return MANAGER.getProjectMeetings(project);
	}

	public boolean addNews(String project, String subj, String descr) {
		// TODO Auto-generated method stub
		return MANAGER.addNews(project,subj,descr);
	}

	public List<List<String>> getProjectNews(String name) {
		return MANAGER.getProjectNews(name);
	}

	public boolean removeTask(String name, String task_id) {
		return MANAGER.removeTask(name, task_id);
	}

	public boolean removeMeeting(String name, String meet_id) {
		return MANAGER.removeMeeting(name,meet_id);
	}

	public boolean addUserToProject(String project, String mail) {
		return MANAGER.addUserToProject(project, mail);
	}

	public String[][] getProjectUsers(String project) {
		return MANAGER.getProjectUsers(project);
	}

	public Map<String, String> getProjectInformations(String project) {
		return MANAGER.getProjectInformations(project);
	}
}
