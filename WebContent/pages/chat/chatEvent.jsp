<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chat Room</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="styles.css" type="text/css"/>
       <script src="jquery-1.10.2.min.js"></script>
    
    <script>
 // Websocket Endpoint url
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + "/chat/";

    var chatClient = null;
    var room = '';
    function connect () {
    	room = $('#selectId option:selected').val();
        chatClient = new WebSocket(endPointURL+room);
        chatClient.onmessage = function (event) {
            var messagesArea = document.getElementById("messages");
            var jsonObj = JSON.parse(event.data);
            var message = jsonObj.user + ": " + jsonObj.message + "\r\n";
            messagesArea.value = messagesArea.value + message;
            messagesArea.scrollTop = messagesArea.scrollHeight;
        };
    }

    function disconnect () {
        chatClient.close();
    }

    function sendMessage() {
        var user = document.getElementById("userName").value.trim();
        if (user === "")
            alert ("Please enter your name!");
        
        var inputElement = document.getElementById("messageInput");
        var message = inputElement.value.trim();
        if (message !== "") {
            var jsonObj = {"user" : user, "message" : message, "komy":"meni"};
            chatClient.send(JSON.stringify(jsonObj));
            inputElement.value = "";
        }
        inputElement.focus();
    }

    </script>
    </head>
    <body onload="connect();" onunload="disconnect();">
        <h1> Chat Room </h1>
        <textarea id="messages" class="panel message-area" readonly ></textarea>
        <div class="panel input-area">
            <input id="userName" class="text-field" type="text" placeholder="Name"/>
            <input id="messageInput" class="text-field" type="text" placeholder="Message" 
                   onkeydown="if (event.keyCode == 13) sendMessage();" />
            <input class="button" type="submit" value="Send" onclick="sendMessage();" />
        </div>
 <select id ="selectId"name ="selectId">
  <option value ="1c">1</option>
  <option value = "2a">2</option>
</select>
    </body>
</html>