<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/mystyle.css" type="text/css">

<title>Project Home Page</title>

<s:head theme="ajax"/>
</head>

<SCRIPT type="text/javascript" src="javascript/ProjectPagesJS.js"></SCRIPT>

<script type="text/javascript">
//Pour les popups affichant les informations
dojo.require("dojo.lfx.*");
	function testExplode(start,node){
		dojo.lfx.explode(start, node, 300).play();
	}
	
	function testImplode(start,node){
		dojo.lfx.implode(node, start, 300).play();
	}
</script>
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
	<s:url id="TODOPage" action="goToProjectPage"  method="goToTODOPage">
	</s:url>
	<s:url id="MeetingsPage" action="goToProjectPage" method="goToMeetingsPage">
	</s:url>
	<s:url id="MavenPage" action="goToProjectPage" method="goToMavenPage">
	</s:url>
	<s:url id="FAQPage" action="goToProjectPage" method="goToFAQPage">
	</s:url>
	<s:url id="DownUpPage" action="goUpload">
	</s:url>
	<s:url id="Administration" action="goToProjectPage" method="goToAdministrationPage">
	</s:url>

	<!-- Le TabbedPanel contenant les différentes pages -->
	<s:tabbedPanel id="test" cssClass="tabbedPanel" >
   
   	<!-- Page "d'acceuil" contenant les differentes informations sur le projet courant -->
    <s:div id="information" label="Information" labelposition="top" href="%{InformationPage}" refreshOnShow="true" cssClass="tabbedPanel_Div">
   	</s:div>
   	   	
   	<!-- Page contenant l'ensemble des news du projet courant -->
    <s:div id="news" label="News" labelposition="top" href="%{NewsPage}" refreshOnShow="true" cssClass="tabbedPanel_Div">
   	</s:div>
   	
   	<!-- Page accessible uniquement au Utilisateur du projet et Chef de Projet -->
   	<s:if test="userStatus == 2 || userStatus == 1">
   	
   	<!-- Page contenant l'ensemble des tâches du projet courant -->
   	<s:div id="todo" label="TODO" labelposition="top" href="%{TODOPage}" refreshOnShow="true" cssClass="tabbedPanel_Div">
   	</s:div>
   	<!-- Page contenant l'ensemble des réunions du projet courant -->
   	<s:div id="meetings" label="Meetings" labelposition="top" href="%{MeetingsPage}" refreshOnShow="true" cssClass="tabbedPanel_Div">
   	</s:div>
  	<!-- Page contenant les actions permettant la génération maven du projet courant -->
  	<s:div id="maven" label="Maven" labelposition="top" href="%{MavenPage}" cssClass="tabbedPanel_Div">
   	</s:div>
   	<!-- Page contenant l'ensemble des "Frequently Asked Questions" du projet courant -->
   	<s:div id="FAQ" label="FAQ" labelposition="top" href="%{FAQPage}" refreshOnShow="true" cssClass="tabbedPanel_Div">
   	</s:div>
  	<!-- Page contenant la liste des documents pouvant etre téléchargés + upload de fichier -->
  	<s:div id="download_upload" label="Download/UpLoad" labelposition="top" href="%{DownUpPage}" refreshOnShow="true" cssClass="tabbedPanel_Div">
   	</s:div>
   	
   	</s:if>
   	
   	<s:if test="userStatus == 2">
   	<!-- Page servant à l'adminstration du projet (reserver chef de projet) -->
  	<s:div id="administration" label="Administration" labelposition="top" href="%{Administration}" refreshOnShow="true" cssClass="tabbedPanel_Div">
   	</s:div>
   	</s:if>
   	
	</s:tabbedPanel>

<div id="goback">
<s:text name="projectIndex.backToHomePage"/>
<a href="index.html"><img src="img/project/backHomePage.png" border="0" 
	alt="<s:text name="projectIndex.backToHomePage"/>"></a>
</div>

</div><!-- close content -->
</div><!-- close wrapper-content -->

<jsp:include flush="true" page="../../JSP/theme/Footer.jsp"></jsp:include>

</body>
</html>