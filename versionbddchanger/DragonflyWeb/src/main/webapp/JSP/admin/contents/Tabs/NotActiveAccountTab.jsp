<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <s:div id="accountnotactive">
            <div id="subtitle">
                <div class="bout-gauche">
                    <div class="bout-droit">
                        <div class="fond">
                            <h3><s:text name="admin.accountToActived"></s:text></h3>
                        </div>
                    </div>
                </div>
            </div>
            <div dojoType="ContentPane" sizeMin="20" sizeShare="20" style="" align="center">
                <table width=90% >
                    <s:iterator value="notactiveAccount">
                        <tr id="<s:property />">
                            <td width="30">
                                <s:form>
                                    <s:submit type="image" theme="ajax" label="Active Account" targets="accountDiv"  src="img/project/check.gif" href="%{ajaxTest}" align="left" />
                                </s:form>
                            </td>
                            <td width="30">
                                <s:form>
                                    <s:submit type="image" theme="ajax" label="Suppress Account" targets="accountDiv"  src="img/project/delete.jpg" href="%{ajaxTest}" align="left" />
                                </s:form>
                            </td>
                            <td><s:property /></td>
                        </tr>
                    </s:iterator>
                </table>
            </div>         
        </s:div>
    </body>
</html>
