/*
 * Admin.java
 *
 * Created on 27 mars 2007, 07:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package administration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionSupport;

import fr.umlv.dragonflyBdd.exception.DragonflyBddException;
import fr.umlv.dragonflyEJB.remote.DragonflyEJB;

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
		//System.out.println("Execute Action");
		//initAccountList();
		return SUCCESS;
	}

	public String projectList() throws Exception{
		//System.out.println("ProjectList Action");
		initProjectList();
		return "projectList";
	}

	public String accountList() throws Exception{
		//System.out.println("AccountList Action");
		initAccountList();
		return "accountList";
	}

	public String acceptAccount() {
		//System.out.println("acceptAccount "+adddelObject);
		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//final AccountModification am=(AccountModification) ctx.lookup("AccountModification/remote");   
			dEjb.activateUser(adddelObject);
			initAccountList();
		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		}
		return "accountList";
	}

	public String deleteAccount(){
		//System.out.println("deleteAccount "+adddelObject);
		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//final AccountDropper ad=(AccountDropper) ctx.lookup("AccountDropper/remote");
			dEjb.removeAccount(adddelObject);
			initAccountList();
		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		} catch (Exception e) {
			e.printStackTrace();
			return "actionError";
		}
		return "accountList";
	}

	public String acceptProject(){
		//System.out.println("acceptProject "+adddelObject);
		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//final ProjectModification pm=(ProjectModification) ctx.lookup("ProjectModification/remote");
			dEjb.activateProject(adddelObject);
			initProjectList();
		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		} catch (Exception e) {
			e.printStackTrace();
			return "actionError";
		}
		return "projectList";
	}

	public String deleteProject(){
		//System.out.println("deleteProject "+adddelObject);
		final InitialContext ctx;
		try {
			ctx = new InitialContext();
			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			// final ProjectDropper pd=(ProjectDropper) ctx.lookup("ProjectDropper/remote");
			dEjb.removeProject(adddelObject);
			
			//Ajout delete project
			initProjectList();
		} catch (NamingException e) {
			e.printStackTrace();
			return "actionError";
		} catch (DragonflyBddException e) {
			e.printStackTrace();
			return "actionError";
		} catch (Exception e) {
			e.printStackTrace();
			return "actionError";
		}
		return "projectList";
	}
	public String ActiveAccountTab(){
		//initActiveAccountList();
		init();
		return "activeAccountTab";
	}

	public String NotActiveAccountTab(){
		//initNotActiveAccountList();
		init();
		return "notActiveAccountTab";
	}

	private void initProjectList(){
		//System.out.println("InitProjectList");
		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//final ProjectInformation pi=(ProjectInformation) ctx.lookup("ProjectInformation/remote");
			//final ProjectModification pm=(ProjectModification) ctx.lookup("ProjectModification/remote");

			setProject(dEjb.getActiveProject());
//			System.out.println("Active Project **********");
//			if(getAccount() !=null){
//				for(String s : project){
//					System.out.println(s);
//				}
//			}

			setNotactiveProject(dEjb.getNotActiveProject());
//			System.out.println("Not Active Project ***********");
//			if(getNotactiveAccount() !=null){
//				for(String s : notactiveProject)
//					System.out.println(s);
//			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			e.printStackTrace();
		}
//		System.out.println("activate ");
//		for(String s : notactiveProject){
//		pm.activateProject(s);
//		System.out.println("active project "+s);
//		}


	}

	private void initActiveAccountList() {
		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//  final AccountInformation ai=(AccountInformation) ctx.lookup("AccountInformation/remote");

			setAccount(dEjb.getActiveUsers());
//			System.out.println("Active Account **********");
//			if(getAccount() !=null){
//				for(String s : account){
//					System.out.println(s);
//				}
//			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			e.printStackTrace();
		}
	}

	private void initNotActiveAccountList() {
		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			//   final AccountInformation ai=(AccountInformation) ctx.lookup("AccountInformation/remote");

			setNotactiveAccount(dEjb.getNotActiveUsers());
//			System.out.println("Not Active Account ***********");
//			if(getNotactiveAccount() !=null){
//				for(String s : notactiveAccount)
//					System.out.println(s);
//			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			e.printStackTrace();
		}
	}

	private void initAccountList() {
		final InitialContext ctx;
		try {
			ctx = new InitialContext();

			final DragonflyEJB dEjb=(DragonflyEJB) ctx.lookup("DragonflyEJB/remote");
			// final AccountInformation ai=(AccountInformation) ctx.lookup("AccountInformation/remote");
//			final AccountModification am=(AccountModification) ctx.lookup("AccountModification/remote");

			setAccount(dEjb.getActiveUsers());
//			System.out.println("Active Account **********");
//			if(getAccount() !=null){
//				for(String s : account){
//					System.out.println(s);
//				}
//			}

			setNotactiveAccount(dEjb.getNotActiveUsers());
//			System.out.println("Not Active Account ***********");
//			if(getNotactiveAccount() !=null){
//				for(String s : notactiveAccount)
//					System.out.println(s);
//			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (DragonflyBddException e) {
			e.printStackTrace();
		}

//		System.out.println("activate ");
//		for(String s : notactiveAccount){
//		am.activateAccount(s);
//		System.out.println("active account "+s);
//		}

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