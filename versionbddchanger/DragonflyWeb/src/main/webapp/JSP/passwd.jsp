<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="ChangePasswd" method="post">
<s:password name="oldPasswd" size="20" label="Old Password" id="change.oldpass"></s:password>
<s:password name="newPasswd" size="20" label="New Password" id="change.newpass"></s:password>
<s:password name="confirmPasswd" size="15" label="Confirm Password" id="change.confirmpass"></s:password>
<s:submit targets="rightcolumn" onclick="cryptePasswords(new Array('change.oldpass','change.newpass','change.confirmpass'));" showLoadingText="false"  notifyTopics="loginLoading,loading,refresh"></s:submit>
</s:form>


