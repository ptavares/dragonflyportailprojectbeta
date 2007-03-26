<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<div id="news">

<div class="information">
<s:text name="news.title"></s:text><%= session.getAttribute("project") %>
</div>

<br>
<br>

<s:if test="userStatus == 2 || userStatus == 1">
<ul>
	<s:url id="addNews" action="goToProjectPage" method="goToAddNewsPage"></s:url>
	
	<li><s:text name="news.create"></s:text>
	<s:a href="%{addNews}" targets="news">
	<input type="image" src="img/project/create.gif" alt="<s:text name="news.create"/>"/>
	</s:a>
	</li>
</ul>

<br>
<br>
</s:if>

<jsp:include flush="true" page="Tabs/NewsTabs.jsp"></jsp:include>

</div>

</body>
</html>