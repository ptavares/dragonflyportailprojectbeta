<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>

<s:div id="todo">

<div class="information"><s:text name="tasks.title"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>


<ul>	
	<s:url id="addTask" action="goToProjectPage" method="goToAddTaskPage"></s:url>
	
	<li><s:text name="tasks.create"></s:text>
	<s:a href="%{addTask}" targets="todo">
	<input type="image" src="img/project/create.gif" alt="<s:text name="tasks.create"/>"/>
	</s:a>
	</li>		
</ul>

<br>
<br>

<div id="taskTabs">

<jsp:include flush="true" page="Tabs/TaskTabs.jsp"></jsp:include>

</div>

</s:div>


</body>
</html>
