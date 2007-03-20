package fr.umlv.dragonflyEJB.services.account.adds;

import javax.ejb.Remote;

@Remote
public interface AccountAdds {

	public boolean addRole(String user, String role);
	
	public boolean createMessage(String sender, String addressee, String name ,String content);
	
	public boolean removeMessage(String user_id,long id);
}
