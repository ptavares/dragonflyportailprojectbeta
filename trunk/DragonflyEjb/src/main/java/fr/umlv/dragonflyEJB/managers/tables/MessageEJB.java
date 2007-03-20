package fr.umlv.dragonflyEJB.managers.tables;

import java.util.Date;

import fr.umlv.dragonflyBdd.tables.Message;

public class MessageEJB {
	 // Fields    

    private long id;
    private String name;
    private String senter;
    private String content;
    private Date sendTime;
    private boolean readed;

    // Constructors

   /** default constructor */
   public MessageEJB() {
   }

	/** minimal constructor */
   public MessageEJB(String name, String content, Date sendTime) {
       this.name = name;
       this.content = content;
       this.sendTime = sendTime;
   }
   /** full constructor */
   public MessageEJB(String name, String senter, String content, Date sendTime, boolean readed) {
      this.name = name;
      this.senter = senter;
      this.content = content;
      this.sendTime = sendTime;
      this.readed = readed;
   }
   
   public MessageEJB(Message m){
	   this.id=m.getId();
	   this.name=m.getName();
	   this.senter=m.getSenter();
	   this.content=m.getContent();
	   this.sendTime=m.getSendTime();
	   this.readed=m.isReaded();
   }
  
   // Property accessors
   public long getId() {
       return this.id;
   }
   
   public void setId(long id) {
       this.id = id;
   }
   public String getName() {
       return this.name;
   }
   
   public void setName(String name) {
       this.name = name;
   }
   public String getSenter() {
       return this.senter;
   }
   
   public void setSenter(String senter) {
       this.senter = senter;
   }
   public String getContent() {
       return this.content;
   }
   
   public void setContent(String content) {
       this.content = content;
   }
   public Date getSendTime() {
       return this.sendTime;
   }
   
   public void setSendTime(Date sendTime) {
       this.sendTime = sendTime;
   }
   public boolean isReaded() {
       return this.readed;
   }
   
   public void setReaded(boolean readed) {
       this.readed = readed;
   }

}
