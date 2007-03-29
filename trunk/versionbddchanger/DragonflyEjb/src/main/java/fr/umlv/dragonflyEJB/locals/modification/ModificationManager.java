package fr.umlv.dragonflyEJB.locals.modification;

import java.util.Date;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Local
public interface ModificationManager {

	/*
	 * Account
	 */
	public boolean changeNickmane(String email, String newNickName)throws DragonflyBddException;

	public boolean changePassword(String email, String newPassword)throws DragonflyBddException;

	public boolean activateAccount(String email)throws DragonflyBddException;

	/*
	 * Project
	 */
	public boolean changeEndDate(String name, Date newEndDate) throws DragonflyBddException;

	public boolean removeTask(String name, String task_id)throws DragonflyBddException;

	public boolean removeMeeting(String name, String meet_id)throws DragonflyBddException;

	public boolean removeNews(String proName, String delete)throws DragonflyBddException;

	public boolean removeQuestionResponse(String name, String qr_id) throws DragonflyBddException;

	public boolean editQuestionResponse(String name, String qr_id,String question, String response) throws DragonflyBddException;

	public boolean removeUserFromProject(String project, String mail)throws DragonflyBddException;
	
	public boolean activateProject(String name) throws DragonflyBddException;

	public boolean removeMessage(String user_id,long id)throws DragonflyBddException;
}
