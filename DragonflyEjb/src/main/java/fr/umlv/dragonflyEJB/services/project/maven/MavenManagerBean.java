/*
 * MavenManagerBean.java
 *
 * Created on 22 mars 2007, 09:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.umlv.dragonflyEJB.services.project.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.maven.Build;
import org.apache.maven.Build.Plugins;
import org.apache.maven.Dependency;
import org.apache.maven.Model;
import org.apache.maven.Model.Modules;
import org.apache.maven.Model.Dependencies;
import org.apache.maven.ObjectFactory;
import org.apache.maven.Plugin;
import org.jboss.annotation.ejb.RemoteBinding;

/**
 *
 * @author J-Paul
 */
@RemoteBinding(jndiBinding = "MavenManager/remote")
public @Stateless class MavenManagerBean implements MavenManager{
    
    public void createNewFile(MavenInformation mavenInformation, String projectName) {
        System.out.println(mavenInformation+"  --  "+projectName);
        
        if(mavenInformation==null)
            System.out.println("null");
        System.out.println("Go CreateNewFile");
        Model model = new Model();
        model.setGroupId(mavenInformation.getGroupId());
        model.setArtifactId(mavenInformation.getArtifactId());
        model.setName(mavenInformation.getName());
        model.setPackaging(mavenInformation.getPackaging());
        model.setDescription(mavenInformation.getDescription());
        
        System.out.println("generalInfo");
        
        Dependencies dependencies = null;
        if(model.getDependencies() == null ){
            System.out.println("Dependencies Does not Exist");
            dependencies = new Dependencies();
            model.setDependencies(dependencies);
        } else{
            System.out.println("Dependencies Exist");
            dependencies = model.getDependencies();
        }
        System.out.println("foreach");
        int i=0;
        for(DependencyInformation depInfo :mavenInformation.getDependencies()){
            System.out.println("foreach "+i);
            i++;
            Dependency dep = new Dependency();
            System.out.println(depInfo.getGroupId()+"---"+depInfo.getArtifactId());
            dep.setArtifactId(depInfo.getArtifactId());
            dep.setGroupId(depInfo.getGroupId());
            dep.setVersion(depInfo.getVersion());
            dep.setScope(depInfo.getScope());
            dependencies.getDependency().add(dep);
            
        }
        System.out.println("modelDependencies "+model.getDependencies());
        if(model.getDependencies() != null){
            System.out.println("modelDependencies Dependency"+model.getDependencies().getDependency());
            if(model.getDependencies().getDependency() != null)
                System.out.println("Size "+model.getDependencies().getDependency().size());
        }
        System.out.println("Debut jaxb ");
        
        try {
            System.out.println("Try");
            JAXBContext context = JAXBContext.newInstance("org.apache.maven");
            System.out.println("create JaxbContext");
            Marshaller marshaller = context.createMarshaller();
            System.out.println(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION +"--"+Marshaller.JAXB_SCHEMA_LOCATION);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd");
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
            marshaller.marshal(new ObjectFactory().createProject(model), System.out);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Fin method");
    }
    
    private File getPomFile(String projectName){
        return new File("Dragonfly/"+projectName+"/pom.xml");
    }
    
    private MavenInformation generateBasicPom(String projectName){
        MavenInformation info = new MavenInformation();
        info.setGroupId("groupId");
        info.setArtifactId(projectName);
        info.setPackaging("jar");
        info.setName(projectName);
        info.setVersion("0.0.1");
        
        Model model = new Model();
        model.setGroupId("groupId");
        model.setArtifactId(projectName);
        model.setPackaging("jar");
        model.setModelVersion("4.0.0");
        model.setName(projectName);
        model.setVersion("0.0.1");
        
        
        try {
            FileOutputStream output = new FileOutputStream("Dragonfly/"+projectName+"/pom.xml");
            JAXBContext context = JAXBContext.newInstance("org.apache.maven");
            System.out.println("create JaxbContext");
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd");
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
//        marshaller.marshal(new ObjectFactory().createProject(model), System.out);
            marshaller.marshal(new ObjectFactory().createProject(model), output);
        } catch (Exception ex) {
            ex.printStackTrace();
            info = null;
        }
        
        return info;
    }
    
    
    public MavenInformation loadMavenFile(String projectName) {
        
        File source = new File("Dragonfly/"+projectName);
        
        if(!source.exists()){
            System.out.println("!!!!!!!!!!!!!!!! project is not activate !!!!!!!!!!!!!!!!!!!!!!!!");
            source.mkdirs();
        }
        
        File pomFile = new File("Dragonfly/"+projectName+"/pom.xml");
        
        MavenInformation info = new MavenInformation();
        if(pomFile.exists() && pomFile.isFile()){
            System.out.println("File Find");
            
            try {
                System.out.println("Try");
                JAXBContext context = JAXBContext.newInstance("org.apache.maven");
                System.out.println("create JaxbContext");
                Unmarshaller unmarshaller = context.createUnmarshaller();
                JAXBElement<Model> rootElement = (JAXBElement<Model>)unmarshaller.unmarshal(pomFile);
                Model model = rootElement.getValue();
                System.out.println("Load General Information------------");
                info.setGroupId(model.getGroupId());
                info.setArtifactId(model.getArtifactId());
                info.setPackaging(model.getPackaging());
                info.setName(model.getName());
                info.setVersion(model.getVersion());
                info.setDescription(model.getDescription());
                
                
                System.out.println("Load Dependencies -----------");
                Dependencies dependencies = model.getDependencies();
                if(dependencies != null){
                    System.out.println("Dependencies --------------"+dependencies.getDependency().size());
                    for(Dependency dep : dependencies.getDependency()){
                        DependencyInformation depInfo = new DependencyInformation();
                        depInfo.setArtifactId(dep.getArtifactId());
                        depInfo.setGroupId(dep.getGroupId());
                        depInfo.setVersion(dep.getVersion());
                        depInfo.setScope(dep.getScope());
                        info.getDependencies().add(depInfo);
                    }
                    System.out.println("DepInfo "+info.getDependencies().size());
                }
                
                System.out.println("Load Modules -------------------");
                Modules modules = model.getModules();
                if(modules != null){
                    System.out.println("Module count "+modules.getModule().size());
                    for(String module:modules.getModule())
                        info.getModules().add(module);
                }
                
                
                System.out.println("Load Plugin --------------------");
                Build build = model.getBuild();
                if(build != null){
                    Plugins plugins = build.getPlugins();
                    if( plugins != null){
                        System.out.println("Plugin count "+plugins.getPlugin());
                        for(Plugin plug : plugins.getPlugin()){
                            PluginInformation pInfo = new PluginInformation();
                            pInfo.setGroupId(plug.getGroupId());
                            pInfo.setArtifactId(plug.getArtifactId());
                            pInfo.setVersion(plug.getVersion());
                            info.getPlugins().add(pInfo);
                        }
                    }
                }
                
                
                
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            
            
            System.out.println("retourne MavenInformation-------------");
            return info;
        } else{
            System.out.println("GenerateBasicPom");
            
            return generateBasicPom(projectName);
        }
    }
    
    public void sayHello() {
        System.out.println("Hello");
    }
    
    public void submitGeneralInformation(MavenInformation mavenInformation, String projectName) {
        System.out.println("submitGeneralInformation");
        File pomFile = getPomFile(projectName);
        try {
            System.out.println("Try");
            JAXBContext context = JAXBContext.newInstance("org.apache.maven");
            System.out.println("create JaxbContext");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<Model> rootElement = (JAXBElement<Model>)unmarshaller.unmarshal(pomFile);
            Model model = rootElement.getValue();
            
            model.setGroupId(mavenInformation.getGroupId());
            model.setArtifactId(mavenInformation.getArtifactId());
            model.setPackaging(mavenInformation.getPackaging());
            model.setName(mavenInformation.getName());
            model.setVersion(mavenInformation.getVersion());
            model.setDescription(mavenInformation.getDescription());
            System.out.println("General Information: "+mavenInformation.getGroupId()+"."+mavenInformation.getArtifactId());
            
            UpdatePomFile(model,context,pomFile);
        }catch (JAXBException ex){
            ex.printStackTrace();
        }
    }
    
    public void addDependency(DependencyInformation dependency, String projectName) {
        System.out.println("addDependency");
        File pomFile = getPomFile(projectName);
        try {
            System.out.println("Try");
            JAXBContext context = JAXBContext.newInstance("org.apache.maven");
            System.out.println("create JaxbContext");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<Model> rootElement = (JAXBElement<Model>)unmarshaller.unmarshal(pomFile);
            Model model = rootElement.getValue();
            
            Dependencies dependencies = null;
            if(model.getDependencies() == null){
                System.out.println("Model.Dependencies null   !!");
                dependencies = new Dependencies();
                model.setDependencies(dependencies);
            }else{
                System.out.println("Model.Dependencies okkk");
                dependencies = model.getDependencies();
            }
            
            Dependency dep = new Dependency();
            dep.setGroupId(dependency.getGroupId());
            dep.setArtifactId(dependency.getArtifactId());
            dep.setVersion(dependency.getVersion());
            dep.setScope(dependency.getScope());
            
            dependencies.getDependency().add(dep);
            
            System.out.println("Add Dependency: "+dependency.getGroupId()+"."+dependency.getArtifactId());
            
            UpdatePomFile(model,context,pomFile);
        }catch (JAXBException ex){
            ex.printStackTrace();
        }
    }
    
    public void submitParent(ParentInformation parent, String projectName) {
        System.out.println("submitParent");
    }
    
    public void addModule(String module, String projectName) {
        System.out.println("addModule");
        File pomFile = getPomFile(projectName);
        try {
            System.out.println("Try");
            JAXBContext context = JAXBContext.newInstance("org.apache.maven");
            System.out.println("create JaxbContext");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<Model> rootElement = (JAXBElement<Model>)unmarshaller.unmarshal(pomFile);
            Model model = rootElement.getValue();
            
            if(model.getModules() == null)
                model.setModules(new Modules());
            model.getModules().getModule().add(module);
            
            System.out.println("Add Module: "+module);
            UpdatePomFile(model,context,pomFile);
        }catch (JAXBException ex){
            ex.printStackTrace();
        }
    }
    
    public void addPlugin(PluginInformation plugin, String projectName) {
        System.out.println("addPlugin");
        File pomFile = getPomFile(projectName);
        try {
            System.out.println("Try");
            JAXBContext context = JAXBContext.newInstance("org.apache.maven");
            System.out.println("create JaxbContext");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<Model> rootElement = (JAXBElement<Model>)unmarshaller.unmarshal(pomFile);
            Model model = rootElement.getValue();
            
            if(model.getBuild() == null)
                model.setBuild(new Build());
            Build build = model.getBuild();
            if(build.getPlugins() == null)
                build.setPlugins(new Plugins());
            
            Plugin plug = new Plugin();
            plug.setGroupId(plugin.getGroupId());
            plug.setArtifactId(plugin.getArtifactId());
            plug.setVersion(plugin.getVersion());
            build.getPlugins().getPlugin().add(plug);
            
            
            System.out.println("Add Plugin: "+plugin.getGroupId()+"."+plugin.getArtifactId());
            UpdatePomFile(model,context,pomFile);
        }catch (JAXBException ex){
            ex.printStackTrace();
        }
    }
    
    private void UpdatePomFile(Model model,JAXBContext context, File out) throws JAXBException{
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd");
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
//        marshaller.marshal(new ObjectFactory().createProject(model), System.out);
        marshaller.marshal(new ObjectFactory().createProject(model), out);
    }
    
    
    
}
