package projects.deletes;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.services.project.modification.ProjectModification;

public class DeleteFAQ extends ActionSupport {
	
	private String delete;
	private String ProName;
	
	public String execute() {
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		System.out.println("Project Name : "+project);
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
		
		final ProjectModification proj=(ProjectModification) ctx.lookup("ProjectModification/remote");
		
		if(!proj.removeQuestionResponse(getProName(), getDelete())){
			addActionError(getText("faq.remove.deleteError"));
			return ERROR;
		}
		
		} catch (NamingException e) {
			// TODO REDIRECTION ERREUR EJB
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			// TODO REDIRECTION ERREUR BDD
			e.printStackTrace();
		}
		
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
