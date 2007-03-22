<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

<div id="taskTabs">


<div id="dojoTable" align="center">

<table dojoType="filteringTable" multiple="true" alternateRows="true" id="taskTable"
	cellpadding="0" cellspacing="0" border="1" >	
	
	<thead>
		<tr>
		<th field="subject" dataType="String" valign="top">Subject</th>
		<th field="description" dataType="String" valign="top">Description</th>
		<th field="postDate" dataType="Date" sort="asc" format="%d %b %Y" valign="top">Post Date</th>
		<th field="startDate" dataType="Date" sort="asc" format="%d %b %Y" valign="top">Start Date</th>
		<th field="endDate" dataType="Date" sort="asc" format="%d %b %Y" valign="top">End Date</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="tasks">
		<tr value="<s:property value="number"/>">
			<td><s:property value="subject"/></td>
			<td><s:property value="description"/></td>
			<td><s:property value="postDate"/></td>
			<td><s:property value="startDate"/></td>
			<td><s:property value="endDate"/></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
	
</div>
</body>
</html>
