<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Projects</title>
<s:head theme="ajax"/>
</head>

<body>
<div id="title">
<div id="title-left">
<div id="title-right">
<h1><s:text name="myproject.title"></s:text></h1>
</div>
</div>
</div> 

<div id="dojoTable" align="center">
<table dojoType="filteringTable" multiple="true" alternateRows="true"
	cellpadding="0" cellspacing="0" border="1" rowSelectedClass="none">
	<thead>
		<tr>
			<th field="name" dataType="String" valign="top"><s:text name="myproject.name"/></th>
			<th field="resume" dataType="String" align="center" valign="top"><s:text name="myproject.resume"/></th>
			<th field="projectLeader" dataType="String" align="center" valign="top"><s:text name="myproject.projectLeader"/></th>
			<th field="creationDate" dataType="Date" sort="desc" format="%d %b %Y" align="center" valign="top"><s:text name="myproject.creationDate"/></th>
		</tr>
	</thead>
	<tbody>
<s:iterator value="projects">
<tr>
<td>
        <s:url id="toto" action="goToProjectPage"><s:param name="ProName" value="%{name}"/></s:url>
        <s:a href="%{toto}" showLoadingText="false" targets="rightcolumn"><s:property value="name"/></s:a>
</td>
<td>
<s:property value="resume"/>
</td>
<td>
<s:property value="projectLeader"/>
</td>
<td> 
<s:property value="creationDate"/>
</td>
</tr>
</s:iterator>

	</tbody>
</table>
</div>
</body>
</html>