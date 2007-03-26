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

<div dojoType="ContentPane" sizeMin="20" sizeShare="20" style="" align="center">
	<table width=90%>
		<s:iterator value="tasks">

			<s:url id="deleteTask" action="DeleteTask">
				<s:param name="delete">
					<s:property value="taskId" />
				</s:param>
			</s:url>

			<tr id="<s:property value="taskId"/>">
				<td>
				<div class="taskImage" />
				</td>
				<td class="postdate"><s:property value="postDate" /></td>
				<td><!-- <div class="subject"><a href=""><s:property value="subject" /></a></div>  -->
				
				<div id="closeTask<s:property value="taskId" />" class="test">
				<a href="javascript:;" onclick="testImplode('openTask<s:property value="taskId" />','closeTask<s:property value="taskId" />')"><img src="img/project/delete.jpg"/></a>
				
				<table width="600px">
				<tr>
				<td align="left" width="100px"><s:text name="table.author"></s:text></td><td align="left"><s:property value="author"/></td>
				</tr>
				<tr>
				<td align="left" width="100px"><s:text name="table.description"></s:text></td><td align="left"><s:property value="description"/></td>
				</tr>
				</table>
				</div>
				<div class="subject">
				<a id="openTask<s:property value="taskId" />" href="javascript:;" onclick="testExplode(this,'closeTask<s:property value="taskId" />')">
				<s:property value="subject" /></a></div>
				
				<s:form>
				<s:submit type="image" src="img/project/delete.jpg"
					href="%{deleteTask}" showLoadingText="false" targets="taskTabsToRefresh" cssClass="suppress"></s:submit>
				</s:form>	
				</td>
			</tr>
		</s:iterator>
	</table>

</s:div>
</body>
</html>
