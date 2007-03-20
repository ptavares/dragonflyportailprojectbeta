package projects;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import projects.beans.ProjectWithResume;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.information.ProjectInformation;
public class PopProject  extends ActionSupport{
	
	
	private ArrayList<ProjectWithResume> projects=new ArrayList<ProjectWithResume>();
	
	
 public String execute() throws NamingException  {
	 final InitialContext ctx = new InitialContext();
	 final ProjectInformation pi=(ProjectInformation) ctx.lookup("ProjectInformation/remote");
	 
	 Map<String, String> map = pi.getProjectNamesWithDescriptions();
	 Set<Entry<String, String>> set = map.entrySet();
	 for(Entry<String, String> entry : set){
		 ProjectWithResume p=new ProjectWithResume(entry.getKey(),entry.getValue());
		 projects.add(p);
	 }
	 
	 return SUCCESS;
 }


public ArrayList<ProjectWithResume> getProjects() {
	return projects;
}


public void setProjects(ArrayList<ProjectWithResume> projects) {
	this.projects = projects;
}

}
