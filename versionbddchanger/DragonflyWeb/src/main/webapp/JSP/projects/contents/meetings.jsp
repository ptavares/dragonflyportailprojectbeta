<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div id="meetings">

<div class="information"><s:text name="meetings.title"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>

<ul>
	<s:url id="addMeeting" action="goToProjectPage" method="goToAddMeetingPage"></s:url>
	
	<li><s:text name="meetings.create"></s:text>
	<s:a href="%{addMeeting}" targets="meetings">
	<input type="image" src="img/project/meeting.gif" alt="<s:text name="meetings.create"/>"/>
	</s:a>
	</li>
	
</ul>

<br>
<br>

<jsp:include flush="true" page="Tabs/MeetingsTabs.jsp"></jsp:include>

</body>
</html>