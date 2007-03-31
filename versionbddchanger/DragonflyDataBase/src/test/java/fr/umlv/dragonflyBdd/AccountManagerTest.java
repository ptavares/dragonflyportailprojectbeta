/**
 * 
 */
package fr.umlv.dragonflyBdd;

import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;
import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Message;
import fr.umlv.dragonflyBdd.tables.Roles;
import fr.umlv.dragonflyBdd.tables.User;

/**
 * JUnit Test Class for {@link ffr.umlv.dragonflyBdd.AccountManager}
 * @author Tavares Patrick 
 *
 */
public class AccountManagerTest extends TestCase {

	private static final String mail = "user@test.org";
	private static String pass = "password";
	private static String nickname = "user";

	private final AccountManager am = AccountManager.getAccountManagerInstance();  

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		for(int i=0;i<10;i++) {
			String user = "user_"+i;
			am.createAccount(user+"@test.org",user,pass+"_"+i);
			am.activateAccount(user);
		}
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		for(int i=0;i<10;i++) {
			String user = "user_"+i;
			am.removeAccount(user+"@test.org");
		}
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#getAccountManagerInstance()}.
	 */
	public void testGetAccountManagerInstance() {
		assertNotNull(AccountManager.getAccountManagerInstance());
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#createAccount(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testCreateAccount() throws DragonflyBddException {
		//Account created
		assertEquals(am.createAccount(mail, nickname, pass),0);
		//Check if the User exist
		User user = am.getUser(mail);
		assertNotNull(user);

		//Account already exist, must return 1
		assertEquals(am.createAccount(mail, nickname,pass),1);

	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#removeAccount(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testRemoveAccount() throws DragonflyBddException {
		String toDelete = "user_6@test.org";
		//Delete OK
		assertTrue(am.removeAccount(toDelete));
		User user = am.getUser(toDelete);
		assertNull(user);

		//Delete impossible
		assertFalse(am.removeAccount(toDelete));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#changeNickmane(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testChangeNickmane() throws DragonflyBddException {

		//Change nickname
		assertTrue(am.changeNickmane(mail, "NewNickname"));

		//Nickname Allready exist now
		assertFalse(am.changeNickmane(mail, "NewNickname"));
		
		//Test
		assertEquals(am.getUserNickname(mail),"NewNickname");

		assertTrue(am.changeNickmane(mail, nickname));

	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#activateAccount(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testActivateAccount() throws DragonflyBddException {
		String userMail1 = "user_1@test.org";
		String userMail2 = "user_2@text2.org";
		User user = null;

		user = am.getUser(userMail1);
		assertFalse(user.isActived());
		assertTrue(am.activateAccount(userMail1));
		user = am.getUser(userMail1);
		assertTrue(user.isActived());
		
		assertNull(am.getUser(userMail2));
		assertFalse(am.activateAccount(userMail2));

	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#changePassword(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testChangePassword() throws DragonflyBddException {
		assertTrue(am.isPasswordCorrect(mail, pass));
		assertTrue(am.changePassword(mail, "newPassword"));
		assertTrue(am.isPasswordCorrect(mail, "newPassword"));
		assertFalse(am.isPasswordCorrect(mail, pass));
		assertTrue(am.changePassword(mail, pass));
		assertTrue(am.isPasswordCorrect(mail, pass));
		assertFalse(am.isPasswordCorrect(mail, "newPassword"));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#isAccountExist(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testIsAccountExist() throws DragonflyBddException {
		assertTrue(am.isAccountExist(mail));
		assertFalse(am.isAccountExist("MailNotDefined"));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#getUser(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetUser() throws DragonflyBddException {
		User user = am.getUser("user_6@test.org");
		assertNotNull(user);
		User nonExistentUser = am.getUser("nonExistentUser@test.org");
		assertNull(nonExistentUser);
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#getAllUser()}.
	 * @throws DragonflyBddException 
	 */
	public void testGetAllUser() throws DragonflyBddException {
		List<User> users = am.getAllUser();
		assertFalse(users.isEmpty());
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#isAuthentificationCorrect(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testIsAuthentificationCorrect() throws DragonflyBddException {
		//The account must be activated before
		assertTrue(am.activateAccount(mail));
		assertTrue(am.isAuthentificationCorrect(mail, pass));
		assertFalse(am.isAuthentificationCorrect(mail, pass + "notGood"));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#getUserNickname(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetUserNickname() throws DragonflyBddException {
		assertEquals(am.getUserNickname(mail),nickname);
		assertNotSame(am.getUserNickname(mail),nickname+"test");
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#isPasswordCorrect(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testIsPasswordCorrect() throws DragonflyBddException {
		assertTrue(am.isPasswordCorrect(mail, pass));
		assertFalse(am.isPasswordCorrect(mail, pass + "notGood"));
	}

	
	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#addRole(java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 * @throws DragonflyBddException 
	 */
	public void testAddRole() throws DragonflyBddException {
		assertTrue(am.addRole(mail, "user"));
		assertFalse(am.addRole("NonValidEmail@test.org", "user"));
	}
	
	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#getUserRoles(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetUserRoles() throws DragonflyBddException {
		assertTrue(am.addRole("user_5@test.org", "user"));
		Collection<Roles> roles = am.getUserRoles("user_5@test.org");
		assertFalse(roles.isEmpty());
		
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#getMessages(java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testGetMessages() throws DragonflyBddException {
		assertTrue(am.CreateMessage("user_4", mail, "testSubject", "testcontent"));
		Collection<Message> messages = am.getMessages(mail);
		assertFalse(messages.isEmpty());
		long id = messages.iterator().next().getId();
		assertTrue(am.RemoveMessage(mail, id));
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#CreateMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws DragonflyBddException 
	 */
	public void testCreateMessage() throws DragonflyBddException {
		assertTrue(am.CreateMessage("user_2", mail, "testSubject", "testcontent"));
		Collection<Message> messages = am.getMessages(mail);
		assertFalse(messages.isEmpty());
		long id = messages.iterator().next().getId();
		assertTrue(am.RemoveMessage(mail, id));
		
	}

	/**
	 * Test method for {@link fr.umlv.dragonflyBdd.AccountManager#RemoveMessage(java.lang.String, long)}.
	 * @throws DragonflyBddException 
	 */
	public void testRemoveMessage() throws DragonflyBddException {
		assertTrue(am.CreateMessage("user_6", mail, "testSubject", "testcontent"));
		Collection<Message> messages = am.getMessages(mail);
		assertFalse(messages.isEmpty());
		long id = messages.iterator().next().getId();
		assertTrue(am.RemoveMessage(mail, id));
		
		//The last method tested, remove the account created in the first method tested
		assertTrue(am.removeAccount(mail));
	}

}
