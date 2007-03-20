package Popular;

import static com.opensymphony.xwork2.ActionSupport.*;

import java.util.List;

import javax.naming.InitialContext;


//import projet.Projets;

//import test.hibernate.Projet;

public class popProjets {
//	List<Projet> projets;

	public String execute() throws Exception {
//		final InitialContext ctx = new InitialContext();
//		final Projets pBean=(Projets)ctx.lookup("DragonflyProject/ProjetsBean/remote");
//		List<Projet> p2=pBean.getPopProjet();
//		this.projets=p2;
//		for (Projet projet : p2) {
//		System.out.println(projet.getName());
//		}
//		if(p2.size()<=0){
//		return "mal";
//		}
		return SUCCESS;
	}

//	public List<Projet> getProjets() {
//	return projets;
//	}

//	public void setProjets(List<Projet> projets) {
//	this.projets = projets;
//	}


}
