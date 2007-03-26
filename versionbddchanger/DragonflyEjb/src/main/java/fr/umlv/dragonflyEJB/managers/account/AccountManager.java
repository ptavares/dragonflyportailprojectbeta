package fr.umlv.dragonflyEJB.managers.account;

import java.util.List;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;

@Local
public interface AccountManager {

	public int createAccount(String email, String nickname, String password)throws DragonflyBddException;
	
	public boolean removeAccount(String email)throws DragonflyBddException;
	
	public boolean changeNickmane(String email, String newNickName)throws DragonflyBddException;
	
	public boolean changePassword(String email, String newPassword)throws DragonflyBddException;
	
	public boolean isAuthentificationCorrect(String email, String password)throws DragonflyBddException;
	
	public boolean doesUserExist(String mail)throws DragonflyBddException;

	public String getUserNickname(String mail)throws DragonflyBddException;

	public boolean isPasswordCorrect(String mail, String passwd)throws DragonflyBddException;

	public boolean addRole(String user, String role)throws DragonflyBddException;

	public List<String> getUserRoles(String user)throws DragonflyBddException;
	
	public List<MessageEJB> getMessages(String UserID)throws DragonflyBddException;
	
	public boolean createMessage(String sender, String addressee, String name, String content)throws DragonflyBddException;
	
	public boolean removeMessage(String user_id, long id)throws DragonflyBddException;
}
