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

<s:div id="createTask">


<div class="information"><s:text name="tasks.creates"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>
 
<s:actionerror/>
<s:actionmessage/>

<s:form action="AddTask.action" id="Taksadds">
<s:textfield name="subj" label="%{getText('tasks.creates.subject')}" size="45" id="subject" required="true" id="subject"/>
<s:datetimepicker label="%{getText('tasks.creates.dateDebut')}" name="start" displayFormat="yyyy/MM/dd" id="start" required="true"></s:datetimepicker>
<s:datetimepicker label="%{getText('tasks.creates.dateFin')}" name="end"  displayFormat="yyyy/MM/dd" id="end" required="true"></s:datetimepicker>
<s:textarea name="descr" label="%{getText('tasks.creates.description')}" rows="8" cols="43" theme="xhtml" id="descr" id="description"></s:textarea>
<s:submit value="%{getText('button.submit')}" label="%{getText('button.submit')}" notifyTopics="deleteWhiteSpace,checkDate" targets="createTask" showLoadingText="false"></s:submit>
</s:form>


<div id="goback">
<s:url id="backTODOPage" action="goToProjectPage"  method="goToTODOPage">
<s:param name="ProName"><%=session.getAttribute("project")%></s:param>
</s:url>
<s:text name="tasks.goBack"></s:text>
<s:a href="%{backTODOPage}" targets="todo">
<img src="img/project/goBack.png" border="0"></s:a>
</div>

</s:div>

</body>
</html>