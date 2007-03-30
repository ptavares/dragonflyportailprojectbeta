/*
 * DeleteUser.java
 *
 * Created on 30 mars 2007, 17:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package projects.deletes;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

/**
 *
 * @author J-Paul
 */
public class DeleteUser extends ActionSupport{
    private String userMail;
    
    public String execute() {
        String name = (String) ServletActionContext.getRequest().getSession().getAttribute("project");
        
        final InitialContext ctx;
		try {
			ctx = new InitialContext();
			final DragonflyEJB dEJB=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
  
            if(!dEJB.removeUserFromProject(name,userMail)){
                addActionError(getText("administration.removeUser.accountError"));
                return ERROR;
            }
            addActionMessage(getText("administration.removeUser.succes"));
        } catch (NamingException e) {
            // TODO REDIRECTION ERREUR EJB
            e.printStackTrace();
        } catch (DragonflyBddException e) {
            // TODO REDIRECTION ERREUR BDD
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String getUserMail() {
        return userMail;
    }
    
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
    
}
