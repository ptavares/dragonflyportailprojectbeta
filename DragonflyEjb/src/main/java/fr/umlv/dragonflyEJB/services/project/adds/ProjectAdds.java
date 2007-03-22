package fr.umlv.dragonflyEJB.services.project.adds;

import java.util.Date;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.tables.User;

@Remote
public interface ProjectAdds {
	
	public boolean addUserToProject(String name, User user);

	public long addTask(String project, String author, String descr, String subj, Date post, Date start, Date end);

	public long addMeeting(String project, String author, String subj, String description, Date post, Date date);

	public long addNews(String project, String author, Date post, String subj, String descr);
	
	public boolean addUser(String project, String mail);
}
