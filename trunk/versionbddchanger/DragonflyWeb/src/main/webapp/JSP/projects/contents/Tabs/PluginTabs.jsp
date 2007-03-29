<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <div id="pluginTabs">        
            
            <div id="dojoTable" align="center">
                
                <h3><s:text name="maven.pluginsTabTitle"></s:text></h3>
                <table dojoType="filteringTable" multiple="true" alternateRows="true" id="pluginTable"
                       cellpadding="0" cellspacing="0" border="1" >	
                    
                    <thead>
                        <tr>
                            <th field="GroupId" dataType="String" align="center" valign="top"><s:text name="maven.groupId" /></th>
                            <th field="ArtifactId" dataType="String" align="center" valign="top"><s:text name="maven.artifactId"/></th>
                            <th field="Version" dataType="String" align="center" valign="top"><s:text name="maven.version"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="mavenInformation.plugins">
                            <tr value="<s:property value="groupId"/>.<s:property value="artifactId"/>">
                                <td><s:property value="groupId" /></td>
                                <td><s:property value="artifactId" /></td>
                                <td><s:property value="version" /></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <br/>
                    <s:url id="showpopup"  action="AddMaven!showPluginPopup"></s:url>
                    <s:submit align="center" value="%{getText('maven.add')}" href="%{showpopup}" targets="maven"/>      
            </div>
        </div>
    </body>
</html>
