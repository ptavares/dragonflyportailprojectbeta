package projects;

import com.opensymphony.xwork2.ActionSupport;

public class MyProject extends ActionSupport {
	public String test;
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String execute() {
		System.out.println("hehehehe"+test);
		return SUCCESS;
	}

}
