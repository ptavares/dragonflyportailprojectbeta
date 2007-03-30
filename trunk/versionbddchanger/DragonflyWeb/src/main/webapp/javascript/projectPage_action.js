

function openPopUp(action, project){
	window.open('JSP/projects/popups/'+action+'.jsp','wclose','width=500,height=400,toolbar=no,status=no,left=20,top=30')	
}
function openPopUp2(action){
	window.open(action+'.action','wclose','width=500,height=400,toolbar=no,status=no,left=20,top=30')	
}
function hide(target){
	target.innerHTML="";
}

function addRowDependencyMaven(groupId,artifactId,version,scope){
    alert("-----"+groupId+" "+artifactId+" "+version+" "+scope);
    var tableId = groupId+"."+artifactId;
    var w=dojo.widget.byId("dependencyTable");
    if(w){
        w.store.addData({
            Id:tableId,
            GroupId:groupId,
            ArtifactId:artifactId,
            Version:version,
            Scope:scope
        });
    }
}

function addRowPluginMaven(groupId,artifactId,version){
    alert("-----"+groupId+" "+artifactId+" "+version);
    var tableId = groupId+"."+artifactId;
    var w=dojo.widget.byId("pluginTable");
    if(w){
        w.store.addData({
            Id:tableId,
            GroupId:groupId,
            ArtifactId:artifactId,
            Version:version
        });
    }
}

function addRowModuleMaven(module){
    alert("-----"+module);
    var w=dojo.widget.byId("moduleTable");
    if(w){
        w.store.addData({
            Id:module,
            Module:module
        });
    }
}
