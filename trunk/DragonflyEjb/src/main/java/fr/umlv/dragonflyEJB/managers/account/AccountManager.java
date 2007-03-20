package fr.umlv.dragonflyEJB.managers.account;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;

@Local
public interface AccountManager {

	public int createAccount(String email, String nickname, String password);
	
	public boolean removeAccount(String email);
	
	public boolean changeNickmane(String email, String newNickName);
	
	public boolean changePassword(String email, String newPassword);
	
	public boolean isAuthentificationCorrect(String email, String password);
	
	public boolean doesUserExist(String mail);

	public String getUserNickname(String mail);

	public boolean isPasswordCorrect(String mail, String passwd);

	public boolean changeMail(String oldMail, String newMail);

	public boolean addRole(String user, String role);

	public List<String> getUserRoles(String user);
	
	public List<MessageEJB> getMessages(String UserID);
	
	public boolean createMessage(String sender, String addressee, String name, String content);
	
	public boolean removeMessage(String user_id, long id);
}
