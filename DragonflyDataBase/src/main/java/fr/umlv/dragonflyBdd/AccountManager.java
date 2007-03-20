package fr.umlv.dragonflyBdd;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

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
	private final Session session;


	/**
	 * Private constructor
	 *
	 */
	private AccountManager() {
		session = DragonFlyDBManager.getcurrentSession();
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
	 */
	public int createAccount(String email, String nickname, String password){
		//Test if an account have this email key
		if(isAccountExist(email))
			return 1;

		//Create an user and add it into User's Table
		Transaction tx = null;
		try{
			Date date = new Date(System.currentTimeMillis());
			tx = session.beginTransaction();
			User user = new User(email,date,nickname,password);
			System.out.println(date);
			session.save(user);
			tx.commit();
		}catch(ConstraintViolationException e){
			System.err.println("An user with the same email allready exist");
			//In case of exception
			if(tx != null)
				tx.rollback();
			return 2;
		}
		//Ok
		return 0;
	}

	/**
	 * Remove an account from database
	 * 
	 * @param email The user's key. Needed to remove the account from User's Table 
	 * @return true if the account have been dropped successfuly, false otherwise.
	 */
	public boolean removeAccount(String email){
		/**
		 * TODO : supprimer cet utilisateur des projets auxquels il est affect锟�
		 * TODO : Attention au cas ou il est le chef de projet d'un projet ^^''
		 */
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			User user = getUser(email);
			if(user == null)
				return false;
			session.delete(user);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			//Error
			return false;
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
	 */
	public boolean changeNickmane(String email, String newNickName){

		User user = getUser(email);

		if(user == null)
			return false;

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			user.setNickname(newNickName);
			session.update(user);
			System.out.println("avant commit");
			tr.commit();
			System.out.println("apres commit");
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}

	/**
	 * Change an user's Password
	 * 
	 * @param email
	 * @param newPassword
	 * @return
	 */
	public boolean changePassword(String email, String newPassword){
		
		User user = getUser(email);

		if(user == null)
			return false;

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			user.setPassword(newPassword);
			session.update(user);
			tr.commit();
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}

	/**
	 * Test if an account exist in DataBase
	 * 
	 * @param email
	 * @return
	 */
	public boolean isAccountExist(String email){
		User user = getUser(email);
		return (user != null);
	}

	/**
	 * Get the User Object with this email
	 * 
	 * @param email The key to get this user in the User's Table from DataBase. 
	 * @return The User Object associated with this email, null otherwise (if doesn't exist in DataBase).
	 */
	public User getUser(String email) {
		//Try to get the user with this email key
		return (User)session.get(User.class,email);
	}

	/**
	 * Check if the email corresponds with the password
	 * 
	 * @param email The emain given by the user
	 * @param password The password given by the user
	 * @return true if the email have the good password, false otherwise
	 */
	public boolean isAuthentificationCorrect(String email, String password) {
		User user = getUser(email);
		if(user == null)
			return false;
		return user.getPassword().equals(password);
		
	}
	
	public String getUserNickname(String mail) {
		System.out.println("AccountManger getUserNickname");
		return getUser(mail).getNickname();
	}

	public boolean isPasswordCorrect(String mail, String passwd) {
		String pass = getUser(mail).getPassword();
		System.out.println("Password : "+pass);
		if(passwd.compareTo(pass)==0)
			return true;
		return false;
	}

	

	/**
	 * Changes the mail of a user
	 * @param oldMail
	 * @param newMail
	 * @return
	 */
	public boolean changeMail(String oldMail, String newMail) {
		System.out.println("Old : "+ oldMail);
		System.out.println("New : "+newMail);
		User user = getUser(oldMail);

		if(user == null)
			return false;

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			user.setEmail(newMail);
			System.out.println("setMail OK");
			session.update(user);
			System.out.println("update OK");
			tr.commit();
			System.out.println("Commit OK");
		} catch (Exception e) {
			if(tr != null)
				tr.rollback();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 */
	public List<String> getUserRoles(String user){
		User u = getUser(user);
		if(u==null)
			return null;
		Collection<Roles> coll =  u.getRoles();
		List<String> set = new ArrayList<String>();
		for(Roles r: coll)
			set.add(r.getRole());
		
		return set;
	}
	
	
	public boolean addRole(String user, String role) {
		User us = getUser(user);
		if(us == null)
			return false;
		
		Transaction tr = null;
		try{
			tr = session.beginTransaction();
			System.out.println("role="+role);
			us.getRoles().add(new Roles(role));
			session.update(us);
			tr.commit();
		}catch(Exception e){
			System.out.println("nononnonno");
		}
		return false;
	}
	
	
	public  Collection<Message> getMessages(String UserID){
		User user=getUser(UserID);
		System.out.println("ds la bdd "+user.getEmail()+" "+user.getMessages());
		if(user==null)
			return null;
		Collection<Message> messages=user.getMessages();
		return messages;
	}
	public boolean CreateMessage(String sender, String addressee, String name, String content){
		User user=getUser(addressee);
		if (user==null)
			return false;
		Transaction trans = null;
		try {
			trans=session.beginTransaction();
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
		} catch (Exception e) {
			if(trans != null)
				trans.rollback();
			return false;
		}
		return true;
	}

	public boolean RemoveMessage(String user_id, long id){
		User user=getUser(user_id);
		if(user==null){
			return false;
		}
		Transaction trans = null;
		try {
			trans=session.beginTransaction();
			Collection<Message> messages=user.getMessages();
			for(Message mes:messages){
				if (mes.getId()==id){
					messages.remove(mes);
				break;
				}
			}
			session.update(user);
			trans.commit();
		} catch (Exception e) {
			if(trans != null)
				trans.rollback();
			return false;
		}
		return true;
	}
}
