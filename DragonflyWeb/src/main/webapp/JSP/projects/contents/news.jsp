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
</div>
<br>
<br>

<ul>
	<li><s:text name="news.create"></s:text><input type="image"
		src="img/project/create.gif" alt="<s:text name="news.create"/>"
		onclick="openPopUp('addNews','<%= session.getAttribute("project") %>');" />
	</li>
</ul>

<br>
<br>

<jsp:include flush="true" page="Tabs/NewsTabs.jsp"></jsp:include>

</div>

</body>
</html>