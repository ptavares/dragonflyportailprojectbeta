package fr.umlv.dragonflyEJB.managers.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.tables.Meeting;
import fr.umlv.dragonflyBdd.tables.News;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.Task;
import fr.umlv.dragonflyBdd.tables.User;



@LocalBinding(jndiBinding = "ProjectManager/local")
public @Stateless class ProjectManagerBean implements ProjectManager {

	private final static fr.umlv.dragonflyBdd.ProjectManager MANAGER;
	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	private final static SimpleDateFormat formatterWithHour = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	static {
		MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}
	
	public boolean addUserToProject(String name, User user) {
		return MANAGER.addUserToProject(name, user);
	}

	public boolean changeEndDate(String name, Date newEndDate) {
		return MANAGER.changeEndDate(name, newEndDate);
	}

	public int createProject(String name,String projectLeader, String resume) {
		return MANAGER.createProject(name,projectLeader,resume);
	}

	public int createProject(String name, String projectLeader, String resume,Date endDate) {
		return MANAGER.createProject(name, projectLeader,resume,endDate);
	}

	public int createProject(String name, String projectLeader,Date endDate, String resume, String description) {
		return MANAGER.createProject(name, projectLeader,endDate,resume,description);
	}

	public List<Project> getAllProject() {
		return MANAGER.getAllProject();
	}

	public boolean removeProject(String name) {
		return MANAGER.removeProject(name);
	}

	public long addTask(String project, String author,String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end) {
		return MANAGER.addTask(project, author,descr,subj,post,start,end);
	}

	public List<List<String>> getProjectTasks(String project) {
		Collection<Task> tasks = MANAGER.getProjectTasks(project);
		if((tasks == null)||(tasks.size()==0))
				return null;
		List<List<String>> taskslist = new ArrayList<List<String>>();
		
		for(Task t : tasks){
			List<String> l = new ArrayList<String>();
			l.add(Long.toString(t.getTask_id()));
			l.add(t.getSubject());
			l.add(t.getDescription());
			l.add(formatter.format(t.getPostDate()));
			l.add(formatter.format(t.getStartTask()));
			l.add(formatter.format(t.getEndTask()));
			taskslist.add(l);
		}
		return taskslist;
	}

	public long addMeeting(String project, String author, String subj, String description, java.util.Date post, java.util.Date date) {
		return MANAGER.addMeeting(project,author,subj,description,post,date);
	}

	public List<List<String>> getProjectMeetings(String project) {
		Collection<Meeting> meets = MANAGER.getProjectMeetings(project);
		ArrayList<List<String>> meetings = new ArrayList<List<String>>();
		if(meets != null){
			for(Meeting m: meets){
				ArrayList<String> list = new ArrayList<String>();
				list.add(new Long(m.getMeeting_id()).toString());
				list.add(m.getSubject());
				list.add(m.getDescription());
				list.add(formatter.format(m.getPostDate()));
				list.add(formatterWithHour.format(m.getDate()));
				meetings.add(list);
			}	
		}
 		return meetings;
	}

	public long addNews(String project,  String author, java.util.Date post, String subj, String descr) {
		return MANAGER.addNews(project,author, post,subj,descr);
	}

	public List<List<String>> getProjectNews(String name) {
		Collection<News> list = MANAGER.getProjectNews(name);
		List<List<String>> newsList = new ArrayList<List<String>>();
		
		for(News n : list){
			List<String> m = new ArrayList<String>();
			m.add(new Long(n.getNews_id()).toString());
			m.add(n.getAuthor());
			m.add(n.getSubject());
			m.add(n.getDescription());
			m.add(formatter.format(n.getPostDate()));
			newsList.add(m);
		}
		return newsList;
	}

	public boolean removeTask(String name, String task_id) {
		return MANAGER.removeTask(name, task_id);
	}

	public boolean removeMeeting(String name, String meet_id) {
		return MANAGER.removeMeeting(name,meet_id);
	}

	public boolean removeNews(String project, String news_id) {
		return MANAGER.removeNews(project,news_id);
	}
	
	public boolean addUserToProject(String project, String mail) {
		return MANAGER.addUserToProject(project, mail);
	}

	public String[][] getProjectUsers(String project) {
		return MANAGER.getProjectUsers(project);
	}

	public Map<String, String> getProjectInformations(String project) {
		return MANAGER.getProjectInformations(project);
	}

	
}
