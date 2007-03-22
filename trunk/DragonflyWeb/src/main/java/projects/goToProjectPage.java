package projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import projects.beans.Meeting;
import projects.beans.News;
import projects.beans.ProjectInformations;
import projects.beans.Task;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.information.ProjectInformation;

public class goToProjectPage extends ActionSupport {
	public String ProName;
	
	public ProjectInformations informationBean;
	public List<Task> tasks;
	public List<Meeting> meetings;
	public List<News> news;
	
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
	public String goToNewsPage()throws NamingException{
		initNews();
		return "newsPage";
	}
	/**
	 * Redirect to TODO Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToTODOPage() throws NamingException{
		initTasks();
		return "TODOPage";
	}
	/**
	 * Redirect to Mettings Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToMeetingsPage() throws NamingException{
		initMeetings();
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
	/**
	 * Redirect to Administration Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToAdministrationPage() throws NamingException{
		return "administration";
	}
	
	public String editableTaskTab() throws NamingException{
		initTasks();
		return "taskTabs";
	}
	
	public String editableMeetingTab() throws NamingException{
		initMeetings();
		return "meetingTabs";
	}
	
	public String editableNewsTab() throws NamingException{
		initNews();
		return "newsTabs";
	}
	
	private void initTasks() throws NamingException{
		final InitialContext ctx = new InitialContext();
		final ProjectInformation proj = (ProjectInformation) ctx.lookup("ProjectInformation/remote");
		tasks = new ArrayList<Task>();
		List<List<String>> list = proj.getProjectTasks(ProName);
		if(list!=null){
			for(List<String> task :list)
				tasks.add(new Task(task));
		}
	}
	
	private void initMeetings() throws NamingException {
		final InitialContext ctx = new InitialContext();
		final ProjectInformation proj = (ProjectInformation) ctx.lookup("ProjectInformation/remote");
		meetings = new ArrayList<Meeting>();
		List<List<String>> list = proj.getProjectMeetings(ProName);
		if(list!=null){
			for(List<String> meeting :list)
				meetings.add(new Meeting(meeting));
		}
	}
	
	private void initNews() throws NamingException {
		final InitialContext ctx = new InitialContext();
		final ProjectInformation proj = (ProjectInformation) ctx.lookup("ProjectInformation/remote");
		news = new ArrayList<News>();
		List<List<String>> list = proj.getProjectNews(ProName);
		if(list!=null){
			for(List<String> n :list)
				news.add(new News(n));
		}
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
