<%@ taglib prefix="s" uri="/struts-tags"%>

<s:div id="tt">
<s:if test="#session.login != 'true'">
	<div dojoType="TitlePane" label="Login" labelNodeClass="DMTitle"
		containerNodeClass="content"><s:div id="tt">

		<s:form id="form1" action="/Login2.action" method="post" enctype="text/plain">
			<s:textfield label="%{getText('login.email')}" name="username"
				size="15" />
			<s:password label="%{getText('login.password')}" name="password" id="login.password"
				size="15" />
			<s:submit targets="tt" showLoadingText="false" onclick="cryptePasswords2(new Array('login.password'));"
				notifyTopics="loginLoading,loading,refresh" />
		</s:form>
	</s:div> <s:url id="inscription" action="Inscription!input" /> <s:a
		href="%{inscription}" targets="rightcolumn">S'inscrire</s:a></div>
</s:if>
<s:else>

	<div dojoType="TitlePane" label="Welcome" labelNodeClass="DMTitle"
		containerNodeClass="content">Welcome, <s:property
		value="#session.nom" /><br />
	
	<s:div>
			<s:url id="profile" action="ChangeNickname.action" />
			<s:a href="%{profile}" targets="rightcolumn">Edit Account</s:a>
			</s:div>	
			<s:url id="Messages" action="ListMessages"/>
			<s:a href="%{Messages}" targets="rightcolumn">My Messages</s:a>
	 	<a href="<%=request.getContextPath()%>/Logout.action">Logout</a>
	</s:else>


</s:div>
