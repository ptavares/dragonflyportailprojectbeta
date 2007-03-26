package projects.beans;

import java.util.List;

public class Task {

	private String number;
	private String subject;
	private String description;
	private String postDate;
	private String startDate;
	private String endDate;
	
	public Task(List<String> taskInfo){
		number = taskInfo.get(0);
		subject = taskInfo.get(1);
		description = taskInfo.get(2);
		postDate = taskInfo.get(3);
		startDate = taskInfo.get(4);
		endDate = taskInfo.get(5);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}
