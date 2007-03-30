<%@ taglib prefix="s" uri="/struts-tags" %>
	
<<s:actionerror/>	
<s:form action="ChangeNickname" method="post" theme="ajax">
	<s:textfield name="newLogin" size="20" label="New Login"></s:textfield>
	<s:submit targets="newLog"></s:submit>
</s:form>
    
    