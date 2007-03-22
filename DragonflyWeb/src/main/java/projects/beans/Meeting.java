package projects.beans;

import java.util.List;

public class Meeting {

	private String number;
	private String subject;
	private String description;
	private String postDate;
	private String date;

	
	public Meeting(List<String> meeting) {
		number = meeting.get(0);
		subject = meeting.get(1);
		description = meeting.get(2);
		postDate = meeting.get(3);
		date = meeting.get(4);
		
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

	
}
