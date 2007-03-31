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
    <script type="text/javascript"> 
        function showDivManager(){
            alert('Show Div script');
        }
    </script>
    <body>
        
        <div id="maven">            
            <div class="information"><s:text name="maven.title"></s:text><%=session.getAttribute("project")%></div>
            <br/>
            <br/>
            
            <s:form action="goToProjectPage!submit">                
                <div id="general">
                    <s:text name="maven.InformationGeneral"/>
                    <s:textfield name="mavenInformation.groupId" label="%{getText('maven.groupId')}" id="groupId" />
                    <s:textfield name="mavenInformation.artifactId" label="%{getText('maven.artifactId')}" id="artifactId"/>
                    <s:select name="mavenInformation.packaging" id="packaging" label="%{getText('maven.packaging')}" list="{'pom','jar','war','ear','ejb'}" onchange=""/>                
                    <s:textfield name="mavenInformation.name" id="name" label="%{getText('maven.name')}"/>
                    <s:textfield name="mavenInformation.version" id="version" label="%{getText('maven.version')}"/>
                    <s:textarea name="mavenInformation.description" id="description" label="%{getText('maven.description')}"  cols="50" rows="8" theme="xhtml"/>                    
                    <s:submit value="%{getText('maven.modify')}" />
                </div>            
            </s:form>
            
            <br/>
            
           <!-- <s:if test="mavenInformation.packaging == 'pom'">-->
                <div id="modules" style="display:inline;">  
                    <jsp:include flush="true" page="Tabs/ModuleTabs.jsp" /><br/>
                </div>    
            <!--</s:if>
            <s:else>       -->         
                <div id="modules" style="display:none;">  
                    <jsp:include flush="true" page="Tabs/ModuleTabs.jsp" /><br/>
                </div>    
           <!-- </s:else>-->
            <div id="dependencies">
                <jsp:include flush="true" page="Tabs/DependencyTabs.jsp"/> <br/>      
            </div>
            <div id="plugins">
                <jsp:include flush="true" page="Tabs/PluginTabs.jsp" /> <br/>
            </div>
            
        </div>
    </body>
</html>
