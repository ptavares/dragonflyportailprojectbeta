package projects.beans;

import java.util.List;

public class News {

	private String number;
	private String author;
	private String subject;
	private String description;
	private String postDate;
	
	public News(List<String> n) {
		number = n.get(0);
		author = n.get(1);
		subject = n.get(2);
		description = n.get(3);
		postDate = n.get(4);
		
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
