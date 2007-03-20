package projects;

import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import projects.beans.ProjectInformations;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.information.ProjectInformation;

public class goToProjectPage extends ActionSupport {
	public String ProName;
	
	public ProjectInformations informationBean;
	
	public String execute(){
		Map<String, String> session = ActionContext.getContext().getSession();
		session.put("project", ProName);
		System.out.println("************"+ProName);
		return SUCCESS;
	}
	
	/**
	 * Redirect to Information Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToInformationPage() throws NamingException{
		 final InitialContext ctx = new InitialContext();
		 final ProjectInformation pi=(ProjectInformation) ctx.lookup("ProjectInformation/remote");
		 informationBean = new ProjectInformations(pi.getProjectInformations(ProName));
		return "informationPage";
	}
	/**
	 * Redirect to News Page
	 * @return
	 */
	public String goToNewsPage(){
		return "newsPage";
	}
	/**
	 * Redirect to TODO Page
	 * @return
	 */
	public String goToTODOPage(){
		return "TODOPage";
	}
	/**
	 * Redirect to Mettings Page
	 * @return
	 */
	public String goToMeetingsPage(){
		return "meetingsPage";
	}
	/**
	 * Redirect to Maven Page
	 * @return
	 */
	public String goToMavenPage(){
		return "mavenPage";
	}
	/**
	 * Redirect to FAQ Page
	 * @return
	 */
	public String goToFAQPage(){
		return "FAQPage";
	}
	/**
	 * Redirect to DownLoad/Upload Page
	 * @return
	 */
	public String goToDownUpPage(){
		return "downUpPage";
	}
	
	public String goToPrivateSpace(){
		return "privateSpace";
	}
	
	public String goToMeetings(){
		return "meeting";
	}
	public String getProName() {
		return ProName;
	}
	public void setProName(String proName) {
		ProName = proName;
	}
}
