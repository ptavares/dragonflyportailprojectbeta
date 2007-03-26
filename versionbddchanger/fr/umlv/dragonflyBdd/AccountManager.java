package fr.umlv.dragonflyBdd;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
		//Test if an account have this email key
		if(isAccountExist(email))
			return 1;

		//Create an user and add it into User's Table
		Transaction tx = null;
		Session session = null;
		try{
			session = DragonFlyDBManager.openSession();
			Date date = new Date(System.currentTimeMillis());
			tx = session.beginTransaction();
			User user = new User(email,date,nickname,password);
			session.save(user);
			tx.commit();
		}catch(ConstraintViolationException e){
			//In case of exception
			if(tx != null)
				tx.rollback();
			return 2;
		}catch (Exception e) {
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
		}
		//Ok
		return 0;
	}

	/**
	 * Remove an account from database
	 * 
	 * @param email The user's key. Needed to remove the account from User's Table 
	 * @return true if the account have been dropped successfuly, false otherwise.
	 * @throws DragonflyBddException 
	 */
	public boolean removeAccount(String email) throws DragonflyBddException{
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
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
		}
		//Account removed
		return true;
	}

	/**
	 * Change an user's Nickname
	 * 
	 * @param email
	 * @param newNickName
	 * @return
	 * @throws DragonflyBddException 
	 */
	public boolean changeNickmane(String email, String newNickName) throws DragonflyBddException{

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
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
		}
		return true;
	}

	/**
	 * Change an user's Password
	 * 
	 * @param email
	 * @param newPassword
	 * @return
	 * @throws DragonflyBddException 
	 */
	public boolean changePassword(String email, String newPassword) throws DragonflyBddException{

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
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
		}
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
		User user = getUser(email);
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
		Transaction tr = null;
		Session session = null;
		try {
			session = DragonFlyDBManager.openSession();
			tr = session.beginTransaction();
			//Try to get the user with this email key
			return (User)session.get(User.class,email);
		}catch (Exception e) {
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
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
		User user = getUser(email);
		if(user == null)
			return false;
		return user.getPassword().equals(password);

	}

	public String getUserNickname(String mail) throws DragonflyBddException {
		System.out.println("AccountManger getUserNickname");
		return getUser(mail).getNickname();
	}

	public boolean isPasswordCorrect(String mail, String passwd) throws DragonflyBddException {
		String pass = getUser(mail).getPassword();
		System.out.println("Password : "+pass);
		if(passwd.compareTo(pass)==0)
			return true;
		return false;
	}


	/**
	 * @throws DragonflyBddException 
	 * 
	 */
	public List<String> getUserRoles(String user) throws DragonflyBddException{
		User u = getUser(user);
		if(u==null)
			return null;
		Collection<Roles> coll =  u.getRoles();
		List<String> set = new ArrayList<String>();
		for(Roles r: coll)
			set.add(r.getRole());

		return set;
	}


	public boolean addRole(String email, String role) throws DragonflyBddException {

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
		}catch (Exception e) {
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
		}
		return false;
	}


	public  Collection<Message> getMessages(String UserID) throws DragonflyBddException{
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
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
		}
	}

	public boolean CreateMessage(String sender, String addressee, String name, String content) throws DragonflyBddException{

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
		}catch (Exception e) {
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
		}
		return true;
	}

	public boolean RemoveMessage(String user_id, long id) throws DragonflyBddException{

		Transaction trans = null;
		Session session = null;
		try {

			session = DragonFlyDBManager.openSession();
			trans=session.beginTransaction();

			User user = (User) session.get(User.class,user_id);

			if(user == null)
				return false;

			Collection<Message> messages=user.getMessages();
			for(Message mes:messages){
				if (mes.getId()==id){
					messages.remove(mes);
					break;
				}
			}
			session.update(user);
			trans.commit();
		}catch (Exception e) {
			throw new DragonflyBddException(e.getMessage());
		} finally {
			session.close();
			//DragonFlyDBManager.closeSession();
		}
		return true;
	}
}
