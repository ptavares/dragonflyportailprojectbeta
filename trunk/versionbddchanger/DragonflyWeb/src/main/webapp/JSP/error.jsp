<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> ERROR </title>
<link rel="stylesheet" href="css/mystyle.css" type="text/css">
</head>
<body>

<div id="title">
<div id="title-left">
<div id="title-right">
<h1><s:text name="error"></s:text></h1>
</div>
</div>
</div>


<s:fielderror />

<s:url id="HomePage" action="Login.action"/>
<s:a href="%{HomePage}">Return to HOME PAGE</s:a>

</body>
</html>