package fr.umlv.dragonflyEJB.services.account.modification;

import javax.ejb.Remote;

@Remote
public interface AccountModification {

	public boolean changeNickmane(String email, String newNickName);
	
	public boolean changePassword(String email, String newPassword);
}
