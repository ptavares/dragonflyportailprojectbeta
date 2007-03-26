package fr.umlv.dragonflyEJB.services.account.dropper;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface AccountDropper {
	
	public boolean removeAccount(String email)throws DragonflyBddException;

}
