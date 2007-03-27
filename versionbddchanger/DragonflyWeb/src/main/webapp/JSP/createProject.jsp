<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Project</title>

</head>
<body>

<s:head/>

<s:div id="createProject">

<div id="title">
<div id="title-left">
<div id="title-right">
<h1><s:text name="createproject.title"></s:text></h1>
</div>
</div>
</div>

<s:actionerror/>

<div class="bodyForm">
<s:form action="CreateProject" method="post" theme="ajax">

	<div>	        
    	<s:textfield label="%{getText('createproject.projectName')}" id="projectName" name="nomProjet" size="50" required="true" />
   </div> 
   <div> 	

    	<s:datetimepicker label="%{getText('createproject.endDate')}" name="date"/>
    </div>
    
     <div>
    	<s:textarea label="%{getText('createproject.resume')}" name="resume" cols="50" rows="3" theme="xhtml" required="true"/>
   </div>
  
    <div>
    	<s:textarea label="%{getText('createproject.descriptif')}" name="descriptif" cols="50" rows="8" theme="xhtml"/>
   </div>
   	
    <s:submit targets="createProject" notifyTopics="DeleteProjetWhiteSpace" showLoadingText="false"/>
</s:form>
</div>


</s:div>
</body>
</html>