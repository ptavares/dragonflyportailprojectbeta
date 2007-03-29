package fr.umlv.dragonflyEJB.locals.dropper;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Local
public interface DropperManager {

	/*
	 * Account
	 */
	public boolean removeAccount(String email)throws DragonflyBddException;
	
	/*
	 * Project
	 */
	public boolean removeProject(String name)throws DragonflyBddException;
}
