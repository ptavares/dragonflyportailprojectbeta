<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <body>
        <div id="accountDiv">
            <jsp:include flush="true" page="Tabs/ActiveAccountTab.jsp" />
            <jsp:include flush="true" page="Tabs/NotActiveAccountTab.jsp" />
        </div>
    </body>
</html>
