<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="javax.naming.InitialContext"%>
<%@page
	import="fr.umlv.dragonflyEJB.services.project.information.ProjectInformation"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

<div id="taskTabs">


<div id="dojoTable" align="center">

<table dojoType="filteringTable" alternateRows="true" multiple="true" id="taskTable"
	cellpadding="0" cellspacing="0" border="1">
	<thead>
		<tr>
		<th field="Number" dataType="String" valign="top">Number</th>
		<th field="Subject" dataType="String" valign="top">Subject</th>
		<th field="Description" dataType="String" valign="top">Description</th>
		<th field="PostDate" dataType="String" valign="top">Post Date</th>
		<th field="StartDate" dataType="String" valign="top">Start Date</th>
		<th field="EndDate" dataType="String" valign="top">End Date</th>
		</tr>
	</thead>
	<tbody>
		<%
			final InitialContext ctx = new InitialContext();
			final ProjectInformation proj = (ProjectInformation) ctx
					.lookup("ProjectInformation/remote");

			//String st = request.getParameter("project");
			String st = (String) session.getAttribute("project");

			List<List<String>> list = proj.getProjectTasks(st);
			if (list == null) {
				System.out.println("List Null : ");
				return;
			}

			for (List<String> l : list) {
				//System.out.println(l.get(0) + "    "+l.get(1));
		%>
		<tr value="<%=l.get(0)%>">
			<%
			for (String str : l) {
			%>
			<td><%=str%></td>

			<%
			}
			%>
		</tr>
		  	<%
			}
			%>
	</tbody>
</table>

<button dojoType="Button" onclick="showSelected();">Show the selected object</button>

<!-- 
<div align="center">
<h2><b>List of tasks</b></h2>
</div>
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

		<%--

			for (List<String> l1 : list) {
				//System.out.println(l.get(0) + "    "+l.get(1));
		--%>
		<tr>
			<%--
			for (String str1 : l1) {
			--%>
			<td><%--=str1%--></td>

			<%--
			}
			--%>
			<td align="center"><input type="checkbox" name="delete"
				value="<%--=l.get(0) %-->" /></td>
		</tr>
		<%--
		}
		--%>
		<tr>
			<td colspan="6"></td>
			<td><s:submit targets="taskTabs" /></td>
		</tr>

	</table>
</s:form>
 -->

</div>
</body>
</html>
