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
        <div id="moduleTabs">        
            
            <div id="dojoTable" align="center">
                   
                <h3 align="center">Modules</h3>
                <table dojoType="filteringTable" multiple="true" alternateRows="true" id="moduleTable"
                       cellpadding="0" cellspacing="0" border="1" >	
                    
                    <thead>
                        <tr>
                            <th field="Module" dataType="String" align="center" valign="top"><s:text name="Module" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="mavenInformation.modules">
                            <tr value="<s:property />">
                                <td><s:property /></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <br/>     
                <s:submit align="center" onclick="openPopUp2('AddMaven!showModulePopup')" value="%{getText('add')}" />              
            </div>
        </div>
    
    </body>
</html>
