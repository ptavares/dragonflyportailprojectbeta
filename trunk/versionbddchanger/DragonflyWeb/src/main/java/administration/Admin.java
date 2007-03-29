/*
 * Admin.java
 *
 * Created on 27 mars 2007, 07:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package administration;

import com.opensymphony.xwork2.ActionSupport;
import fr.umlv.dragonflyEJB.services.account.dropper.AccountDropper;
import fr.umlv.dragonflyEJB.services.account.information.AccountInformation;
import fr.umlv.dragonflyEJB.services.account.modification.AccountModification;
import fr.umlv.dragonflyEJB.services.project.dropper.ProjectDropper;
import fr.umlv.dragonflyEJB.services.project.information.ProjectInformation;
import fr.umlv.dragonflyEJB.services.project.modification.ProjectModification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.InitialContext;

/**
 *
 * @author J-Paul
 */
public class Admin extends ActionSupport{
    private List<String> project = new ArrayList<String>();
    private List<String> notactiveProject = new ArrayList<String>();
    private List<String> account = new ArrayList<String>();
    private List<String> notactiveAccount = new ArrayList<String>();
    private String adddelObject;
    
    private void init(){
        getAccount().addAll(Arrays.asList( new String[]{"jp","pat","alex"} ));
        getNotactiveAccount().addAll(Arrays.asList( new String[]{"bibi","mcdo"} ));
        getProject().addAll(Arrays.asList( new String[]{"jpproject","patproject","alexproject"}) );
        getNotactiveProject().addAll(Arrays.asList(new String[]{"bibiproject","mcdoproject"}));
    }
    
    @Override
    public String execute() throws Exception{
        System.out.println("Execute Action");
        //initAccountList();
        return SUCCESS;
    }
    
    public String projectList() throws Exception{
        System.out.println("ProjectList Action");
        initProjectList();
        return "projectList";
    }
    
    public String accountList() throws Exception{
        System.out.println("AccountList Action");
        initAccountList();
        return "accountList";
    }
    
    public String acceptAccount() throws Exception{
        System.out.println("acceptAccount "+adddelObject);
        final InitialContext ctx = new InitialContext();
        final AccountModification am=(AccountModification) ctx.lookup("AccountModification/remote");   
        am.activateAccount(adddelObject);
        initAccountList();
        return "accountList";
    }
    
    public String deleteAccount() throws Exception{
        System.out.println("deleteAccount "+adddelObject);
        final InitialContext ctx = new InitialContext();
        final AccountDropper ad=(AccountDropper) ctx.lookup("AccountDropper/remote");
        ad.removeAccount(adddelObject);
        //Ajout delete account
        initAccountList();
        return "accountList";
    }
    
    public String acceptProject() throws Exception{
        System.out.println("acceptProject "+adddelObject);
        final InitialContext ctx = new InitialContext();
        final ProjectModification pm=(ProjectModification) ctx.lookup("ProjectModification/remote");
        pm.activateProject(adddelObject);
        initProjectList();
        return "projectList";
    }
    
    public String deleteProject() throws Exception{
        System.out.println("deleteProject "+adddelObject);
        final InitialContext ctx = new InitialContext();
        final ProjectDropper pd=(ProjectDropper) ctx.lookup("ProjectDropper/remote");
        pd.removeProject(adddelObject);
        //Ajout delete project
        initProjectList();
        return "projectList";
    }
    public String ActiveAccountTab() throws Exception{
        //initActiveAccountList();
        init();
        return "activeAccountTab";
    }
    
    public String NotActiveAccountTab() throws Exception{
        //initNotActiveAccountList();
        init();
        return "notActiveAccountTab";
    }
    
    private void initProjectList() throws Exception{
        System.out.println("InitProjectList");
        final InitialContext ctx = new InitialContext();
        final ProjectInformation pi=(ProjectInformation) ctx.lookup("ProjectInformation/remote");
        final ProjectModification pm=(ProjectModification) ctx.lookup("ProjectModification/remote");
        
        setProject(pi.getActiveProject());
        System.out.println("Active Project **********");
        if(getAccount() !=null){
            for(String s : project){
                System.out.println(s);
            }
        }
        
        setNotactiveProject(pi.getNotActiveProject());
        System.out.println("Not Active Project ***********");
        if(getNotactiveAccount() !=null){
            for(String s : notactiveProject)
                System.out.println(s);
        }
        
//        System.out.println("activate ");
//        for(String s : notactiveProject){
//            pm.activateProject(s);
//            System.out.println("active project "+s);
//        }
        
    }
    
    private void initActiveAccountList() throws Exception{
        final InitialContext ctx = new InitialContext();
        final AccountInformation ai=(AccountInformation) ctx.lookup("AccountInformation/remote");
        
        setAccount(ai.getActiveUsers());
        System.out.println("Active Account **********");
        if(getAccount() !=null){
            for(String s : account){
                System.out.println(s);
            }
        }
    }
    
    private void initNotActiveAccountList() throws Exception{
        final InitialContext ctx = new InitialContext();
        final AccountInformation ai=(AccountInformation) ctx.lookup("AccountInformation/remote");
        
        setNotactiveAccount(ai.getNotActiveUsers());
        System.out.println("Not Active Account ***********");
        if(getNotactiveAccount() !=null){
            for(String s : notactiveAccount)
                System.out.println(s);
        }
    }
    
    private void initAccountList() throws Exception{
        final InitialContext ctx = new InitialContext();
        final AccountInformation ai=(AccountInformation) ctx.lookup("AccountInformation/remote");
//        final AccountModification am=(AccountModification) ctx.lookup("AccountModification/remote");
        
        setAccount(ai.getActiveUsers());
        System.out.println("Active Account **********");
        if(getAccount() !=null){
            for(String s : account){
                System.out.println(s);
            }
        }
        
        setNotactiveAccount(ai.getNotActiveUsers());
        System.out.println("Not Active Account ***********");
        if(getNotactiveAccount() !=null){
            for(String s : notactiveAccount)
                System.out.println(s);
        }
        
//        System.out.println("activate ");
//        for(String s : notactiveAccount){
//            am.activateAccount(s);
//            System.out.println("active account "+s);
//        }
        
    }
    
    public List<String> getProject() {
        return project;
    }
    
    public void setProject(List<String> project) {
        this.project = project;
    }
    
    public List<String> getNotactiveProject() {
        return notactiveProject;
    }
    
    public void setNotactiveProject(List<String> notactiveProject) {
        this.notactiveProject = notactiveProject;
    }
    
    public List<String> getAccount() {
        return account;
    }
    
    public void setAccount(List<String> account) {
        this.account = account;
    }
    
    public List<String> getNotactiveAccount() {
        return notactiveAccount;
    }
    
    public void setNotactiveAccount(List<String> notactiveAccount) {
        this.notactiveAccount = notactiveAccount;
    }

    public String getAdddelObject() {
        return adddelObject;
    }

    public void setAdddelObject(String adddelObject) {
        this.adddelObject = adddelObject;
    }
    

    
}