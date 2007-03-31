package projects;

import java.sql.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;


public class CreateProject extends ActionSupport{

	private String nomProjet=getText("createproject.projectName");
	private String descriptif;
	private Date date = new Date(System.currentTimeMillis());
	private String resume=getText("createproject.resume1");

	public String execute() {

		String creator = (String) ServletActionContext.getRequest().getSession().getAttribute("nom");

		if(!checknomProjet(getNomProjet())){
			addActionError(getText("createproject.nameError"));
			nomProjet = getText("createproject.projectName");
			return ERROR;
		}


		InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//final ProjectCreation proj=(ProjectCreation) ctx.lookup("ProjectCreation/remote");

			int val;

			Date dateTest = new Date(System.currentTimeMillis());

			if(getDate()!=null && getDate().before(dateTest)){
				addActionError(getText("createproject.dateError"));
				return ERROR;
			}

			if(getDate()==null && getDescriptif().length()==0){
				val = dEjb.createProject(getNomProjet(), creator, getResume());
			}
			else
				if(getDescriptif().length()==0){
					val = dEjb.createProject(getNomProjet(), creator, getResume(), getDate());
				}
				else{
					val = dEjb.createProject(getNomProjet(), creator, getDate(), getResume(), getDescriptif());
				}
			if(val == 1){
				addActionError(getText("createproject.projectExist"));
				return ERROR;
			}
			else if(val==2){
				addActionError(getText("createproject.projectCreationError"));
				return ERROR;
			}

			addActionMessage(getText("createproject.success1")+" ' "+getNomProjet()+" ' "+getText("createproject.success2"));

			String r = getNomProjet()+"admin";
			//final AccountAdds add = (AccountAdds)ctx.lookup("AccountAdds/remote");
			dEjb.addRole(creator, r);

		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		}
		return SUCCESS;		
	}

	private boolean checknomProjet(String nomProjet) {

		for(int i=0; i<nomProjet.length() ; i++){
			if(!Character.isLetterOrDigit(nomProjet.charAt(i)))
				if(!(nomProjet.charAt(i)=='_'))		
					if(!(Character.isWhitespace(nomProjet.charAt(i))) )
						return false;
		}

		return true;

	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getNomProjet() {
		return nomProjet;
	}

	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
