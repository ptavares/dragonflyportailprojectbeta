package projects.deletes;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class DeleteFAQ extends ActionSupport {

	private String delete;
	private String ProName;

	public String execute() {
		//String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		//System.out.println("Project Name : "+project);

		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");

			if(!dEJB.removeQuestionResponse(getProName(), getDelete())){
				addActionError(getText("faq.remove.deleteError"));
				return ERROR;
			}

		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
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
