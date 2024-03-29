package fr.umlv.dragonflyEJB.services.project.creation;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyEJB.managers.project.ProjectManager;

@RemoteBinding(jndiBinding = "ProjectCreation/remote")
public @Stateless class ProjectCreationBean implements ProjectCreation {

	@EJB
	private ProjectManager managerLocal;
	
	public int createProject(String name, String projectLeader, String resume) {
		return managerLocal.createProject(name, projectLeader, resume);
	}

	public int createProject(String name, String projectLeader, String resume, Date endDate) {
		return managerLocal.createProject(name, projectLeader, resume,endDate);
	}

	public int createProject(String name, String projectLeader, Date endDate, String resume, String description) {
		return managerLocal.createProject(name, projectLeader, endDate, resume, description);
	}

}
