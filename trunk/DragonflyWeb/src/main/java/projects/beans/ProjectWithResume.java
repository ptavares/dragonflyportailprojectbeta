package projects.beans;

public class ProjectWithResume {
	private String name;
	private String resume;
	
	public ProjectWithResume(String name, String resume){
		this.name=name;
		this.resume=resume;
	}
	
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
