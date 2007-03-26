<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div id="administration">
<s:div id="fileTabsToRefresh">
<div id="subtitle">
	<div class="bout-gauche">
	<div class="bout-droit">
	<div class="fond">
	<h3><s:text name="administration.subtitleMeeting"></s:text></h3>
	</div>
	</div>
	</div>
	</div>
<div id="listPane" dojoType="ContentPane" sizeMin="20" sizeShare="20" style="" align="center">
<table width=70%>
<s:iterator  value="nameList">
<tr>
<td>
<s:property value="fliename"/>
</td>
<td width="20">
<s:url id="down" action="download"><s:param name="inputPath" value="path"/></s:url>
<s:a href="%{down}" theme="xhtml"><img src="img/download.jpg" height="18"></s:a>
</td>
<td width="20">
<s:url id="del" action="FileDelete">
<s:param name="fileDel" value="path"/>
</s:url>
<s:a href="%{del}" targets="fileTabs"> <img src="img/project/delete.jpg" height="18"/> </s:a>
</td>
<td width="20">
<s:url id="valide" action="FileValide">
<s:param name="file" value="fliename"/>
</s:url>
<s:a href="%{valide}" targets="fileTabs"> <img src="img/yes.jpg" height="18"/> </s:a>
</td>
</tr>
</s:iterator>
</table>
</div>	
</s:div>
</div>
</body>
</html>