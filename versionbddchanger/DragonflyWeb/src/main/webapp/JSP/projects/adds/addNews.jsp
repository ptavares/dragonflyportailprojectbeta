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

<s:div id="createNews">


<div class="information"><s:text name="news.creates"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>
 
<s:actionerror/>
<s:actionmessage/>

<s:form action="AddNews.action" id="Newsadds">
<s:textfield name="subj" label="%{getText('news.creates.subject')}" size="45" id="subject" required="true" id="sujbect"/>
<s:textarea name="descr" label="%{getText('news.creates.description')}" rows="8" cols="43" theme="xhtml" id="descr" id="description"></s:textarea>
<s:submit value="%{getText('button.submit')}" label="%{getText('button.submit')}" notifyTopics="deleteWhiteSpace" targets="createNews" showLoadingText="false"></s:submit>
</s:form>


<div id="goback">
<s:url id="backNewsPage" action="goToProjectPage"  method="goToNewsPage">
<s:param name="ProName"><%=session.getAttribute("project")%></s:param>
</s:url>
<s:text name="news.goBack"></s:text>
<s:a href="%{backNewsPage}" targets="news">
<img src="img/project/goBack.png" border="0"></s:a>
</div>

</s:div>

</body>
</html>