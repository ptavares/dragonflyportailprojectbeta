<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="javax.naming.InitialContext"%>
<%@page import="fr.umlv.dragonflyEJB.services.project.information.ProjectInformation"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>

table,td,th {
	border-style: solid;
	border-width: 1px;
	border-collapse: collapse;
}

</style>

</head>
<body>

<%
String stra = request.getParameter("project");
String path = request.getContextPath()+"/projects/Dragonfly/contents/";
//session.setAttribute("project",stra);
%>

<div>
Create a new action<input type="image" src="../img/create.gif" name="img" onclick="window.open('<%=path %>addTask.jsp?project=<%= stra %>','wclose','width=500,height=400,toolbar=no,status=no,left=20,top=30')"/></div>
<div>
Create a new meeting<input type="image" src="../img/meeting.gif" name="meet" onclick="window.open('<%=path %>addMeeting.jsp?project=<%= stra %>','wclose','width=500,height=400,toolbar=no,status=no,left=20,top=30')"/></div>
<div>
Add a FAQ<input type="image" src="../img/check.gif" name="faq" onclick="window.open('<%=path %>addFAQ.jsp?project=<%= stra %>','wclose','width=500,height=400,toolbar=no,status=no,left=20,top=30')"/></div>
<div>
Add a news<input type="image" src="../img/createsmall.gif" name="faq" onclick="window.open('<%=path %>addNews.jsp?project=<%= stra %>','wclose','width=500,height=400,toolbar=no,status=no,left=20,top=30')"/></div>
<div>
Add a User<input type="image" src="../img/createsmall.gif" name="faq" onclick="window.open('<%=path %>addUser.jsp?project=<%= stra %>','wclose','width=500,height=100,toolbar=no,status=no,left=20,top=30')"/></div>



<br><br>
<div align="center"><h2><b>List of tasks</b></h2></div>
<s:form action="DeleteTask.action" theme="simple">
<table align="center">
<tr>
<th>Number</th>
<th>Subject</th>
<th>Description</th>
<th>Post Date</th>
<th>Start Date</th>
<th>End Date</th>
<th>Delete</th>
</tr>

<%
final InitialContext ctx = new InitialContext();
final ProjectInformation proj=(ProjectInformation) ctx.lookup("ProjectInformation/remote");

//String st = request.getParameter("project");
String st = (String)session.getAttribute("project");

List<List<String>> list = proj.getProjectTasks(st);
if(list == null){
	System.out.println("List Null : ");
	return;	
}

for(List<String> l : list){
	//System.out.println(l.get(0) + "    "+l.get(1));
%>
<tr>
<%
	for(String str : l){
%>
<td>
<%= str	%></td>
	
<% } %>
<td align="center"><input type="checkbox" name="delete" value="<%=l.get(0) %>" /></td>
</tr>
<%
}
%>
<tr><td colspan="6"></td><td><s:submit/></td></tr>

</table>
</s:form>
</body>
</html>