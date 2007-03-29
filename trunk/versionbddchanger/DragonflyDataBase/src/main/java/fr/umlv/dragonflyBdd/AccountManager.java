package fr.umlv.dragonflyBdd;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Message;
import fr.umlv.dragonflyBdd.tables.Roles;
import fr.umlv.dragonflyBdd.tables.User;
import fr.umlv.dragonflyBdd.utils.DragonFlyDBManager;


/**
 * This class manages all user's database access.
 *
 * @author Tavares Patrick
 *
 */

public class AccountManager {


	private final static AccountManager AM_INSTANCE = new AccountManager();
	private final static ProjectManager PM_INSTANCE = ProjectManager.getProjectManagerInstance();
	
	private final static Log logger = LogFactoryImpl.getLog(AccountManager.class);

	/**
	 * Private constructor
	 *
	 */
	private AccountManager() {
	}

	/**
	 * Get the only Account Manager instance
	 *
	 * @return The Accont Manager instance
	 */
	public static AccountManager getAccountManagerInstance(){
		return AM_INSTANCE;
	}

	/**
	 * Create an account and add this user to database
	 *
	 * @param email User's mail address - key for User's Table
	 * @param nickname The user's nickname
	 * @param password The user's password
	 * @return 0 - Account created ; 1 - Account with this email allready exist ; 2 - Error during Account creation
	 * @throws DragonflyBddException
	 */
	public int createAccount(String email, String nickname, String password) throws DragonflyBddException{
		logger.info("Enter in createAccount method");
		//Test if an account have this email key
		if(isAccountExist(email)){
			return 1;
		}
		//Create an user and add it into User's Table
		Transaction tx = null;
		Session session = null;
		Date date = new Date(System.currentTimeMillis());
		try{
			session = DragonFlyDBManager.openSession();
			tx = session.beginTransaction();
			User user = new User(email,date,nickname,password);
			session.save(user);
			tx.commit();
			//Ok
			return 0;
		}catch(ConstraintViolationException e){
			logger.error("Error ConstraintViolationException while trying create User "
					+email+" at "+date+" error message ->"+e.getConstraintName()
					+" ConstraintName ->"+e.getConstraintName()+" SQLstate ->"+e.getSQLState());
			//In case of exception
			if(tx != null)
				tx.rollback();
			return 2;
		}catch (Exception e) {
			logger.error("Exception while trying create User "+email+" at "+date+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			logger.info("User "+email+" have been created at "+date);
			logger.info("leave createAccount method");
			session.close();
		}
	}

	/**
	 * Remove an account from database
	 *
	 * @param email The user's key. Needed to remove the account from User's Table
	 * @return true if the account have been dropped successfuly, false otherwise.
	 * @throws DragonflyBddException
	 */
	public boolean removeAccount(String email) throws DragonflyBddException{
		logger.info("Enter in removeAccount method");
		/**
		 * TODO : supprimer cet utilisateur des projets auxquels il est affect锟�
		 * TODO : Attention au cas ou il est le chef de projet d'un projet ^^''
		 */
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			User user = (User) session.get(User.class,email);
			if(user == null)
				return false;
			session.delete(user);
			tr.commit();

		}catch (Exception e) {
			logger.error("Exception while trying removed User "+email+" at "+new Date(System.currentTimeMillis())+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();

		}
		logger.info("User "+email+" have been removed at "+new Date(System.currentTimeMillis()));
		logger.info("leave removeAccount method");
		//Account removed
		return true;
	}

	/**
	 * Change an user's Nickname
	 *
	 * @param email The user's key. Needed to change the nickname 
	 * @param newNickName The new Nickname
	 * @return true if the nickname have been changed, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean changeNickmane(String email, String newNickName) throws DragonflyBddException{
		logger.info("Enter in changeNickmane method");
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			User user = (User) session.get(User.class,email);

			if(user == null)
				return false;

			user.setNickname(newNickName);
			session.update(user);
			tr.commit();
		}catch (Exception e) {
			logger.error("Exception while trying change User "+email+" nickname at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();

		}
		logger.info("User "+email+" nickname have been change at "+
				new Date(System.currentTimeMillis()));
		logger.info("leave changeNickmane method");
		return true;
	}

	/**
	 * Activate an account by the admin of the site (optional)
	 * @param email The user's key. Needed to remove activate an account
	 * @return true if the activation succed, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean activateAccount(String email) throws DragonflyBddException{
		logger.info("Enter in activateAccount method");
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();

			User user = (User) session.get(User.class,email);

			if(user == null)
				return false;

			user.setActived(true);
			session.update(user);
			tr.commit();
		}catch (Exception e) {
			logger.error("Exception while trying activate User "+email+" account at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();

		}
		logger.info("User "+email+" account have been activated at "+
				new Date(System.currentTimeMillis()));
		logger.info("leave activateAccount method");
		return true;
	}


	/**
	 * Change an user's Password
	 *
	 * @param email The user's key. Needed to change user Password
	 * @param newPassword The new Password for this user
	 * @return true if the password have been changed, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean changePassword(String email, String newPassword) throws DragonflyBddException{
		logger.info("Enter in changePassword method");
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			User user = (User) session.get(User.class,email);

			if(user == null)
				return false;

			user.setPassword(newPassword);
			session.update(user);
			tr.commit();
		}catch (Exception e) {
			logger.error("Exception while trying change User "+email+" password at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
		}
		logger.info("User "+email+" password have been changed at "+
				new Date(System.currentTimeMillis()));
		logger.info("leave changePassword method");
		return true;
	}

	/**
	 * Test if an account exist in DataBase
	 *
	 * @param email
	 * @return
	 * @throws DragonflyBddException
	 */
	public boolean isAccountExist(String email) throws DragonflyBddException{
		logger.info("Enter in isAccountExist method");
		User user = getUser(email);
		logger.info("leave isAccountExist method");
		return (user != null);
	}

	/**
	 * Get the User Object with this email
	 *
	 * @param email The key to get this user in the User's Table from DataBase.
	 * @return The User Object associated with this email, null otherwise (if doesn't exist in DataBase).
	 * @throws DragonflyBddException
	 */
	public User getUser(String email) throws DragonflyBddException {
		logger.info("Enter in getUser method");
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			//Try to get the user with this email key
			return (User)session.get(User.class,email);
		}catch (Exception e) {
			tr.rollback();
			logger.error("Exception while trying getUser with email "+email+" at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			logger.info("getUser with email "+email+" at "+
					new Date(System.currentTimeMillis()));
			logger.info("leave getUser method");
			session.close();
		}
		
	}

	/**
	 * Returns all The User's in Database
	 * @return A list of Users
	 * @throws DragonflyBddException
	 */
	public List<User> getAllUser() throws DragonflyBddException{
		logger.info("Enter in getAllUser method");
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			return session.createQuery("from User").list();
		}catch (Exception e) {
			tr.rollback();
			logger.error("Exception while trying getAllUser at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			logger.info("leave getAllUser method");
			session.close();

		}
	}

	/**
	 * Check if the email corresponds with the password
	 *
	 * @param email The emain given by the user
	 * @param password The password given by the user
	 * @return true if the email have the good password, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean isAuthentificationCorrect(String email, String password) throws DragonflyBddException {
		logger.info("Enter in isAuthentificationCorrect method");
		User user = getUser(email);
		if(user == null){
			logger.info("leave isAuthentificationCorrect method without find User with email "+email);
			return false;
		}
		if(!user.isActived()){
			logger.info("leave isAuthentificationCorrect method with User with email "+email+" non actived");
			return false;
		}
		logger.info("leave isAuthentificationCorrect method");
		return user.getPassword().equals(password);

	}

	/**
	 * Return the user nickname
	 * @param mail The user's key. Needed to get his nickname
	 * @return The user's nickname if exist, null otherwise
	 * @throws DragonflyBddException
	 */
	public String getUserNickname(String mail) throws DragonflyBddException {
		logger.info("Enter in getUserNickname method");
		logger.info("leave getUserNickname method");
		return getUser(mail).getNickname();
	}

	/**
	 * 
	 * @param mail
	 * @param passwd
	 * @return
	 * @throws DragonflyBddException
	 */
	public boolean isPasswordCorrect(String mail, String passwd) throws DragonflyBddException {
		logger.info("Enter in isPasswordCorrect method");
		String pass = getUser(mail).getPassword();
		if(passwd.compareTo(pass)==0){
			logger.info("leave isPasswordCorrect method");
			return true;
		}
		logger.info("leave isPasswordCorrect method");
		return false;
	}


	/**
	 * @throws DragonflyBddException
	 *
	 */
	public Collection<Roles> getUserRoles(String userId) throws DragonflyBddException{
		logger.info("Enter in getUserRoles method");
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			//Try to get the user with this email key
			User user = (User)session.get(User.class,userId);
			Collection<Roles> coll =  user.getRoles();
			if(coll == null)
				return Collections.emptyList();
			return Collections.unmodifiableCollection(new ArrayList<Roles>(coll));
		}catch (Exception e) {
			tr.rollback();
			logger.error("Exception while trying getUserRoles for User "+userId+" at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			logger.info("leave getUserRoles method");
			session.close();

		}
	}

	/**
	 * Add role to User
	 * @param email The user's key. Needed to add a role
	 * @param role The user Role (admin, user, etc...)
	 * @return true if the role have been added successfully, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean addRole(String email, String role) throws DragonflyBddException {
		logger.info("Enter in addRole method");
		Transaction tr = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			User user = (User)session.get(User.class,email);

			if(user == null)
				return false;

			user.getRoles().add(new Roles(role));
			session.update(user);
			tr.commit();
			return true;
		}catch (Exception e) {
			tr.rollback();
			logger.error("Exception while trying addRole for User "+email+" at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			logger.info("leave addRole method");
			session.close();
		}
	}

	/**
	 * Return all the user's messages
	 * @param UserID The user's key. Needed to get all his message from database
	 * @return a Collection of message if exist, null otherwise
	 * @throws DragonflyBddException
	 */
	public  Collection<Message> getMessages(String UserID) throws DragonflyBddException{
		logger.info("Enter in getMessages method");
		Transaction tr = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			User user=(User)session.get(User.class,UserID);
			if(user==null)
				return null;

			return Collections.unmodifiableCollection(new ArrayList<Message>(user.getMessages()));
		}catch (Exception e) {
			tr.rollback();
			logger.error("Exception while trying getMessages for User "+UserID+" at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			logger.info("leave getMessages method");
			session.close();

		}
	}

	/**
	 * Create a mesasge and add it into the user's message
	 * @param sender The sender of the message
	 * @param addressee The expeditor of the message
	 * @param name The subject of the message
	 * @param content The body of the message
	 * @return true if the message have been created and added, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean CreateMessage(String sender, String addressee, String name, String content) throws DragonflyBddException{
		logger.info("Enter in CreateMessage method");
		Transaction trans = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			trans=session.beginTransaction();

			User user = (User)session.get(User.class,addressee);

			if(user == null )
				return false;

			Collection<Message> messages=user.getMessages();
			if(messages==null){
				user.setMessages(new LinkedList<Message>());
				messages=user.getMessages();
			}

			java.util.Date date=new java.util.Date();
			Message mes=new Message(name,sender,content,date,false);
			messages.add(mes);
			session.update(user);
			trans.commit();
			return true;
		}catch (Exception e) {
			trans.rollback();
			logger.error("Exception while trying CreateMessage for User "+addressee+" at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			logger.info("leave CreateMessage method");
		}
	}

	/**
	 * Remove a message for the user
	 * @param user_id The user's key. Needed to remove the message
	 * @param id the id of the message to remove
	 * @return true if the message have been removed, false otherwise
	 * @throws DragonflyBddException
	 */
	public boolean RemoveMessage(String user_id, long id) throws DragonflyBddException{
		logger.info("Enter in RemoveMessage method");
		Transaction trans = null;
		Session session = null;
		try {

			session = DragonFlyDBManager.openSession();
			trans=session.beginTransaction();

			User user = (User) session.get(User.class,user_id);

			if(user == null)
				return false;

			Collection<Message> messages=user.getMessages();

			Message message = (Message) session.get(Message.class, id);

			if(message != null)
				messages.remove(message);

			session.update(user);
			trans.commit();
			return true;
		}catch (Exception e) {
			logger.error("Exception while trying RemoveMessage for User "+user_id+" at "+new Date(System.currentTimeMillis())
			+" message->"+e.getMessage());
			throw new DragonflyBddException(e.getMessage());
		} finally {
			logger.info("Enter in RemoveMessage method");
			session.close();
		}
	}	
}
