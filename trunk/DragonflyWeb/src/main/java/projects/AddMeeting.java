package projects;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.project.adds.ProjectAdds;

public class AddMeeting extends ActionSupport {
	
	private String subj;
	private String start;
	private String hour;
	private String min;
	private String descr;
	
	private Date dateMeeting;
	private String meetingId;
	private Date post;
	
	
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
		String[] tab = getStart().split("/");
		Calendar calendar = new GregorianCalendar(Integer.parseInt(tab[0]), Integer.parseInt(tab[1])-1, Integer.parseInt(tab[2]),Integer.parseInt(getHour()),Integer.parseInt(getMin()));
		dateMeeting = new Date(calendar.getTimeInMillis());
				
		System.out.println("------>Date : "+ dateMeeting.toString());
		
		String author = (String) ServletActionContext.getRequest().getSession().getAttribute("NickName");
		
		post = new java.sql.Date(System.currentTimeMillis());
		
		final InitialContext ctx = new InitialContext();
		final ProjectAdds proj=(ProjectAdds) ctx.lookup("ProjectAdds/remote");
		
		Long  id = proj.addMeeting(project, author,getSubj(), getDescr(), post, dateMeeting);
		meetingId = id.toString();
		
		return INPUT;
	}


	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
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


	public String getMeetingId() {
		return meetingId;
	}


	public String getPost(){
		return new SimpleDateFormat("yyyy/MM/dd").format(post);
	}


	public String getDateMeeting() {
		return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(dateMeeting);
	}
	
	
}
