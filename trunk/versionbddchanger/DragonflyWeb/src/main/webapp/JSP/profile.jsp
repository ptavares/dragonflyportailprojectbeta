<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="newLog">
</div>


Your email : <s:property value="#session.nom"/>

<s:head theme="ajax"/>
<s:form action="ModifPassword.action" theme="ajax" method="post" >
<s:submit type="submit" theme="ajax" targets="newLog" value="Change Password" />
</s:form>


<s:form id="form1" action="ModifNickname.action" method="post" theme="ajax">
<s:submit targets="newLog" value="Change Nickname"/>
</s:form>

<s:form id="form1" action="ModifEmail.action" method="post" theme="ajax">
<s:submit targets="newLog" value="Change E-mail" theme="ajax"/>
</s:form>




