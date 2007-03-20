<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>

<div id="infoProject">

<div>
<table border="0">
	<tr>
		<td class="text"><s:text name="information.cdp"></s:text></td>
		<td><s:property value="informationBean.projectLeader"/></td>
	</tr>
	<tr>
		<td class="text"><s:text name="information.dateCreation"></s:text></td>
		<td><s:property value="informationBean.creationDate" /></td>
	<tr>
		<td class="text" ><s:text name="information.resume"></s:text></td>
		<td><s:property value="informationBean.resume" /></td>
	</tr>
	<tr>
		<td class="text"><s:text name="information.description"></s:text></td>
		<td><s:property value="informationBean.description" /></td>
	</tr>
	<tr>
		<td class="text"><s:text name="information.developpeur"></s:text></td>
		<td><s:property value="informationBean.developers" /></td>
	</tr>
</table>
</div>



</div>
</body>
</html>
