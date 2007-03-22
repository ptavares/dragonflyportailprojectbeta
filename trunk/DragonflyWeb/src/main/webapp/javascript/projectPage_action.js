

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
			 	subject:subject,   
			 	description:description,   
			 	postDate:post,
			 	startDate:start,
			 	endDate:end   
			});
			}
}

function addRowMeeting(meetingId,subject,description,post,dateMeeting){
	alert("-----"+meetingId+" "+subject+" "+description+" "+post+" "+dateMeeting);
	var w=dojo.widget.byId("meetingTable");
		if(w){
			w.store.addData({
				Id:meetingId,   
			 	subject:subject,   
			 	description:description,   
			 	postDate:post,
			 	date:dateMeeting
			});
			}
}

function addRowNews(newsId,subject,description,post){
	alert("-----"+newsId+" "+subject+" "+description+" "+post);
	var w=dojo.widget.byId("newsTable");
		if(w){
			w.store.addData({
				Id:newsId,   
			 	subject:subject,   
			 	description:description,   
			 	postDate:post
			});
			}
}

