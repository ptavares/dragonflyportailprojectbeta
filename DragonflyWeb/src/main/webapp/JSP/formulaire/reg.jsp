<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dragonfly Portail Project Registration</title>
<meta http-equiv="Content-Language" content="fr" />
</head>

<s:head theme="ajax"/>

<body>

<s:div id="register">

<div id="title">
<div id="title-left">
<div id="title-right">

<h1><s:text name="register.title"></s:text></h1>

</div>
</div>
</div>

<s:fielderror/>

<s:form action="Inscription" method="post" validate="true" theme="ajax">

		<!-- Information général (optionnelle) -->
		<s:textfield label="%{getText('register.firstname')}" name="firstname"
			size="20" />
		<s:textfield label="%{getText('register.lastname')}" name="lastname"
			size="20" />

		<s:radio label="%{getText('register.sexe')}" list="sexe_choice" name="sexe"></s:radio>
		<!-- Champs obligatoire -->

		<s:textfield label="%{getText('register.username')}" name="username" 
			size="20" required="true" />
		<s:password label="%{getText('register.password')}" name="password" id="register.password"
			size="20" required="true" />
		<s:password label="%{getText('register.confirmPassword')}" id="register.confirmpassword"
			name="confirmPassword" size="20" required="true" />
		<s:textfield label="%{getText('register.email')}" name="email"
			size="20" required="true" />

		<s:submit targets="register" showLoadingText="false" onclick="cryptePasswords2(new Array('register.password','register.confirmpassword'));"
			notifyTopics="registerLoading,loading" />
		<s:label value="%{getText('register.fieldscondition')}" />
	</s:form>

</s:div>
</body>
</html>

