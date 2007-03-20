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
dojo.event.topic.subscribe("refreshTaskTab",function(data,type,request){
		if(type=="load"){
			alert(data);
			
			/* Supprime la balise <form> de la reponse contenue dans Data*/			
			var suppBalise = data.replace(/<([^>])*>/g,'');
			/*Supprime les espaces inutiles */
			var suppSpace = suppBalise.replace(/(^\s*)|(\s*$)/g,''); 
			/*On extrait les différentes variables*/
			var split = suppSpace.split(",");
			/* On envoit dans l'ordre taskId,subject,description,post,start,end*/			
			opener.addRowTask(split[0],split[1],split[2],split[3],split[4],split[5]);
	 		window.close();
	 		}
	 } );
</script>

</head>

<%
System.out.println("--------->"+(String)session.getAttribute("project"));
%>

<body>


<div class="back">
<h3 align="center"><b>Create a new Task</b></h3>
<s:form action="AddTask.action" id="Taksadds">
<s:textfield name="subj" label="Subject" id="subject"/>
<s:datetimepicker label="Start Date" name="start" displayFormat="yyyy/MM/dd" id="start"></s:datetimepicker>
<s:datetimepicker label="End Date" name="end"  displayFormat="yyyy/MM/dd" id="end"></s:datetimepicker>
<s:textarea name="descr" label="Description" rows="8" cols="43" theme="xhtml" id="descr"></s:textarea>
<s:submit notifyTopics="refreshTaskTab"></s:submit>

</s:form>
</div>

</body>
</html>