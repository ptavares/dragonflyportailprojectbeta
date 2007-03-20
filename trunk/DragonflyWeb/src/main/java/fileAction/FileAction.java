package fileAction;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileAction extends ActionSupport{
	private File myDoc;
	private String myDocContentType;
	private String myDocFileName;
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public File getMyDoc() {
		return myDoc;
	}

	public void setMyDoc(File myDoc) {
		this.myDoc = myDoc;
	}

	public String getMyDocContentType() {
		return myDocContentType;
	}

	public void setMyDocContentType(String myDocContentType) {
		this.myDocContentType = myDocContentType;
	}

	public String getMyDocFileName() {
		return myDocFileName;
	}

	public void setMyDocFileName(String myDocFileName) {
		this.myDocFileName = myDocFileName;
	}
	
	public String upload(){
		System.out.println("here");
		System.out.println(projectName);
		if(myDoc==null){
			System.out.println("there");
			return INPUT;
		}
		System.out.println("myDocFileName  "+myDocFileName+" myDocContentType "+myDocContentType );
		HttpServletRequest request = ServletActionContext.getRequest();
		
		File f = new File(getText("fileAction.path")+"/Upload/"+projectName+"/tmp");
		if (!f.exists()){
			f.mkdirs();
		}
		myDoc.renameTo(new File(f,myDocFileName));
		return SUCCESS;
	}
	
	public String goload(){
		System.out.println(projectName);
		return SUCCESS;
	}
}
