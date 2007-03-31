/*
 * MavenManager.java
 *
 * Created on 22 mars 2007, 08:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.umlv.dragonflyEJB.locals.maven;

import javax.ejb.Local;

import fr.umlv.dragonflyEJB.beans.DependencyInformationBean;
import fr.umlv.dragonflyEJB.beans.MavenInformationBean;
import fr.umlv.dragonflyEJB.beans.ParentInformationBean;
import fr.umlv.dragonflyEJB.beans.PluginInformationBean;

/**
 *
 * @author J-Paul
 */
@Local
public interface MavenManager {
	public void createNewFile(MavenInformationBean mavenInformation,String projectName);
	public void submitGeneralInformation(MavenInformationBean mavenInformation,String projectName);
	public void addDependency(DependencyInformationBean dependency,String projectName);
	public void submitParent(ParentInformationBean parent,String projectName);
	public void addModule(String module,String projectName);
	public void addPlugin(PluginInformationBean plugin,String projectName);
	public MavenInformationBean loadMavenFile(String projectName);
	public void sayHello();
}
