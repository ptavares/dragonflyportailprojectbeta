package fr.umlv.dragonflyEJB.services.project.adds;

import java.util.Date;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.tables.User;

@Remote
public interface ProjectAdds {

	public boolean addTask(String name);
	
	public boolean addMeeting(String name);
	
	public boolean addNews(String name);
	
	public boolean addUserToProject(String name, User user);
	
	public long addTask(String project,String descr, String subj, Date post,Date start,Date end);
	
	public boolean addMeeting(String project,String subj, String description, Date date);

	public boolean addNews(String project, String subj, String descr);
	
	public boolean addUser(String project, String name);
	
}
