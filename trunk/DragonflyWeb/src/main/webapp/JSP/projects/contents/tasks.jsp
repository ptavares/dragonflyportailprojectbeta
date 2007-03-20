<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>

<div id="todo">

<div class="information"><s:text name="tasks.title"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>

<ul>
	<li><s:text name="tasks.create"></s:text><input type="image"
		src="img/project/create.gif" alt="<s:text name="tasks.create"/>"
		onclick="openPopUp('addTask','<%= session.getAttribute("project") %>');" />
	</li>
</ul>

<br>
<br>

<jsp:include flush="true" page="Tabs/TaskTabs.jsp"></jsp:include>

</div>

</body>
</html>
