package messageAction;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.managers.tables.MessageEJB;
import fr.umlv.dragonflyEJB.services.account.authentification.AccountAuthentification;
import fr.umlv.dragonflyEJB.services.account.information.AccountInformation;

public class ListMessagesAction extends ActionSupport {
	private String UserID;
	private List<MessageEJB> mes;
	
	public String execute() throws NamingException{
		Map session = ActionContext.getContext().getSession();
		UserID=(String) session.get("nom");
		
		final InitialContext ctx = new InitialContext();
		final AccountInformation Authen=(AccountInformation) ctx.lookup("AccountInformation/remote");
		mes=Authen.getMessages(UserID);
		if(mes.isEmpty()){
			mes.add(new MessageEJB("test","youself","this is  your first message",new Date(),false));
			mes.add(new MessageEJB("test22","youself22","this is  your seconde message",new Date(),false));
		}
		System.out.println("here"+UserID);
		return SUCCESS;
	}
	

	public List<MessageEJB> getMes() {
		return mes;
	}

	public void setMes(List<MessageEJB> mes) {
		this.mes = mes;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}
}
