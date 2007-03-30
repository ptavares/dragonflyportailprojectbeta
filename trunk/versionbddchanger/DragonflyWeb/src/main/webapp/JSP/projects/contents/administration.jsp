<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>

<div id="administration">

<div class="information"><s:text name="administration.title"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>

<div id="addUser">
<div id="subtitle">
<div class="bout-gauche">
<div class="bout-droit">
<div class="fond">
<h3><s:text name="administration.addUser"></s:text></h3>
</div>
</div>
</div>
</div>
<jsp:include flush="true" page="../adds/addUser.jsp"></jsp:include></div>

<br>

<div id="removeUser">
<div id="subtitle">
<div class="bout-gauche">
<div class="bout-droit">
<div class="fond">
<h3><s:text name="administration.removeUser"></s:text></h3>
</div>
</div>
</div>
</div>
<jsp:include flush="true" page="../adds/removeUser.jsp"></jsp:include></div>

<br>

<div id="newsTabs">
<jsp:include flush="true" page="../results/deleteNewsResult.jsp"></jsp:include>
</div>

<br>

<div id="taskTabs">
<jsp:include flush="true" page="../results/deleteTaskResult.jsp"></jsp:include>
</div>

<br>

<div id="meetingTabs">
<jsp:include flush="true" page="../results/deleteMeetingResult.jsp"></jsp:include>
</div>

<br>

<div id="FAQTabs">
<jsp:include flush="true" page="../results/deleteFaqResult.jsp"></jsp:include>
</div>

<br>

<div id="fileTabs">
<jsp:include flush="true" page="../results/valideFichier.jsp"></jsp:include>
</div>


</div>
</body>
</html>
