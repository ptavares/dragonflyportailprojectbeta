function ajaxCallRemotePage(url,processChange) {
    alert(url);
    if (window.XMLHttpRequest) { 
        alert("no IE");
        req = new XMLHttpRequest();
        req.onreadystatechange = processChange;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
        req.send(null);
    }
    else if (window.ActiveXObject) { 
        alert("IE");
        req = new ActiveXObject("Microsoft.XMLHTTP");
        req.onreadystatechange = processChange;
        req.open("GET", url, true);
        req.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
        req.send();
    }
    else {
        return; 	
    }    
}

function ajaxDependencyPage(){
    var url = 'AddMaven!Dependency.action?'+getDependencyElements();
    ajaxCallRemotePage(url,processDependencyStateChange);
}

function getDependencyElements(){
    var groupId=document.getElementById("groupId").value;
    var artifactId=document.getElementById("artifactId").value;
    var version=document.getElementById("version").value;
    var scope=document.getElementById("scope").value;
    var elements="groupId="+groupId+"&artifactId="+artifactId+"&version="+version+"&scope="+scope;
    return elements;
}

function processDependencyStateChange() {
    if (req.readyState == 4) { 
        alert("here2");
        if (req.status == 200) {
            alert("yes200");
            var response = req.responseText;
            alert(response);
            var split = response.split(",");
            opener.addRowDependencyMaven(split[0],split[1],split[2],split[3]);
            window.close();
        }
        else{
            alert("no200");
        }
    }
}

function ajaxPluginPage(){
    var url = 'AddMaven!Plugin.action?'+getPluginElements();
    ajaxCallRemotePage(url,processPluginStateChange);
}

function getPluginElements(){
    var groupId = document.getElementById('groupId').value;
    var artifactId = document.getElementById('artifactId').value;
    var version = document.getElementById('version').value;
    var elements="groupId="+groupId+"&artifactId="+artifactId+"&version="+version;
    return elements;
}

function processPluginStateChange() {
    if (req.readyState == 4) { 
        alert("here2");
        if (req.status == 200) {
            alert("yes200");
            var response = req.responseText;
            alert(response);
            var split = response.split(",");
            opener.addRowPluginMaven(split[0],split[1],split[2]);
            window.close();
        }
        else{
            alert("no200");
        }
    }
}

function ajaxModulePage(){
    var url = 'AddMaven!Module.action?module='+document.getElementById('module').value;
    ajaxCallRemotePage(url,processModuleStateChange);
}


function processModuleStateChange() {
    if (req.readyState == 4) { 
        alert("here2");
        if (req.status == 200) {
            alert("yes200");
            var response = req.responseText;
            alert(response);
            opener.addRowModuleMaven(response);
            window.close();
        }
        else{
            alert("no200");
        }
    }
}

function ajaxGeneralMavenPage(){
    var url = 'AddMaven.action?'+getGeneralMavenElements();
    ajaxCallRemotePage(url,processGeneralMavenStateChange);
}

function getGeneralMavenElements(){
    var groupId=document.getElementById("groupId").value;
    var artifactId=document.getElementById("artifactId").value;
    var packaging=document.getElementById("packaging").value;
    var name=document.getElementById("name").value;
    var version=document.getElementById("version").value;
    var description=document.getElementById("description").value;
    var elements="groupId="+groupId+"&artifactId="+artifactId+"&packaging="+packaging+"&name="+name+"&version="+version+"&scope="+scope+"&description="+description;
    return elements;
}

function processGeneralMavenStateChange() {
    if (req.readyState == 4) { 
        alert("here2");
        if (req.status == 200) {
            alert("yes200");
            var response = req.responseText;
            alert(response);           
        }
        else{
            alert("no200");
        }
    }
}

funtion showDivManager(){
    alert('showDivManager');
}
