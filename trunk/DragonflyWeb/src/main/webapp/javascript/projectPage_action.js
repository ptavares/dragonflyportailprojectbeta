

function openPopUp(action, project){
	window.open('JSP/projects/popups/'+action+'.jsp','wclose','width=500,height=400,toolbar=no,status=no,left=20,top=30')	
}
function openPopUp2(action){
	window.open(action+'.action','wclose','width=500,height=400,toolbar=no,status=no,left=20,top=30')	
}
function hide(target){
	target.innerHTML="";
}
function showSelected(){
  var w=dojo.widget.byId("taskTable");
   if(w){
      	w.getSelectedData();
   }
}


function addRowTask(taskId,subject,description,post,start,end){
	alert("-----"+taskId+" "+subject+" "+description+" "+post+" "+start+" "+end);
	var w=dojo.widget.byId("taskTable");
		if(w){
			w.store.addData({
				Id:taskId,   
			 	Number:taskId,   
			 	Subject:subject,   
			 	Description:description,   
			 	PostDate:post,
			 	StartDate:start,
			 	EndDate:end   
			});
			}
}
