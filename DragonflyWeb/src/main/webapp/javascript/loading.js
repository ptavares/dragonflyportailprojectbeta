/**
 * Find the element in the current HTML document with the given id or ids
 */
var $;
if (!$ && document.getElementById) {
  $ = function() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
      var element = arguments[i];
      if (typeof element == 'string') {
        element = document.getElementById(element);
      }
      if (arguments.length == 1) {
        return element;
      }
      elements.push(element);
    }
    return elements;
  }
}
else if (!$ && document.all) {
  $ = function() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
      var element = arguments[i];
      if (typeof element == 'string') {
        element = document.all[element];
      }
      if (arguments.length == 1) {
        return element;
      }
      elements.push(element);
    }
    return elements;
  }
}


var message="Loading...";
	
	dojo.event.topic.subscribe("loginLoading",function(data,type,request){
		message="Check Login..."
		
	}
	)
	
	dojo.event.topic.subscribe("registerLoading",function(data,type,request){
		message="Reading informations..."
		
	}
	)
	
		
	dojo.event.topic.subscribe("loading",function(data,type,request){
	
	var loadingMessage;
  	if (message) loadingMessage = message;
  	else loadingMessage = "Loading";
  	  	
  	if(type=="before") {
  	var disabledZone = $('disabledZone');
    if (!disabledZone) {
    
      disabledZone = document.createElement('div');
      disabledZone.setAttribute('id', 'disabledZone');
      disabledZone.style.position = "absolute";
      disabledZone.style.zIndex = "1000";
      disabledZone.style.left = "0px";
      disabledZone.style.top = "0px";
      disabledZone.style.width = "100%";
      disabledZone.style.height = "5%";
      
      var messageZone = document.createElement('div');
      messageZone.setAttribute('id', 'messageZone');
      messageZone.style.position = "absolute";
      messageZone.style.top = "0px";
      messageZone.style.right = "0px";
      messageZone.style.background = "red";
      messageZone.style.color = "white";
      messageZone.style.fontFamily = "Arial,Helvetica,sans-serif";
      messageZone.style.fontWeight = "Bold";
      messageZone.style.padding = "4px";
     
      var text = document.createTextNode(loadingMessage);
      messageZone.appendChild(text);
      
      disabledZone.appendChild(messageZone);
      document.body.appendChild(disabledZone);
    }
    else {
    	 $('messageZone').innerHTML = loadingMessage;
     	 disabledZone.style.visibility = 'visible';
    }
    }
    else {
   		 $('disabledZone').style.visibility = 'hidden';
    }
    
	} );
	dojo.event.topic.subscribe("refresh",function(data,type,request){
		if(type=="before")
	 	ajaxpage("JSP/Acceuil.jsp", "rightcolumn");
	 } );