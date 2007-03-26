<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload et Download</title>
<link rel="stylesheet" href="css/list.css" type="text/css">
</head>
<body>

<div id="listPane" dojoType="ContentPane" sizeMin="20" sizeShare="20" style="" align="center">
<table width="80%">
<tr class="header"><td align="left" width="%70">File</td> <td align="left">Download</td></tr>
<s:if test="%{listLength>0}">
<s:iterator value="FileLists">
<tr>
<td>
<s:property/>
</td>
<td>
<img src="img/download.jpg" height="20">
</td>
</tr>
</s:iterator>
</s:if>

<tr>
</tr>
</table>
</div>
<div align="center">
<s:form action="doUpload" method="POST" enctype="multipart/form-data" >
	<s:file name="myDoc" />
	<s:submit value="Upload"  />
</s:form>
</div>
</body>
</html>