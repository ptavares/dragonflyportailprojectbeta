package authentification;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

public class Login  {
	private String username;
	private String password;


	@SuppressWarnings("unchecked")
	public String execute()  {
		if (isInvalid(getUsername())) return INPUT;
		if (isInvalid(getPassword())) return INPUT;
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//final AccountAuthentification Authen=(AccountAuthentification) ctx.lookup("AccountAuthentification/remote");

			//System.out.println("--login---->"+password);
			if(dEjb.isAuthentificationCorrect(getUsername(), getPassword())){
				Map session = ActionContext.getContext().getSession();
				//final AccountInformation info=(AccountInformation) ctx.lookup("AccountInformation/remote");
				session.put("login", "true");
				session.put("nom", getUsername());
				session.put("NickName", dEjb.getUserNickname(getUsername()));
				if(getUsername().equals("admin@dragonfly.com"))
					session.put("admin",true);
				//System.out.println(session.get("nom"));
				return SUCCESS;
			}
		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		}
		return INPUT;
	}

	public String logout(){
		Map session = ActionContext.getContext().getSession();
		session.remove("login");
		session.remove("nom");
		session.remove("admin");
		return SUCCESS;
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}