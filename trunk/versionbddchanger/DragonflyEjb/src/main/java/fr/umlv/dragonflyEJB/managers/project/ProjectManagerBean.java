package fr.umlv.dragonflyEJB.managers.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Meeting;
import fr.umlv.dragonflyBdd.tables.News;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.ProjectDescription;
import fr.umlv.dragonflyBdd.tables.QuestionResponse;
import fr.umlv.dragonflyBdd.tables.Task;
import fr.umlv.dragonflyBdd.tables.User;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;



@LocalBinding(jndiBinding = "ProjectManager/local")
public @Stateless class ProjectManagerBean implements ProjectManager {

	private final static fr.umlv.dragonflyBdd.ProjectManager MANAGER;
	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	private final static SimpleDateFormat formatterWithHour = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	
	static {
		MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}

	public boolean changeEndDate(String name, Date newEndDate) throws DragonflyBddException {
		return MANAGER.changeEndDate(name, newEndDate);
	}

	public int createProject(String name,String projectLeader, String resume) throws DragonflyBddException {
		return MANAGER.createProject(name,projectLeader,resume);
	}

	public int createProject(String name, String projectLeader, String resume,Date endDate) throws DragonflyBddException {
		return MANAGER.createProject(name, projectLeader,resume,endDate);
	}

	public int createProject(String name, String projectLeader,Date endDate, String resume, String description) throws DragonflyBddException {
		return MANAGER.createProject(name, projectLeader,endDate,resume,description);
	}

	public List<Project> getAllProject() throws DragonflyBddException {
		return MANAGER.getAllProject();
	}

	public boolean removeProject(String name) throws DragonflyBddException {
		return MANAGER.removeProject(name);
	}

	public long addTask(String project, String author,String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end) throws DragonflyBddException {
		return MANAGER.addTask(project, author,descr,subj,post,start,end);
	}

	public List<TaskBean> getProjectTasks(String project) throws DragonflyBddException {
		Collection<Task> tasks = MANAGER.getProjectTasks(project);
		
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

	public long addMeeting(String project, String author, String subj, String description, java.util.Date post, java.util.Date date) throws DragonflyBddException {
		return MANAGER.addMeeting(project,author,subj,description,post,date);
	}

	public List<MeetingBean> getProjectMeetings(String project) throws DragonflyBddException {
		Collection<Meeting> meets = MANAGER.getProjectMeetings(project);
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

	public long addNews(String project,  String author, java.util.Date post, String subj, String descr) throws DragonflyBddException {
		return MANAGER.addNews(project,author, post,subj,descr);
	}

	public List<NewsBean> getProjectNews(String name) throws DragonflyBddException {
		Collection<News> list = MANAGER.getProjectNews(name);
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

	public boolean removeTask(String name, String task_id) throws DragonflyBddException {
		return MANAGER.removeTask(name, task_id);
	}

	public boolean removeMeeting(String name, String meet_id) throws DragonflyBddException {
		return MANAGER.removeMeeting(name,meet_id);
	}

	public boolean removeNews(String project, String news_id) throws DragonflyBddException {
		return MANAGER.removeNews(project,news_id);
	}
	
	public boolean addUserToProject(String project, String mail) throws DragonflyBddException {
		return MANAGER.addUserToProject(project, mail);
	}

	public ProjectInformationsBean getProjectInformations(String project) throws DragonflyBddException {
		
		Project p = MANAGER.getProject(project);
		ProjectDescription description = MANAGER.getProjectDescription(project);
		Collection<User> usersSet = MANAGER.getProjectUsers(project);
		
		ProjectInformationsBean projectInformationsProperties = new ProjectInformationsBean();		
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

	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException {
		return MANAGER.addQuestionResponse(proj, question, response);
	}

	public boolean editQuestionResponse(String name, String qr_id, String question, String response) throws DragonflyBddException {
		return MANAGER.editQuestionResponse(name, qr_id, question, response);
	}

	public boolean removeQuestionResponse(String name, String qr_id) throws DragonflyBddException {
		return MANAGER.removeQuestionResponse(name, qr_id);
	}

	public List<QuestionResponseBean> getProjectFAQ(String project) throws DragonflyBddException {
		Collection<QuestionResponse> list = MANAGER.getProjectFAQ(project);
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
	
}
