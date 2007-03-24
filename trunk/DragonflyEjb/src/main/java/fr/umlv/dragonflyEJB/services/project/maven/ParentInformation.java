/*
 * ParentInformation.java
 *
 * Created on 24 mars 2007, 00:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.umlv.dragonflyEJB.services.project.maven;

import java.io.Serializable;

/**
 *
 * @author J-Paul
 */
public class ParentInformation implements Serializable{
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
