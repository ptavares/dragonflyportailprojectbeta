package messageAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class SendMessageAction extends ActionSupport {
	private String address;
	private String subject;
	private String content;
	private String UserID;

	public String go(){
		return INPUT;
	}
	public String goContact(){
		return INPUT;
	}
	public String execute(){
		String test="sended failed";
		Map session = ActionContext.getContext().getSession();
		UserID=(String) session.get("nom");
		try {
			final InitialContext ctx = new InitialContext();
			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//final AccountAdds AA= (AccountAdds) ctx.lookup("AccountAdds/remote");
			boolean havesended;
			try {
				havesended = dEjb.createMessage(UserID, address, subject, content);

				if (havesended){
					test="your Message has been sended";
				}
				HttpServletResponse response = ServletActionContext.getResponse();

				//System.out.println("begin response");
				PrintWriter out = response.getWriter();
				out.println(test);
				out.flush();
				//System.out.println("end response");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (DragonflyBddException e1) {
			e1.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String sendContact(){
		String adres="admin@dragonfly.com";
		InitialContext ctx;
		if (UserID==null||UserID.equals(""))
		{
			addActionError(getText("sendmessage.error"));
			return INPUT;
		}
		try {
			ctx = new InitialContext();
			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			boolean havesended = dEjb.createMessage(UserID, adres, subject, content);
			if (havesended){
				addActionMessage(getText("sendmessage.succes"));
				return SUCCESS;
			}
				
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			e.printStackTrace();
		}
		return INPUT;
	}


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
}
