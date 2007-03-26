<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<s:form action="doUpload" method="POST" enctype="multipart/form-data" theme="xhtml" >
	<s:file name="myDoc" />
	<s:hidden name="projectName"/>
	<s:submit  value="Upload" theme="xhtml"  />
</s:form>

</body>
</html>