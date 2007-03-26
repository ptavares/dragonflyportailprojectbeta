package fr.umlv.dragonflyEJB.services.project.adds;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.managers.project.ProjectManager;

@RemoteBinding(jndiBinding = "ProjectAdds/remote")
public @Stateless class ProjectAddsBean implements ProjectAdds {

	
	@EJB
	private ProjectManager managerLocal;
	
	public long addTask(String project, String author, String descr, String subj, Date post, Date start, Date end) throws DragonflyBddException {
		return managerLocal.addTask(project,author,descr,subj,post,start,end);
	}

	public long addMeeting(String project, String author, String subj, String description, Date post, Date date) throws DragonflyBddException {
		return managerLocal.addMeeting(project, author, subj, description, post, date);
	}

	public long addNews(String project, String author, Date post, String subj, String descr) throws DragonflyBddException {
		return managerLocal.addNews(project, author, post, subj, descr);
	}
	
	public boolean addUser(String project, String mail) throws DragonflyBddException {
		return managerLocal.addUserToProject(project, mail);
	}

	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException {
		return managerLocal.addQuestionResponse(proj, question, response);
	}
	
}
