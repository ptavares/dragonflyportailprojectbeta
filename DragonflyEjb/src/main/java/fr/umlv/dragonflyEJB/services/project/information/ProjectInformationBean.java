package fr.umlv.dragonflyEJB.services.project.information;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyEJB.managers.project.ProjectManager;

@RemoteBinding(jndiBinding = "ProjectInformation/remote")
public @Stateless class ProjectInformationBean implements ProjectInformation {

	@EJB
	private ProjectManager managerLocal;
	
	public List<Project> getAllProject() {
		return managerLocal.getAllProject();
	}

	public List<String> getAllProjectsNames() {
		List<Project> list =getAllProject();
		List<String> ls =new ArrayList<String>();
		
		for(Project pr : list){
			ls.add(pr.getName());
		}
		return ls;
	}


	public Map<String, String> getProjectNamesWithDescriptions() {
		List<Project> list = getAllProject();
		Map<String,String> map = new LinkedHashMap<String, String>();
		for(Project pr : list){	
//			System.out.println("-------> list des projets ");
//			System.out.println(pr.getName()+" "+pr.getDescription().getResume());
			map.put(pr.getName(), pr.getDescription().getResume());
		}
		return map;
	}

	public List<List<String>> getProjectTasks(String project) {
		return managerLocal.getProjectTasks(project);
	}

	public List<List<String>> getProjectMeetings(String project) {
		return managerLocal.getProjectMeetings(project);
	}

	public List<List<String>> getProjectNews(String name) {
		return managerLocal.getProjectNews(name);
	}

	public Map<String, String> getProjectInformations(String project) {
		return managerLocal.getProjectInformations(project);		
	}

}
