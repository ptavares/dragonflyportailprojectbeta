package projects;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.modification.ProjectModification;

public class DeleteTask extends ActionSupport {
	private String[] delete;
	
	public String execute() throws Exception{
		//System.out.println("Delete Task + "+getDel());
		
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		//String project = ServletActionContext.getRequest().getParameter("project");
		System.out.println("Project Name : "+project);
		
		String[] tab = ServletActionContext.getRequest().getParameterValues("delete");
		if((tab == null)||(tab.length == 0)){
			System.out.println("DeleteTask.class : Tab Size null ou 0");
			return "rien";
		}
		
		final InitialContext ctx = new InitialContext();
		final ProjectModification proj=(ProjectModification) ctx.lookup("ProjectModification/remote");
	
		for(int i = 0; i<tab.length;i++){
			System.out.println("Before Remove");
			proj.removeTask(project, tab[i]);
			System.out.println("Task removed");
		}		
		return INPUT;
	}

	public String[] getDel() {
		return delete;
	}

	public void setDelete(String[] del) {
		this.delete = del;
	}
}
