<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <div id="dependencyTabs">        
            
            <div id="dojoTable" align="center">
                
                <h3 align="center">Dependencies</h3>
                
                <table dojoType="filteringTable" multiple="true" alternateRows="true" id="dependencyTable"
                       cellpadding="0" cellspacing="0" border="1" >	
                    
                    <thead>
                        <tr>
                            <th field="GroupId" dataType="String" align="center" valign="top"><s:text name="GroupId" /></th>
                            <th field="ArtifactId" dataType="String" align="center" valign="top"><s:text name="ArtifactId"/></th>
                            <th field="Version" dataType="String" align="center" valign="top"><s:text name="Version"/></th>
                            <th field="Scope" dataType="String" align="center" valign="top"><s:text name="Scope"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="mavenInformation.dependencies">
                            <tr value="<s:property value="groupId"/>.<s:property value="artifactId"/>">
                                <td><s:property value="groupId" /></td>
                                <td><s:property value="artifactId" /></td>
                                <td><s:property value="version" /></td>
                                <td><s:property value="scope" /></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <br/>
                    <s:url id="showpopup"  action="AddMaven!showDependencyPopup"></s:url>
                    <s:submit align="center" value="%{getText('add')}" href="%{showpopup}" targets="maven"/>  
                <!--<s:submit align="center" onclick="alert('clic');openPopUp2('AddMaven!showDependencyPopup')" value="%{getText('add')}" />   -->
            </div>  
        </div>
    </body>
</html>
