package fr.umlv.dragonflyEJB.services.account.information;

import java.util.List;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.beans.MessageBean;

@Remote
public interface AccountInformation {

	public String getUserNickname(String mail)throws DragonflyBddException;
	
	public boolean isPasswordCorrect(String mail, String passwd)throws DragonflyBddException;
	
	public List<String> getUserRoles(String user)throws DragonflyBddException;
	
	public List<MessageBean> getMessages(String UserID)throws DragonflyBddException;
        
        public List<String> getActiveUsers()throws DragonflyBddException;
        
        public List<String> getNotActiveUsers()throws DragonflyBddException;
}
