/*
 * ParentInformation.java
 *
 * Created on 27 mars 2007, 07:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.umlv.dragonflyEJB.beans;

import java.io.Serializable;

/**
 *
 * @author J-Paul
 */
public class ParentInformationBean implements Serializable{
    private String artifactId;
    private String groupId;
    private String version;
    
    public String getArtifactId() {
        return artifactId;
    }
    
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
    
    public String getGroupId() {
        return groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
}
