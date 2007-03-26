package fr.umlv.dragonflyEJB.services.account.adds;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface AccountAdds {

	public boolean addRole(String user, String role)throws DragonflyBddException;
	
	public boolean createMessage(String sender, String addressee, String name ,String content)throws DragonflyBddException;
	
	public boolean removeMessage(String user_id,long id)throws DragonflyBddException;
}
