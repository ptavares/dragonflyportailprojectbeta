dojo.require("dojo.crypto.SHA1");


function cryptePasswords2 (tab){
	for(var i=0; i< tab.length; i++){
		var password = document.getElementById(tab[i]).value;
		
		if(password.length != 0)
		document.getElementById(tab[i]).value = 
					dojo.crypto.SHA1.compute(password, dojo.crypto.outputTypes.Hex);
	}
}