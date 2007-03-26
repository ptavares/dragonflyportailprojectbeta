package fr.umlv.dragonflyEJB.services.account.adds;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.managers.account.AccountManager;



@RemoteBinding(jndiBinding = "AccountAdds/remote")
public @Stateless class AccountAddsBean implements AccountAdds {

	@EJB
	private AccountManager managerLocal;

	public boolean addRole(String user, String role) throws DragonflyBddException{
		return managerLocal.addRole(user,role);
	}
	
	
	public boolean createMessage(String sender, String addressee, String name ,String content)throws DragonflyBddException{
		return managerLocal.createMessage(sender, addressee, name, content);
	}
	
	public boolean removeMessage(String user_id,long id)throws DragonflyBddException{
		return managerLocal.removeMessage(user_id, id);
	}
}
