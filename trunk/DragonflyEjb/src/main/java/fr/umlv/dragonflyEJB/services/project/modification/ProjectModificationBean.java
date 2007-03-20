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

	public boolean changeEndDate(String name, Date newEndDate) {
		return managerLocal.changeEndDate(name, newEndDate);
	}

	public boolean removeTask(String name, String task_id) {
		return managerLocal.removeTask(name,task_id);
	}

	public boolean removeMeeting(String name, String meet_id) {
		return managerLocal.removeMeeting(name,meet_id);
	}
	
	
}
