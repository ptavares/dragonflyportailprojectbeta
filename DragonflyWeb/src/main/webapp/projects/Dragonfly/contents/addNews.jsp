<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<STYLE>
.back{
background-color: #87cefa;
height: 100%;
}
</STYLE>
</head>
<body>

<%
String str = (String)session.getAttribute("project");
session.setAttribute("project",str);
%>

<div class="back">
<h3 align="center"><b>Create a new Meeting</b></h3>
<s:head/>



<s:form action="AddNews.action" method="post" theme="simple">

<table border="0" style="solid">
<tr>
<td>

<s:label value="%{getText('adds.subject')}" label="%{getText('adds.subject')}"></s:label>
</td>
<td colspan="5">
<div><s:textfield name="subj" label="Subject" size="45"/></div></td>
</tr>

<tr>
<td><s:label value="%{getText('adds.descr')}" label="%{getText('adds.descr')}"></s:label></td>
<td colspan="5">
<div><s:textarea name="descr" label="Description" rows="8" cols="45"/></div></td>
</tr>

<tr>
<td colspan="5"></td>
<td><div><s:submit onclick="opener.location.reload(true);window.close()"/></div></td>
</tr>
</table>
</s:form>



</div>

</body>
</html>