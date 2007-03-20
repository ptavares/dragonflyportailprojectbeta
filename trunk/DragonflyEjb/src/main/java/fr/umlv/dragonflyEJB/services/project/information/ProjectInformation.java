package fr.umlv.dragonflyEJB.services.project.information;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.tables.Project;

@Remote
public interface ProjectInformation {
	
	public List<Project> getAllProject();
	
	public List<String> getAllProjectsNames();
	
	public Map<String,String> getProjectNamesWithDescriptions();
	
	public List<List<String>> getProjectTasks(String project);
	
	public List<List<String>> getProjectMeetings(String project);
	
	public List<List<String>> getProjectNews(String name);

	public Map<String, String> getProjectInformations(String proName);
	
}