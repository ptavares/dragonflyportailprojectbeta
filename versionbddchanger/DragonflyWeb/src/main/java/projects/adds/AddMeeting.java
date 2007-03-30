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

public class AddMeeting extends ActionSupport {

	private String subj;
	private String start;
	private String hour;
	private String min;
	private String descr;

	public String execute() {

		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");
		//System.out.println("Project Name : "+project);
		if(project==null)
			return "rien";

		if(!Tools.checkSpecialChar(subj)){
			addActionError(getText("creates.SpecialCharactersError"));
			return ERROR;
		}

		String[] tab = getStart().split("/");
		Calendar calendar = new GregorianCalendar(Integer.parseInt(tab[0]), Integer.parseInt(tab[1])-1, Integer.parseInt(tab[2]),Integer.parseInt(getHour()),Integer.parseInt(getMin()));

		Date dateMeeting = new Date(calendar.getTimeInMillis());

		String author = (String) ServletActionContext.getRequest().getSession().getAttribute("NickName");
		if(author == null){
			addActionError(getText("meetings.creates.rightCreationError"));
			return ERROR;
		}


		Date post = new java.sql.Date(System.currentTimeMillis());

		if( dateMeeting.before(post) ){
			addActionError(getText("meetings.creates.dateMeetingBeforePost"));
			return ERROR;
		}

		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");

			long id = dEJB.addMeeting(project, author,getSubj(), getDescr(), post, dateMeeting);

			if(id == -1){
				addActionError(getText("meetings.creates.creationError"));
				return ERROR;
			}

			addActionMessage(getText("meetings.creates.creationSuccess1")+" ' "+getSubj()+" ' "+getText("meetings.creates.creationSuccess2"));


		} catch (NamingException e1) {
			// TODO A REDIRIGER VERS PAGE D'ERREUR NIVO EJB
			e1.printStackTrace();
		} catch (DragonflyBddException e) {
			// TODO A REDIRIGER VERS PAGE D'ERREUR NIVO BDD
			e.printStackTrace();
		}
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
}
