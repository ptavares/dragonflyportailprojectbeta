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



<s:form action="Inscription" method="post" theme="ajax">

		<!-- Information général (optionnelle) -->
		<s:textfield label="%{getText('register.firstname')}" name="firstname"
			size="20" />
		<s:textfield label="%{getText('register.lastname')}" name="lastname"
			size="20" />

		<s:radio label="%{getText('register.sexe')}" list="sexe_choice" name="sexe"></s:radio>
		<!-- Champs obligatoire -->

		<s:textfield label="%{getText('register.username')}" name="username"
			size="20" required="true" />
		<s:password label="%{getText('register.password')}" name="password"
			size="20" required="true" />
		<s:password label="%{getText('register.confirmPassword')}"
			name="confirmPassword" size="20" required="true" />
		<s:textfield label="%{getText('register.email')}" name="email"
			size="20" required="true" />

		<s:submit targets="register" showLoadingText="false"
			notifyTopics="registerLoading,loading" />
		<s:label value="%{getText('register.fieldscondition')}" />
	</s:form>


</body>
</html>