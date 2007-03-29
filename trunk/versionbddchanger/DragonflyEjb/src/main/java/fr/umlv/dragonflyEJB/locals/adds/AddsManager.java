package fr.umlv.dragonflyEJB.locals.adds;

import java.util.Date;

import javax.ejb.Local;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Local
public interface AddsManager {

	/*
	 * Account
	 */

	public boolean addRole(String user, String role)throws DragonflyBddException;

	public boolean createMessage(String sender, String addressee, String name ,String content)throws DragonflyBddException;

	/*
	 * Project
	 */

	public long addMeeting(String project, String author, String subj, String description, Date post, Date date)throws DragonflyBddException;

	public long addNews(String project, String author, Date post, String subj, String descr)throws DragonflyBddException;

	public boolean addUser(String project, String mail)throws DragonflyBddException;

	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException;

	public long addTask(String project, String author,String descr, String subj, java.util.Date post, java.util.Date start, java.util.Date end)throws DragonflyBddException;

}
