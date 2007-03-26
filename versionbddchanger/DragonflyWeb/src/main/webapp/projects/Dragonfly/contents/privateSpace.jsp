<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<%
String stra = (String)session.getAttribute("project");
System.out.println(stra);
//session.setAttribute("project",stra);


System.out.print("------------>"+request.getContextPath());

%>


<s:div id="boutonPopup">

Creation d'un tâche <input type="image" src="projects/Dragonfly/img/create.gif" onclick="openPopUp('addTask','<%= stra %>');" />
Create a new meeting<input type="image" src="projects/Dragonfly/img/meeting.gif" name="meet" onclick="openPopUp('addMeeting','<%= stra %>');" />
Add a FAQ<input type="image" src="projects/Dragonfly/img/check.gif" name="faq" onclick="openPopUp('addFAQ','<%= stra %>');" /> 
Add a news<input type="image" src="projects/Dragonfly/img/createsmall.gif" name="faq" onclick="openPopUp('addNews','<%= stra %>');" /> 
Add a User<input type="image" src="projects/Dragonfly/img/createsmall.gif" name="faq" onclick="openPopUp('addUser','<%= stra %>');" />

</s:div>

<br><br>

<jsp:include flush="true" page="Tabs/TaskTabs.jsp"></jsp:include>

</body>
</html>