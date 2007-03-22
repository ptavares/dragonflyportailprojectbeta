<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>   
        <div class="information"><s:text name="maven.title" />
        </div>
        <s:form id="mavenForm" action="GenerateMaven!submit" method="post">                       
            <div id="general">
                <s:text name="maven.InformationGeneral"/>
                <s:textfield name="mavenInformation.groupId" label="GroupId"/>
                <s:textfield name="mavenInformation.artifactId" label="ArtifactId"/>
                <s:select name="mavenInformation.packaging" label="Packaging" list="{'pom','jar','war','ear','ejb'}" onselect=""/>                
                <s:textfield name="mavenInformation.name" label="Name"/>
                <s:textfield name="mavenInformation.version" label="Version"/>
                <s:textarea name="mavenInformation.description" label="Description"  cols="50" rows="8" theme="xhtml"/>
                <s:hidden name="isClic" id="submitForm"/>
            </div>            
        </s:form>
               
        <div id="modules" style="visibility:hidden">
            
        </div>
        
        <div>
            <s:submit showLoadingText="false" formId="mavenForm" />
        </div>
        
        
        
    </body>
</html>