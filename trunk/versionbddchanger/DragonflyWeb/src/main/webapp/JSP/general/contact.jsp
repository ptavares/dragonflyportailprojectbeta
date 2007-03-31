<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact</title>
</head>
<body>

<body>
<div id="title">
<div id="title-left">
<div id="title-right">
<h1><s:text name="newindex.contact.title"></s:text></h1>
</div>
</div>
</div> 

<s:actionerror/>
<s:form action="sendContact">
<s:textfield name="UserID" label="%{getText('newindex.contact.email')}" size="45"/>
<s:textfield  name="subject" label="%{getText('newindex.contact.subjet')}" size="45"/>
<s:textarea  name="content" rows="8" cols="45" label="%{getText('newindex.contact.content')}" theme="xhtml"/>
<s:submit value="send" targets="rightcolumn"/>
</s:form>
</body>
</html>