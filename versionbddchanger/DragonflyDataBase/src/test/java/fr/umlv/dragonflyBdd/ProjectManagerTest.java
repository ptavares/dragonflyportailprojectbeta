/**
 * 
 */
package fr.umlv.dragonflyBdd;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import junit.framework.TestCase;
import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyBdd.tables.QuestionResponse;

/**
 * JUnit Test Class for {@link fr.umlv.dragonflyBdd.ProjectManager}
 * @author Tavares Patrick 
 */
public class ProjectManagerTest extends TestCase {

	/**
	 * Project 
	 */
	private static final String projectName = "ProjectTest";
	private static final String resume = "resume";
	private static final String description= "description";

	/**
	 * User for the Project Leader
	 */
	private static final String email = "user@test.org";
	private static String password = "password";
	private static String nickname = "user";

	/**
	 * The Managers 
	 */
	private final ProjectManager pm = ProjectManager.getProjectManagerInstance();
	private final AccountManager am = AccountManager.getAccountManagerInstance();

	/**
	 * Utils
	 */
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d hh:mm:ss yyyy");
	private String user2 = "user2@test.org";
	private String userToAdd = "userToadd@test.org";
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		am.createAccount(email, nickname, password);
		am.activateAccount(email);
		
		am.createAccount(userToAdd, nickname, password);
		am.activateAccount(userToAdd);
		
		am.createAccount(user2, nickname, password);
		am.activateAccount(user2);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		am.removeAccount(email);
		am.removeAccount(userToAdd);
		am.removeAccount(user2);
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getProjectManagerInstance()}.
	 */
	public void testGetProjectManagerInstance() {
		assertNotNull(ProjectManager.getProjectManagerInstance());
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#createProject(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testCreateProjectStringStringString() throws DragonflyBddException {
		//Project created
		assertEquals(pm.createProject(projectName, email, resume),0);
		Project project = pm.getProject(projectName);
		assertNotNull(project);
		//EndDate must be null
		assertNull(project.getEndDate());
		//Project exist
		assertEquals(pm.createProject(projectName, email, resume),1);
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#createProject(java.lang.String, java.lang.String, java.lang.String, java.util.Date)}.
	 * @throws DragonflyBddException 
	 */
	public void testCreateProjectStringStringStringDate() throws DragonflyBddException {
		Date date = new Date(System.currentTimeMillis());
		//Project created
		assertEquals(pm.createProject(projectName+"1", email, resume,date),0);
		Project project = pm.getProject(projectName+"1");
		assertNotNull(project);
		//EndDate muste be equals to date
		assertEquals(dateFormat.format(project.getEndDate()),dateFormat.format(date));
		//Project exist
		assertEquals(pm.createProject(projectName+"1", email, resume,date),1);
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#createProject(java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testCreateProjectStringStringDateStringString() throws DragonflyBddException {
		
		Date date = new Date(System.currentTimeMillis()); 
		//Project created
		assertEquals(pm.createProject(projectName+"2",user2,date,resume,description),0);
		Project project = pm.getProject(projectName+"2");
		assertNotNull(project);
		//EndDate muste be equals to date
		assertEquals(dateFormat.format(project.getEndDate()),dateFormat.format(date));
		//Description must not be null
		assertNotNull(project.getDescription().getDescription());
		//Project exist
		assertEquals(pm.createProject(projectName+"2", user2, date,resume,description),1);
		
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#removeProject(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testRemoveProject() throws DragonflyBddException {
		/**
		 * Remove project1 created in testCreateProjectStringStringStringDate
		 */
		assertTrue(pm.removeProject(projectName+"1"));
		/**
		 * Remove project2 created in testCreateProjectStringStringDateStringString
		 */
		assertTrue(pm.removeProject(projectName+"2"));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#changeEndDate(java.lang.String, java.util.Date)}.
	 * @throws DragonflyBddException 
	 */
	public void testChangeEndDate() throws DragonflyBddException {
		Date date = new Date(System.currentTimeMillis());
		//Project created
		assertEquals(pm.createProject(projectName+"1", email, resume,date),0);
		Project project = pm.getProject(projectName+"1");
		assertNotNull(project);
		//EndDate muste be equals to date
		assertEquals(dateFormat.format(project.getEndDate()),dateFormat.format(date));

		//Change endDate
		Date newdate = new Date(System.currentTimeMillis());
		project.setEndDate(newdate);
		//New EndDate muste be equals to date
		assertEquals(dateFormat.format(project.getEndDate()),dateFormat.format(newdate));


		//Remove project
		assertTrue(pm.removeProject(projectName+"1"));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#activateProject(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testActivateProject() throws DragonflyBddException {
		Project project = pm.getProject(projectName);
		assertNotNull(project);

		//Test
		assertFalse(project.isActived());
		//Project Activation 
		assertTrue(pm.activateProject(projectName));

		project = pm.getProject(projectName);
		assertNotNull(project);
		//Test
		assertTrue(project.isActived());

	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getAllProject()}.
	 * @throws DragonflyBddException 
	 */
	public void testGetAllProject() throws DragonflyBddException {
		assertEquals(pm.createProject(projectName+"1", email, resume),0);
		Project project = pm.getProject(projectName+"1");
		assertNotNull(project);

		assertFalse(pm.getAllProject().isEmpty());

		assertTrue(pm.removeProject(projectName+"1"));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#addTask(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date)}.
	 * @throws DragonflyBddException 
	 */
	public void testAddTask() throws DragonflyBddException {
		long task_id;
		task_id = pm.addTask(projectName, nickname,"task sujb", "task descrip", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
		//assTask return -1 if the task was no added
		assertNotSame(task_id, "-1");
		//Remove task
		assertTrue(pm.removeTask(projectName, String.valueOf(task_id)));
	}


	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#removeTask(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testRemoveTask() throws DragonflyBddException {
		long task_id;
		task_id = pm.addTask(projectName, nickname,"task sujb", "task descrip", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
		//addTask return -1 if the task was no added
		assertNotSame(task_id, "-1");
		//Remove task
		assertTrue(pm.removeTask(projectName, String.valueOf(task_id)));
		//Already Removed, must return false
		assertFalse(pm.removeTask(projectName, String.valueOf(task_id)));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#addMeeting(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)}.
	 * @throws DragonflyBddException 
	 */
	public void testAddMeeting() throws DragonflyBddException {
		long meeting_id;
		meeting_id = pm.addMeeting(projectName, nickname,"meeting sujb", "meeting descrip", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		//addmeeting return -1 if the meeting was no added
		assertNotSame(meeting_id, "-1");
		//Remove meeting
		assertTrue(pm.removeMeeting(projectName, String.valueOf(meeting_id)));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#removeMeeting(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testRemoveMeeting() throws DragonflyBddException {
		long meeting_id;
		meeting_id = pm.addMeeting(projectName, nickname,"meeting sujb", "meeting descrip", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		//addMeeting return -1 if the meeting was no added
		assertNotSame(meeting_id, "-1");
		//Remove meeting
		assertTrue(pm.removeMeeting(projectName, String.valueOf(meeting_id)));
		//Already Removed, must return false
		assertFalse(pm.removeMeeting(projectName, String.valueOf(meeting_id)));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#addNews(java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testAddNews() throws DragonflyBddException {
		long news_id;
		news_id = pm.addNews(projectName, nickname, new Date(System.currentTimeMillis()), "meeting sujb", "meeting descrip");
		//addNews return -1 if the news was no added
		assertNotSame(news_id, "-1");
		//Remove news
		assertTrue(pm.removeNews(projectName, String.valueOf(news_id)));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#removeNews(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testRemoveNews() throws DragonflyBddException {
		long news_id;
		news_id = pm.addNews(projectName, nickname, new Date(System.currentTimeMillis()), "meeting sujb", "meeting descrip");
		//addNews return -1 if the news was no added
		assertNotSame(news_id, "-1");
		//Remove news
		assertTrue(pm.removeNews(projectName, String.valueOf(news_id)));
		//Allready removed
		assertFalse(pm.removeNews(projectName, String.valueOf(news_id)));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#addQuestionResponse(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testAddQuestionResponse() throws DragonflyBddException {
		//Return True if created
		assertTrue(pm.addQuestionResponse(projectName, "question", "response"));
		//The QuestionResponse added is the only in this collection
		Collection<QuestionResponse> faq = pm.getProjectFAQ(projectName);
		assertEquals(faq.size(), 1);
		assertTrue(pm.removeQuestionResponse(projectName, String.valueOf(faq.iterator().next().getQr_id())));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#removeQuestionResponse(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testRemoveQuestionResponse() throws DragonflyBddException {
		//Return True if created
		assertTrue(pm.addQuestionResponse(projectName, "question", "response"));
		//The QuestionResponse added is the only in this collection
		Collection<QuestionResponse> faq = pm.getProjectFAQ(projectName);
		assertEquals(faq.size(), 1);
		String qr_id = String.valueOf(faq.iterator().next().getQr_id()); 
		assertTrue(pm.removeQuestionResponse(projectName, qr_id));
		//Already Removed
		assertFalse(pm.removeQuestionResponse(projectName, qr_id));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#editQuestionResponse(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testEditQuestionResponse() throws DragonflyBddException {
		//Return True if created
		assertTrue(pm.addQuestionResponse(projectName, "question", "response"));
		//The QuestionResponse added is the only in this collection
		Collection<QuestionResponse> faq = pm.getProjectFAQ(projectName);
		assertEquals(faq.size(), 1);
		String qr_id = String.valueOf(faq.iterator().next().getQr_id()); 
		//Edit
		assertTrue(pm.editQuestionResponse(projectName, qr_id, "new question", "new response"));
		//Look if the edit have been done
		faq = pm.getProjectFAQ(projectName);
		assertEquals(faq.iterator().next().getQuestion(), "new question");
		assertEquals(faq.iterator().next().getResponse(), "new response");
		//Remove Question REsponse
		assertTrue(pm.removeQuestionResponse(projectName, qr_id));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#isProjectExist(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testIsProjectExist() throws DragonflyBddException {
		//Project created
		assertEquals(pm.createProject(projectName+"1", email, resume),0);
		//Test if exist
		assertTrue(pm.isProjectExist(projectName+"1"));
		//Remove project
		assertTrue(pm.removeProject(projectName+"1"));
		//Doesn't exist anymore
		assertFalse(pm.isProjectExist(projectName+"1"));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getProject(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetProject() throws DragonflyBddException {
		//ProjectExist
		assertNotNull(pm.getProject(projectName));
		//Project doesn't exist
		assertNull(pm.getProject("NonExistingProject"));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getProjectDescription(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetProjectDescription() throws DragonflyBddException {
		//Test if the projectDescription of the project exist
		assertNotNull(pm.getProject(projectName).getDescription());
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getProjectUsers(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetProjectUsers() throws DragonflyBddException {
		//Test if there is a user in the project "projectName" (normally the ProjectLeader)
		//So collection of Users must not be empty
		assertFalse(pm.getProjectUsers(projectName).isEmpty());
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getProjectTasks(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetProjectTasks() throws DragonflyBddException {
		long task_id;
		task_id = pm.addTask(projectName, nickname,"task sujb", "task descrip", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
		//assTask return -1 if the task was no added
		assertNotSame(task_id, "-1");
		
		//Test if the method return a non empty collection
		assertFalse(pm.getProjectTasks(projectName).isEmpty());
		
		//Remove task
		assertTrue(pm.removeTask(projectName, String.valueOf(task_id)));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getProjectMeetings(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetProjectMeetings() throws DragonflyBddException {
		long meeting_id;
		meeting_id = pm.addMeeting(projectName, nickname,"meeting sujb", "meeting descrip", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		//addmeeting return -1 if the meeting was no added
		assertNotSame(meeting_id, "-1");
		
		//Test if the method return a non empty collection
		assertFalse(pm.getProjectMeetings(projectName).isEmpty());
		
		//Remove meeting
		assertTrue(pm.removeMeeting(projectName, String.valueOf(meeting_id)));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getProjectNews(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetProjectNews() throws DragonflyBddException {
		long news_id;
		news_id = pm.addNews(projectName, nickname, new Date(System.currentTimeMillis()), "meeting sujb", "meeting descrip");
		//addNews return -1 if the news was no added
		assertNotSame(news_id, "-1");
		
		//Test if the method return a non empty collection
		assertFalse(pm.getProjectNews(projectName).isEmpty());
		
		//Remove news
		assertTrue(pm.removeNews(projectName, String.valueOf(news_id)));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getProjectFAQ(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetProjectFAQ() throws DragonflyBddException {
		//Return True if created
		assertTrue(pm.addQuestionResponse(projectName, "question", "response"));
		
		//Test if the method return a non empty collection
		assertFalse(pm.getProjectFAQ(projectName).isEmpty());
		
		//The QuestionResponse added is the only in this collection
		Collection<QuestionResponse> faq = pm.getProjectFAQ(projectName);
		assertEquals(faq.size(), 1);
		assertTrue(pm.removeQuestionResponse(projectName, String.valueOf(faq.iterator().next().getQr_id())));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#addUserToProject(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testAddUserToProject() throws DragonflyBddException {
		
		//Add an existing account non yet added into Project
		assertTrue(pm.addUserToProject(projectName, userToAdd));
		
		//Try to add an existing account allready added into Project
		assertFalse(pm.addUserToProject(projectName, userToAdd));
		
		//Try to add a non existing account into Project
		assertFalse(pm.addUserToProject(projectName, "nonExistingAccount@test.org"));
		
	}
	
	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.ProjectManager#getUserProjects(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testgetUserProjects() throws DragonflyBddException {
		
		//User must be in projectName
		assertFalse(pm.getUserProjects(email).isEmpty());
		
		//Try to get project from a non existing Account
		assertTrue(pm.getUserProjects("nonExistingAccount@test.org").isEmpty());
		
		
		//Remove the prject created at start
		assertTrue(pm.removeProject(projectName));
	}
}
