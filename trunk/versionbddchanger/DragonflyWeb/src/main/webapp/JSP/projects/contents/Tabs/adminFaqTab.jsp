<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<s:div id="faqTabsToRefresh">

	<div id="subtitle">
	<div class="bout-gauche">
	<div class="bout-droit">
	<div class="fond">
	<h3><s:text name="administration.subtitleFAQ"></s:text></h3>
	</div>
	</div>
	</div>
	</div>

<div dojoType="ContentPane" sizeMin="20" sizeShare="20" style="" align="center">
	<table width=90%>
		<s:iterator value="questionsResponse">

			<s:url id="deleteFAQ" action="DeleteFAQ">
				<s:param name="delete">
					<s:property value="QRId" />
				</s:param>
			</s:url>

			<tr id="<s:property value="QRId"/>">
				
				<td>
				<div class="faqImage" />
				</td>
				<td>

				<div id="closeFAQ<s:property value="QRId" />" class="test">
				<a href="javascript:;" onclick="testImplode('openFAQ<s:property value="QRId" />','closeFAQ<s:property value="QRId" />')"><img src="img/project/delete.jpg"/></a>
				
				<table width="600px">
				<tr>
				<td align="left" width="100px"><s:text name="faq.showfaq.question"></s:text></td><td align="left"><s:property value="question"/></td>
				</tr>
				<tr>
				<td align="left" width="100px"><s:text name="faq.showfaq.response"></s:text></td><td align="left"><s:property value="response"/></td>
				</tr>
				</table>
				</div>
				
				<div class="subject">
				<a id="openFAQ<s:property value="QRId" />" href="javascript:;" onclick="testExplode(this,'closeFAQ<s:property value="QRId" />')">
				<s:text name="faq.creates.question"/> <s:property value="QRId" /></a></div>
				
				
				<s:form>
				<s:submit type="image" src="img/project/delete.jpg"
					href="%{deleteFAQ}" showLoadingText="false" targets="faqTabsToRefresh" cssClass="suppress"></s:submit>
				</s:form>	
				</td>
			</tr>
		</s:iterator>
	</table>

</s:div>
</body>
</html>
