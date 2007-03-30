package messageAction;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.beans.MessageBean;
import fr.umlv.dragonflyEJB.services.account.adds.AccountAdds;
import fr.umlv.dragonflyEJB.services.account.information.AccountInformation;

public class ListMessagesAction extends ActionSupport {
	private String UserID;
	private List<MessageBean> mes;
	private List<Integer> ids;
	
	
	public String execute() throws NamingException{
		Map session = ActionContext.getContext().getSession();
		UserID=(String) session.get("nom");
		
		final InitialContext ctx = new InitialContext();
		final AccountInformation Authen=(AccountInformation) ctx.lookup("AccountInformation/remote");
		try {
			mes=Authen.getMessages(UserID);
		
		if(mes.isEmpty()){
			mes.add(new MessageBean("test","youself","this is  your first message",new Date(),false));
			mes.add(new MessageBean("test22","youself22","this is  your seconde message",new Date(),false));
		}
		} catch (DragonflyBddException e) {
			// TODO REDIRECTION ERREUR BDD
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	public List<MessageBean> getMes() {
		return mes;
	}

	public void setMes(List<MessageBean> mes) {
		this.mes = mes;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}


	public List<Integer> getIds() {
		return ids;
	}


	public void setIds(List<Integer> ids) {
		System.out.println("here");
		this.ids = ids;
	}
	
	public String Supprimer() throws NamingException{
		Map session = ActionContext.getContext().getSession();
		UserID=(String) session.get("nom");
		final InitialContext ctx = new InitialContext();
		AccountAdds AA=(AccountAdds) ctx.lookup("AccountAdds/remote");
		System.out.println("here-----------"+UserID);
		
		try {
		if(ids!=null){
			for(int i=0;i<ids.size();i++){
				System.out.println(ids.size());
				if(ids.get(i)!=null)
					
						AA.removeMessage(UserID, ids.get(i));
					
			}
		}
		final AccountInformation Authen=(AccountInformation) ctx.lookup("AccountInformation/remote");
		mes=Authen.getMessages(UserID);
		
		} catch (DragonflyBddException e) {
			// TODO REDIRECTION ERREUR BDD
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
