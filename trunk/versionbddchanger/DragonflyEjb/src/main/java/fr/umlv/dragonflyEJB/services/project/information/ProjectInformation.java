package fr.umlv.dragonflyEJB.services.project.information;

import java.util.List;

import javax.ejb.Remote;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;

@Remote
public interface ProjectInformation {
	
	public List<Project> getAllProject()throws DragonflyBddException;
	
	public List<String> getAllProjectsNames()throws DragonflyBddException;
	
	public List<ProjectInformationsBean> getProjectNamesWithDescriptions()throws DragonflyBddException;
	
	public List<TaskBean> getProjectTasks(String project)throws DragonflyBddException;
	
	public List<MeetingBean> getProjectMeetings(String project)throws DragonflyBddException;
	
	public List<NewsBean> getProjectNews(String name)throws DragonflyBddException;

	public ProjectInformationsBean getProjectInformations(String proName)throws DragonflyBddException;
	
	public List<QuestionResponseBean> getProjectFAQ(String project) throws DragonflyBddException;
        
        public List<String> getActiveProject() throws DragonflyBddException;
        
        public List<String> getNotActiveProject() throws DragonflyBddException;

}