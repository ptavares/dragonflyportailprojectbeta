package projects;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.modification.ProjectModification;

public class DeleteMeeting extends ActionSupport {
	private String delete;
	private String ProName;
	
	public String execute() throws Exception{
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		System.out.println("Project Name : "+project);
		
		final InitialContext ctx = new InitialContext();
		final ProjectModification proj=(ProjectModification) ctx.lookup("ProjectModification/remote");
		
		proj.removeMeeting(getProName(), getDelete());
		
		return INPUT;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getProName() {
		return ProName;
	}

	public void setProName(String proName) {
		ProName = proName;
	}
}
