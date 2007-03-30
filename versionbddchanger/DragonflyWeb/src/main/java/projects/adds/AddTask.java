package projects.adds;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import projects.adds.tools.Tools;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class AddTask extends ActionSupport {

	private Date post;
	
	private String subj;
	private String start;
	private String end ;
	private String descr;
	private String taskId;
	
 
	
	public String execute() {

		String p = (String) ServletActionContext.getRequest().getSession().getAttribute("project");
		if(p==null){
			return "rien";
		}

		if(!Tools.checkSpecialChar(subj)){
			addActionError(getText("creates.SpecialCharactersError"));
			return ERROR;
		}
		
		String author = (String) ServletActionContext.getRequest().getSession().getAttribute("NickName");
		if(author == null){
			addActionError(getText("tasks.creates.rightCreationError"));
			return ERROR;
		}
		
				
		final InitialContext ctx;

		try {
			ctx = new InitialContext();

			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			
			post = new java.sql.Date(System.currentTimeMillis());
			
			String[] tab = getStart().split("/");
			String[] tab2 = getEnd().split("/");

			
			
			Calendar calendar = new GregorianCalendar(Integer.parseInt(tab[0]), Integer.parseInt(tab[1])-1, Integer.parseInt(tab[2]));
			Date debut = new Date(calendar.getTimeInMillis());

			Calendar calendar2 = new GregorianCalendar(Integer.parseInt(tab2[0]), Integer.parseInt(tab2[1])-1, Integer.parseInt(tab2[2]));
			Date fin = new Date(calendar2.getTimeInMillis());

			if( fin.before(debut) ){
				addActionError(getText("tasks.creates.dateEndBeforeStartError"));
				return ERROR;
			}
			
			if( debut.before(post) ){
				addActionError(getText("tasks.creates.dateStartBeforePost"));
				return ERROR;
			}
			
			Long  id = dEJB.addTask(p,author,getDescr(),getSubj(),post, debut, fin);	
			taskId = id.toString();
			
			if(id == -1){
				addActionMessage(getText("tasks.creates.creationError"));
				return ERROR;
			}
			
			addActionMessage(getText("tasks.creates.creationSuccess1")+" ' "+getSubj()+" ' "+getText("tasks.creates.creationSuccess2"));

		} catch (NamingException e) {
			// TODO REDIRECTION ERREUR EJB
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			// TODO REDIRECTION ERREUR BDD
			e.printStackTrace();
		}

		return INPUT;

	}

	public String getTaskId(){
		return taskId;
	}

	public Date getPost(){
		return post;
	}

	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}
	
	public String getSubj() {
		return subj;
	}


	public void setSubj(String subj) {
		this.subj = subj;
	}
}
