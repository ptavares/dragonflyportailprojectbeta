package fr.umlv.dragonflyEJB.services.project.modification;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface ProjectModification {

	public boolean changeEndDate(String name, Date newEndDate);
	
	public boolean removeTask(String name, String task_id);

	public boolean removeMeeting(String name, String meet_id);

	public boolean removeNews(String proName, String delete);
}
