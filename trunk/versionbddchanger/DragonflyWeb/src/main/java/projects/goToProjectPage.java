package projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;
import fr.umlv.dragonflyEJB.services.project.information.ProjectInformation;

public class goToProjectPage extends ActionSupport {
	public String ProName;

	public ProjectInformationsBean informationBean = null;
	public List<TaskBean> tasks = null;
	public List<MeetingBean> meetings = null;
	public List<NewsBean> news = null;
	public List<QuestionResponseBean> questionsResponse = null;

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
		try {
			informationBean = pi.getProjectInformations(ProName);
		} catch (DragonflyBddException e) {
			// TODO A REDIRIGER VERS PAGE D'ERREUR NIVO BDD
			e.printStackTrace();
		}
		return "informationPage";
	}
	/**
	 * Redirect to News Page
	 * @return
	 */
	public String goToNewsPage(){
		initNews();
		return "newsPage";
	}
	/**
	 * Redirect to Todo Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToTODOPage() {
		initTasks();
		return "TODOPage";
	}
		
	/**
	 * Redirect to Mettings Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToMeetingsPage() {
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
		initFAQ();
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
	 */
	public String goToAdministrationPage(){
		return "administration";
	}

	/**
	 * Redirect to AddTask Page
	 * @return
	 */
	public String goToAddTaskPage() {
		return "addTaskPage";
	}
	/**
	 * Redirect to AddNews Page
	 * @return
	 */
	public String goToAddNewsPage() {
		return "addNewsPage";
	}
	/**
	 * Redirect AddMeeting Page 
	 * @return
	 */
	public String goToAddMeetingPage(){
		return "addMeetingPage";
	}
	
	/**
	 * Redirect AddFAQ Page 
	 * @return
	 */
	public String goToAddFAQPage(){
		return "addFAQPage";
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
	
	private void initTasks() {
		try {
		final InitialContext ctx = new InitialContext();
		final ProjectInformation proj = (ProjectInformation) ctx.lookup("ProjectInformation/remote");

	
			tasks = proj.getProjectTasks(ProName);
			if(tasks == null)
				tasks = new ArrayList<TaskBean>();

		} catch (DragonflyBddException e) {
//			TODO A REDIRIGER VERS PAGE D'ERREUR NIVO BDD
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO A REDIRIGER VERS PAGE D'ERREUR NIVO EJB
			e.printStackTrace();
		}
	}

	private void initMeetings()  {
		try {
		final InitialContext ctx = new InitialContext();
		final ProjectInformation proj = (ProjectInformation) ctx.lookup("ProjectInformation/remote");

		
			meetings = proj.getProjectMeetings(ProName);
			if(meetings == null)
				meetings = new ArrayList<MeetingBean>();

		} catch (DragonflyBddException e) {
//			TODO A REDIRIGER VERS PAGE D'ERREUR NIVO BDD
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO A REDIRIGER VERS PAGE D'ERREUR NIVO EJB
			e.printStackTrace();
		}
	}

	private void initNews()  {
		try {
		final InitialContext ctx = new InitialContext();
		final ProjectInformation proj = (ProjectInformation) ctx.lookup("ProjectInformation/remote");

		
			news = proj.getProjectNews(ProName);
			if(news == null)
				news = new ArrayList<NewsBean>();

		} catch (DragonflyBddException e) {
//			TODO A REDIRIGER VERS PAGE D'ERREUR NIVO BDD
			e.printStackTrace();
		} catch (NamingException e) {
//			 TODO A REDIRIGER VERS PAGE D'ERREUR NIVO EJB
			e.printStackTrace();
		}
	}

	private void initFAQ() {
		try {
		final InitialContext ctx = new InitialContext();
		final ProjectInformation proj = (ProjectInformation) ctx.lookup("ProjectInformation/remote");

			questionsResponse = proj.getProjectFAQ(ProName);
			if(questionsResponse == null)
				questionsResponse = new ArrayList<QuestionResponseBean>();

		} catch (DragonflyBddException e) {
//			TODO A REDIRIGER VERS PAGE D'ERREUR NIVO BDD
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO A REDIRIGER VERS PAGE D'ERREUR NIVO EJB
			e.printStackTrace();
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

	public ProjectInformationsBean getInformationBean() {
		return informationBean;
	}

	public void setInformationBean(ProjectInformationsBean informationBean) {
		this.informationBean = informationBean;
	}

	public List<MeetingBean> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<MeetingBean> meetings) {
		this.meetings = meetings;
	}

	public List<NewsBean> getNews() {
		return news;
	}

	public void setNews(List<NewsBean> news) {
		this.news = news;
	}

	public List<QuestionResponseBean> getQuestionsResponse() {
		return questionsResponse;
	}

	public void setQuestionsResponse(List<QuestionResponseBean> questionsResponse) {
		this.questionsResponse = questionsResponse;
	}

	public List<TaskBean> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskBean> tasks) {
		this.tasks = tasks;
	}


}
