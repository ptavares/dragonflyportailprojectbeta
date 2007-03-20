package fr.umlv.dragonflyEJB.services.account.information;

import java.util.List;

import javax.ejb.Remote;

import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;

@Remote
public interface AccountInformation {

	public String getUserNickname(String mail);
	
	public boolean isPasswordCorrect(String mail, String passwd);
	
	public List<String> getUserRoles(String user);
	
	public List<MessageEJB> getMessages(String UserID);
}
