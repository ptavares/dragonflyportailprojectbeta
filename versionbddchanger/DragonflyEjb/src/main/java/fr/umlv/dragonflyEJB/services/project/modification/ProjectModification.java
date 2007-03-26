package fr.umlv.dragonflyEJB.services.project.modification;

import java.util.Date;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;

@Remote
public interface ProjectModification {

	public boolean changeEndDate(String name, Date newEndDate) throws DragonflyBddException;
	
	public boolean removeTask(String name, String task_id)throws DragonflyBddException;

	public boolean removeMeeting(String name, String meet_id)throws DragonflyBddException;

	public boolean removeNews(String proName, String delete)throws DragonflyBddException;

	public boolean removeQuestionResponse(String name, String qr_id) throws DragonflyBddException;
	
	public boolean editQuestionResponse(String name, String qr_id,String question, String response) throws DragonflyBddException;

}
