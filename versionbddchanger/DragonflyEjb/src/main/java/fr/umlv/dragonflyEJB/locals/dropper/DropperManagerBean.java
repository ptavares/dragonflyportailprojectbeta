package fr.umlv.dragonflyEJB.locals.dropper;

import java.io.File;

import org.jboss.annotation.ejb.LocalBinding;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import javax.ejb.Stateless;

@LocalBinding(jndiBinding = "DropperManager/local")
public @Stateless class DropperManagerBean implements DropperManager {
	private final static fr.umlv.dragonflyBdd.AccountManager AM_MANAGER;

	static {
		AM_MANAGER = fr.umlv.dragonflyBdd.AccountManager.getAccountManagerInstance();
	}

	private final static fr.umlv.dragonflyBdd.ProjectManager PM_MANAGER;

	static {
		PM_MANAGER = fr.umlv.dragonflyBdd.ProjectManager.getProjectManagerInstance();
	}

	public boolean removeAccount(String email) throws DragonflyBddException {
		return AM_MANAGER.removeAccount(email);
	}
	public boolean removeProject(String name) throws DragonflyBddException {
		File source = new File("Dragonfly/"+name);
		deleteDirectory(source);
		return PM_MANAGER.removeProject(name);
	}


	/**
	 * Remove a directory
	 * @param path the path of the directory
	 * @return true - directory removed, false otherwise
	 */
	private boolean deleteDirectory(File path) { 
		boolean resultat = true; 
		if( path.exists() ) { 
			File[] files = path.listFiles(); 
			for(int i=0; i<files.length; i++) { 
				if(files[i].isDirectory()) { 
					resultat &= deleteDirectory(files[i]); 
				} 
				else { 
					resultat &= files[i].delete(); 
				} 
			} 
		} 
		resultat &= path.delete(); 
		return( resultat ); 
	} 
}
