package fr.umlv.dragonflyEJB.services.account.adds;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyEJB.managers.account.AccountManager;
import fr.umlv.dragonflyEJB.services.account.authentification.AccountAuthentification;



@RemoteBinding(jndiBinding = "AccountAdds/remote")
public @Stateless class AccountAddsBean implements AccountAdds {

	@EJB
	private AccountManager managerLocal;

	public boolean addRole(String user, String role) {
		return managerLocal.addRole(user,role);
	}
	
	
	public boolean createMessage(String sender, String addressee, String name ,String content){
		return managerLocal.createMessage(sender, addressee, name, content);
	}
	
	public boolean removeMessage(String user_id,long id){
		return managerLocal.removeMessage(user_id, id);
	}
}
