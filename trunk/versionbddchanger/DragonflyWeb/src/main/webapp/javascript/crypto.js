dojo.require("dojo.crypto.SHA1");


function cryptePasswords (tab){
	for(var i=0; i< tab.length; i++){
		var password = document.getElementById(tab[i]).value;
				
		/*On accepte que des mot de passe supérieur ou égale a 4 caractères*/
		if(password.length >= 4)
		document.getElementById(tab[i]).value = 
					dojo.crypto.SHA1.compute(password, dojo.crypto.outputTypes.Hex);
		else
			document.getElementById(tab[i]).value = "";
	}
}