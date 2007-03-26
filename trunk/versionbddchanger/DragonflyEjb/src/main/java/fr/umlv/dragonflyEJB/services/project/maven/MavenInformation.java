/*
 * MavenInformation.java
 *
 * Created on 22 mars 2007, 00:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.umlv.dragonflyEJB.services.project.maven;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author J-Paul
 */
public class MavenInformation implements Serializable{
    private String groupId;
    private String artifactId;
    private String packaging;
    private String name;
    private String version;
    private String description;
    
    private ArrayList<DependencyInformation> dependencies = new ArrayList<DependencyInformation>();

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

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<DependencyInformation> getDependencies() {
        return dependencies;
    }

    public void setDependencies(ArrayList<DependencyInformation> dependencies) {
        this.dependencies = dependencies;
    }
    
    
}
