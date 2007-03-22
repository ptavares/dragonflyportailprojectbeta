<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<STYLE>
.back{
background-color: #87cefa;
height: 100%;
}
</STYLE>

<s:head theme="ajax"/>

<script type="text/javascript">
dojo.event.topic.subscribe("refreshMeetingTab",function(data,type,request){
alert("ca passe la");
		if(type=="load"){
			alert(data);
			/* Supprime la balise <form> de la reponse contenue dans Data*/			
			var suppBalise = data.replace(/<([^>])*>/g,'');
			/*Supprime les espaces inutiles */
			var suppSpace = suppBalise.replace(/(^\s*)|(\s*$)/g,''); 
			/*On extrait les différentes variables*/
			var split = suppSpace.split("-");
			/* On envoit dans l'ordre meetingId,subject,description,post,dateMeeting*/			
			opener.addRowMeeting(split[0],split[1],split[2],split[3],split[4]);
	 		window.close();
	 		}
	 } );
</script>


</head>
<body>

<%
String str = (String)session.getAttribute("project");
System.out.println("yooiooo------>"+str);
//session.setAttribute("project",str);
%>

<div class="back">
<h3 align="center"><b>Create a new Meeting</b></h3>

<s:form action="AddMeeting.action" method="post">
<s:textfield name="subj" label="%{getText('adds.subject')}" size="45" />
<s:datetimepicker label="%{getText('adds.date')}"  name="start" displayFormat="yyyy/MM/dd"/>
<s:select label="%{getText('adds.hour')}" name="hour" list="{'01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'}"/>
<s:select name="min" label="%{getText('adds.min')}" list="{'00','05','10','15','20','25','30','35','40','45','50','55','60'}"/>
<s:textarea name="descr" label="%{getText('adds.descr')}" rows="8" cols="45" theme="xhtml"/>
<s:submit notifyTopics="refreshMeetingTab"/>
</s:form>

</div>

</body>
</html>