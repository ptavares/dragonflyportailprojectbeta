package projects;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;

public class AddNews extends ActionSupport {
	
	private String subj;
	private String descr;
	private Date post;
	private String newsId;
	
	public String execute() throws Exception{
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		
		System.out.println("Project Name : "+project);
		System.out.println("Project Subject : "+ getSubj());
		System.out.println("Project Descreption : "+ getDescr());
				
		String author = (String) ServletActionContext.getRequest().getSession().getAttribute("NickName");
		
		
		final InitialContext ctx = new InitialContext();
		final ProjectAdds proj=(ProjectAdds) ctx.lookup("ProjectAdds/remote");
		
		post = new java.sql.Date(System.currentTimeMillis());
		Long  id = proj.addNews(project, author, post, subj, descr);
		newsId = id.toString();
		
		return INPUT;
	}

	public String getNewsId() {
		return newsId;
	}
	
	public String getPost(){
		return new SimpleDateFormat("yyyy/MM/dd").format(post);
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
