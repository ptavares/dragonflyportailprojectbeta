/*
 * Admin.java
 *
 * Created on 22 mars 2007, 15:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package administration;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author J-Paul
 */
public class Admin extends ActionSupport{
    
    @Override
    public String execute() throws Exception{
        System.out.println("Execute");
        return SUCCESS;
    }
    
    public String projectList() throws Exception{
        System.out.println("ProjectList");
        return "projectList";
    }
    
    public String accountList() throws Exception{
        System.out.println("AccountList");
        return "accountList";
    }
    
}
