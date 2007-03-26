<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

<div id="faqTabs">
<s:iterator value="questionsResponse">
	<table id="<s:property value="QRId"/>" width="85%" align="center" border="1" cellspacing="2">

		<tr>
			<td class="faqTitleTab" bordercolor="white"><s:text name="faq.showfaq.question"/></td>
			<td class="faqContentTab" bordercolor="white"><s:property value="question" /></td>
		</tr>
		<tr>
			<td class="faqTitleTab" bordercolor="white"><s:text name="faq.showfaq.response"/></td>
			<td class="faqContentTab" bordercolor="white"><s:property value="response" /></td>
		</tr>
	</table>
	<br>
</s:iterator></div>
</body>
</html>
