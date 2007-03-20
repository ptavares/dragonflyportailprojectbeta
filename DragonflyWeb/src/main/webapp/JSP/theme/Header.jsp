<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dragonfly Portail Project Header</title>
<meta http-equiv="Content-Language" content="fr" />
<meta http-equiv="Content-Style-Type" content="text/css" />

<link rel="stylesheet" href="css/mystyle.css" type="text/css" media="screen" title="Normal" />

</head>
<body>

<div id="wrapper-header-top">
<div id="header-top">
	<br>
</div>
</div>

<div id="wrapper-header">
<div id="header">
<div id="lang">
<a href="<s:url action="Language">
            <s:param name="request_locale">en</s:param>
        </s:url>"><img src="img/uk-flag.jpg" alt="english" border="0" />
        </a>
<a href="<s:url action="Language">
            <s:param name="request_locale">fr</s:param>
        </s:url>"><img src="img/French-flag.jpg" alt="français" border="0" /></a>
<a href="<s:url action="Language">
            <s:param name="request_locale">zh</s:param>
        </s:url>"><img src="img/China-flag.jpg" alt="Chinese" border="0" /></a>        
</div>
</div>
</div>

</body>
</html>