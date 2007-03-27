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

<s:div id="createMeeting">


<div class="information"><s:text name="meetings.creates"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>
 
<s:actionerror/>
<s:actionmessage/>

<s:form action="AddMeeting.action" method="post">
<s:textfield name="subj" label="%{getText('meetings.creates.subject')}" size="45" required="true" id="subject"/>
<s:datetimepicker label="%{getText('meetings.creates.date')}"  name="start" displayFormat="yyyy/MM/dd" required="true" id="start"/>
<s:select label="%{getText('meetings.creates.hour')}" name="hour" list="{'01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'}"/>
<s:select name="min" label="%{getText('meetings.creates.min')}" list="{'00','05','10','15','20','25','30','35','40','45','50','55','60'}"/>
<s:textarea name="descr" label="%{getText('meetings.creates.description')}" rows="8" cols="45" theme="xhtml" id="description"/>
<s:submit value="%{getText('button.submit')}" label="%{getText('button.submit')}" notifyTopics="deleteWhiteSpace,checkDate" targets="createMeeting" showLoadingText="false"></s:submit>
</s:form>


<div id="goback">
<s:url id="backMeetingPage" action="goToProjectPage"  method="goToMeetingsPage">
<s:param name="ProName"><%=session.getAttribute("project")%></s:param>
</s:url>
<s:text name="meetings.goBack"></s:text>
<s:a href="%{backMeetingPage}" targets="meetings">
<img src="img/project/goBack.png" border="0"></s:a>
</div>

</s:div>

</body>
</html>