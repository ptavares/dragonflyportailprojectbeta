package fr.umlv.dragonflyEJB.beans;

public class QuestionResponseBean {
	
		private String question;
		private String response;
		private String QRId;
		
		public QuestionResponseBean() {
		}

		public String getQRId() {
			return QRId;
		}

		public void setQRId(String id) {
			QRId = id;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getResponse() {
			return response;
		}

		public void setResponse(String response) {
			this.response = response;
		}
	
}
