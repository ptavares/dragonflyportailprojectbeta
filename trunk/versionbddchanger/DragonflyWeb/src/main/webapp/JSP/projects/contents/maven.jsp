<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    </head>
    <script language="JavaScript" src="javascript/maven.js"></script>
    <body>
        
        <div id="maven">            
            <div class="information"><s:text name="maven.title"></s:text><%=session.getAttribute("project")%></div>
            <br/>
            <br/>
            
            <s:form action="goToProjectPage!submit">                
                <div id="general">
                    <s:text name="maven.InformationGeneral"/>
                    <s:textfield name="mavenInformation.groupId" label="GroupId" id="groupId" />
                    <s:textfield name="mavenInformation.artifactId" label="ArtifactId" id="artifactId"/>
                    <s:select name="mavenInformation.packaging" id="packaging" label="Packaging" list="{'pom','jar','war','ear','ejb'}" onchange="alert('change');"/>                
                    <s:textfield name="mavenInformation.name" id="name" label="Name"/>
                    <s:textfield name="mavenInformation.version" id="version" label="Version"/>
                    <s:textarea name="mavenInformation.description" id="description" label="Description"  cols="50" rows="8" theme="xhtml"/>                    
                    <s:submit value="Modify" />
                </div>            
            </s:form>
            
            <br/>
            
            <div id="modules" style="display:inline;">  
                <jsp:include flush="true" page="Tabs/ModuleTabs.jsp" /><br/>
            </div>      
            <jsp:include flush="true" page="Tabs/DependencyTabs.jsp"/> <br/>          
            
            <jsp:include flush="true" page="Tabs/PluginTabs.jsp" /> <br/>
            
        </div>
    </body>
</html>
