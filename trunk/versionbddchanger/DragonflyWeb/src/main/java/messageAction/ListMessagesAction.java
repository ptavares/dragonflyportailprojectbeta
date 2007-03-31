package messageAction;

import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.beans.MessageBean;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class ListMessagesAction extends ActionSupport {
	private String UserID;
	private List<MessageBean> mes;
	private List<Integer> ids;


	public String execute(){

		Map session = ActionContext.getContext().getSession();
		UserID=(String) session.get("nom");
		try {
			InitialContext ctx;
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//final AccountInformation Authen=(AccountInformation) ctx.lookup("AccountInformation/remote");
			mes=dEjb.getMessages(UserID);
			
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
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

	public String Supprimer(){
		Map session = ActionContext.getContext().getSession();
		UserID=(String) session.get("nom");
		try {
		final InitialContext ctx = new InitialContext();
		final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
		//AccountAdds AA=(AccountAdds) ctx.lookup("AccountAdds/remote");
		System.out.println("here-----------"+UserID);

			if(ids!=null){
				for(int i=0;i<ids.size();i++){
					System.out.println(ids.size());
					if(ids.get(i)!=null)

						dEjb.removeMessage(UserID, ids.get(i));

				}
			}
			mes=dEjb.getMessages(UserID);

		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		}
		return SUCCESS;
	}
}
