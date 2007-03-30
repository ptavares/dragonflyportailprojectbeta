package projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;

public class MyProject extends ActionSupport {
	private List<ProjectInformationsBean> projects= new ArrayList<ProjectInformationsBean>();

	public String execute() {
		//System.out.println("listProject");

		final InitialContext ctx;
		Map session = ActionContext.getContext().getSession();
		String login = (String) session.get("nom");
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			projects = dEJB.getMyProject(login);
			// System.out.println("List "+projects.size());

		} catch (DragonflyBddException e) {
//			TODO A REDIRIGER VERS PAGE D'ERREUR NIVO BDD
			e.printStackTrace();
		} catch (NamingException e1) {
//			TODO A REDIRIGER VERS PAGE D'ERREUR NIVO EJB
			e1.printStackTrace();
		}
		return SUCCESS;
	}

	public List<ProjectInformationsBean> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectInformationsBean> projects) {
		this.projects = projects;
	}

}
