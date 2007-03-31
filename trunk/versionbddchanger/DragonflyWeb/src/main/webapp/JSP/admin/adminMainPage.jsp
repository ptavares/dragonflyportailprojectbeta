<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/mystyle.css" type="text/css">
        
        <title><s:text name="%{getText('admin.title')}" /></title>
        
        <s:head theme="ajax"/>
    </head>
    <body>
        <!-- Header  -->
        <jsp:include flush="true" page="../theme/Header.jsp"></jsp:include>
        
        <!-- Contenue de la page Index.jsp : un Titre et un tabbedPanel parmettant d'acceder à différentes pages-->
        <div id="wrapper-content">
            <div id="content">
                <div id="administration">
                    <h1><s:text name="admin.accueil"></s:text></h1>
                    <!-- Init URL -->
                    <s:url id="ProjectListPage" action="Admin" method="projectList"></s:url>
                    <s:url id="AccountListPage" action="Admin" method="accountList"></s:url>
                    <!-- Le TabbedPanel contenant les différentes pages -->
                    <s:tabbedPanel id="adminTPanel" cssClass="tabbedPanel" >
                        <s:div id="AccountList" label="%{getText('admin.accountTab')}" labelposition="top" href="%{AccountListPage}" refreshOnShow="true" cssClass="tabbedPanel_Div" >
                        </s:div>
                        <s:div id="ProjectList" label="%{getText('admin.projectTab')}" labelposition="top" href="%{ProjectListPage}" refreshOnShow="true" cssClass="tabbedPanel_Div" />
                    </s:tabbedPanel>
                    
                </div>
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
