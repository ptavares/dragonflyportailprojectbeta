package fr.umlv.dragonflyEJB.managers.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.tables.Message;
import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;

@LocalBinding(jndiBinding = "AccountManager/local")
public @Stateless class AccountManagerBean implements AccountManager {

	
private final static fr.umlv.dragonflyBdd.AccountManager MANAGER;
	
	static {
		MANAGER = fr.umlv.dragonflyBdd.AccountManager.getAccountManagerInstance();
	}
	
	
	public int createAccount(String email, String nickname, String password) {
		return MANAGER.createAccount(email, nickname, password);
	}
	
	public boolean removeAccount(String email) {
		return MANAGER.removeAccount(email);
	}
	
	public boolean changeNickmane(String email, String newNickName) {
		return MANAGER.changeNickmane(email, newNickName);
	}

	public boolean changePassword(String email, String newPassword) {
		return MANAGER.changePassword(email, newPassword);
	}

	public boolean isAuthentificationCorrect(String email, String password) {
		return MANAGER.isAuthentificationCorrect(email, password);
	}	

	public boolean doesUserExist(String mail) {
		if(MANAGER.getUser(mail) == null)
			return false;
		return true;
	}

	public String getUserNickname(String mail) {
		return MANAGER.getUserNickname(mail);
	}

	public boolean isPasswordCorrect(String mail, String passwd) {
		return MANAGER.isPasswordCorrect(mail,passwd);
	}

	public boolean changeMail(String oldMail, String newMail) {
		return MANAGER.changeMail(oldMail,newMail);
	}

	public boolean addRole(String user, String role) {
		return MANAGER.addRole(user,role);
	}

	public List<String> getUserRoles(String user) {
		return MANAGER.getUserRoles(user);
	}

	public List<MessageEJB> getMessages(String UserID) {
		Collection<Message> mes=MANAGER.getMessages(UserID);
		List<MessageEJB> mesEJB=new ArrayList<MessageEJB>();
		for(Message m:mes){
			MessageEJB	meEJB=new MessageEJB(m);
			mesEJB.add(meEJB);
		}
		return mesEJB;
	}

	public boolean createMessage(String sender, String addressee, String name, String content) {
		return MANAGER.CreateMessage(sender, addressee, name, content);
	}

	public boolean removeMessage(String user_id, long id) {
		return MANAGER.RemoveMessage(user_id, id);
	}	
	
}
