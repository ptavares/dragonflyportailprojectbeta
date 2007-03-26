/*
 * MavenManagerBean.java
 *
 * Created on 22 mars 2007, 09:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fr.umlv.dragonflyEJB.services.project.maven;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.maven.Dependency;
import org.apache.maven.Model;
import org.apache.maven.Model.Dependencies;
import org.apache.maven.ObjectFactory;
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
    
    public MavenInformation loadMavenFile(String projectName) {
        return null;
    }
    
    public void sayHello() {
        System.out.println("Hello");
    }
    
}
