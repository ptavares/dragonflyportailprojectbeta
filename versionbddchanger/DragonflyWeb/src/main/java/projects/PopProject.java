package projects;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.services.project.information.ProjectInformation;
public class PopProject  extends ActionSupport{


	private List<ProjectInformationsBean> projects;


	public String execute() {
		InitialContext ctx;

		try {
			ctx = new InitialContext();

			final ProjectInformation pi=(ProjectInformation) ctx.lookup("ProjectInformation/remote");

			projects = new ArrayList<ProjectInformationsBean>();

			projects = pi.getProjectNamesWithDescriptions();
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


	public void setProjects(ArrayList<ProjectInformationsBean> projects) {
		this.projects = projects;
	}


}
