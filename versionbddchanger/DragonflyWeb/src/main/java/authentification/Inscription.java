package authentification;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;
import fr.umlv.dragonflyEJB.services.account.adds.AccountAdds;
import fr.umlv.dragonflyEJB.services.account.creation.AccountCreation;


public class Inscription extends ActionSupport {

	
	public String execute()  {
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
	
		final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
		//final AccountCreation creator=(AccountCreation) ctx.lookup("AccountCreation/remote");
		int result=dEjb.createAccount(email, username, password);
		if (result==1){
			addActionError(getText("register.emailExist"));
			return ("error");
		}
		else if(result==2){
			addActionError(getText("register.erreurInterne"));
			return ("error");
		}
		
		final AccountAdds AA= (AccountAdds) ctx.lookup("AccountAdds/remote");
		AA.createMessage("Dragonfly", getEmail(), "Welcome", "Welcome To Dragonfly Web Portail. Have Fun !");
		addActionMessage(getText("register.success"));
		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String confirmPassword;
	private String sexe;
	
	private ArrayList<String> sexe_choice ;
	
	
	public ArrayList<String> getSexe_choice() {
		if(sexe_choice == null){
			sexe_choice = new ArrayList<String>();
			sexe_choice.add(getText("register.sexe_m"));
			sexe_choice.add(getText("register.sexe_f"));
		}
		return sexe_choice;
	}


	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
