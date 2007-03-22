package projects;

import javax.naming.InitialContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.modification.ProjectModification;

public class DeleteTask extends ActionSupport {
	private String delete;
	private String ProName;
	
	public String execute() throws Exception{			
		final InitialContext ctx = new InitialContext();
		final ProjectModification proj=(ProjectModification) ctx.lookup("ProjectModification/remote");
	
		System.out.println("Before Remove");
		proj.removeTask(getProName(), getDelete());
		System.out.println("Task removed");	
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
