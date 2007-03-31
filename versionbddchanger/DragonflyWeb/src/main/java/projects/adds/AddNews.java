package projects.adds;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import projects.adds.tools.Tools;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class AddNews extends ActionSupport {

	private String subj;
	private String descr;
	private Date post;
	private String newsId;

	public String execute() {
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");

		if(!Tools.checkSpecialChar(subj)){
			addActionError(getText("creates.SpecialCharactersError"));
			return ERROR;
		}

		String author = (String) ServletActionContext.getRequest().getSession().getAttribute("NickName");
		if(author == null){
			addActionError(getText("news.creates.rightCreationError"));
			return ERROR;
		}

		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");

			post = new java.sql.Date(System.currentTimeMillis());
			Long  id = dEJB.addNews(project, author, post, subj, descr);
			newsId = id.toString();

			if(id == -1){
				addActionMessage(getText("news.creates.creationError"));
				return ERROR;
			}

			addActionMessage(getText("news.creates.creationSuccess1")+" ' "+getSubj()+" ' "+getText("news.creates.creationSuccess2"));


		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
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
