package fr.umlv.dragonflyEJB.services.account.information;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.User;
import fr.umlv.dragonflyEJB.managers.account.AccountManager;
import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;
import java.util.ArrayList;
import java.util.Collections;


@RemoteBinding(jndiBinding = "AccountInformation/remote")
public @Stateless class AccountInformationBean implements AccountInformation {

	@EJB
	private AccountManager managerLocal;

	public String getUserNickname(String mail)throws DragonflyBddException{
		return managerLocal.getUserNickname(mail);
	}

	public boolean isPasswordCorrect(String mail, String passwd) throws DragonflyBddException{
		return managerLocal.isPasswordCorrect(mail,passwd);
	}

	public List<String> getUserRoles(String user) throws DragonflyBddException{
		return managerLocal.getUserRoles(user);
	}
	
	public List<MessageEJB> getMessages(String UserID)throws DragonflyBddException{
		return managerLocal.getMessages(UserID);
	}

    public List<String> getActiveUsers() throws DragonflyBddException {
        List<User> users = managerLocal.getAllUsers();
        List<String> reponse = new ArrayList<String>();
        if(users == null || users.isEmpty())
            return Collections.EMPTY_LIST;
        for(User user : users)
            if(user.isActived())
                reponse.add(user.getEmail());
        System.out.println("fin getActiveUsers");
        return reponse;
    }

    public List<String> getNotActiveUsers() throws DragonflyBddException {
        List<User> users = managerLocal.getAllUsers();
        List<String> reponse = new ArrayList<String>();
        if(users == null || users.isEmpty())
            return Collections.EMPTY_LIST;
        for(User user : users)
            if(!user.isActived())
                reponse.add(user.getEmail());
        System.out.println("fin getNotActiveUsers");
        return reponse;
    }
}
