package fr.umlv.dragonflyEJB.services.project.creation;

import java.util.Date;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface ProjectCreation {
		
public int createProject(String name,String projectLeader, String resume)throws DragonflyBddException;

public int createProject(String name, String projectLeader, String resume,Date endDate)throws DragonflyBddException;

public int createProject(String name, String projectLeader,Date endDate, String resume, String description)throws DragonflyBddException;


}
