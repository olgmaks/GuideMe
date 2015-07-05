<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.table-wrapper {
	width: 100%;
	height: 450px;
	overflow: auto;
}
#messageUser {
	border-collapse: separate;
	border-spacing: 0 10px;
}
.table-wrapper
        td {
	font-size: 12px;
}
.td-left-round {
	border-radius: 25px 0px 0px 25px;
}
.td-right-round {
	border-radius: 0px 25px 25px 0px;
}

</style>
<script src="js/moment.js"></script>
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
    	$("#enterMessage").show();
		$("#submitButton").show();
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
						var colorSender = "#CEF6E3";// = item.senderId == "${sessionUser.id}"? "#CEF6E3": "#2ECCFA" ;
						var colorRecived = "#2ECCFA";
						var tdUser = '<tr>'
								+ '<td class = "td-left-round" bgcolor="'
								+ colorSender
								+ '" width="10%">'
								+ item.sender.firstName
								+ " "
								+ item.sender.lastName
								+ '<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="'
            					+ avatar + '"></td>'
								+ '<td bgcolor= "'
								+ colorSender
								+ '" width="20%"> '
								+ moment(item.createdOn)
										.format(
												'hh:mm MM D, YYYY')
								+ '</td><td  class = "td-right-round"  bgcolor= "'
								+ colorSender
								+ '" width="60%">'
								+ item.message.replace(
										/</g, '&lt')
								+ '</td>'
								+ '<td width="10%"></td>'
								+ '</tr>'
					
						var tdSender = '<tr>'
								+ '<td width = "10%"></td>'
								+ '<td class = "td-left-round" bgcolor= "'
								+ colorRecived
								+ '" width="20%"> '
								+ moment(item.createdOn)
										.format(
												'hh:mm MM D, YYYY')
								+ '</td><td bgcolor= "'
								+ colorRecived
								+ '" width="60%">'
								+ item.message.replace(
										/</g, '&lt')
								+ '</td>'
								+ '<td class = "td-right-round" bgcolor="'
								+ colorRecived
								+ '" width="10%">'
								+ item.sender.firstName
								+ " "
								+ item.sender.lastName
								+ '<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="'
            					+ avatar + '"></td>'
								+ '</tr>'
						trHTML += item.senderId == "${sessionUser.id}" ? tdUser
								: tdSender;
					
	                	
	                	
	                	      
	                });
	             
	                $("#messageUser").append(trHTML);
	                scrollToBottom();
	            },         
	        });    		
    }
</script>

<div class="row">
<h5 id = "friendTitle"></h5>
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
            	   var td = ' <td class = "td-right-round" bgcolor= "#2ECCFA" width="10%">'
						+ jsonObj.userName
						+ '<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="'
                           + jsonObj.friendAvatar
                           + '" ></td>';
					var trHTML = '<tr><td></td>'
						+ '<td class = "td-left-round" bgcolor= "#2ECCFA" width = "20%">'
						+ moment().format('hh:mm MM D, YYYY')
						+ '</td><td bgcolor= "#2ECCFA" width="70%">  '
						+ jsonObj.message.replace(/</g, '&lt') + '</td>' + td
						+ '</tr>';
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
         var userID = "${sessionUser.id}";
         var inputElement = document.getElementById("enterMessage");
         var message = inputElement.value.trim();
         if (message !== "") {
              var jsonObj = {"userName" : userName, "message" : message, "userId": userID,  "friendId" : friendId, 
            		  		"friendAvatar" :"${sessionUser.avatar.path}","friendFirstName" : "${sessionUser.firstName}", "friendLastName": "${sessionUser.lastName}" } ;
              console.log(jsonObj);
              waitForSocketConnection(chatClient, function(){
                  chatClient.send(JSON.stringify(jsonObj));
              });
            
             var table = document.getElementById("messageUser");
             var rowCount = table.rows.length;
             var trHTML = '<tr>'
					+ '<td class = "td-left-round" bgcolor= "#CEF6E3" width="10%">'
					+ userName
					+ '<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="${sessionUser.avatar.path}"></td>'
					+ '<td bgcolor= "#CEF6E3" width = "20%">'
					+ moment().format('hh:mm MM D, YYYY')
					+ '</td><td class = "td-right-round" bgcolor= "#CEF6E3" width="60%">  '
					+ message.replace(/</g, '&lt')
					+ '</td><td width="10%"></td></tr>';
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
    	if("${lastMessanger.id}"){
    		getMessageByUser("${lastMessanger.id}", "${lastMessanger.lastName}", "${lastMessanger.firstName}", "${lastMessanger.avatar.path}")
    	}else{
    		$("#enterMessage").hide();
    		$("#submitButton").hide();
    	}
    });
    </script>