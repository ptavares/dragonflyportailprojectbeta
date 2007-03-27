<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Upload et Download</title>
        <link rel="stylesheet" href="css/list.css" type="text/css">
    </head>
    <body>
        
        <div id="downUp">
            
            <div id="listPane"  align="left">
                <table width="80%">
                    <tr class="header"><td align="left" width="%70"><s:text name="download.file"/></td> <td align="left"><s:text name="download.download"/></td><td><button dojoType="Button" onclick="openPopUp2('goUpload');">UploadFile</button></td></tr>
                    <s:if test="%{listLength>0}">
                        <s:iterator value="FileLists">
                            <tr>
                                <td>
                                    <s:property value="fliename"/>
                                </td>
                                <td>
                                    <s:url id="down" action="download"><s:param name="inputPath" value="path"/></s:url>
                                    <s:a href="%{down}" theme="xhtml"><img src="img/download.jpg" height="20"></s:a>
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                </table>
            </div>
            
        </div>
        
    </body>
</html>