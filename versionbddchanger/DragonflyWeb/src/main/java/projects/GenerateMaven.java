/*
 * GenerateMaven.java
 *
 * Created on 22 mars 2007, 09:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package projects;

import com.opensymphony.xwork2.ActionSupport;
import fr.umlv.dragonflyEJB.services.project.maven.DependencyInformation;
import fr.umlv.dragonflyEJB.services.project.maven.MavenInformation;
import fr.umlv.dragonflyEJB.services.project.maven.MavenManager;
import javax.naming.InitialContext;

/**
 *
 * @author J-Paul
 */
public class GenerateMaven extends ActionSupport{
    private MavenInformation mavenInformation = new MavenInformation();
    private String groupId;
    private String artifactId;
    private String version;
    private String scope;
    
    @Override
    public String execute() throws Exception {
        System.out.println("Execute");
        intializeMavenInformation();
        return INPUT;
    }
    
    private void intializeMavenInformation(){
        mavenInformation.setGroupId("groupId");
        mavenInformation.setArtifactId("artifactId");
        mavenInformation.setPackaging("jar");
        mavenInformation.setName("name");
        mavenInformation.setDescription("description");
        mavenInformation.setVersion("0.0.1");
        
        DependencyInformation depend = new DependencyInformation();
        depend.setArtifactId("artifactIDDep");
        depend.setGroupId("groupIdDep");
        depend.setVersion("0.0.2");
        depend.setScope("test");
        mavenInformation.getDependencies().add(depend);
        
        depend = new DependencyInformation();
        depend.setArtifactId("artifactIDDep1");
        depend.setGroupId("groupIdDep1");
        depend.setVersion("0.0.3");
        depend.setScope("compile");
        mavenInformation.getDependencies().add(depend);
        System.out.println("initGenPom "+mavenInformation.getDependencies().size());
    }
    
    public String submit() throws Exception{
        System.out.println("Submit");
        
        final InitialContext ctx = new InitialContext();
        System.out.println("Before");
        final MavenManager mavenManager=(MavenManager) ctx.lookup("MavenManager/remote");
        System.out.println("recup MavenManger");
        System.out.println(mavenManager);
        mavenManager.sayHello();
        intializeMavenInformation();
        System.out.println("Init ok");
        try{
            mavenManager.createNewFile(mavenInformation, "projectName");
        }catch (Exception e){
            System.out.println("GenerateMaven.submit()"+e.getMessage());
            e.printStackTrace();
        }
        
        return SUCCESS;
    }
    
    
    public MavenInformation getMavenInformation() {
        return mavenInformation;
    }
    
    public void setMavenInformation(MavenInformation mavenInformation) {
        this.mavenInformation = mavenInformation;
    }
    
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
