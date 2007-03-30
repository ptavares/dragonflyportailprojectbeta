package projects.adds;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class AddFAQ extends ActionSupport{

	private String question;
	private String response;


	public String execute() {

		String project = (String)ServletActionContext.getRequest().getSession().getAttribute("project");

		if(project==null)
			return "rien";

		final InitialContext ctx;
		
		try {

			ctx = new InitialContext();

			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");

			if(!dEJB.addQuestionResponse(project, question, response)){
				addActionError(getText("faq.creates.creationError"));
				return ERROR;
			}

			addActionMessage(getText("faq.creates.creationSuccess"));


		} catch (NamingException e1) {
			// TODO A REDIRIGER VERS PAGE D'ERREUR NIVO EJB
			e1.printStackTrace();
		} catch (DragonflyBddException e) {
			// TODO A REDIRIGER VERS PAGE D'ERREUR NIVO BDD
			e.printStackTrace();
		}
		return INPUT;
	}


	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}




}
