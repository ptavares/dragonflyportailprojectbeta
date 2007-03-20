<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="javax.naming.InitialContext"%>
<%@page import="fr.umlv.dragonflyEJB.services.account.information.AccountInformation"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mystyle.css" type="text/css">

<title>My Project Home Page</title>

<s:head theme="ajax"/>
</head>

<SCRIPT type="text/javascript" src="javascript/projectPage_action.js"></SCRIPT>

<body>
<% 

String st = (String)session.getAttribute("project");

if(st!=null)
	session.setAttribute("project", st);

String path=request.getContextPath();
System.out.println("------------->"+st);
%>
<!-- Permet de tester les diff2rents roles de l'utilisqteur logge -->
<%
String project = (String)session.getAttribute("project");
String name = (String)session.getAttribute("nom");
boolean b = false;

if(name!=null){
final InitialContext ctx = new InitialContext();
final AccountInformation info = (AccountInformation)ctx.lookup("AccountInformation/remote");
List<String> list = info.getUserRoles(name);
for(String s : list)
	if(s.compareTo(project+"admin")==0)
		b=true;
}
%>

<!-- Header  -->
<jsp:include flush="true" page="../../JSP/theme/Header.jsp"></jsp:include>

<div id="wrapper-content">
<div id="content">


<s:url id="gotoPrivateSpace" action="goProject" method="goToPrivateSpace">
</s:url>
<s:url id="gotoMeetings" action="goProject" method="goToMeetings">
</s:url>

<s:tabbedPanel id="test" cssClass="tabbedPanel" >
   
   <% if (b==true){ %>   
   <s:div id="privateSpace" label="Private Space" refreshOnShow="true" labelposition="top" href="%{gotoPrivateSpace}" cssClass="tabbedPanel_Div">
   </s:div>
   <%} %>
   <s:div id="gotoMeetings" label="Meetings" refreshOnShow="true" labelposition="top" href="%{gotoMeetings}" cssClass="tabbedPanel_Div">
   </s:div>
</s:tabbedPanel>


</div><!-- close content -->
</div><!-- close wrapper-content -->

<jsp:include flush="true" page="../../JSP/theme/Footer.jsp"></jsp:include>

</body>
</html>