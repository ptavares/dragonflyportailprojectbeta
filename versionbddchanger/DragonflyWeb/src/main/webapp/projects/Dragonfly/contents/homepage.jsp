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
table,tr,td {
border-width: 1px;
border-style: solid;
border-collapse: collapse;
}
</style>
</head>
<body>

<div align="left">
<h2><b>News</b></h2>
<br>
</div>




<%
final InitialContext ctx = new InitialContext();
final ProjectInformation proj=(ProjectInformation) ctx.lookup("ProjectInformation/remote");

String st = (String)session.getAttribute("project");
//System.out.println("--->Project Name meeting.jsp: "+st);

List<List<String>> list = proj.getProjectNews(st);

for(List<String> l : list){
%>
<div align="center"><b><%= l.get(1) %></b></div>
<div align="left">Written by :<%= l.get(0) %></div>
<div align="right">Posted : <%= l.get(3) %></div>
<hr>
<div align="left"><%= l.get(2) %></div>
<br><br>
<%
}
%>


</body>
</html>