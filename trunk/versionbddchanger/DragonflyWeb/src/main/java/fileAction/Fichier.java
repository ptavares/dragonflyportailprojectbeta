package fileAction;

public class Fichier {
	private String fliename;
	private String path;
	
	public Fichier(String filename,String path){
		this.fliename=filename;
		this.path=path;
	}
	
	public String getFliename() {
		return fliename;
	}
	public void setFliename(String fliename) {
		this.fliename = fliename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
