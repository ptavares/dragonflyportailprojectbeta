package fr.umlv.dragonflyEJB.services.account.modification;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface AccountModification {

	public boolean changeNickmane(String email, String newNickName)throws DragonflyBddException;
	
	public boolean changePassword(String email, String newPassword)throws DragonflyBddException;
}
