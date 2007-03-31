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
<s:actionerror/>
<s:form action="sendContact">
<s:textfield name="UserID" label="Your E-mail" size="45"/>
<s:textfield  name="subject" label="Subject" size="45"/>
<s:textarea  name="content" rows="8" cols="45" label="Content" theme="xhtml"/>
<s:submit value="send" targets="rightcolumn"/>
</s:form>
</body>
</html>