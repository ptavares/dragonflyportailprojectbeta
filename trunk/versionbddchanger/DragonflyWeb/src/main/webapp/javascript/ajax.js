function ajaxCallRemotePage(url) {
	alert(url);
	var query=url+"?"+getElements();
    if (window.XMLHttpRequest) { 
		alert("no IE");
      	req = new XMLHttpRequest();
       	req.onreadystatechange = processStateChange;
        req.open("GET", query, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send(null);
	}
	else if (window.ActiveXObject) { 
		alert("IE");
      	req = new ActiveXObject("Microsoft.XMLHTTP");
       	req.onreadystatechange = processStateChange;
       	req.open("GET", query, true);
       	req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
		req.send();
	}
	else {
		return; 	
	}
	
 }
 
 function getElements(){
 	var address=document.getElementById("address").value;
 	var subject=document.getElementById("subject").value;
 	var content=document.getElementById("content").value;
 	var elements="address="+address+"&subject="+subject+"&content="+content;
 	return elements;
 }
 
 function processStateChange() {
    if (req.readyState == 4) { 
    	alert("here2");
      if (req.status == 200) {
     	 	alert("yes200");
       var d = window.opener;
       var dd=d.document.getElementById("result");
  	   dd.innerHTML= req.responseText;
  	   window.close();
      }
      else{
      	alert("no200");
      }
    }
}