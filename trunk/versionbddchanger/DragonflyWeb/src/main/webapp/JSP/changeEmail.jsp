<%@ taglib prefix="s" uri="/struts-tags" %>

<div>
<s:form action="ChangeEmail" method="post" theme="ajax">
<s:textfield name="newMail" size="15" label="New E-mail"></s:textfield>
<s:submit targets="rightcolumn"></s:submit>
</s:form>


</div>
