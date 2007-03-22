package fr.umlv.dragonflyBdd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.MappingException;
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
	
	
	/**
	 * Add a Task for the named project
	 * 
	 * @param project The project to add the Task
	 * @param author The Author of the Task (nickname)
	 * @param descr The description of the Task
	 * @param subj The Subject of the Task
	 * @param post The PostDate of the Task
	 * @param start The start date for the Task
	 * @param end The end date for the Task
	 * @return id of the task if the Task have been added, -1 otherwise
	 */
	public long addTask(String project, String author, String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end) {
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
			task = new Task(post,author,subj,descr,start,end);
			collection.add(task);
			session.update(proj);
			trans.commit();
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
	 * Remove a Task for the named Project
	 * @param name The name of the project to delete the Task
	 * @param task_id The task id of the Task to delete
	 * @return true if the delete have been done, false otherwise
	 */
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
	
	/**
	 * Add a Meeting for the named project
	 * @param project The project to add the Meeting
	 * @param author The Author of the Meeting (nickname)
	 * @param description The description of the Meeting
	 * @param subj The Subject of the Meeting
	 * @param post The PostDate of the Meeting
	 * @param date The date for the Meeting
	 * @return id of the Meeting if the Meeting have been added, -1 otherwise
	 */
	public long addMeeting(String project, String author, String subj, String description, java.util.Date post, java.util.Date date) {
		Project proj = getProject(project);
		if(project == null)
			return -1;
		
		Transaction trans = null;
		Meeting meeting = null;
		try {
			trans = session.beginTransaction();
			Collection<Meeting> collection = proj.getMeetings();
			if(collection == null){
				proj.setMeetings(new LinkedList<Meeting>());
				collection = proj.getMeetings();
			}
			meeting = new Meeting(post,author,subj,date,description);
			collection.add(meeting);
			session.update(proj);
			trans.commit();
		} catch (Exception e) {
			System.out.println("DataBase Exception "+e.getMessage());
			e.printStackTrace();
			if(trans != null)
				trans.rollback();
			return -1;
		}
		return meeting.getMeeting_id();
	}

	/**
	 * Remove a Meeting for the named Project
	 * @param name The name of the project to delete the Meeting
	 * @param meet_id The meeting id of the Meeting to delete
	 * @return true if the delete have been done, false otherwise
	 */
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
			System.err.println("Remove Meeting probleme :"+e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Add a News for the named project
	 * @param proj The project to add the News
	 * @param author The Author of the News (nickname)
	 * @param description The description of the News
	 * @param subj The Subject of the News
	 * @param post The PostDate of the News
	 * @return id of the News if the News have been added, -1 otherwise
	 */
	public long addNews(String proj, String author, java.util.Date post, String subj, String description) {
		Project project = getProject(proj);

		if(project == null)
			return -1;

		Transaction tr = null;
		News news = null;
		try {
			tr = session.beginTransaction();
			Collection<News> collection = project.getNews();
			if(collection == null){
				project.setNews(new LinkedList<News>());
				collection = project.getNews();
			}
			news = new News(post,author,subj,description);
			collection.add(news);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return -1;
		}
		return news.getNews_id();
	}
	
	/**
	 * Remove a News for the named Project
	 * @param name The name of the project to delete the News
	 * @param news_id The meeting id of the Meeting to delete
	 * @return true if the delete have been done, false otherwise
	 */
	public boolean removeNews(String name, String news_id) {
		Project project = getProject(name);
		long id = 0;
		
		if(project == null){
			System.out.println("Project manager remove task :project name=null");
			return false;
		}
		Transaction tr = null;
		try{
			tr = session.beginTransaction();
			Collection<News> coll = project.getNews();
			try{
				id = Long.parseLong(news_id);
			}catch(NumberFormatException e){
				System.err.println("Task_id cannot be converted to long");
				return false;
			}
		//TODO La meuilleur solution serait de ne pas parcourir toute la liste mais
			//faire une requ阾e SQL
			for(News t : coll){
				if(t.getNews_id() == id){
					coll.remove(t);
					break;
				}
			}
			session.update(project);
			tr.commit();
		}catch(Exception e){
			System.err.println("Remove News probleme :"+e.getMessage());
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

	/**
	 * Return all The Tasks for the named project 
	 * 
	 * @param project The name of the project to get The Tasks
	 * @return an unmodifiableCollection with all the Tasks for this project, a empty Collection otherwise
	 */
	public Collection<Task> getProjectTasks(String project) {
		Project proj = getProject(project);
		if(proj == null)
			new ArrayList<Task>();
		Collection<Task> list = proj.getTasks();
		if(list == null)
			list = new ArrayList<Task>();
		return Collections.unmodifiableCollection(new ArrayList<Task>(list));
	}

	/**
	 * Return all The Meetings for the named project 
	 * 
	 * @param project The name of the project to get The Meetings
	 * @return an unmodifiableCollection with all the Meetings for this project, a empty Collection otherwise
	 */
	public Collection<Meeting> getProjectMeetings(String project) {
		Project proj = getProject(project);
		if(proj == null)
			new ArrayList<Meeting>();
		Collection<Meeting> list = proj.getMeetings();
		if(list == null)
			list = new ArrayList<Meeting>();
		return Collections.unmodifiableCollection(new ArrayList<Meeting>(list));
	}

	/**
	 * Return all The News for the named project 
	 * 
	 * @param project The name of the project to get The News
	 * @return an unmodifiableCollection with all the News for this project, a empty Collection otherwise
	 */
	public Collection<News> getProjectNews(String project) {
		Project proj = getProject(project);
		if(proj == null)
			new ArrayList<News>();
		Collection<News> list = proj.getNews();
		if(list == null)
			list = new ArrayList<News>();
		return Collections.unmodifiableCollection(new ArrayList<News>(list));
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
	 *  Add an user to the named project 
	 *  
	 * @param project The name of the project where the user must be added
	 * @param mail The id of the user to add to the project
	 * @return
	 */
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
