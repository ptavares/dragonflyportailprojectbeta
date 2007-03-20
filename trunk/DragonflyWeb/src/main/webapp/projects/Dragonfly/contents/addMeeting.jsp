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
System.out.println("yooiooo------>"+str);
//session.setAttribute("project",str);
%>

<div class="back">
<h3 align="center"><b>Create a new Meeting</b></h3>
<s:head/>



<s:form action="AddMeeting.action" method="post" theme="simple">

<table border="0" style="solid">
<tr>

<td colspan="5">
<div><s:textfield name="subj" label="%{getText('adds.subject')}" size="45" theme="ajax"/></div></td>
</tr>

<tr>

<td>
<div><s:datetimepicker label="%{getText('adds.date')}"  name="start" displayFormat="yyyy-MM-dd" theme="ajax"/></div></td>
<td>
<div><s:select label="%{getText('adds.min')}" name="hour" list="{'01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'}" theme="ajax"/>
</div></td>

<td>
<div><s:select name="min" label="%{getText('adds.min')}" list="{'00','05','10','15','20','25','30','35','40','45','50','55','60'}" theme="ajax"/></div></td>
</tr>

<tr>

<td colspan="5">
<div><s:textarea name="descr" label="%{getText('adds.descr')}" rows="8" cols="45" theme="xhtml"/></div></td>
</tr>

<tr>
<td></td>
<td><div><s:submit onclick="opener.location.reload(true);window.close()"/></div></td>
</tr>
</table>
</s:form>



</div>

</body>
</html>