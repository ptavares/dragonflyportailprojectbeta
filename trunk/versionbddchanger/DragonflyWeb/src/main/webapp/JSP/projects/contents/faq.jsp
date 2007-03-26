<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="faq">

<div class="information"><s:text name="faq.title"></s:text><%=session.getAttribute("project")%>
</div>
<br>
<br>

<ul>	
	<s:url id="addFAQ" action="goToProjectPage" method="goToAddFAQPage"></s:url>
	
	<li><s:text name="faq.create"></s:text>
	<s:a href="%{addFAQ}" targets="faq">
	<input type="image" src="img/project/create.gif" alt="<s:text name="faq.create"/>"/>
	</s:a>
	</li>		
</ul>

<br>
<br>

<div id="faqTabs">

<jsp:include flush="true" page="Tabs/FAQTabs.jsp"></jsp:include>

</div>


</div>
</body>
</html>