<%@ taglib prefix="s" uri="/struts-tags"%>

<s:action name="goToProjectPage!loadTasks" executeResult="false" />

YYYYYYESSSSSSS

<s:div id="admintaskTab">
	<table>
		<s:iterator value="tasks">
			<tr id="<s:property value="number"/>">
				<td>
				<div class="taskImage" />
				</td>
				<td class="postdate"><s:property value="postDate" /></td>
				<td class="subject"><a href=""><s:property value="subject" /></a></td>
				<td><s:url id="deleteTask" action="DeleteTask">
					<s:param name="delete">
						<s:property value="number" />
					</s:param>
				</s:url> 
				<s:submit type="image" src="img/project/delete.jpg"
					action="%{deleteTask}" targets="admintaskTab"></s:submit></td>
			</tr>
		</s:iterator>
		</s:div>
	</table>
	