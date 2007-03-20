package fr.umlv.dragonflyEJB.services.project.creation;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface ProjectCreation {
		
public int createProject(String name,String projectLeader, String resume);

public int createProject(String name, String projectLeader, String resume,Date endDate);

public int createProject(String name, String projectLeader,Date endDate, String resume, String description);


}
