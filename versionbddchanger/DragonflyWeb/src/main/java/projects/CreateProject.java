package projects;

import java.sql.Date;

import javax.naming.InitialContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyEJB.services.account.adds.AccountAdds;
import fr.umlv.dragonflyEJB.services.project.creation.ProjectCreation;

public class CreateProject extends ActionSupport{

	private String nomProjet;
	private String descriptif;
	private Date date = new Date(System.currentTimeMillis());
	private String resume;
	
	public String execute() throws Exception {
		System.out.println(getDescriptif());
				
		String creator = (String) ServletActionContext.getRequest().getSession().getAttribute("nom");
		System.out.println("Createur du projet = " +creator);
		
		final InitialContext ctx = new InitialContext();
		final ProjectCreation proj=(ProjectCreation) ctx.lookup("ProjectCreation/remote");
		
		int val;
		
		if(getDate()==null && getDescriptif().length()==0){
			System.out.println("date et description null !!");
			val = proj.createProject(getNomProjet(), creator, getResume());
		}
		else
			if(getDescriptif().length()==0){
				System.out.println("description null !!");
				val = proj.createProject(getNomProjet(), creator, getResume(), getDate());
			}
			else{
				System.out.println("ok tout les champs son rempli");
				val = proj.createProject(getNomProjet(), creator, getDate(), getResume(), getDescriptif());
			}
		if(val == 1){
			addFieldError("project exist","A project with that name already exists");
			return "errors";
		}
		else if(val==2){
			addFieldError("project error","An error has occured while creating the project");
			return "errors";
		}
		
		
		System.out.println(getDescriptif()+" "+ getNomProjet());
		addActionMessage(getText("createproject.sucess"));
				
		String r = getNomProjet()+"admin";
		final AccountAdds add = (AccountAdds)ctx.lookup("AccountAdds/remote");
		add.addRole(creator, r);
		return SUCCESS;		
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
