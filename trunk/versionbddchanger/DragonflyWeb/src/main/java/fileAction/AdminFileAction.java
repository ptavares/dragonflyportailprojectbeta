package fileAction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminFileAction extends  ActionSupport {
	private String projectName;
	private List<Fichier> nameList=new ArrayList<Fichier>();
	private int listLength;
	private String fileDel;
	private String file;

	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public String getFileDel() {
		return fileDel;
	}


	public void setFileDel(String fileDel) {
		this.fileDel = fileDel;
	}

	private void setProject(){
		Map<String, String> session = ActionContext.getContext().getSession();
		projectName=session.get("project");
	}
	public String load(){
		if (projectName==null)
			setProject();
		File f = new File(getText("fileAction.path")+"/Upload/"+projectName+"/tmp");
		if (!f.exists()){
			f.mkdirs();
		}
		File[] l=f.listFiles();
		for(int i=0;i<l.length;i++){
			nameList.add(new Fichier(l[i].getName(),l[i].getPath()));
			System.out.println("--------------------------------->"+l[i].getName());
		}
		listLength=l.length;
		return SUCCESS;
	}


	public String del(){
		setProject();
		File fd=new File(fileDel);
		if (fd.exists())
			fd.delete();
		return load();
	}

	public String valide(){
		setProject();
		File f = new File(getText("fileAction.path")+"/Upload/"+projectName+"/tmp/"+file);
		File f2=new File(getText("fileAction.path")+"/Upload/"+projectName+"/realise");
		if (!f2.exists()){
			f2.mkdirs();
		}
		System.out.println(f==null);
		System.out.println(f2==null);
		System.out.println(file);
		File des=new File(f2,file);
		if (des.exists())
			des.delete();
		f.renameTo(des);
		return load();
	}
	public int getListLength() {
		return listLength;
	}

	public void setListLength(int listLength) {
		this.listLength = listLength;
	}

	public List<Fichier> getNameList() {
		return nameList;
	}

	public void setNameList(List<Fichier> nameList) {
		this.nameList = nameList;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


}
