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
String str = request.getParameter("project");
session.setAttribute("project",str);
%>

<div class="back">
<h3 align="center"><b>Create a new Meeting</b></h3>
<s:head/>



<s:form action="AddMeeting.action" method="post" theme="simple">

<table border="0" style="solid">
<tr>
<td>
<s:label value="%{getText('adds.subject')}" label="%{getText('adds.subject')}"></s:label>
</td>
<td colspan="5">
<div><s:textfield name="subj" label="Subject" size="45"/></div></td>
</tr>

<tr>
<td><s:label value="%{getText('adds.date')}" label="%{getText('adds.date')}"></s:label></td>
<td>
<div><s:datetimepicker label="Date" name="start" displayFormat="yyyy-MM-dd"/></div></td>

<td><s:label value="%{getText('adds.hour')}" label="%{getText('adds.hour')}"></s:label></td>
<td>
<div><s:select label="Hour" name="hour" list="{'01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'}"/>
</div></td>

<td><s:label value="%{getText('adds.min')}" label="%{getText('adds.min')}"></s:label></td>
<td>
<div><s:select name="min" label="Minutes" list="{'00','05','10','15','20','25','30','35','40','45','50','55','60'}"/></div></td>
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