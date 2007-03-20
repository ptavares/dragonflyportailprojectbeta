<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Here is your Messages</title>
</head>
<body>
<div id="result" align="center" onclick="hide(this)"></div>
<div align="center">
<div id="topMenu" dojoType="ContentPane" class="header" 
	 style="width : 80%; padding-bottom: 5px;" >
	 <div style="float: left;">
	 <button dojoType="Button" onclick="openPopUp2('goSendMessage');">
	 <img src="img/mail_new.gif" height=18>
				New Message</button>
	 </div>
	 <div style="float: right">
	 <s:url id="refresh" action="ListMessages"/>
	 <s:a href="%{refresh}" targets="rightcolumn">refresh</s:a>
	 </div>
	 <div align=center>My mailbox</div>
	 
</div>	 
<div id="listPane" dojoType="ContentPane" sizeMin="20" sizeShare="20" style="" align="center">
<table width=80%>
<tr class="header"><td>Sender</td><td>Subject</td><td>Date</td></tr>
<%int i=0;%>
<s:iterator value="mes">

	<tr>
		<td>
			<s:property value="senter"/>
		</td>
		<td>
			<s:property value="name"/>
		</td>
		<td>
			<s:property value="sendTime"/>
		</td>
		
		<td>
		<div id="test<%=i%>" class="test"><s:property value="content"/> 
		<a href="javascript:;" onclick="testImplode('open<%=i%>','test<%=i%>')">Implode!</a></div>
		<a id="open<%=i%>" href="javascript:;" onclick="testExplode(this,'test<%=i%>')">Explode!</a>
	    
		</td>
	</tr>
	<%i++;%>
</s:iterator>
</table>
</div>
</div>
</body>
</html>