<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<SCRIPT type="text/javascript" src="javascript/ajax.js"></SCRIPT>
<s:head theme="ajax"/>
</head>
<body>
<div id="form">
<s:form action="#">
	<s:textfield id="address" name="address" label="To" size="45"/>
	<s:textfield id="subject" name="subject" label="subject" size="45"/>
	
	<s:textarea id="content" name="content" rows="8" cols="45" label="content" theme="xhtml"/>
	
	<br/><s:submit onclick="ajaxCallRemotePage('sendMessage.action');" value="send"/>
</s:form>
</div>
</body>
</html>