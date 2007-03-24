<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" href="css/list.css" type="text/css">
<link rel="stylesheet" href="css/mystyle.css" type="text/css">

<s:head theme="ajax"/>

		
<script language="JavaScript" src="javascript/loadframe.js"></script>
<SCRIPT type="text/javascript" src="javascript/projectPage_action.js"></SCRIPT>
<SCRIPT type="text/javascript" src="javascript/loading.js"></SCRIPT>
<SCRIPT type="text/javascript" src="javascript/crypto.js"></SCRIPT>
<script type="text/javascript">
	dojo.require("dojo.widget.Clock");
	dojo.require("dojo.lfx.*");
	function testExplode(start,node){
		dojo.lfx.explode(start, node, 300).play();
	}

	function testImplode(start,node){
		dojo.lfx.implode(node, start, 300).play();
	}
</script>
</head>
<body>
<!-- Header  -->
<jsp:include flush="true" page="theme/Header.jsp"></jsp:include>
<div id="wrapper-content"> 
<div id="content"> 

<table  cellspacing="0" cellpadding="0">
<tr>
<td valign="top" width="200" height="600" >
<div id="dragMenu">


<table id="t1">
<tr>
<td>

<jsp:include flush="true" page="formulaire/log.jsp"></jsp:include>

</td>
</tr>
</table>


<table id="t2">
<tr>
<td>
<div dojoType="TitlePane" label="Projects" labelNodeClass="DMTitle" containerNodeClass="content">

<s:url id="test" action="MyProject.action"><s:param name="test" value="%{#session.nom}"></s:param> </s:url>
<s:a href="%{test}">test</s:a>
<s:url id="test2" action="goUpload"><s:param name="projectName">ttt22</s:param> </s:url>
<s:a href="%{test2}" theme="xhtml">test2</s:a>
<a href="projects/myproject/index.jsp">MY PROJECT</a><br/>
<s:url id="createproject" action="CreateProject!input" />
<s:a href="%{createproject}" targets="rightcolumn" >Create Project</s:a><br/>
<s:url id="myproject" action="MyProject" />
<s:a href="%{myproject}" targets="rightcolumn" >My Project</s:a><br/>
<s:url id="listproject" action="ListProjects" />
<s:a href="%{listproject}" targets="rightcolumn" >List Projects</s:a><br/>

<s:url id="admin" action="Admin" />
<s:a href="%{admin}" targets="content" >Administration</s:a><br/>
</div>

</td>
</tr>
</table>


<table id="t3">
<tr>
<td>
<div dojoType="TitlePane" label="Information" labelNodeClass="DMTitle" containerNodeClass="content">
<a href="javascript:ajaxpage('general/general.html', 'rightcolumn');">General</a><br/>
<a href="javascript:ajaxpage('general/about.html', 'rightcolumn');">About</a><br/>
<a href="javascript:ajaxpage('general/contact.html', 'rightcolumn');">Contact</a><br/>
<a href="javascript:ajaxpage('general/faq.html', 'rightcolumn');">FAQ</a><br/>
<br>
</div>
</td>
</tr>
</table>

<table id="t4">
<tr>
<td>
<div dojoType="clock" image="img/clock.png" align="center"></div>

<br>
</td>
</tr>
</table>

</div>
</td>
<td width="780" height="600" valign="top">
	<div id="rightcolumn">
		<jsp:include flush="true" page="Acceuil.jsp"></jsp:include>
	</div>
</td>
</tr>
</table>

</div>
</div>

<jsp:include flush="true" page="theme/Footer.jsp"></jsp:include>

</body>
</html>