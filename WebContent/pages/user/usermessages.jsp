<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.table-wrapper
{
    width: 100%;
    height: 550px;
    overflow: auto;
}

td {
   font-size: 12px;
}

</style>
<script type="text/javascript">
var friendId;
var friendLastName;
var friendFirstName;
var friendAvatar;
    function getMessageByUser(userId, friendLastNameIn, friendFirstNameIn, friendAvatarIn) {
    	friendId        = userId;	
    	friendLastName  = friendLastNameIn;
    	friendFirstName = friendFirstNameIn;
    	friendAvatar    = friendAvatarIn;
    	$("#friendTitle").html(friendLastName + ' ' + friendFirstName);
    	var nemberMessageId = '#numberNewMessage' + userId;//recive number of new message
    	$(nemberMessageId).html(0);
    	scrollToBottom();
    	$("#messageUser tr").remove();
    		$.ajax({
	            url: "chatUserRequest.do?action=getByUser&userId=" + userId,
	            type: "post",
	            dataType: "json",
	            success: function(data) {
	            	 var trHTML = '';
	                jQuery.each(data, function(index, item) {	              
	                	var avatar = item.sender.avatar.path;
	                	var color = item.senderId == "${sessionUser.id}"? "#CEF6E3": "#2ECCFA" ;  
	               	 	trHTML += '<tr bgcolor= '+color +'><td width="10%">' + item.sender.firstName + " "+ item.sender.lastName +'<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="' + 
	               	 	 	avatar + '" ></td><td width="20%"> ' + item.createdOn + '</td><td width="60">' + item.message.replace(/</g,'&lt') + '</td></tr>';
	                
	                });
	               
	                $("#messageUser").append(trHTML);
	                scrollToBottom();
	            },         
	        });    		
    }
</script>

<div class="row">
<h3 id = "friendTitle"></h3>
    	<div class="col s12" style="margin-top:10px;">

				<div class="table-wrapper" id = "divTableMessages">
					<table  id = "messageUser">
					</table>		
				</div>
				<input  type = text id = "enterMessage" onkeydown="if (event.keyCode == 13) sendMessage();">
				<input  type="submit" value="Send" onclick="sendMessage();" id = "submitButton"/>
        </div>
	
    </div>

    <script>
 // Websocket Endpoint url
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + "/chatuser/";

    var chatClient = null;
    var room = '';
    function connect () {
    	room = ${sessionUser.id};
        chatClient = new WebSocket(endPointURL+room);
        chatClient.onmessage = function (event) {
               var jsonObj = JSON.parse(event.data);
               var nemberMessageId = '#numberNewMessage' + jsonObj.userId;//recive number of new message
               var number = parseInt($(nemberMessageId).html()) +1 ;
               if (friendId == jsonObj.userId){
        	   		var trHTML = '<tr bgcolor= "#2ECCFA"><td width="10%">'+ jsonObj.friendFirstName + ' ' + jsonObj.friendLastName +'<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="' + jsonObj.friendAvatar + '"></td><td width = "20%">' + new Date() + '</td><td width="70%">  '+ jsonObj.message.replace(/</g,'&lt')+ '</td></tr>';
               		$("#messageUser").append(trHTML);
               	 	scrollToBottom();
               }else{
          	   		$(nemberMessageId).html(number); // increment new message
               }
        };
    }

    function disconnect () {
        chatClient.close();
    }

    function sendMessage() {
         var userName = '${sessionUser.lastName} ${sessionUser.firstName}';
         var inputElement = document.getElementById("enterMessage");
         var message = inputElement.value.trim();
         if (message !== "") {
              var jsonObj = {"userName" : userName, "message" : message, "userId": "${sessionUser.id}",  "friendId" : friendId, 
            		  		"friendAvatar" :"${sessionUser.avatar.path}","friendFirstName" : "${sessionUser.firstName}", "friendLastName": "${sessionUser.lastName}" } ;
              waitForSocketConnection(chatClient, function(){
                  console.log("message sent!!!");
                  chatClient.send(JSON.stringify(jsonObj));
              });
            
             
             var table = document.getElementById("messageUser");
             var rowCount = table.rows.length;
             var trHTML = '<tr bgcolor= "#CEF6E3"><td width="10%">${sessionUser.firstName} ${sessionUser.lastName}<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="${sessionUser.avatar.path}"></td><td width = "20%">' + new Date() +'</td><td width="70%">  '+ message.replace(/</g,'&lt')+ '</td></tr>';
             $("#messageUser").append(trHTML);
             scrollToBottom();
             inputElement.value = "";
         }
         inputElement.focus();
    }
    
    function waitForSocketConnection(socket, callback){
        setTimeout(
            function () {
                if (socket.readyState === 1) {
                    console.log("Connection is made")
                    if(callback != null){
                        callback();
                    }
                    return;

                } else {
                    console.log("wait for connection...")
                    waitForSocketConnection(socket, callback);
                }

            }, 5); // wait 5 milisecond for the connection...
    }
    
    
    function scrollToBottom() {
   	   var scrollBottom = Math.max($('#messageUser').height() - $('#divTableMessages').height() + 20, 0);
   	   $('#divTableMessages').scrollTop(scrollBottom);
   	}

    $( document ).ready(function() {
    	getMessageByUser("${lastMessanger.id}", "${lastMessanger.lastName}", "${lastMessanger.firstName}", "${lastMessanger.avatar.path}")
    });
    </script>