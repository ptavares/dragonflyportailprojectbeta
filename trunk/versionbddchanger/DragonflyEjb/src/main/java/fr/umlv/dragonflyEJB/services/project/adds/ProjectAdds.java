package fr.umlv.dragonflyEJB.services.project.adds;

import java.util.Date;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface ProjectAdds {
	
	public long addTask(String project, String author, String descr, String subj, Date post, Date start, Date end)throws DragonflyBddException;

	public long addMeeting(String project, String author, String subj, String description, Date post, Date date)throws DragonflyBddException;

	public long addNews(String project, String author, Date post, String subj, String descr)throws DragonflyBddException;
	
	public boolean addUser(String project, String mail)throws DragonflyBddException;

	public boolean addQuestionResponse(String proj, String question, String response) throws DragonflyBddException;

}
