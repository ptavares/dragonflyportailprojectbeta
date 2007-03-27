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
import fr.umlv.dragonflyEJB.services.account.information.AccountInformation;
import fr.umlv.dragonflyEJB.services.project.information.ProjectInformation;
import fr.umlv.dragonflyEJB.services.project.maven.MavenInformation;
import fr.umlv.dragonflyEJB.services.project.maven.MavenManager;

public class goToProjectPage extends ActionSupport {
	public String ProName;

	public ProjectInformationsBean informationBean = null;
	public List<TaskBean> tasks = null;
	public List<MeetingBean> meetings = null;
	public List<NewsBean> news = null;
	public List<QuestionResponseBean> questionsResponse = null;

	/**
	 * Status : 0 -> visitor, 1 -> User , 2 -> ProjectLeader
	 */
	public int userStatus;

	public String execute(){
		Map<String, String> session = ActionContext.getContext().getSession();
		session.put("project", ProName);
		System.out.println("************"+ProName);


		String project = (String)session.get("project");
		String name = (String)session.get("nom");

		if(name == null) {
			userStatus = 0;
			session.put("userStatus", "0");
			return SUCCESS;
		}

		if(name!=null){
			InitialContext ctx;
			List<String> list=null;
			try {
				ctx = new InitialContext();

				final AccountInformation info = (AccountInformation)ctx.lookup("AccountInformation/remote");
				list = info.getUserRoles(name);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DragonflyBddException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(list == null) {
				userStatus = 0;
				session.put("userStatus", "0");
				return SUCCESS;
			}

			for(String s : list){
				if(s.compareTo(project+"admin")==0){
					session.put("userStatus", "2");
					userStatus=2;
					return SUCCESS;
				}
				if(s.compareTo(project+"user")==0){
					session.put("userStatus", "1");
					userStatus=1;
					return SUCCESS;
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * Redirect to Information Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToInformationPage() throws NamingException{
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
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
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		initNews();
		return "newsPage";
	}
	/**
	 * Redirect to Todo Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToTODOPage() {
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		initTasks();
		return "TODOPage";
	}

	/**
	 * Redirect to Mettings Page
	 * @return
	 * @throws NamingException 
	 */
	public String goToMeetingsPage() {
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		initMeetings();
		return "meetingsPage";
	}


	/**
	 * Redirect to Maven Page
	 * @return
	 */
	public String goToMavenPage(){
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		return "mavenPage";
	}
	/**
	 * Redirect to FAQ Page
	 * @return
	 */
	public String goToFAQPage(){
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		initFAQ();
		return "FAQPage";
	}


	/**
	 * Redirect to DownLoad/Upload Page
	 * @return
	 */
	public String goToDownUpPage(){
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		return "downUpPage";
	}
	/**
	 * Redirect to Administration Page
	 * @return
	 */
	public String goToAdministrationPage(){
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		return "administration";
	}

	/**
	 * Redirect to AddTask Page
	 * @return
	 */
	public String goToAddTaskPage() {
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		return "addTaskPage";
	}
	/**
	 * Redirect to AddNews Page
	 * @return
	 */
	public String goToAddNewsPage() {
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		return "addNewsPage";
	}
	/**
	 * Redirect AddMeeting Page 
	 * @return
	 */
	public String goToAddMeetingPage(){
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		return "addMeetingPage";
	}

	/**
	 * Redirect AddFAQ Page 
	 * @return
	 */
	public String goToAddFAQPage(){
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		return "addFAQPage";
	}

	public String editableTaskTab() throws NamingException{
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		initTasks();
		return "taskTabs";
	}

	public String editableMeetingTab() throws NamingException{
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		initMeetings();
		return "meetingTabs";
	}

	public String editableNewsTab() throws NamingException{
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		initNews();
		return "newsTabs";
	}

	public String editableFAQTab() throws NamingException{
		userStatus = Integer.parseInt((String) ActionContext.getContext().getSession().get("userStatus"));
		initFAQ();
		return "FAQTabs";
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
//			TODO A REDIRIGER VERS PAGE D'ERREUR NIVO EJB
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

    public MavenInformation getMavenInformation() {
        return mavenInformation;
    }

    public void setMavenInformation(MavenInformation mavenInformation) {
        this.mavenInformation = mavenInformation;
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

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}


}
