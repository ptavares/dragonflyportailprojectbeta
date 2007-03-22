<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<s:div id="meetingTabsToRefresh">

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
<table width=90%>
<s:iterator value="meetings">
	<s:url id="deleteMeeting" action="DeleteMeeting">
				<s:param name="delete">
					<s:property value="number" />
				</s:param>
	</s:url>
	
	<tr id="<s:property value="number"/>">
				<td>
				<div class="meetingImage" />
				</td>
				<td class="postdate"><s:property value="postDate" /></td>
				<td class="postdate"><s:property value="date" /></td>
				<td>
				<div class="subject"><a href=""><s:property value="subject" /></a></div>
				<s:form>
				<s:submit type="image" src="img/project/delete.jpg"
					href="%{deleteMeeting}" targets="meetingTabsToRefresh" cssClass="suppress"></s:submit>
				</s:form>	
			</td>
	</tr>
</s:iterator>
</table>

<!-- <div class="subject"><a href=""><s:property value="subject" /></a></div> -->
				
</s:div>
</body>
</html>
