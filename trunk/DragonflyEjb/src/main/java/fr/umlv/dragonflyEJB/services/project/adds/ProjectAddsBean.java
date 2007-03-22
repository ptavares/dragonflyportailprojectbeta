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
	
	public boolean addUserToProject(String name, User user) {
		return managerLocal.addUserToProject(name, user);
	}

	public long addTask(String project, String author, String descr, String subj, Date post, Date start, Date end) {
		return managerLocal.addTask(project,author,descr,subj,post,start,end);
	}

	public long addMeeting(String project, String author, String subj, String description, Date post, Date date) {
		return managerLocal.addMeeting(project, author, subj, description, post, date);
	}

	public long addNews(String project, String author, Date post, String subj, String descr) {
		return managerLocal.addNews(project, author, post, subj, descr);
	}
	
	public boolean addUser(String project, String mail) {
		return managerLocal.addUserToProject(project, mail);
	}
	
}
