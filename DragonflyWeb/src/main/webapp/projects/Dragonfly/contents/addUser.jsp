<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<STYLE>
.back{
background-color: #87cefa;
height: 100%;
}
</STYLE>
</head>
<body>

<div class="back">
<s:form action="AddUser.action" method="post">
Enter a user's mail :<s:textfield name="userMail" size="60"/>
<s:submit onclick="opener.location.reload(true);window.close()"></s:submit>
</s:form>
</div>

</body>
</html>