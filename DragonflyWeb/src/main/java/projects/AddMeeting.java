package projects;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;

public class AddMeeting extends ActionSupport {
	private String subj;
	//private Date start;	
	private String start;
	private String hour;
	private String min;
	private String descr;
	
	
	public String execute() throws Exception{
		System.out.println("Subject : "+ getSubj());
		System.out.println("Date : "+ getStart().toString());
		System.out.println("Hours : "+ getHour());
		System.out.println("Minutes : "+ getMin());
		System.out.println("Description : "+getDescr());
		
		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		System.out.println("Project Name : "+project);
		if(project==null)
			return "rien";
		String[] tab = getStart().split("-");
		int y=0,m=0,d=0;
		try{
			y = Integer.parseInt(tab[0]);
			m = Integer.parseInt(tab[1]);
			d = Integer.parseInt(tab[2]);			
		}catch(NumberFormatException n){
			System.out.println("Add meeting exception. Cannot parse item "+ n.getMessage());
			return "rien";
		}
				
		Calendar calendar = new GregorianCalendar(y, m-1, d, Integer.parseInt(getHour()), Integer.parseInt(getMin()));
		//calendar.s=Integer.parseInt(getHour());
		java.util.Date debut = new Date(calendar.getTimeInMillis());
		
		System.out.println("------>Date : "+ debut.toString());
		
		final InitialContext ctx = new InitialContext();
		//System.out.println("Initial Contexte");
		final ProjectAdds proj=(ProjectAdds) ctx.lookup("ProjectAdds/remote");
		//System.out.println("Project Adds OK");
		proj.addMeeting(project, getSubj(), getDescr(), debut);
		//System.out.println("Meeting added");
		return INPUT;
	}


	public String getStart() {
		return start;
	}


	public void setStart(String date) {
		this.start = date;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getHour() {
		return hour;
	}


	public void setHour(String hour) {
		this.hour = hour;
	}


	public String getMin() {
		return min;
	}


	public void setMin(String min) {
		this.min = min;
	}


	public String getSubj() {
		return subj;
	}


	public void setSubj(String subj) {
		this.subj = subj;
	}
	
}
