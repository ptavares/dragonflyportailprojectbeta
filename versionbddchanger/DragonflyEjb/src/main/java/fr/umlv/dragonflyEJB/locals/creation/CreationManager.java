package fr.umlv.dragonflyEJB.locals.creation;

import java.util.Date;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Local
public interface CreationManager {

	/*
	 * Account
	 */
	public int createAccount(String email, String nickname, String password)throws DragonflyBddException;
	
	/*
	 * Project
	 */
	public int createProject(String name,String projectLeader, String resume)throws DragonflyBddException;

	public int createProject(String name, String projectLeader, String resume,Date endDate)throws DragonflyBddException;

	public int createProject(String name, String projectLeader,Date endDate, String resume, String description)throws DragonflyBddException;

}
