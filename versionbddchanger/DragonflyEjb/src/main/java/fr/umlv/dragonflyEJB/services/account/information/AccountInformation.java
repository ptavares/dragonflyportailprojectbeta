package fr.umlv.dragonflyEJB.services.account.information;

import java.util.List;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;

@Remote
public interface AccountInformation {

	public String getUserNickname(String mail)throws DragonflyBddException;
	
	public boolean isPasswordCorrect(String mail, String passwd)throws DragonflyBddException;
	
	public List<String> getUserRoles(String user)throws DragonflyBddException;
	
	public List<MessageEJB> getMessages(String UserID)throws DragonflyBddException;
}
