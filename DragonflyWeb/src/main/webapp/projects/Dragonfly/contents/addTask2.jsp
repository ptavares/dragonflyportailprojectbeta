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

<%
String str = (String)session.getAttribute("project");
System.out.println("--------->"+(String)session.getAttribute("project"));
System.out.println("------->"+str);
//session.setAttribute("project",str);
%>

<body>

<div class="back">
<h3 align="center"><b>Create a new Task</b></h3>
<s:head/>
<s:form action="AddTask.action">
<s:textfield name="subj" label="Subject"/>
<s:datetimepicker label="Start Date" name="start" displayFormat="yyyy-MM-dd"></s:datetimepicker>
<s:datetimepicker label="End Date" name="end" displayFormat="yyyy-MM-dd"></s:datetimepicker>
<s:textarea name="descr" label="Description" rows="8" cols="43" theme="xhtml"></s:textarea>
<s:submit onclick="opener.location.reload(true);window.close()"></s:submit>

</s:form>
</div>

</body>
</html>