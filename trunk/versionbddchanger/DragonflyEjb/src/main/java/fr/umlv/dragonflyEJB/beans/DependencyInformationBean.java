/*
 * DependencyInformation.java
 *
 * Created on 22 mars 2007, 00:02
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
public class DependencyInformationBean implements Serializable{
    
    private String groupId;
    private String artifactId;
    private String version;
    private String scope;
    
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    
}
