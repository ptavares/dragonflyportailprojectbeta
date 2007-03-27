//Supprime les espaces blancs avant et après le texte des sujets et descriptions
dojo.event.topic.subscribe("DeleteProjetWhiteSpace",function(data,type,request){
	if(type=="before"){
		var projectName = dojo.byId('projectName').value;
		if(projectName)
			dojo.byId('projectName').value = projectName.replace(/(^\s*)|(\s*$)/g,"");
	}
});
