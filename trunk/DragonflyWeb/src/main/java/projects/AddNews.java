package projects;

import java.util.Date;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;

public class AddNews extends ActionSupport {
	private String subj;
	private String descr;
	
	
	public String execute() throws Exception{
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		
		System.out.println("Project Name : "+project);
		System.out.println("Project Subject : "+ getSubj());
		System.out.println("Project Descreption : "+ getDescr());
		
		if(project==null){
			System.out.println("AddNews class : Project name=NULL");
			return "rien";
		}
		final InitialContext ctx = new InitialContext();
		final ProjectAdds proj=(ProjectAdds) ctx.lookup("ProjectAdds/remote");
		
		try{
			proj.addNews(project, getSubj(),getDescr());
		}catch(Exception e){
			System.err.println("News could not be added to the ect");
			return "rien";
		}
		return INPUT;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getSubj() {
		return subj;
	}


	public void setSubj(String subj) {
		this.subj = subj;
	}
	
}
