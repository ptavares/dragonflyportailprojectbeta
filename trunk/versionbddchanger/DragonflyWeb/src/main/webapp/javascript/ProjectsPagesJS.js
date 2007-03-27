//Pour les popups affichant les informations
dojo.require("dojo.lfx.*");
	function testExplode(start,node){
		dojo.lfx.explode(start, node, 300).play();
	}
	
	function testImplode(start,node){
		dojo.lfx.implode(node, start, 300).play();
	}
	
//Supprime les espaces blancs avant et après le texte des sujets et descriptions
dojo.event.topic.subscribe("deleteWhiteSpace",function(data,type,request){
	
	if(type=="before"){
		var subject = dojo.byId('subject').value;
		if(subject)
			dojo.byId('subject').value = subject.replace(/(^\s*)|(\s*$)/g,'');
		
		var description = dojo.byId('description').value;
		if(description)
			dojo.byId('description').value = description.replace(/(^\s*)|(\s*$)/g,'');
	}
});

//Verifie que les champs dates sont remplies
dojo.event.topic.subscribe("checkDate",function(data,type,request){
	
	if(type=="before"){
		var ladate=new Date();
		var start = dojo.widget.byId('start').getValue();
		if(start==""){
			dojo.widget.byId('start').setValue(ladate);
		}
		var end = dojo.widget.byId('end').getValue();
		if(end=="")
			dojo.widget.byId('end').setValue(ladate);
	}
});