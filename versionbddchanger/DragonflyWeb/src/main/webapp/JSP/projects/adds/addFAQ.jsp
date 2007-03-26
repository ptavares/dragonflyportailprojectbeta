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

<s:div id="createFAQ">


<div class="information"><s:text name="meetings.creates"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>
 
<s:actionerror/>
<s:actionmessage/>

<s:form action="AddFAQ.action" method="post">
<s:textarea name="question" label="%{getText('faq.creates.question')}" rows="8" cols="45" theme="xhtml" id="description" required="true"/>
<s:textarea name="response" label="%{getText('faq.creates.response')}" rows="8" cols="45" theme="xhtml" id="description" required="true"/>
<s:submit value="%{getText('button.submit')}" label="%{getText('button.submit')}" targets="createFAQ" showLoadingText="false"></s:submit>
</s:form>


<div id="goback">
<s:url id="backFAQPage" action="goToProjectPage"  method="goToFAQPage">
<s:param name="ProName"><%=session.getAttribute("project")%></s:param>
</s:url>
<s:text name="tasks.goBack"></s:text>
<s:a href="%{backFAQPage}" targets="faq">
<img src="img/project/goBack.png" border="0"></s:a>
</div>

</s:div>

</body>
</html>