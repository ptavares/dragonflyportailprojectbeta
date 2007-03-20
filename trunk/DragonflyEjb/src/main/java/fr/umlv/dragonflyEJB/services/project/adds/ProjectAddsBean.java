package fr.umlv.dragonflyEJB.services.project.adds;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;


import fr.umlv.dragonflyBdd.tables.User;
import fr.umlv.dragonflyEJB.managers.project.ProjectManager;
import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;

@RemoteBinding(jndiBinding = "ProjectAdds/remote")
public @Stateless class ProjectAddsBean implements ProjectAdds {

	
	@EJB
	private ProjectManager managerLocal;
	
	public boolean addMeeting(String name) {
		return managerLocal.addMeeting(name);
	}

	public boolean addNews(String name) {
		return managerLocal.addNews(name);
	}

	public boolean addTask(String name) {
		return managerLocal.addTask(name);
	}

	public boolean addUserToProject(String name, User user) {
		return managerLocal.addUserToProject(name, user);
	}

	public long addTask(String project, String descr, String subj, Date post, Date start, Date end) {
		return managerLocal.addTask(project,descr,subj,post,start,end);
	}

	public boolean addMeeting(String project, String subj, String description, Date date) {
		System.out.println("Remote Bean");
		return managerLocal.addMeeting(project, subj,description,date);
	}

	public boolean addNews(String project, String subj, String descr) {
		return managerLocal.addNews(project, subj,descr);
	}
	
	public boolean addUser(String project, String mail) {
		return managerLocal.addUserToProject(project, mail);
	}
	
}
