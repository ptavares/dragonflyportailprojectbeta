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
    public MavenInformation loadMavenFile(String projectName);
    public void sayHello();
}
