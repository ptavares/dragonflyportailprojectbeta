package projects;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;
public class PopProject  extends ActionSupport{


	private List<ProjectInformationsBean> projects;


	public String execute() {
		InitialContext ctx;

		try {
			ctx = new InitialContext();

			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");

			projects = new ArrayList<ProjectInformationsBean>();

			projects = dEJB.getProjectNamesWithDescriptions();
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		} catch (NamingException e1) {
			e1.printStackTrace();
			return "actionError";
		}

		return SUCCESS;
	}


	public List<ProjectInformationsBean> getProjects() {
		return projects;
	}


	public void setProjects(ArrayList<ProjectInformationsBean> projects) {
		this.projects = projects;
	}


}
