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
<s:fielderror/>
<s:actionmessage/>
<s:form id="uploadForm" action="doUpload" method="POST" enctype="multipart/form-data" >
	<s:file name="myDoc" />
	<s:submit value="Upload"/>
</s:form>

<h2><s:text name="uploadPopup.maxSize" /></h2>
</body>
</html>