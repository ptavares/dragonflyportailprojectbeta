<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Project Home Page</title>

<s:head theme="ajax"/>


<style>
    html, body{	
		width: 100%;	/* make the body expand to fill the visible window */
		height: 100%;
		overflow: hidden;	/* erase window level scrollbars */
		padding: 0 0 0 0;
		margin: 0 0 0 0;
    }
	.dojoSplitPane{
		margin: 5px;
	}
	#rightPane {
		margin: 0;
	}
   </style>

</head>
<body>
<div class="logo">
<img src="../../img/LOGO1.png""/>
</div>
<br>
<%
System.out.print("------------>"+request.getContextPath());
String path=request.getContextPath();
%>

<div dojoType="LayoutContainer"
	layoutChildPriority='top-bottom'
	style="width: 100%; height: 100%;">
	<div dojoType="ContentPane" layoutAlign="top" style="background-color: #274383; color: white;">
		top bar
	</div>
	<div dojoType="ContentPane" layoutAlign="bottom" style="background-color: #274383; color: white;">
		bottom bar
	</div>
	
		<% String st = (String) session.getAttribute("project"); %>
		<div dojoType="SplitContainer"
			id="rightPane"
			orientation="vertical"
			sizerWidth="5"
			activeSizing="0"
			sizeMin="50" sizeShare="80"
			layoutAlign="client"			
		>
			<div id="mainTabContainer" dojoType="TabContainer" sizeMin="20" sizeShare="70" style="height: 50%;">
	
				<a id="tab6" dojoType="LinkPane" style="overflow:auto;width: 100%;height: 50%;" href="<%=request.getContextPath()%>/projects/Dragonfly/contents/espaceprive.jsp?project=<%= st %>">Espace Privé</a>

				<a id="tab1" dojoType="LinkPane" style="overflow:scroll;" href="<%=path%>/projects/Dragonfly/test.jsp">Home Page</a>
				
				<a id="tab2" dojoType="LinkPane" href="<%=path%>/projects/Dragonfly/contents/faq.html" style="overflow:scroll;">FAQ</a>
				
				<a id="tab3" dojoType="LinkPane" href="<%=path%>/projects/Dragonfly/contents/doc.html" style="overflow:scroll;">Dodumentation</a>
				
				<a id="tab4" dojoType="LinkPane" href="<%=path%>/projects/Dragonfly/contents/contact.html" style="overflow:scroll;">Contact</a>
				
				<a id="tab5" dojoType="LinkPane" href="<%=path%>/projects/Dragonfly/contents/meeting.jsp?project=<%= st %>" style="overflow:scroll;">Meetings</a>				
				
			</div>
			
		</div>
	</div>
</body>
</html>