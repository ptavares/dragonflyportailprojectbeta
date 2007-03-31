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

<div id="newsTabs">


<div id="dojoTable" align="center">

<table dojoType="filteringTable" multiple="true" alternateRows="true" id="newsTable"
	cellpadding="0" cellspacing="0" border="1" >	
	
	<thead>
		<tr>
			<th field="subject" dataType="String" valign="top" width="200px"><s:text name="table.subject"></s:text> </th>
		<th field="description" dataType="String" valign="top" width="320px"><s:text name="table.description"></s:text></th>
		<th field="postDate" dataType="Date" sort="desc" format="%d %b %Y" valign="top"><s:text name="table.postDate"></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="news">
		<tr value="<s:property value="newsId"/>">
			<td><s:property value="subject"/></td>
			<td><s:property value="description"/></td>
			<td><s:property value="postDate"/></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
	
</div>
</body>
</html>
