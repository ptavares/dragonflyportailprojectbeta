<%@ taglib prefix="s" uri="/struts-tags"%>

<s:div id="tt">
<div id="link">
<s:if test="#session.login != 'true'">
	<div dojoType="TitlePane" label="<s:text name="newindex.login"/>" labelNodeClass="DMTitle"
		containerNodeClass="content"><s:div id="tt">

		<s:form id="form1" action="/Login2.action" method="post" enctype="text/plain">
			<s:textfield label="%{getText('login.email')}" name="username"
				size="15" />
			<s:password label="%{getText('login.password')}" name="password" id="login.password"
				size="15" />
			<s:submit targets="tt" showLoadingText="false" onclick="cryptePasswords(new Array('login.password'));"
				notifyTopics="loginLoading,loading,refresh" />
		</s:form>
	</s:div> <s:url id="inscription" action="Inscription!input" /> <s:a
		href="%{inscription}" targets="rightcolumn"><s:text name="newindex.subscribe"></s:text> </s:a>
            
            </div>
</s:if>
<s:else>

	<div dojoType="TitlePane" label="<s:text name="newindex.welcome"/>" labelNodeClass="DMTitle"
		containerNodeClass="content">
		<div><s:text name="newindex.welcome"></s:text>,</div>
		 <s:property value="#session.NickName" />
		<br>
		
	<s:div>
			<s:url id="profile" action="goProfile" />
			<s:a href="%{profile}" targets="rightcolumn"><s:text name="newindex.editAccount"></s:text></s:a>
			</s:div>	
			<s:url id="Messages" action="ListMessages"/>
			<s:a href="%{Messages}" targets="rightcolumn"><s:text name="newindex.myMessage"></s:text></s:a>
                        <s:if test="#session.admin">
                            <div>
                                <s:url id="admin" action="Admin" /> <s:a
                                    href="%{admin}" theme="xhtml"><s:text name="newindex.adminPage"></s:text> </s:a>
                            </div>
                        </s:if>
			<br/>
	 	<a href="<%=request.getContextPath()%>/Logout.action"><s:text name="newindex.logout"></s:text></a>
	</s:else>

</div>
</s:div>
