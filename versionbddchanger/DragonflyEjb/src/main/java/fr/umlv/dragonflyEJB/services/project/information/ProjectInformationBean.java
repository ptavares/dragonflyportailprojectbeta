package fr.umlv.dragonflyEJB.services.project.information;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.RemoteBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyBdd.tables.Project;
import fr.umlv.dragonflyEJB.beans.MeetingBean;
import fr.umlv.dragonflyEJB.beans.NewsBean;
import fr.umlv.dragonflyEJB.beans.ProjectInformationsBean;
import fr.umlv.dragonflyEJB.beans.QuestionResponseBean;
import fr.umlv.dragonflyEJB.beans.TaskBean;
import fr.umlv.dragonflyEJB.managers.project.ProjectManager;

@RemoteBinding(jndiBinding = "ProjectInformation/remote")
public @Stateless class ProjectInformationBean implements ProjectInformation {

	@EJB
	private ProjectManager managerLocal;
	private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	public List<Project> getAllProject() throws DragonflyBddException{
		return managerLocal.getAllProject();
	}

	public List<String> getAllProjectsNames() throws DragonflyBddException {
		List<Project> list =getAllProject();
		List<String> ls =new ArrayList<String>();
		
		for(Project pr : list){
			ls.add(pr.getName());
		}
		return ls;
	}


	public List<ProjectInformationsBean> getProjectNamesWithDescriptions() throws DragonflyBddException{
		List<Project> list = getAllProject();
		List<ProjectInformationsBean> projectsWithResume = new ArrayList<ProjectInformationsBean>();
		
		for(Project project : list){
			ProjectInformationsBean bean = new ProjectInformationsBean();
			bean.setResume(project.getDescription().getResume());
			bean.setName(project.getName());
			bean.setProjectLeader(project.getProjectLeader());
			bean.setCreationDate(formatter.format(project.getCreationDate()));
			projectsWithResume.add(bean);
		}
		
		return projectsWithResume;
		
	}

	public List<TaskBean> getProjectTasks(String project) throws DragonflyBddException{
		return managerLocal.getProjectTasks(project);
	}

	public List<MeetingBean> getProjectMeetings(String project) throws DragonflyBddException{
		return managerLocal.getProjectMeetings(project);
	}

	public List<NewsBean> getProjectNews(String name) throws DragonflyBddException{
		return managerLocal.getProjectNews(name);
	}

	public ProjectInformationsBean getProjectInformations(String project) throws DragonflyBddException{
		return managerLocal.getProjectInformations(project);		
	}

	public List<QuestionResponseBean> getProjectFAQ(String project) throws DragonflyBddException {
		return managerLocal.getProjectFAQ(project);
	}

        public List<String> getActiveProject() throws DragonflyBddException {
                List<String> reponse = new ArrayList<String>();
                List<Project> projects = getAllProject();
                for(Project p:projects){
                        if(p.isActived()){
                                System.out.println("EJB "+p.getName());
                                reponse.add(p.getName());
                        }
                }
                return reponse;
        }

        public List<String> getNotActiveProject() throws DragonflyBddException {
                List<String> reponse = new ArrayList<String>();
                List<Project> projects = getAllProject();
                for(Project p:projects){
                        if(!p.isActived()){
                                System.out.println("EJB "+p.getName());
                                reponse.add(p.getName());
                        }
                }
                return reponse;
        }

}
