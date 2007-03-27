package fr.umlv.dragonflyEJB.beans;


/**
 * Properties for One Project Description
 * 
 * @author Patrick
 *
 */
public class ProjectInformationsBean {

        private boolean actived;
	private String projectLeader;
	private String creationDate;
	private String resume;
	private String description;
	private String developers;
	private String name;
	
	public ProjectInformationsBean() {
	}
	       
	/**
	 *  
	 * @return Creation Date for this Project
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * Set Creation Date for this Project
	 * 
	 * @param creationDate
	 */
	public void setCreationDate(String creationDate) {
		if(creationDate == null)
			this.creationDate = "";
		this.creationDate = creationDate;
	}

	/**
	 * 
	 * @return Description for this Project
	 */
	public String getDescription() {
		 return description;
	}

	/**
	 * Set Description for this Project
	 * @param description
	 */
	public void setDescription(String description) {
		if(description == null)
			this.description = "";
		this.description = description;
	}

	/**
	 * 
	 * @return Developers for this Project
	 */
	public String getDevelopers() {
		return developers;
	}

	/**
	 * Set Developers for this Project
	 * @param developers
	 */
	public void setDevelopers(String developers) {
		if(developers == null)
			this.developers = "";
		this.developers = developers;
	}

	/**
	 * 
	 * @return ProjectLeader for this Project
	 */
	public String getProjectLeader() {
		return projectLeader;
	}

	/**
	 * Set ProjectLeader for this Project
	 * @param projectLeader
	 */
	public void setProjectLeader(String projectLeader) {
		if(projectLeader == null)
			this.projectLeader = "";
		this.projectLeader = projectLeader;
	}

	/**
	 * 
	 * @return Resume for this Project
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * Set Resume for this Project
	 * @param resume
	 */
	public void setResume(String resume) {
		if(resume == null)
			this.resume = "";
		this.resume = resume;
	}

	/**
	 * Set Project Name
	 * @param name
	 */
	public void setName(String name) {
		if(name == null)
			this.name = "";
		this.name = name;
	}

	/**
	 * 
	 * @return Project Name
	 */
	public String getName() {
		return name;
	}

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean Actived) {
        this.actived = Actived;
    }
}
