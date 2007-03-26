package projects.adds;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;

public class AddNews extends ActionSupport {
	
	private String subj;
	private String descr;
	private Date post;
	private String newsId;
	
	public String execute() {
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		
		System.out.println("Project Name : "+project);
		System.out.println("Project Subject : "+ getSubj());
		System.out.println("Project Descreption : "+ getDescr());
				
		String author = (String) ServletActionContext.getRequest().getSession().getAttribute("NickName");
		if(author == null){
			addActionError(getText("news.creates.rightCreationError"));
			return ERROR;
		}
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
		
		final ProjectAdds proj=(ProjectAdds) ctx.lookup("ProjectAdds/remote");
		
		post = new java.sql.Date(System.currentTimeMillis());
		Long  id = proj.addNews(project, author, post, subj, descr);
		newsId = id.toString();
		
		if(id == -1){
			addActionMessage(getText("news.creates.creationError"));
			return ERROR;
		}
		
		addActionMessage(getText("news.creates.creationSuccess1")+" ' "+getSubj()+" ' "+getText("news.creates.creationSuccess2"));

		
		} catch (NamingException e) {
			// TODO REDIRECTION ERREUR EJB
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			// TODO REDIRECTION ERREUR BDD
			e.printStackTrace();
		}
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
