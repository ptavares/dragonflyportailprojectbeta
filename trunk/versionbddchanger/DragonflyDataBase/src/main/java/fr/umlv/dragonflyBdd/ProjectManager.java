package fr.umlv.dragonflyBdd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Meeting;
import fr.umlv.dragonflyBdd.tables.News;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.ProjectDescription;
import fr.umlv.dragonflyBdd.tables.QuestionResponse;
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

	/**
	 * Private constructor
	 *
	 */
	private ProjectManager() {
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
	 * @throws DragonflyBddException
	 */
	public int createProject(String name,String projectLeader,String resume) throws DragonflyBddException {
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
	 * @throws DragonflyBddException
	 */
	public int createProject(String name,String projectLeader,String resume,Date enDate) throws DragonflyBddException{
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
	 * @throws DragonflyBddException
	 */
	public int createProject(String name, String projectLeader,Date endDate,String resume, String description) throws DragonflyBddException {

		//Test if an account have this email key
		if(isProjectExist(name))
			return 1;
		//Create an user and add it into User's Table
		Transaction tx = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			Date creationDate = new Date(System.currentTimeMillis());
			tx = session.beginTransaction();

			if(AM_INSTANCE.getUser(projectLeader)==null)
				return 2;

			Project project = new Project(name,AM_INSTANCE.getUserNickname(projectLeader),creationDate);
			ProjectDescription projectDescription = new ProjectDescription(resume,project);
			projectDescription.setDescription(description);
			project.setDescription(projectDescription);

			project.getUsers().add(AM_INSTANCE.getUser(projectLeader));

			if(endDate != null)
				project.setEndDate(endDate);
			session.save(project);
			session.save(projectDescription);
			tx.commit();
		}catch(ConstraintViolationException e){
			//if this exception is catch : A project with the same name already Exist
			if(tx != null)
				tx.rollback();
			return 2;
		}catch (Exception e) {
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();

		}
		//Ok
		return 0;
	}

	/**
	 * Remove a Project from database
	 *
	 * @param name The project's key. Needed to remove the project from Project's Table
	 * @return true if the project have been dropped successfuly, false otherwise.
	 * @throws DragonflyBddException
	 */
	public boolean removeProject(String name) throws DragonflyBddException{
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			Project project = getProject(name);
			if(project == null)
				return false;
			ProjectDescription description = project.getDescription();
			session.delete(description);
			session.delete(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();

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
	 * @throws DragonflyBddException
	 */
	public boolean changeEndDate(String name, Date newEndDate) throws DragonflyBddException{
		Project project = getProject(name);

		if(project == null)
			return false;

		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			project.setEndDate(newEndDate);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();

		}
		return true;
	}

	/**
	 * Activate a Project by the admin (optional)
	 * @param name The name of the project to activate
	 * @return true if the project have been successfully activated, false oherwise
	 * @throws DragonflyBddException
	 */
	public boolean activateProject(String name) throws DragonflyBddException{
		Project project = getProject(name);

		if(project == null)
			return false;

		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			project.setActived(true);
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();

		}
		return true;
	}



	/**
	 * Get all The Project present in Project's Table
	 *
	 * @return A list with all Project present in Project's Table.
	 * @throws DragonflyBddException
	 */
	@SuppressWarnings("unchecked")
	public List<Project> getAllProject() throws DragonflyBddException{
		Transaction tr = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			return session.createQuery("from Project").list();
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
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
	 * @throws DragonflyBddException
	 */
	public long addTask(String project, String author, String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end) throws DragonflyBddException {

		Transaction tr = null;
		Task task = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project proj = (Project)session.get(Project.class,project);
			if(project == null)
				return -1;

			Collection<Task> collection = proj.getTasks();
			if(collection == null){
				proj.setTasks(new LinkedList<Task>());
				collection = proj.getTasks();
			}
			task = new Task(post,author,subj,descr,start,end);
			collection.add(task);
			session.update(proj);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}

		return task.getTask_id();
	}

	/**
	 * Remove a Task for the named Project
	 * @param name The name of the project to delete the Task
	 * @param task_id The task id of the Task to delete
	 * @return true if the delete have been done, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean removeTask(String name, String task_id) throws DragonflyBddException{

		long id = 0;

		Transaction tr = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project project = (Project)session.get(Project.class,name);
			if(project == null)
				return false;

			Collection<Task> coll = project.getTasks();
			try{
				id = Long.parseLong(task_id);
			}catch(NumberFormatException e){
				System.err.println("Task_id cannot be converted to long");
				return false;
			}

			Task t = (Task) session.get(Task.class, id);

			if(t!=null)
				coll.remove(t);
			else
				return false;
			session.update(project);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

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
	 * @throws DragonflyBddException
	 */
	public long addMeeting(String project, String author, String subj, String description, java.util.Date post, java.util.Date date) throws DragonflyBddException {

		Transaction tr = null;
		Meeting meeting = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project proj = (Project)session.get(Project.class,project);
			if(project == null)
				return -1;

			Collection<Meeting> collection = proj.getMeetings();
			if(collection == null){
				proj.setMeetings(new LinkedList<Meeting>());
				collection = proj.getMeetings();
			}
			meeting = new Meeting(post,author,subj,date,description);
			collection.add(meeting);
			session.update(proj);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
		return meeting.getMeeting_id();
	}

	/**
	 * Remove a Meeting for the named Project
	 * @param name The name of the project to delete the Meeting
	 * @param meet_id The meeting id of the Meeting to delete
	 * @return true if the delete have been done, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean removeMeeting(String name, String meet_id) throws DragonflyBddException {

		long id = 0;

		Transaction tr = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project project = (Project)session.get(Project.class,name);
			if(project == null)
				return false;

			Collection<Meeting> coll = project.getMeetings();
			try{
				id = Long.parseLong(meet_id);
			}catch(NumberFormatException e){
				System.err.println("meet_id cannot be converted to long");
				return false;
			}

			Meeting meeting = (Meeting) session.get(Meeting.class, id);

			if(meeting!=null)
				coll.remove(meeting);
			else
				return false;

			session.update(project);
			tr.commit();
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

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
	 * @throws DragonflyBddException
	 */
	public long addNews(String proj, String author, java.util.Date post, String subj, String description) throws DragonflyBddException {

		Transaction tr = null;
		News news = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project project = (Project)session.get(Project.class,proj);
			if(project == null)
				return -1;

			Collection<News> collection = project.getNews();
			if(collection == null){
				project.setNews(new LinkedList<News>());
				collection = project.getNews();
			}
			news = new News(post,author,subj,description);
			collection.add(news);
			session.update(project);
			tr.commit();
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
		return news.getNews_id();
	}

	/**
	 * Remove a News for the named Project
	 * @param name The name of the project to delete the News
	 * @param news_id The meeting id of the Meeting to delete
	 * @return true if the delete have been done, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean removeNews(String name, String news_id) throws DragonflyBddException {

		long id = 0;

		Transaction tr = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project project = (Project)session.get(Project.class,name);
			if(project == null)
				return false;

			Collection<News> coll = project.getNews();
			try{
				id = Long.parseLong(news_id);
			}catch(NumberFormatException e){
				System.err.println("news_id cannot be converted to long");
				return false;
			}

			News news = (News) session.get(News.class, id);

			if(news!=null)
				coll.remove(news);
			else
				return false;

			session.update(project);
			tr.commit();
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
		return true;
	}


	/**
	 * Add a QuestionResponse object to the FAS of the named Project
	 * @param proj The name of the project
	 * @param question The question
	 * @param response The response
	 * @return true if the question response have been added successfully - false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException {

		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project project = (Project)session.get(Project.class,proj);
			if(project == null)
				return false;

			Collection<QuestionResponse> collection = project.getFaq();
			if(collection == null){
				collection = new LinkedList<QuestionResponse>();
				project.setFaq(collection);
			}

			QuestionResponse questionResponse = new QuestionResponse(question,response);
			collection.add(questionResponse);
			session.update(project);
			tr.commit();
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();
		}
		return true;
	}

	/**
	 * Remove a Question Response from the FAQ of the named Project
	 * @param name The name of the Project
	 * @param qr_id
	 * @return
	 * @throws DragonflyBddException
	 */
	public boolean removeQuestionResponse(String name, String qr_id) throws DragonflyBddException {

		long id = 0;

		Transaction tr = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project project = (Project)session.get(Project.class,name);
			if(project == null)
				return false;

			Collection<QuestionResponse> coll = project.getFaq();
			try{
				id = Long.parseLong(qr_id);
			}catch(NumberFormatException e){
				System.err.println("qr_id cannot be converted to long");
				return false;
			}

			QuestionResponse questionResponse = (QuestionResponse) session.get(QuestionResponse.class, id);

			if(questionResponse!=null)
				coll.remove(questionResponse);
			else
				return false;

			session.update(project);
			tr.commit();
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
		return true;
	}

	/**
	 * Edit a Question Response from the FAQ of the named Project
	 * @param name The name of the Project
	 * @param qr_id
	 * @return
	 * @throws DragonflyBddException
	 */
	public boolean editQuestionResponse(String name, String qr_id,String question, String response) throws DragonflyBddException {

		long id = 0;

		Transaction tr = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project project = (Project)session.get(Project.class,name);
			if(project == null)
				return false;

			try{
				id = Long.parseLong(qr_id);
			}catch(NumberFormatException e){
				System.err.println("qr_id cannot be converted to long");
				return false;
			}

			QuestionResponse questionResponse = (QuestionResponse) session.get(QuestionResponse.class, id);

			if(questionResponse!=null){
				questionResponse.setQuestion(question);
				questionResponse.setResponse(response);
			} else
				return false;

			session.update(project);
			tr.commit();
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
		return true;
	}

	/**
	 * Test if a project exist in DataBase
	 *
	 * @param name
	 * @return
	 * @throws DragonflyBddException
	 */
	public boolean isProjectExist(String name) throws DragonflyBddException{
		Project project = getProject(name);
		return (project != null);
	}


	/**
	 * Get the Project Object with this name
	 *
	 * @param name The key to get this project in the Project's Table from DataBase.
	 * @return The Project Object associated with this name, null otherwise (if doesn't exist in DataBase).
	 * @throws DragonflyBddException
	 */
	public Project getProject(String name) throws DragonflyBddException {
		Transaction tr =null;
		Session session = null;
		try{

			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			//Try to get the Project with this name key
			return (Project)session.get(Project.class,name);

		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
	}

	/**
	 * Get the Project Description Object for the named Project
	 * @param name The key to get this project in the Project's Table from DataBase.
	 * @return The Project Object associated with this name, null otherwise (if doesn't exist in DataBase).
	 * @throws DragonflyBddException
	 */
	public ProjectDescription getProjectDescription(String name) throws DragonflyBddException {
		Transaction tr =null;
		Session session = null;
		try{

			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			Project project = (Project)session.get(Project.class,name);
			return project.getDescription();

		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}

	}

	/**
	 * Get the Project Users for the named Project
	 * @param name The key to get this project in the Project's Table from DataBase.
	 * @return
	 * @return The Project Users associated with this name, null otherwise (if doesn't exist in DataBase).
	 * @throws DragonflyBddException
	 */
	public  Collection<User> getProjectUsers(String name) throws DragonflyBddException{
		Transaction tr =null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			Project project = (Project)session.get(Project.class,name);
			if(project == null)
				return Collections.emptySet();
			return Collections.unmodifiableCollection(new HashSet<User>(project.getUsers()));

		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
	}

	/**
	 * Return all The Tasks for the named project
	 *
	 * @param project The name of the project to get The Tasks
	 * @return an unmodifiableCollection with all the Tasks for this project, a empty Collection otherwise
	 * @throws DragonflyBddException
	 */
	public Collection<Task> getProjectTasks(String project) throws DragonflyBddException {
		Transaction tr =null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project proj = (Project)session.get(Project.class,project);
			if(proj == null)
				return Collections.unmodifiableCollection(new ArrayList<Task>());
			Collection<Task> list = proj.getTasks();
			if(list == null)
				return Collections.unmodifiableCollection(new ArrayList<Task>());
			return Collections.unmodifiableCollection(new ArrayList<Task>(list));
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
	}

	/**
	 * Return all The Meetings for the named project
	 *
	 * @param project The name of the project to get The Meetings
	 * @return an unmodifiableCollection with all the Meetings for this project, a empty Collection otherwise
	 * @throws DragonflyBddException
	 */
	public Collection<Meeting> getProjectMeetings(String project) throws DragonflyBddException {
		Transaction tr =null;
		Session session = null;
		try{

			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project proj = (Project)session.get(Project.class,project);

			if(proj == null)
				return Collections.unmodifiableCollection(new ArrayList<Meeting>());

			Collection<Meeting> list = proj.getMeetings();

			if(list == null)
				return Collections.unmodifiableCollection(new ArrayList<Meeting>());

			return Collections.unmodifiableCollection(new ArrayList<Meeting>(list));

		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
	}

	/**
	 * Return all The News for the named project
	 *
	 * @param project The name of the project to get The News
	 * @return an unmodifiableCollection with all the News for this project, a empty Collection otherwise
	 * @throws DragonflyBddException
	 */
	public Collection<News> getProjectNews(String project) throws DragonflyBddException {
		Transaction tr =null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project proj = (Project)session.get(Project.class,project);

			if(proj == null)
				return Collections.unmodifiableCollection(new ArrayList<News>());
			Collection<News> list = proj.getNews();
			if(list == null)
				return Collections.unmodifiableCollection(new ArrayList<News>());
			return Collections.unmodifiableCollection(new ArrayList<News>(list));

		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
	}

	/**
	 * Return all The FAQ's QuestionResponse for the named project
	 *
	 * @param project The name of the project to get The News
	 * @return an unmodifiableCollection with all the News for this project, a empty Collection otherwise
	 * @throws DragonflyBddException
	 */
	public Collection<QuestionResponse> getProjectFAQ(String project) throws DragonflyBddException {
		Transaction tr =null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			Project proj = (Project)session.get(Project.class,project);

			if(proj == null)
				return Collections.unmodifiableCollection(new ArrayList<QuestionResponse>());
			Collection<QuestionResponse> list = proj.getFaq();
			if(list == null)
				return Collections.unmodifiableCollection(new ArrayList<QuestionResponse>());
			return Collections.unmodifiableCollection(new ArrayList<QuestionResponse>(list));

		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
	}

	/**
	 *  Add an user to the named project
	 *
	 * @param project The name of the project where the user must be added
	 * @param mail The id of the user to add to the project
	 * @return true if the user have been added - false if the user is already in project
	 * @throws DragonflyBddException
	 */
	public boolean addUserToProject(String project, String mail) throws DragonflyBddException {
		Transaction tr =null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			Project proj = (Project)session.get(Project.class,project);

			//Chek if the user exist
			if(AccountManager.getAccountManagerInstance().getUser(mail)==null)
				return false;

			List result = session.createFilter(proj.getUsers(), "where this.email='"+mail+"'").list();

			if(result.size()!=0){
				//User already exist in project
				return false;
			}
			//the user doesn't exist in this project
			User user = AccountManager.getAccountManagerInstance().getUser(mail);
			proj.getUsers().add(user);
			session.update(proj);
			tr.commit();	
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
		return true;
	}
	/**
	 *  Remove an user to the named project
	 *
	 * @param project The name of the project where the user must be added
	 * @param mail The id of the user to add to the project
	 * @return true if the user have been added - false if the user is already in project
	 * @throws DragonflyBddException
	 */
	public boolean removeUserFromProject(String project, String mail) throws DragonflyBddException {
		Transaction tr =null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			Project proj = (Project)session.get(Project.class,project);

			//Chek if the user exist
			if(AccountManager.getAccountManagerInstance().getUser(mail)==null)
				return false;

			List result = session.createFilter(proj.getUsers(), "where this.email='"+mail+"'").list();
			
			if(result.size()==0){
				//User doesn't exist in project
				return false;
			}
			//the user exist in this project
			User user = (User) result.get(0);
			//Test if he is the projectLeader
			if(user.getNickname().equals(proj.getProjectLeader()) ){
				return false;
                        }
			proj.getUsers().remove(user);
			session.update(proj);
			tr.commit();
			return true;
		}catch (Exception e) {
			if(tr != null)
				tr.rollback();
			throw new DragonflyBddException(e.getMessage());
		}finally {
			session.close();

		}
	}

	/**
	 * Get a user's project's list.
	 * @param email the user's username.
	 * @return the project's list.
	 * @throws DragonflyBddException
	 */
	public List<Project> getUserProjects(String email) throws DragonflyBddException {
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			
			//Check if the user exist
			if(AccountManager.getAccountManagerInstance().getUser(email)==null)
				return Collections.emptyList();
			
			Criteria criteria = session.createCriteria(Project.class)
				.createCriteria("users")
				.add(Restrictions.eq("email", email));

			List<Project> projectsList = criteria.list();
			
			if(projectsList.size()==0)
				return Collections.emptyList();
			
			return projectsList;
		} catch(Exception e) {
			if(tr != null) {
				tr.rollback();
			}
			throw new DragonflyBddException(e);
		} finally {
			session.close();
		}
	}



}
