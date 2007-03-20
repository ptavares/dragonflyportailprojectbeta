package authentification;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.Map;

import javax.naming.InitialContext;

import com.opensymphony.xwork2.ActionContext;

import fr.umlv.dragonflyEJB.services.account.authentification.AccountAuthentification;

public class Login  {
	private String username;
	private String password;


	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		if (isInvalid(getUsername())) return INPUT;
		if (isInvalid(getPassword())) return INPUT;

		final InitialContext ctx = new InitialContext();
		final AccountAuthentification Authen=(AccountAuthentification) ctx.lookup("AccountAuthentification/remote");
		
		System.out.println("--login---->"+password);
		if(Authen.isAuthentificationCorrect(getUsername(), getPassword())){
			Map session = ActionContext.getContext().getSession();
		   
			session.put("login", "true");
			session.put("nom", getUsername());
			return SUCCESS;
		}
		return INPUT;
	}
	
	public String logout(){
		Map session = ActionContext.getContext().getSession();
		session.remove("login");
		session.remove("nom");
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