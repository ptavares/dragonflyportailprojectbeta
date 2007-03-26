package fr.umlv.dragonflyEJB.services.project.dropper;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.managers.project.ProjectManager;

@RemoteBinding(jndiBinding = "ProjectDropper/remote")
public @Stateless class ProjectDropperBean implements ProjectDropper {
	
	@EJB
	private ProjectManager managerLocal;
	
	public boolean removeProject(String name)throws DragonflyBddException {
		return managerLocal.removeProject(name);
	}

}
