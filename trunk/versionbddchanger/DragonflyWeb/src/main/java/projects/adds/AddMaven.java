/*
 * AddMaven.java
 *
 * Created on 27 mars 2007, 07:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package projects.adds;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.umlv.dragonflyEJB.services.project.maven.DependencyInformation;
import fr.umlv.dragonflyEJB.services.project.maven.MavenInformation;
import fr.umlv.dragonflyEJB.services.project.maven.MavenManager;
import fr.umlv.dragonflyEJB.services.project.maven.PluginInformation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.maven.Plugin;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author J-Paul
 */
public class AddMaven extends ActionSupport{
    private String module;
    private String groupId;
    private String artifactId;
    private String version;
    private String scope;
    private String description;
    private String name;
    private String packaging;
    
    public String showDependencyPopup() throws Exception{
        System.out.println("showDependencyPopup");
        return "dependency";
    }
    
    public String showPluginPopup() throws Exception{
        System.out.println("showPluginPopup");
        return "plugin";
    }
    
    public String showModulePopup() throws Exception{
        System.out.println("showModulePopup");
        return "module";
    }
    
    public String Dependency() throws Exception{
        Map<String, String> session = ActionContext.getContext().getSession();
        String ProName=session.get("project");
        System.out.println("------------>project "+ProName);
        System.out.println("Dependency");
        System.out.println("groupId "+getGroupId());
        System.out.println("artifactId "+getArtifactId());
        System.out.println("version "+getVersion());
        System.out.println("scope "+getScope());
        final InitialContext ctx = new InitialContext();
        System.out.println("Before");
        final MavenManager mavenManager =(MavenManager) ctx.lookup("MavenManager/remote");
        DependencyInformation depInfo = new DependencyInformation();
        depInfo.setGroupId(getGroupId());
        depInfo.setArtifactId(getArtifactId());
        depInfo.setVersion(getVersion());
        depInfo.setScope(getScope());
        mavenManager.addDependency(depInfo,ProName);
        
        String result = getGroupId()+","+getArtifactId()+","+getVersion()+","+getScope();
        System.out.println("Result : "+result);
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            System.out.println("begin response");
            PrintWriter out = response.getWriter();
            out.println(result);
            out.flush();
            System.out.println("end response");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String Plugin() throws Exception{
        Map<String, String> session = ActionContext.getContext().getSession();
        String ProName=session.get("project");
        System.out.println("------------>project "+ProName);
        System.out.println("Plugin");
        System.out.println("groupId "+getGroupId());
        System.out.println("artifactId "+getArtifactId());
        System.out.println("version "+getVersion());
        final InitialContext ctx = new InitialContext();
        System.out.println("Before");
        final MavenManager mavenManager =(MavenManager) ctx.lookup("MavenManager/remote");
        PluginInformation plugInfo = new PluginInformation();
        plugInfo.setGroupId(getGroupId());
        plugInfo.setArtifactId(getArtifactId());
        plugInfo.setVersion(getVersion());
        mavenManager.addPlugin(plugInfo,ProName);
        
        String result = getGroupId()+","+getArtifactId()+","+getVersion();
        System.out.println("Result : "+result);
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            System.out.println("begin response");
            PrintWriter out = response.getWriter();
            out.println(result);
            out.flush();
            System.out.println("end response");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String Module() throws Exception{
        Map<String, String> session = ActionContext.getContext().getSession();
        String ProName=session.get("project");
        System.out.println("------------>project "+ProName);
        System.out.println("Module");
        System.out.println("module "+getModule());
        final InitialContext ctx = new InitialContext();
        System.out.println("Before");
        final MavenManager mavenManager =(MavenManager) ctx.lookup("MavenManager/remote");
        mavenManager.addModule(getModule(),ProName);
        
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            System.out.println("begin response");
            PrintWriter out = response.getWriter();
            out.println(getModule());
            out.flush();
            System.out.println("end response");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public String execute() throws Exception{
        Map<String, String> session = ActionContext.getContext().getSession();
        String ProName=session.get("project");
        System.out.println("------------>project "+ProName);
        System.out.println("groupId "+getGroupId());
        System.out.println("artifactId "+getArtifactId());
        System.out.println("packaging "+getPackaging());
        System.out.println("name "+getName());
        System.out.println("version "+getVersion());
        System.out.println("description "+getDescription());
        final InitialContext ctx = new InitialContext();
        System.out.println("Before");
        final MavenManager mavenManager =(MavenManager) ctx.lookup("MavenManager/remote");
        MavenInformation info = mavenManager.loadMavenFile(ProName);
        
//        HttpServletResponse response = ServletActionContext.getResponse();
//        try {
//            System.out.println("begin response");
//            PrintWriter out = response.getWriter();
//            out.println("Maven File Update General Information");
//            out.flush();
//            System.out.println("end response");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        return null;
    }
    
    public String getModule() {
        return module;
    }
    
    public void setModule(String module) {
        this.module = module;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

}