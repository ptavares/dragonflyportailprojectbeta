<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mystyle.css" type="text/css">

<title>Project Home Page</title>

<s:head theme="ajax"/>
</head>

<SCRIPT type="text/javascript" src="javascript/projectPage_action.js"></SCRIPT>

<body>

<!-- Header  -->
<jsp:include flush="true" page="../theme/Header.jsp"></jsp:include>

<!-- Contenue de la page Index.jsp : un Titre et un tabbedPanel parmettant d'acceder à différentes pages-->
<div id="wrapper-content">
<div id="content">

	<!-- On affiche le Nom du Projet -->
	<div id="project_title" >
		<h1><%= session.getAttribute("project") %></h1>
	</div>


	<!-- Initialisation des URL en directions des différentes pages -->
	<s:url id="InformationPage" action="goToProjectPage" method="goToInformationPage">
	</s:url>
	<s:url id="NewsPage" action="goToProjectPage" method="goToNewsPage">
	</s:url>
	<s:url id="TODOPage" action="goToProjectPage" method="goToTODOPage">
	</s:url>
	<s:url id="MeetingsPage" action="goToProjectPage" method="goToMeetingsPage">
	</s:url>
	<s:url id="MavenPage" action="goToProjectPage" method="goToMavenPage">
	</s:url>
	<s:url id="FAQPage" action="goToProjectPage" method="goToFAQPage">
	</s:url>
	<s:url id="DownUpPage" action="goToProjectPage" method="goToDownUpPage">
	</s:url>


	<!-- Le TabbedPanel contenant les différentes pages -->
	<s:tabbedPanel id="test" cssClass="tabbedPanel" >
   
   	<!-- Page "d'acceuil" contenant les differentes informations sur le projet courant -->
    <s:div id="information" label="Information" labelposition="top" href="%{InformationPage}" cssClass="tabbedPanel_Div">
   	</s:div>
   	<!-- Page contenant l'ensemble des news du projet courant -->
    <s:div id="news" label="News" labelposition="top" href="%{NewsPage}" cssClass="tabbedPanel_Div">
   	</s:div>
   	<!-- Page contenant l'ensemble des tâches du projet courant -->
   	<s:div id="todo" label="TODO" labelposition="top" href="%{TODOPage}" cssClass="tabbedPanel_Div">
   	</s:div>
   	<!-- Page contenant l'ensemble des réunions du projet courant -->
   	<s:div id="meetings" label="Meetings" labelposition="top" href="%{MeetingsPage}" cssClass="tabbedPanel_Div">
   	</s:div>
  	<!-- Page contenant les actions permettant la génération maven du projet courant -->
  	<s:div id="maven" label="Maven" labelposition="top" href="%{MavenPage}" cssClass="tabbedPanel_Div">
   	</s:div>
   	<!-- Page contenant l'ensemble des "Frequently Asked Questions" du projet courant -->
   	<s:div id="FAQ" label="FAQ" labelposition="top" href="%{FAQPage}" cssClass="tabbedPanel_Div">
   	</s:div>
  	<!-- Page contenant la liste des documents pouvant etre téléchargés + upload de fichier -->
  	<s:div id="download_upload" label="Download/UpLoad" labelposition="top" href="%{DownUpPage}" cssClass="tabbedPanel_Div">
   	</s:div>
   	 
	</s:tabbedPanel>


</div><!-- close content -->
</div><!-- close wrapper-content -->

<jsp:include flush="true" page="../../JSP/theme/Footer.jsp"></jsp:include>

</body>
</html>