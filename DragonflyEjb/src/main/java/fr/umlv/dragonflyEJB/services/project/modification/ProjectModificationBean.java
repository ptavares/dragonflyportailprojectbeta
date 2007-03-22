package fr.umlv.dragonflyEJB.services.project.modification;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyEJB.managers.project.ProjectManager;
import fr.umlv.dragonflyEJB.services.project.modification.ProjectModification;

@RemoteBinding(jndiBinding = "ProjectModification/remote")
public @Stateless class ProjectModificationBean implements ProjectModification {

	@EJB
	private ProjectManager managerLocal;

	public boolean changeEndDate(String project, Date newEndDate) {
		return managerLocal.changeEndDate(project, newEndDate);
	}

	public boolean removeTask(String project, String task_id) {
		return managerLocal.removeTask(project,task_id);
	}

	public boolean removeMeeting(String project, String meet_id) {
		return managerLocal.removeMeeting(project,meet_id);
	}

	public boolean removeNews(String project, String news_id) {
		return managerLocal.removeNews(project,news_id);
	}
	
	
}
