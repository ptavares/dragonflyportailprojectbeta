package projects;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.modification.ProjectModification;

public class DeleteMeeting extends ActionSupport {
	private String[] deleteMeet;
	
	public String execute() throws Exception{
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		System.out.println("Project Name : "+project);
		
		String[] tab = ServletActionContext.getRequest().getParameterValues("deleteMeet");
		if((tab == null)||(tab.length==0)){
			System.out.println("DeleteMeeting.class : getPaarametersValues null ou 0");
			return "rien";
		}
		setDeleteMeet(tab);
		
		final InitialContext ctx = new InitialContext();
		final ProjectModification proj=(ProjectModification) ctx.lookup("ProjectModification/remote");
		
		for(int i = 0; i<tab.length;i++){
			System.out.println("Before Remove");
			proj.removeMeeting(project, tab[i]);
			System.out.println("Task removed");
		}
		
		return INPUT;
	}

	public String[] getDeleteMeet() {
		return deleteMeet;
	}

	public void setDeleteMeet(String[] deleteMeet) {
		this.deleteMeet = deleteMeet;
	}
}
