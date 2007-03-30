package fileAction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileAction extends ActionSupport{
	private File myDoc;
	private String myDocContentType;
	private String myDocFileName;
	private String projectName;
	private List<Fichier> FileLists=new ArrayList<Fichier>();
	private int listLength;
	
	public int getListLength() {
		return listLength;
	}

	public void setListLength(int listLength) {
		this.listLength = listLength;
	}

	public List<Fichier> getFileLists() {
		return FileLists;
	}

	public void setFileLists(List<Fichier> fileLists) {
		FileLists = fileLists;
	}

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
	
	
	private void clean(){
		myDoc=null;
		myDocContentType="";
		myDocFileName="";
	}
	
	
	public String init(){
		return SUCCESS;
	}
	
	
	
	public String upload(){
		Map<String, String> session = ActionContext.getContext().getSession();
		projectName=session.get("project");
		if(myDoc==null){
			//System.out.println("there");
			addFieldError("upload failed", "Your upload haven't success, please retry");
			return INPUT;
		}
		//System.out.println("myDocFileName  "+myDocFileName+" myDocContentType "+myDocContentType );
		
		File f = new File(getText("fileAction.path")+projectName+"/tmp");
		if (!f.exists()){
			f.mkdirs();
		}
		myDoc.renameTo(new File(f,myDocFileName));
		addActionMessage(getText("upload.theFile")+" "+myDocFileName+getText("upload.success"));
		clean();
		return SUCCESS;
	}
	
	
	public String goload() throws IOException{
		Map<String, String> session = ActionContext.getContext().getSession();
		projectName=session.get("project");
		//System.out.println(projectName);
		File f=new File(getText("fileAction.path")+projectName+"/realise");
		if (!f.exists()){
			f.mkdirs();
		}
		//System.out.println(f.getPath());
		File[] lists=f.listFiles();
		for(int i=0; i<lists.length;i++){
			//System.out.println(lists[i].getName());
			FileLists.add(new Fichier(lists[i].getName(),lists[i].getPath()));
		}
		listLength=lists.length;
		return SUCCESS;
	}
}
