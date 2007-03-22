<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<s:div id="taskTabsToRefresh">

	<div id="subtitle">
	<div class="bout-gauche">
	<div class="bout-droit">
	<div class="fond">
	<h3><s:text name="administration.subtitleTask"></s:text></h3>
	</div>
	</div>
	</div>
	</div>

<div id="listPane" dojoType="ContentPane" sizeMin="20" sizeShare="20" style="" align="center">
	<table width=90%>
		<s:iterator value="tasks">

			<s:url id="deleteTask" action="DeleteTask">
				<s:param name="delete">
					<s:property value="number" />
				</s:param>
			</s:url>

			<tr id="<s:property value="number"/>">
				<td>
				<div class="taskImage" />
				</td>
				<td class="postdate"><s:property value="postDate" /></td>
				<td><div class="subject"><a href=""><s:property value="subject" /></a></div>
				<s:form>
				<s:submit type="image" src="img/project/delete.jpg"
					href="%{deleteTask}" targets="taskTabsToRefresh" cssClass="suppress"></s:submit>
				</s:form>	
				</td>
			</tr>
		</s:iterator>
	</table>

</s:div>
</body>
</html>
