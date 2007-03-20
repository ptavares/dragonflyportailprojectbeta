package fr.umlv.dragonflyBdd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import fr.umlv.dragonflyBdd.tables.FAQ;
import fr.umlv.dragonflyBdd.tables.Meeting;
import fr.umlv.dragonflyBdd.tables.News;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.ProjectDescription;
import fr.umlv.dragonflyBdd.tables.Task;
import fr.umlv.dragonflyBdd.tables.User;
import fr.umlv.dragonflyBdd.utils.DragonFlyDBManager;

/**
 * This class manages all project's database access.
 * 
 * @author Tavares Patrick
 *
 */
public class ProjectManager {

	private static ProjectManager PM_INSTANCE = new ProjectManager();
	private static AccountManager AM_INSTANCE = AccountManager.getAccountManagerInstance();
	private final Session session;
	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	
	
	/**
	 * Private constructor
	 *
	 */
	private ProjectManager() {
		session = DragonFlyDBManager.getcurrentSession();
	}
	
	/**
	 * Get the only Project Manager instance
	 * 
	 * @return The Project Manager instance
	 */
	public static ProjectManager getProjectManagerInstance() {
		return PM_INSTANCE;
	}


	/**
	 * Create a project and add this project to database
	 * 
	 * @param name Project's Name - key for Project's Table
	 * @param projectLeader The ProjectLeader for this Project. Has the admin's right 
	 * @param resume The resume for this project
	 * @return 0 - Project created ; 1 - Project with this name allready exist ; 2 - Error during Project creation
	 */
	public int createProject(String name,String projectLeader,String resume) {
		return createProject(name, projectLeader,resume,null);
	}
	
	/**
	 * Create a project and add this project to database
	 * 
	 * @param name Project's Name - key for Project's Table
	 * @param projectLeader The ProjectLeader for this Project. Has the admin's right 
	 * @param endDate The project endDate (null if no Date is specified)
	 * @param resume The resume for this project
	 * @return 0 - Project created ; 1 - Project with this name allready exist ; 2 - Error during Project creation
	 */
	public int createProject(String name,String projectLeader,String resume,Date enDate){
		return createProject(name,projectLeader,enDate,resume,null);
	}
	
	/**
	 * Create a project and add this project to database
	 * 
	 * @param name Project's Name - key for Project's Table
	 * @param projectLeader The ProjectLeader for this Project. Has the admin's right 
	 * @param endDate The project endDate (null if no Date is specified)
	 * @param resume The resume for this project
	 * @param description Description for this project
	 * @return 0 - Project created ; 1 - Project with this name allready exist ; 2 - Error during Project creation
	 */
	public int createProject(String name, String projectLeader,Date endDate,String resume, String description) {
		//Test if an account have this email key
		System.out.println("Entering the Project manager with name= "+name+" "+resume+" "+description);
		
		if(isProjectExist(name))
			return 1;
		//Create an user and add it into User's Table
		Transaction tx = null;
		try{
			Date creationDate = new Date(System.currentTimeMillis());
			tx = session.beginTransaction();
			
			Project project = new Project(name,AM_INSTANCE.getUserNickname(projectLeader),creationDate);
			ProjectDescription projectDescription = new ProjectDescription(resume,project);
			projectDescription.setDescription(description);
			project.setDescription(projectDescription);
			FAQ faq = new FAQ(project);
			
			project.setFaq(faq);
			if(endDate != null)
				project.setEndDate(endDate);
			session.save(project);
			session.save(faq);
			session.save(projectDescription);
			tx.commit();
			addUserToProject(name, AM_INSTANCE.getUser(projectLeader));
			System.out.println("end commit");
		}catch(ConstraintViolationException e){
			System.err.println("A project with the same name allready exist");
			//In case of exception
			if(tx != null)
				tx.rollback();
			return 2;
		}
		//Ok
		return 0;
	}
		
	/**
	 * Remove a Project from database
	 * 
	 * @param name The project's key. Needed to remove the project from Project's Table 
	 * @return true if the project have been dropped successfuly, false otherwise.
	 */
	public boolean removeProject(String name){
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Project project = getProject(name);
			FAQ faq = project.getFaq();
			ProjectDescription description = project.getDescription();
			session.delete(faq);
			session.delete(description);
			session.delete(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			//Error
			return false;
		}
		//Project removed
		return true;
	}
	
	/**
	 * Change the project's endDate
	 * 
	 * @param name The name of the project to change the endDate
	 * @param newEndDate The new endDate
	 * @return true if the change have been done - false otherwise
	 */
	public boolean changeEndDate(String name, Date newEndDate){
		Project project = getProject(name);

		if(project == null)
			return false;

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			project.setEndDate(newEndDate);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}
	
	/**
	 * Get all The Project present in Project's Table
	 * 
	 * @return A list with all Project present in Project's Table.
	 */
	@SuppressWarnings("unchecked")
	public List<Project> getAllProject(){
		return session.createQuery("from Project").list();
	}
	
	
	public boolean removeTask(String name, String task_id){
		Project project = getProject(name);
		long id = 0;
		
		if(project == null){
			System.out.println("Project manager remove task :project name=null");
			return false;
		}
		Transaction tr = null;
		try{
			tr = session.beginTransaction();
			Collection<Task> coll = project.getTasks();
			try{
				id = Long.parseLong(task_id);
			}catch(NumberFormatException e){
				System.err.println("Task_id cannot be converted to long");
				return false;
			}
			for(Task t : coll){
				if(t.getTask_id() == id){
					coll.remove(t);
					break;
				}
			}
			session.update(project);
			tr.commit();
		}catch(Exception e){
			System.err.println("Remove Task probleme :"+e.getMessage());
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String id = "1";
		ProjectManager.getProjectManagerInstance().removeTask("Dragonfly", id);
	}
	
	public long addTask(String project, String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end) {
		Project proj = getProject(project);
		if(project == null)
			return -1;
		
		Transaction trans = null;
		Task task = null;
		try {
			trans = session.beginTransaction();
			Collection<Task> collection = proj.getTasks();
			if(collection == null){
				proj.setTasks(new LinkedList<Task>());
				collection = proj.getTasks();
			}
			//TODO: Rajouter les parametres pour la tache
			task = new Task(post,"Author",subj,descr,start,end);
			/*
			System.out.println("Post :"+post.toString());
			System.out.println("Project Name :"+ project);
			System.out.println("Start date :" + start.toString());
			System.out.println("End Date :"+end.toString());
			System.out.println("Tasks :"+proj.getTasks().size());
			*/
			collection.add(task);
			//System.out.println("1");
			session.update(proj);
			//System.out.println("2");
			trans.commit();
			//System.out.println("jdsfsfd");
		} catch (Exception e) {
			System.out.println("DataBase Exception "+e.getMessage());
			e.printStackTrace();
			if(trans != null)
				trans.rollback();
			return -1;
		}
		
		return task.getTask_id();
	}
	/**
	 * Add a Task for the named project
	 * 
	 * @param name The name of the project to add a task
	 * @return true if the Task have been added - false otherwise
	 */
	public boolean addTask(String name){
		Project project = getProject(name);
		System.out.println("--->"+name);
		if(project == null)
			return false;

		
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Collection<Task> collection = project.getTasks();
			if(collection == null){
				project.setTasks(new LinkedList<Task>());
				collection = project.getTasks();
			}
			//TODO: Rajouter les parametres pour la tache
			
			Date postdate = new Date();
			Task task = new Task(postdate,postdate,postdate);
			collection.add(task);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}
	
	/**
	 * Add a Meeting for the named project
	 * 
	 * @param name The name of the project to add a meeting
	 * @return true if the Meeting have been added - false otherwise
	 */
	public boolean addMeeting(String name){
		Project project = getProject(name);

		if(project == null)
			return false;
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Collection<Meeting> collection = project.getMeetings();
			if(collection == null){
				project.setMeetings(new LinkedList<Meeting>());
				collection = project.getMeetings();
			}
			//TODO: Rajouter les parametres pour le meeting
			Date postDate = new Date(System.currentTimeMillis());
			Date date = new Date(System.currentTimeMillis());
			Meeting meeting = new Meeting(postDate,date,"12:30");
			collection.add(meeting);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}
	
	public boolean addMeeting(String proj, String subj, String description, java.util.Date date) {
		System.out.println("addMeeting fonction entered : "+proj);
		Project project = getProject(proj);

		if(project == null){
		System.out.println("project null");
			return false;
		}
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Collection<Meeting> collection = project.getMeetings();
			if(collection == null){
				project.setMeetings(new LinkedList<Meeting>());
				collection = project.getMeetings();
			}
			Date postDate = new Date(System.currentTimeMillis());
			System.out.println("Date created");
			Meeting meeting = new Meeting(postDate,"jdsh",subj,date,"hghg",description);
			System.out.println("Meeting created");
			collection.add(meeting);
			System.out.println("Meeting added");
			session.update(project);
			System.out.println("session update");
			tr.commit();
			System.out.println("session commit");
		} catch (Exception e) {
			e.printStackTrace();
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}

	
	public boolean removeMeeting(String name, String meet_id) {
		Project project = getProject(name);
		long id = 0;
		
		if(project == null){
			System.out.println("Project manager remove task :project name=null");
			return false;
		}
		Transaction tr = null;
		try{
			tr = session.beginTransaction();
			Collection<Meeting> coll = project.getMeetings();
			try{
				id = Long.parseLong(meet_id);
			}catch(NumberFormatException e){
				System.err.println("Task_id cannot be converted to long");
				return false;
			}
		//TODO La meuilleur solution serait de ne pas parcourir toute la liste mais
			//faire une requ阾e SQL
			for(Meeting t : coll){
				if(t.getMeeting_id() == id){
					coll.remove(t);
					break;
				}
			}
			session.update(project);
			tr.commit();
		}catch(Exception e){
			System.err.println("Remove Task probleme :"+e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public boolean addNews(String proj, String subj, String descr) {
		Project project = getProject(proj);

		if(project == null)
			return false;

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Collection<News> collection = project.getNews();
			if(collection == null){
				project.setNews(new LinkedList<News>());
				collection = project.getNews();
			}
			//TODO: Rajouter les parametres pour la news
			Date postdate = new Date(System.currentTimeMillis());
			News news = new News(postdate,"Author",subj,descr);
			collection.add(news);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}

	/**
	 * Add a News for the named project
	 * 
	 * @param name The name of the project to add a news
	 * @return true if the News have been added - false otherwise
	 */
	public boolean addNews(String name){
		Project project = getProject(name);

		if(project == null)
			return false;

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Collection<News> collection = project.getNews();
			if(collection == null){
				project.setNews(new LinkedList<News>());
				collection = project.getNews();
			}
			//TODO: Rajouter les parametres pour la news
			Date postdate = new Date(System.currentTimeMillis());
			News news = new News(postdate);
			collection.add(news);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}
	
	
	/**
	 * Add an user to the project 'projectName'
	 * 
	 * @param name The name of the project where the user must be added
	 * @param user The user to add into this named project
	 * @return true if the user have been added - false otherwise
	 */
	public boolean addUserToProject(String name, User user){

		Project project = getProject(name);

		if(project == null)
			return false;

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			project.getUsers().add(user);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}
	
	/**
	 * Test if a project exist in DataBase
	 * 
	 * @param name
	 * @return
	 */
	public boolean isProjectExist(String name){
		Project project = getProject(name);
		return (project != null);
	}
	
	
	/**
	 * Get the Project Object with this name
	 * 
	 * @param email The key to get this project in the Project's Table from DataBase. 
	 * @return The Project Object associated with this name, null otherwise (if doesn't exist in DataBase).
	 */
	public Project getProject(String name) {
		//Try to get the Project with this name key
		return (Project)session.get(Project.class,name);
	}

	public List<List<String>> getProjectTasks(String project) {
		Project proj = getProject(project);
		Collection<Task> tasks = proj.getTasks();
		if((tasks == null)||(tasks.size()==0))
				return null;
		List<List<String>> list = new ArrayList<List<String>>();
		//Map<String, String> map = new HashMap<String, String>();
		
		for(Task t : tasks){
			List<String> l = new ArrayList<String>();
			l.add(Long.toString(t.getTask_id()));
			l.add(t.getSubject());
			l.add(t.getDescription());
			l.add(formatter.format(t.getPostDate()));
			l.add(formatter.format(t.getStartTask()));
			l.add(formatter.format(t.getEndTask()));
			list.add(l);
		}
		return list;
	}

	public List<List<String>> getProjectMeetings(String project) {
		Project proj = getProject(project);
		Collection<Meeting> list = proj.getMeetings();
		List<List<String>> l = new ArrayList<List<String>>();
		
		for(Meeting meet : list){
			List<String> m = new ArrayList<String>();
			m.add(Long.toString(meet.getMeeting_id()));
			m.add(meet.getSubject());
			m.add(meet.getDate().toString());
			m.add(meet.getPostDate().toString());
			m.add(meet.getDescription());			
			l.add(m);
		}
		return l;
	}

	public List<List<String>> getProjectNews(String name) {
		Project project = getProject(name);
		Collection<News> list = project.getNews();
		List<List<String>> l = new ArrayList<List<String>>();
		
		for(News n : list){
			List<String> m = new ArrayList<String>();
			m.add(n.getAuthor());
			m.add(n.getSubject());
			m.add(n.getDescription());
			m.add(n.getPostDate().toString());
			l.add(m);
		}
		return l;
	}
	
	public boolean addUserToProject(String project, String mail) {
		Project proj = getProject(project);
		Set<User> set = proj.getUsers();
		Transaction tr = null;
		try{
		tr = session.beginTransaction();
		for(User u : set){
			if((u.getEmail()).compareTo(mail) == 0){
				System.out.println("User already in the project");
				return true;
				}				
		}
		User user = AccountManager.getAccountManagerInstance().getUser(mail);
		set.add(user);
		session.update(proj);
		tr.commit();
		}catch(Exception e){
			System.err.println("Probleme adding user to the project");
			return false;
		}
		System.out.println("User Added");
		return true;
	}

	public String[][] getProjectUsers(String project) {
		System.out.println("-------->Entering getProjectUsers");
		Project proj = getProject(project);
		if(proj==null)
			return null;
		System.out.println("Project : "+proj);
		Set<User> set = proj.getUsers();
		System.out.println("Size : "+set.size());
		String[][] tab = new String[set.size()][];
		for(int i = 0; i<set.size();i++)
			tab[i]=new String[2];
		System.out.println("----> After malloc");
		int i = 0;
		for(User u :set){
			tab[i][0] = u.getNickname();
			tab[i][1] = u.getEmail();
			i++;			
		}
		for(int j = 0; j <tab.length; j++){
			System.out.println("Name : "+tab[j][0]);
			System.out.println("Emain :"+tab[j][1]);
		}
		
		return tab;
	}

	public Map<String, String> getProjectInformations(String project) {
		Project proj = getProject(project);
		if(proj==null)
			return null;
		Map<String, String> informations = new HashMap<String, String>();
		ProjectDescription description = proj.getDescription();
		informations.put("ProjectLeader", proj.getProjectLeader());
		informations.put("CreationDate", formatter.format(proj.getCreationDate()));
		informations.put("Resume", description.getResume());
		informations.put("Description", description.getDescription());
		//TODO: A verif si ca marche ^^
		StringBuilder builder = new StringBuilder();
		Iterator<User> users = proj.getUsers().iterator();
		while(users.hasNext()){
			builder.append(users.next().getNickname());
			if(users.hasNext())
				builder.append(", ");
		}
		informations.put("Developers", builder.toString());		
		return informations;
	}


}
