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
</body>
</html>