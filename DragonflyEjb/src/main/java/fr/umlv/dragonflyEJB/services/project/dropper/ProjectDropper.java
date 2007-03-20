package fr.umlv.dragonflyEJB.services.project.dropper;

import javax.ejb.Remote;

@Remote
public interface ProjectDropper {
	
	public boolean removeProject(String name);

}
