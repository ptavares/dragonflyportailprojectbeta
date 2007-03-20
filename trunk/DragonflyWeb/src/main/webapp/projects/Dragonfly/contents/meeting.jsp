<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="fr.umlv.dragonflyEJB.services.project.information.ProjectInformation"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
table,td,th{
border-width: 1px;
border-style: solid;
border-collapse: collapse;
}
</style>
</head>
<body>

<div align="center">
<h2><b>List of meetings</b></h2>
<br>
</div>

<s:form action="DeleteMeeting.action" theme="simple">
<table align="center">
<tr>
<th>Number</th>
<th>Subject</th>
<th>Date</th>
<th>Post Date</th>
<th>Description</th>
<th>Delete</th>
</tr>


<%
final InitialContext ctx = new InitialContext();
final ProjectInformation proj=(ProjectInformation) ctx.lookup("ProjectInformation/remote");

String st = (String)session.getAttribute("project");
System.out.println("--->Project Name meeting.jsp: "+st);

List<List<String>> list = proj.getProjectMeetings(st);
if(list == null){
	System.out.println("Meeting.jsp : Liste null");
}

int i=1;
for(List<String> l : list){
%>

<tr>
<%
i++;
for(String str : l){ %>
<td><%=str %></td>
<%
}%>
<td align="center"><input type="checkbox" name="deleteMeet" value="<%= l.get(0) %>" /> </td>
</tr>

<%
}
%>
<tr><td colspan="5"></td><td><s:submit/></td></tr>
</table>
</s:form>
</body>
</html>