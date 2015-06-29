<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.table-wrapper
{
    border: 1px solid red;
    width: 600px;
    height: 450px;
    overflow: auto;
}
#bottom, #top {
    font-size: 12px;
    cursor: pointer;
    min-width: 50px;
    padding: 5px;
    border: 2px solid #0099f9;
    border-radius: 5px;
}

</style>
<script type="text/javascript">
var friendId;
    $(document).ready(function () {

        $("#friendfilter").keyup(function () {
            console.log($(this).val());

            $.ajax({
                url: "friendfilter.do",
                type: "post",
                dataType: "json",
                data: $(this).serialize(),
                success: function (data) {
//                    if(!data.isEmpty){
                    console.log(data.value);

                    var resultCollenction = $("#collectionResults");
                    resultCollenction.find("li").empty();
//                        resultCollenction.append("<li class='collection-item' id='inner-row'>");
                    resultCollenction = $("#inner-row");

                    $.each(data.results, function (counts, currentUser) {
//                            console.log('iteration'+counts);
//                            console.log('current user friend id = '+currentUser.id);
//                            console.log('current friend firstName = '+currentUser.friend.firstName);
//                            console.log('current frieng lastName = '+currentUser.friend.firstName);
//                            console.log('current user friend id = '+currentUser.id);
                        resultCollenction.append("<form id='userFriendFormWithId"
                                + currentUser.id
                                + "'><input type='hidden' id='userFriendId' class='userFriendId' name='userFriendId' value='"
                                + currentUser.id
                                + "'></form><div class='card' style='height: 150px; width: 330px; float: left; margin-left: 10px;' id='userFriendCard"
                                + currentUser.id
                                + "'><table><tr><td style='width: 120px;'><img class='circle' style='height: 120px; width: 120px; object-fit: cover'src='"
                                + currentUser.friend.avatar.path
                                + "'></td><td><div><a href='#'class='black-text'>"
                                + currentUser.friend.firstName + " " + currentUser.friend.lastName
                                + "</a><br><br><div style='float: right; margin-right: 10px;'><span style='margin-right: 10px;'>Remove friend</span><a href='#_' class='btn-floating light-blue removefriend' data-id='"
                                + currentUser.id
                                + "'><i class='mdi-navigation-close'></i></a></div></div></td></tr></table></div>"
                        );
                    });
                    resultCollenction.append("</li>");
                    resultCollenction.append("</ul>");
                    console.log('success filtering');
                    updateAnchors();

                    data = null;
                }

//                }
            });
        });
    });

  
    function getMessageByUser(userId) {
    	friendId = userId;	
    	$("#messageUser tr").remove();
    		$.ajax({
	            url: "chatUserRequest.do?action=getByUser&userId=" + userId,
	            type: "post",
	            dataType: "json",
	            success: function(data) {
	            	 var trHTML = '';
	                jQuery.each(data, function(index, item) {	              
	                	var avatar = item.sender.avatar.path;
	               	 	trHTML += '<tr><td width="10%">' + item.sender.firstName + " "+ item.sender.lastName+'<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="' + 
	               	 	 	avatar + '" ></td><td width="20%"> ' + item.sender.lastName + '</td><td width="60"> ' + item.message + '</td></tr>';
	                });
	                $("#messageUser").append(trHTML);
	            },         
	        });
    		var wtf    = $('#divTableMessages');
    		  var height = wtf[0].scrollHeight;
    		  wtf.scrollTop(height);		 
    }

</script>

<div class="row">
    <div class="col s12" style="margin-top:10px;">


        <ul class="collection z-depth-2">
            <li class="collection-item" style="height : 50px;">
                <table style="width: 700px;height:30px; margin-top:-12px;">
                    <tr>
                        <td>
                            <input placeholder="Filter" name="friendfilter" id="friendfilter" />
                        </td>
                    </tr>
                </table>

            </li>
        </ul>
	
        <ul class="collection z-depth-2 " id="collectionResults">
            <li class="collection-item" id="inner-row">
			<table>
				<tr>
				<td width="30%">
                <c:forEach var="userFriend" items="${userFriends}">
                    <form id="userFriendFormWithId${userFriend.id}">
                        <input type="hidden" id="userFriendId" class="userFriendId" name="userFriendId"
                               value="${userFriend.id}">
                    </form>      
                            <div class="card" style="height: 70px; width: 200px;"
                                 id="userFriendCard${userFriend.id}">
                                <table>
                                    <tr>
                                        <td style="width: 50px;">
                                            <img class="circle" style="height: 50px; width: 50px; object-fit: cover"
                                                 src="${userFriend.friend.avatar.path}">
                                        </td>
                                        <td>
                                            <div>
                                                <a href="#"  onclick = "getMessageByUser(${userFriend.friend.id})"
                                                	
                                                   class="black-text">${userFriend.friend.firstName} ${userFriend.friend.lastName}
                                                  </a>
                                                <br>
                                                <span id = "numberNewMessage${userFriend.friend.id}" >0</span>
                                            </div>
                                        </td>
                                  </tr>
                                </table>
                            </div>
                </c:forEach>
                </td>
				<td width="70%">
				<span id="bottom">scroll to bottom</span>
				<div class="table-wrapper" id = "divTableMessages">
					<table class="striped" id = "messageUser">
					</table>		
				</div>
				<input type = text id = "enterMessage" onkeydown="if (event.keyCode == 13) sendMessage();"><input class="button" type="submit" value="Send" onclick="sendMessage();" />
				</td>
				</tr>
			</table>
            </li>
        </ul>
	
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
               var number = $(nemberMessageId).html()
               if (friendId == jsonObj.userId){
        	   		var trHTML = '<tr><td width="10%"><img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="${sessionUser.avatar.path}"></td><td width = "20%">  ${sessionUser.lastName}</td><td width="70%">  '+ jsonObj.message+ '</td></tr>';
               		$("#messageUser").append(trHTML);
               }else{
          	   		$(nemberMessageId).html(parseInt(number) + 1); // increment new message
               }
        };
    }

    function disconnect () {
        chatClient.close();
    }

    function sendMessage() {
         var userName = '${sessionUser.lastName} ${sessionUser.firstName}';
         	//document.getElementById("userName").value.trim();
         var inputElement = document.getElementById("enterMessage");
         var message = inputElement.value.trim();
         if (message !== "") {
              var jsonObj = {"userName" : userName, "message" : message, "userId": "${sessionUser.id}",  "friendId" : friendId } ;
             chatClient.send(JSON.stringify(jsonObj));
             
             var table = document.getElementById("messageUser");
             var rowCount = table.rows.length;
             var trHTML = '<tr><td width="10%"><img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="${sessionUser.avatar.path}"></td><td width = "20%">  ${sessionUser.lastName}</td><td width="70%">  '+ message+ '</td></tr>';
             $("#messageUser").append(trHTML);
             $("#messageUser").scrollHeight;
             inputElement.value = "";
         }
         inputElement.focus();
    }
    
    $(function () {
        var height = 0;
        function scroll(height, ele) {
            this.stop().animate({ scrollTop: height }, 1000, function () {            
                var dir = height ? "top" : "bottom";
                $(ele).html("scroll to "+ dir).attr({ id: dir });
            });
        };
        var wtf = $('#divTableMessages');
        $("#bottom, #top").click(function () {
            height = height < wtf[0].scrollHeight ? wtf[0].scrollHeight : 0;
            scroll.call(wtf, height, this);
        });
    });

    </script>