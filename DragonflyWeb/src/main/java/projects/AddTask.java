package projects;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;

public class AddTask extends ActionSupport {
	
	private String subj;
	private String start;
	private String end;
	private String descr;
	private String taskId;
	private Date post;
	
	public String execute() throws Exception {
			
		String p = (String) ServletActionContext.getRequest().getSession().getAttribute("project");
		if(p==null){
			System.out.println("AddTask class: session.attribute=null : ");
			return "rien";
		}
		
		final InitialContext ctx = new InitialContext();
		
		final ProjectAdds proj=(ProjectAdds) ctx.lookup("ProjectAdds/remote");
		
		String[] tab = getStart().split("/");
		String[] tab2 = getEnd().split("/");
		
			
		Calendar calendar = new GregorianCalendar(Integer.parseInt(tab[0]), Integer.parseInt(tab[1])-1, Integer.parseInt(tab[2]));
		Date debut = new Date(calendar.getTimeInMillis());
		
		Calendar calendar2 = new GregorianCalendar(Integer.parseInt(tab2[0]), Integer.parseInt(tab2[1])-1, Integer.parseInt(tab2[2]));
		Date fin = new Date(calendar2.getTimeInMillis());
		
		post = new java.sql.Date(System.currentTimeMillis());
		Long  id = proj.addTask(p,getDescr(),getSubj(),post, debut, fin);	
		taskId = id.toString();
		return INPUT;
	}
	
	public String getTaskId(){
		return taskId;
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
