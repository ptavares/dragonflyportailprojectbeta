package fr.umlv.dragonflyEJB.locals.information;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Meeting;
import fr.umlv.dragonflyBdd.tables.Message;
import fr.umlv.dragonflyBdd.tables.News;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.ProjectDescription;
import fr.umlv.dragonflyBdd.tables.QuestionResponse;
import fr.umlv.dragonflyBdd.tables.Roles;
import fr.umlv.dragonflyBdd.tables.Task;
import fr.umlv.dragonflyBdd.tables.User;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.MessageBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;

@LocalBinding(jndiBinding = "InformationManager/local")
public @Stateless class InformationManagerBean implements InformationManager {
	private final static fr.umlv.dragonflyBdd.AccountManager AM_MANAGER;

	static {
		AM_MANAGER = fr.umlv.dragonflyBdd.AccountManager.getAccountManagerInstance();
	}

	private final static fr.umlv.dragonflyBdd.ProjectManager PM_MANAGER;

	static {
		PM_MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}

	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	private final static SimpleDateFormat formatterWithHour = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

	public List<String> getActiveProject() throws DragonflyBddException {
		List<String> reponse = new ArrayList<String>();
		List<Project> projects = getAllProject();
		for(Project p:projects){
			if(p.isActived()){
				reponse.add(p.getName());
			}
		}
		return reponse;
	}
	public List<String> getActiveUsers() throws DragonflyBddException {
		List<User> users = AM_MANAGER.getAllUser();
		List<String> reponse = new ArrayList<String>();
		if(users == null || users.isEmpty())
			return Collections.EMPTY_LIST;
		for(User user : users)
			if(user.isActived())
				reponse.add(user.getEmail());
		return reponse;
	}
	public List<Project> getAllProject() throws DragonflyBddException {
		return PM_MANAGER.getAllProject();
	}
	public List<String> getAllProjectsNames() throws DragonflyBddException {
		List<Project> list =getAllProject();
		List<String> ls =new ArrayList<String>();

		for(Project pr : list){
			ls.add(pr.getName());
		}
		return ls;
	}
	public List<MessageBean> getMessages(String UserID) throws DragonflyBddException {
		Collection<Message> mes=AM_MANAGER.getMessages(UserID);
		List<MessageBean> mesEJB=new ArrayList<MessageBean>();
		for(Message m:mes){
			MessageBean	meEJB=new MessageBean(m);
			mesEJB.add(meEJB);
		}
		return mesEJB;
	}
	public List<String> getNotActiveProject() throws DragonflyBddException {
		List<String> reponse = new ArrayList<String>();
		List<Project> projects = getAllProject();
		for(Project p:projects){
			if(!p.isActived()){
				System.out.println("EJB "+p.getName());
				reponse.add(p.getName());
			}
		}
		return reponse;
	}
	public List<String> getNotActiveUsers() throws DragonflyBddException {
		List<User> users = AM_MANAGER.getAllUser();
		List<String> reponse = new ArrayList<String>();
		if(users == null || users.isEmpty())
			return Collections.EMPTY_LIST;
		for(User user : users)
			if(!user.isActived())
				reponse.add(user.getEmail());
		System.out.println("fin getNotActiveUsers");
		return reponse;
	}
	public List<QuestionResponseBean> getProjectFAQ(String project) throws DragonflyBddException {
		Collection<QuestionResponse> list = PM_MANAGER.getProjectFAQ(project);
		List<QuestionResponseBean> FAQList = new ArrayList<QuestionResponseBean>();

		for(QuestionResponse faq : list){
			QuestionResponseBean faqBean = new QuestionResponseBean();
			faqBean.setQuestion(faq.getQuestion());
			faqBean.setResponse(faq.getResponse());
			faqBean.setQRId(Long.toString(faq.getQr_id()));
			FAQList.add(faqBean);
		}

		return FAQList;
	}
	public ProjectInformationsBean getProjectInformations(String project) throws DragonflyBddException {

		Project p = PM_MANAGER.getProject(project);
		ProjectDescription description = PM_MANAGER.getProjectDescription(project);
		Collection<User> usersSet = PM_MANAGER.getProjectUsers(project);

		ProjectInformationsBean projectInformationsProperties = new ProjectInformationsBean();	
		projectInformationsProperties.setActived(p.isActived());
		projectInformationsProperties.setProjectLeader(p.getProjectLeader());
		projectInformationsProperties.setCreationDate(formatter.format(p.getCreationDate()));
		projectInformationsProperties.setResume(description.getResume());
		projectInformationsProperties.setDescription(description.getDescription());

		Iterator<User> users = usersSet.iterator();
		StringBuilder builder = new StringBuilder();
		while(users.hasNext()){
			builder.append(users.next().getNickname());
			if(users.hasNext())
				builder.append(", ");
		}
		projectInformationsProperties.setDevelopers(builder.toString());

		return projectInformationsProperties;
	}
	public List<MeetingBean> getProjectMeetings(String project) throws DragonflyBddException {
		Collection<Meeting> meets = PM_MANAGER.getProjectMeetings(project);
		ArrayList<MeetingBean> meetings = new ArrayList<MeetingBean>();
		if(meets != null){
			for(Meeting m: meets){
				MeetingBean meetingBean =new MeetingBean();
				meetingBean.setAuthor(m.getAuthor());
				meetingBean.setDescription(m.getDescription());
				meetingBean.setPostDate(formatter.format(m.getPostDate()));
				meetingBean.setDate(formatterWithHour.format(m.getDate()));
				meetingBean.setSubject(m.getSubject());
				meetingBean.setMeetingId(Long.toString(m.getMeeting_id()));
				meetings.add(meetingBean);
			}	
		}
		return meetings;
	}
	public List<ProjectInformationsBean> getProjectNamesWithDescriptions() throws DragonflyBddException {
		List<Project> list = getAllProject();
		List<ProjectInformationsBean> projectsWithResume = new ArrayList<ProjectInformationsBean>();

		for(Project project : list){
			ProjectInformationsBean bean = new ProjectInformationsBean();
			bean.setResume(project.getDescription().getResume());
			bean.setName(project.getName());
			bean.setProjectLeader(project.getProjectLeader());
			bean.setCreationDate(formatter.format(project.getCreationDate()));
			projectsWithResume.add(bean);
		}

		return projectsWithResume;
	}
	public List<NewsBean> getProjectNews(String name) throws DragonflyBddException {
		Collection<News> list = PM_MANAGER.getProjectNews(name);
		List<NewsBean> newsList = new ArrayList<NewsBean>();

		for(News n : list){
			NewsBean newsBean = new NewsBean();
			newsBean.setAuthor(n.getAuthor());
			newsBean.setDescription(n.getDescription());
			newsBean.setPostDate(formatter.format(n.getPostDate()));
			newsBean.setSubject(n.getSubject());
			newsBean.setNewsId(Long.toString(n.getNews_id()));
			newsList.add(newsBean);
		}

		return newsList;
	}

	public List<TaskBean> getProjectTasks(String project) throws DragonflyBddException {
		Collection<Task> tasks = PM_MANAGER.getProjectTasks(project);

		if((tasks == null)||(tasks.size()==0))
			return null;
		List<TaskBean> taskslist = new ArrayList<TaskBean>();

		for(Task t : tasks){
			TaskBean taskBean = new TaskBean();
			taskBean.setAuthor(t.getAuthor());
			taskBean.setDescription(t.getDescription());
			taskBean.setEndDate(formatter.format(t.getEndTask()));
			taskBean.setPostDate(formatter.format(t.getPostDate()));
			taskBean.setStartDate(formatter.format(t.getStartTask()));
			taskBean.setSubject(t.getSubject());
			taskBean.setTaskId(Long.toString(t.getTask_id()));
			taskslist.add(taskBean);
		}
		return taskslist;
	}
	public String getUserNickname(String mail) throws DragonflyBddException {
		return AM_MANAGER.getUserNickname(mail);
	}
	public List<String> getUserRoles(String user) throws DragonflyBddException {
		Collection<Roles> coll = AM_MANAGER.getUserRoles(user);
		List<String> set = new ArrayList<String>();
		for(Roles r: coll)
			set.add(r.getRole());
		return set;
	}
	public boolean isPasswordCorrect(String mail, String passwd) throws DragonflyBddException {
		return AM_MANAGER.isPasswordCorrect(mail,passwd);
	}
        
        public List<ProjectInformationsBean> getMyProject(String UserNname)throws DragonflyBddException{
            List<Project> list = PM_MANAGER.getUserProjects(UserNname);
            List<ProjectInformationsBean> projects = new ArrayList<ProjectInformationsBean>();
            
            for(Project project : list){
                ProjectInformationsBean bean = new ProjectInformationsBean();
                bean.setResume(project.getDescription().getResume());
                bean.setName(project.getName());
                bean.setProjectLeader(project.getProjectLeader());
                bean.setCreationDate(formatter.format(project.getCreationDate()));
                projects.add(bean);
            }
            System.out.println("Username "+UserNname);
            System.out.println("EJB List "+list.size());
            System.out.println("EJB Project "+projects.size());
            return projects;
        }
}
