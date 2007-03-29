<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <s:head theme="ajax"/>
                
        <script language="JavaScript" src="javascript/maven.js"></script>
    </head>
    <body>
        
        <div class="back"id="createModule">
            <div class="information"><s:text name="maven.createModule"></s:text><%=session.getAttribute("project")%>
            </div>
            <br>
            <br>
            
            <s:actionerror/>
            <s:actionmessage/>
            
            <s:form action="AddMaven!Module">
                <s:textfield name="module" label="%{getText('maven.module')}" id="module"/>
                <s:submit onclick="alert('createModule');" value="%{getText('maven.add')}"></s:submit>                
            </s:form>
            
            <div id="goback">
                <s:url id="backMavenPage" action="goToProjectPage"  method="goToMavenPage">
                    <s:param name="ProName"><%=session.getAttribute("project")%></s:param>
                </s:url>
                <s:text name="maven.goBack"></s:text>
                <s:a href="%{backMavenPage}" targets="maven">
                <img src="img/project/goBack.png" border="0"></s:a>
            </div>
        </div>
        
    </body>
</html>

