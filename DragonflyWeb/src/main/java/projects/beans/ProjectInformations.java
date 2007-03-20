package projects.beans;

import java.util.Map;

public class ProjectInformations {

		private String projectLeader;
		private String creationDate;
		private String resume;
		private String description;
		private String developers;
		
			
		public ProjectInformations(Map<String, String> projectInformations) {
			projectLeader = projectInformations.get("ProjectLeader");
			creationDate = projectInformations.get("CreationDate");
			resume = projectInformations.get("Resume");
			description = projectInformations.get("Description");
			developers = projectInformations.get("Developers");
		}

		public String getCreationDate() {
			return creationDate;
		}

		public void setCreationDate(String creationDate) {
			this.creationDate = creationDate;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getDevelopers() {
			return developers;
		}

		public void setDevelopers(String developers) {
			this.developers = developers;
		}

		public String getProjectLeader() {
			return projectLeader;
		}

		public void setProjectLeader(String projectLeader) {
			this.projectLeader = projectLeader;
		}

		public String getResume() {
			return resume;
		}

		public void setResume(String resume) {
			this.resume = resume;
		}
	
}
