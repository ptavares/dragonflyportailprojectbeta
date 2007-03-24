/*
 * MavenManager.java
 *
 * Created on 22 mars 2007, 08:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.umlv.dragonflyEJB.services.project.maven;

import javax.ejb.Remote;

/**
 *
 * @author J-Paul
 */
@Remote
public interface MavenManager {
    public void createNewFile(MavenInformation mavenInformation,String projectName);
    public void submitGeneralInformation(MavenInformation mavenInformation,String projectName);
    public void addDependency(DependencyInformation dependency,String projectName);
    public void submitParent(ParentInformation parent,String projectName);
    public void addModule(String module,String projectName);
    public void addPlugin(PluginInformation plugin,String projectName);
    public MavenInformation loadMavenFile(String projectName);
    public void sayHello();
}
