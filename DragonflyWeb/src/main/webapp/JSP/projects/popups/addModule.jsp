<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        
        <STYLE>
            .back{
            background-color: #87cefa;
            height: 100%;
            }
        </STYLE>
        
        <s:head theme="ajax"/>
                
        <script language="JavaScript" src="javascript/maven.js"></script>
    </head>
    <body>
        
        <div class="back">
            <h3 align="center"><b>Add a new Module </b></h3>
            <s:form action="#">
                <s:textfield name="module" label="Module" id="module"/>
                <s:submit onclick="ajaxModulePage();" value="Add"></s:submit>                
            </s:form>
        </div>
        
    </body>
</html>

