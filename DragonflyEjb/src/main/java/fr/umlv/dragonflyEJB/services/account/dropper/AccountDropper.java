package fr.umlv.dragonflyEJB.services.account.dropper;

import javax.ejb.Remote;

@Remote
public interface AccountDropper {
	
	public boolean removeAccount(String email);

}
