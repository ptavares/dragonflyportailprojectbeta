package fr.umlv.dragonflyEJB.services.project.dropper;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface ProjectDropper {
	
	public boolean removeProject(String name)throws DragonflyBddException;

}
