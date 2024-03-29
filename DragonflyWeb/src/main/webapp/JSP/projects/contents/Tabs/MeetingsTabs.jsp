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

<div id="meetingTabs">


<div id="dojoTable" align="center">

<table dojoType="filteringTable" multiple="true" alternateRows="true" id="meetingTable"
	cellpadding="0" cellspacing="0" border="1" >	
	
	<thead>
		<tr>
		<th field="subject" dataType="String" valign="top">Subject</th>
		<th field="description" dataType="String" valign="top">Description</th>
		<th field="postDate" dataType="Date" sort="asc" format="%d %b %Y" valign="top">Post Date</th>
		<th field="date" dataType="String" valign="top">Meeting Date</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="meetings">
		<tr value="<s:property value="number"/>">
			<td><s:property value="subject"/></td>
			<td><s:property value="description"/></td>
			<td><s:property value="postDate"/></td>
			<td><s:property value="date"/></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
	
</div>
</body>
</html>
