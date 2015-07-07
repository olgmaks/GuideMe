<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>User Cabinet</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />

<title>Guide ME</title>
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<%--<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />--%>

<!-- Include jTable script file. -->
<link rel="shortcut icon" href="http://designshack.net/favicon.ico">
<link rel="icon" href="http://designshack.net/favicon.ico">
<link rel="stylesheet" type="text/css" media="all"
	href="css/styleUserProfile.css">

<link type="text/css" rel="stylesheet" href="css/jquery.ratings.css" />

<%--Image gallery css--%>
<link rel="stylesheet"
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/south-street/jquery-ui.css"
	id="theme" />
<link rel="stylesheet"
	href="//blueimp.github.io/Gallery/css/blueimp-gallery.min.css" />

	<%--Tag it css--%>
	<link href="css/jquery.tagit.css" rel="stylesheet" type="text/css">
	<link href="css/tagit.ui-zendesk.css" rel="stylesheet" type="text/css">

<!-- tag start -->
<%--Maskymuk commented this line to provide goog visibility of img gallery--%>
<%--<link rel="StyleSheet" href="css/ui-lightness/jquery-ui-1.9.2.custom.min.css" type="text/css" media="all"/>--%>
<link rel="StyleSheet" href="css/jquery.tagedit.css" type="text/css"
	media="all" />

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery.ratings.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>
<script src="js/moment.js"></script>

<%--Image gallery js--%>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>
<script
	src="//blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<script src="js/jquery.image-gallery.js"></script>

<script src="js/controller/eventDeletePhotoController.js"></script>

<%--Tag it Java script--%>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>--%>

	<script src="js/tag-it.js" type="text/javascript" charset="utf-8"></script>
<!-- <script src="js/tag-it.js" type="text/javascript" charset="utf-8"></script>	 -->


<!--     <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script> -->


<script type="text/javascript" src="js/jquery.autoGrowInput.js"></script>
<script type="text/javascript" src="js/jquery.tagedit.js"></script>
<!-- tag end     -->


<style>
/* .panel { */
/* 	border: 2px solid #0078ae; */
/* 	border-radius: 5px; */
/* 	width: 100%; */
/* } */
/* .message-area { */
/* 	height: 70%; */
/* 	resize: none; */
/* 	box-sizing: border-box; */
/* } */
/* .input-area { */
/* 	background: #0078ae; */
/* 	box-shadow: inset 0 0 10px #00568c; */
/* } */
/* .input-area input { */
/* 	margin: 0.5em 0em 0.5em 0.5em; */
/* } */
/* .text-field { */
/* 	border: 1px solid grey; */
/* 	padding: 0.2em; */
/* 	box-shadow: 0 0 5px #000000; */
/* } */
/* .button { */
/* 	border: none; */
/* 	padding: 5px 5px; */
/* 	border-radius: 5px; */
/* 	width: 60px; */
/* 	background: orange; */
/* 	box-shadow: inset 0 0 10px #000000; */
/* 	font-weight: bold; */
/* } */
/* .button:hover { */
/* 	background: yellow; */
/* } */
/* #messageInput { */
/* 	min-width: 60%; */
/* 	max-width: 80%; */
/* } */
.table-wrapper {
	width: 100%;
	height: 450px;
	overflow: auto;
}
#messageEvent {
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
nav {
	color: #fff;
	background-color: #6699FF;
	width: 100%;
	height: 56px;
	line-height: 56px;
}
</style>

<script>
	// Websocket Endpoint url
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + "/chatevent/";
	var chatClient = null;
	var room = '';
	function connect() {
		room = '${event.id}';
		var userName = '${sessionUser.lastName} ${sessionUser.firstName}';
		var userID = "${sessionUser.id}";
		var inputElement = document.getElementById("enterMessage");
		var message = inputElement.value.trim();
		$
				.ajax({
					url : "chatEventRequest.do?action=getByEvent&eventId="
							+ '${event.id}',
					type : "post",
					dataType : "json",
					success : function(data) {
						var trHTML = '';
						jQuery
								.each(
										data,
										function(index, item) {
											var avatar = item.sender.avatar.path;
											var colorSender = "#CEF6E3";// = item.senderId == "${sessionUser.id}"? "#CEF6E3": "#2ECCFA" ;
											var colorRecived = "#2ECCFA";
											var tdUser = '<tr><td width = "0%" hidden>'+item.id+'</td>'
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
													+ '" width="55%">'
													+ item.message.replace(
															/</g, '&lt')
													+ '</td>'
													+ '<td width="10%"></td>'
												
													+'<td width="5%">'
													+ '<c:if test="${isAdmin}">'
													+'<button name ="delete" data-id = "'+item.id+'" id = "delete" class ="delete" style="border: 0; background: transparent">'
													+'<img src="icons/delete-photo-icon.png" style="height: 20px; width: 20px; object-fit: cover"/>'
													+'</button>'
													+'</c:if>'
													+'</td>'
												
					                                + '</tr>'
											
											var tdSender = '<tr><td width = "1%" hidden>'+item.id+'</td>'
													+ '<td width = "10%"></td>'
													+ '<td class = "td-left-round" bgcolor= "'
													+ colorRecived
													+ '" width="20%"> '
													+ moment(item.createdOn)
															.format(
																	'hh:mm MM D, YYYY')
													+ '</td><td bgcolor= "'
													+ colorRecived
													+ '" width="55%">'
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
					                               
													+'<td width="5%">'
													 +'<c:if test="${isAdmin}">'
													+'<button name ="delete" data-id = "'+item.id+'" id = "delete" class ="delete" style="border: 0; background: transparent">'
													+'<img src="icons/delete-photo-icon.png" style="height: 20px; width: 20px; object-fit: cover"/>'
													+'</button>'
													+'</c:if>'		
													+'</td>'
														
					                                + '</tr>'
											trHTML += item.senderId == "${sessionUser.id}" ? tdUser
													: tdSender;
										});
						$("#messageEvent").append(trHTML);
						scrollToBottom();
					}
				});
		chatClient = new WebSocket(endPointURL + room);
		chatClient.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("${sessionUser.id}" != jsonObj.userId) {
				var td = ' <td class = "td-right-round" bgcolor= "#2ECCFA" width="10%">'
						+ jsonObj.userName
						+ '<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="'
                            + jsonObj.userAvatar
                            + '" ></td>';
				var trHTML = '<tr><td></td>'
						+ '<td class = "td-left-round" bgcolor= "#2ECCFA" width = "20%">'
						+ moment().format('hh:mm MM D, YYYY')
						+ '</td><td bgcolor= "#2ECCFA" width="70%">  '
						+ jsonObj.message.replace(/</g, '&lt') + '</td>' + td
						+ '</tr>';
				$("#messageEvent").append(trHTML);
				scrollToBottom();
			}
		};
	}
	function disconnect() {
		chatClient.close();
	}
	function sendMessage() {
		var userName = '${sessionUser.lastName} ${sessionUser.firstName}';
		var inputElement = document.getElementById("enterMessage");
		var message = inputElement.value.trim();
		if (message !== "") {
			var jsonObj = {
				"userName" : userName,
				"message" : message,
				"userId" : "${sessionUser.id}",
				"userAvatar" : "${sessionUser.avatar.path}"
			};
			chatClient.send(JSON.stringify(jsonObj));
			var trHTML = '<tr>'
					+ '<td class = "td-left-round" bgcolor= "#CEF6E3" width="10%">'
					+ userName
					+ '<img class="circle" style="height: 30px; width: 30px; object-fit: cover" src="${sessionUser.avatar.path}"></td>'
					+ '<td bgcolor= "#CEF6E3" width = "20%">'
					+ moment().format('hh:mm MM D, YYYY')
					+ '</td><td class = "td-right-round" bgcolor= "#CEF6E3" width="60%">  '
					+ message.replace(/</g, '&lt')
					+ '</td><td width="10%"></td></tr>';
			$("#messageEvent").append(trHTML);
			scrollToBottom();
			inputElement.value = "";
		}
		inputElement.focus();
	}
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#ratingEvent').ratings(5, '${mark}').bind('ratingchanged',
						function(event, data) {
							rate(data.rating)
							$('#example-rating-1').text(data.rating);
						});
			
				$("#messageEvent").on("click",".delete",function(){
					var id  = $(this).attr('data-id');
					$.ajax({
		                url: "chatEventRequest.do?action=deleteMessage&id=" + id,
		                type: "post",
		                success: function () {
		                }
		            });
					location.reload();
				});
				
			});
</script>
<script type="text/javascript">
	function rate(mark) {
		$.ajax({
			url : "adminEventRequest.do?action=ratingEvent&mark=" + mark
					+ "&eventId=" + '${event.id}',
			type : "post",
			success : function() {
			}
		});
	}
</script>

<script>
	$(document).ready(function() {
		$(".acceptfriendrequest").click(function() {
			console.log("acceptfriendrequest ajax call");
			var value = $(this).data('id');
			var strSelector = "#userFriendCard" + value;
			var formSelector = "#userFriendFormWithId" + value;
			$.ajax({
				url : "acceptEventRequest.do",
				type : "post",
				dataType : "json",
				data : $(formSelector).serialize()
			});
			console.log(strSelector);
			$(strSelector).remove();
		});
		$(".removefriend").on("click", function() {
			console.log("removefriend ajax call");
			var value = $(this).data('id');
			var strSelector = "#userFriendCard" + value;
			var formSelector = "#userFriendFormWithId" + value;
			$.ajax({
				url : "removeMember.do",
				type : "post",
				dataType : "json",
				data : $(formSelector).serialize()
			});
			console.log(strSelector);
			$(strSelector).remove();
		});
		$(document).on('click', '.removefriend', function(e) {
			console.log("removefriend ajax call");
			var value = $(this).data('id');
			var strSelector = "#userFriendCard" + value;
			var formSelector = "#userFriendFormWithId" + value;
			$.ajax({
				url : "removeMember.do",
				type : "post",
				dataType : "json",
				data : $(formSelector).serialize()
			});
			console.log(strSelector);
			$(strSelector).remove();
		})
	});
</script>

<script type="text/javascript">
	$(function() {
		// Edit only
		$("#tagform-editonly").find('input.tag').tagedit({
			autocompleteURL : 'autocompleteUserTags.do',
			autocompleteOptions : {
				minLength : 0
			},
			allowEdit : false,
			allowAdd : false
		});
	});
</script>

<script>
	$(document).ready(function() {
		$("#tagform-editonly").submit(function() {
			$.ajax({
				url : 'submitEventTags.do',
				type : 'post',
				dataType : 'json',
				data : $("#tagform-editonly").serialize(),
				success : function(data) {
					Materialize.toast(data.valid, 2000, '', function() {
					})
				}
			});
			return false;
		});
	});
</script>

</head>
<body onload="connect();" onunload="disconnect();">


	<jsp:include page="adminHeader.jsp" />

	<table>
		<tr>
			<td style="width: 20%; vertical-align: top;"><jsp:include
					page="../user/usercabinetpanelleft.jsp" /></td>

			<td style="width: 80%; vertical-align: top;">
				<div id="w">
					<div id="content" class="clearfix">
						<div id="userphoto">
							<img src="${event.avatar.path}" alt="default avatar"
								style="height: 120px; width: 120px; object-fit: cover">
						</div>

						<c:if test="${!isModerator}">
							<c:if test="${showJoin}">
								<form action="joinEvent.do" method="POST">
									<input type="hidden" name="operation" value="join"> <input
										type="hidden" name="id" value="${requestScope.event.id}">


									<button class="btn light-blue waves-effect waves-light"
										type="submit" name="action"
										style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
										Join event<i class="mdi-content-add-circle-outline right"></i>
									</button>


									<br> <br>Status: <select class="browser-default"
										id="status" name="status"
										style="width: 50%; text-align: left; font-size: 100%; text-transform: capitalize">
										<option selected value="guest">Guest</option>
										<option value="resident">Resident</option>
									</select>

									<script>
										$('#status')
												.change(
														function() {
															var selectedValue = $(
																	this).val();
															if (selectedValue == "guest") {
																$(
																		'#bedCountSelect')
																		.val(
																				'need');
															} else {
																$(
																		'#bedCountSelect')
																		.val(
																				'accept');
															}
														});
									</script>


									<br>Apartments: <select class="browser-default"
										id="bedCountSelect" name="bedCountSelect"
										style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
										<option value="need">Need apartments:</option>
										<option value="accept">Accepting guests:</option>
									</select> <br>Persons: <select class="browser-default"
										id="bedCount" name="bedCount"
										style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
										<option selected value="0">No</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>


								</form>
							</c:if>

							<c:if test="${showQuit || showCancel}">
								<form action="joinEvent.do" method="POST">
									<input type="hidden" name="operation" value="quit"> <input
										type="hidden" name="id" value="${requestScope.event.id}">

									<button class="btn light-blue waves-effect waves-light"
										type="submit" name="action"
										style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
										<c:if test="${showCancel}">Cancel request</c:if>
										<c:if test="${showQuit}">Quit event</c:if>

										<i class="mdi-navigation-close right"></i>
									</button>
								</form>
							</c:if>
						</c:if>


                    <h3>
                        <c:out value="${requestScope.event.name} "/>
                    </h3>
                    
                    <p>
                    <div id='totalNeed'>
                    <c:if test="${requestScope.totalBed > 0}">
                    	<span class="blue-text" >${requestScope.totalBedStr}</span>
                    </c:if>
                    
                    <c:if test="${requestScope.totalBed < 0}">
                    	<span class="red-text" >${requestScope.totalBedStr}</span>
                    </c:if>
                    </div>
                    </p>
                    

						<!-- 		       <form action="joinEvent.do" method="POST" > -->
						<%-- 				<input type="hidden" name = "eventId" value ="${event.id}"> --%>
						<!-- 			   </div> -->

						<!-- 			   </form>      -->


						<c:if test="${!isModerator}">

							<c:forEach items="${event.tagList}" var="tagElem">
								<a href='home.do?tag=${tagElem}'>#${tagElem}</a>
							</c:forEach>

							<br>
							<br>
							<b> Event status: ${event.status} </b>
							<br>

						</c:if>

						<c:if test="${isModerator}">
							<form action="changeEventStatus.do" method="POST"
								id="changeEventForm">
								<input type="hidden" name="id" value="${requestScope.event.id}">
								<br>Event status: <select class="browser-default"
									id="eventStatus" name="eventStatus"
									style="width: 50%; text-align: left; font-size: 100%; text-transform: capitalize">

									<option ${selActive} value="active">Active</option>
									<option ${selFilled} value="filled">Filled</option>
									<option ${selCancelled} value="cancelled">Cancelled</option>
									<option ${selDone} value="done">Done</option>
								</select>

								<button class="btn light-blue waves-effect waves-light"
									type="submit" name="action"
									style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
									Change</i>
								</button>

							</form>

							<script type="text/javascript">
								$(document)
										.ready(
												function() {
													$("#changeEventForm")
															.submit(
																	function() {
																		$
																				.ajax({
																					url : 'changeEventStatus.do',
																					type : 'post',
																					dataType : 'json',
																					data : $(
																							"#changeEventForm")
																							.serialize(),
																					success : function(
																							data) {
																						Materialize
																								.toast(
																										'Status changed !',
																										2000,
																										'',
																										function() {
																										})
																					}
																				});
																		return false;
																	});
												});
							</script>

							<form method="POST" id="tagform-editonly">
								<input type="hidden" name="id" value="${requestScope.event.id}">
								<br>Interests: <input type="text" name="tag[]" value=""
									class="tag" />

								<c:forEach items="${requestScope.tags}" var="tag">
									<input type="text" name="tag[3-a]" value="${tag.name}"
										class="tag" />
								</c:forEach>


								<button class="btn light-blue waves-effect waves-light"
									type="submit" name="action"
									style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
									Save</i>
								</button>

							</form>
						</c:if>


						<ul class="collection">
							<li class="collection-item avatar"><img
								src="${event.moderator.avatar.path}" alt="" class="circle">

								<span class="title"><a
									href="userProfile.do?id=${event.moderator.id}">Author:
										${event.moderator.firstName} ${event.moderator.lastName}</a></span>

								<p>
									<i>${requestScope.moderatorMark}</i> <br>
								</p> <%--       <a  class="secondary-content"><i class="material-icons">${m.status}</i></a> --%>
							</li>

						</ul>


						<nav id="profiletabs">
							<ul class="clearfix">
								<li><a href="#bio" class="sel">About</a></li>
								<li><a href="#services">Services</a></li>
								<li><a href="#photos">Fotos</a></li>

								<c:if test="${isAdmin||isModerator}">
									<li><a href="#edit">Edit</a></li>
								</c:if>
								<li><a href="#members">Members</a></li>
								<c:if test="${isModerator}">
									<li><a href="#requests">Requests</a></li>
								</c:if>
								<li><a href="#chat">Chat</a></li>

								<c:if test="${isModerator}">
									<li><a href="#addservice">Add Service</a></li>
								</c:if>
								<c:if test="${isAdmin}">

								</c:if>
							</ul>
						</nav>

						<section id="bio">
							<p>
								<span><b>Type: </b> </span>
								<c:out value="${type}" />
							</p>

							<p>
								<span><b>Description: </b> </span>
								<c:out value="${event.description}" />
							</p>

                       		 <p>
                        	    <span><b>From: </b></span>
                        	    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.dateFrom}" />
                        	</p>

                        	<p>
                        	    <span><b>To: </b></span>
                        	    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.dateTo}" />
                        	</p>

							<p>
								<span><b>Address: </b></span>
								<c:out value="${event.address.address}" />
							</p>

							<p>
								<span><b>City: </b></span>
								<c:out value="${event.address.city.name}" />
							</p>

							<p>
								<span><b>Average mark: </b></span>
								<c:out value="${eventMark}" />
							</p>

							<p>
								<span><b>Total points: </b></span>
								<c:out value="${eventPoints}" />
							</p>
							
                        	<p>
                         	   <span><b>Capacity: </b></span>
                         	   <c:out value="${capacity}"/>
                        	</p>							
							<br>

							<c:if test="${isMember}">


								<c:if test="${!isModerator}">
									<p>
							 		 <span><b>Your mark: </b></span>
									</p>
								</c:if>

								<c:choose>
									<c:when test="${not empty userLogined}">
									<c:if test="${!isModerator}">
										<div id="ratingEvent"></div>
									</c:if> 	
										<br />

										<form action="adminEventRequest.do?action=commentEvent"
											method="POST">
											<div class="row">
												<div class="input-field col s6">
													<input type="hidden" name="eventId" value="${event.id}">
												</div>
												<div class="input-field col s12">
													<p>
														<span> <b> <i> Comment</i>
														</b>
														</span> <input required type="text" name="comment">
													</p>
												</div>
											</div>
											<!-- 		   	<input type="submit" value="Submit"> -->

											<button class="btn light-blue waves-effect waves-light"
												type="submit" name="action"
												style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
												Add comment<i class="mdi-content-add-circle-outline right"></i>
											</button>
										</form>

										<div class="table-wrapper" id = "divTableMessages">
										<ul class="collection" id="friendslist">

											<c:forEach items="${requestScope.commentEvent}" var="ce">
												<li class="collection-item avatar"><img
													src="${ce.commentator.avatar.path}" alt="" class="circle">

													<span class="title"><a
														href="userProfile.do?id=${ce.commentator.id}">
															${ce.commentator.firstName} ${ce.commentator.lastName}</a></span>

													<p>
														<br> <c:out value="${ce.comment}"/>
													</p> <c:if test="${ce.commentator.id == userLogined.id}">
														<form id="deleteForm"
															action="deleteEventComment.do?id=${ce.id}&eventId=${event.id}"
															method="POST">

															<button type="submit" class="secondary-content">
																<i class="material-icons">delete</i>
															</button>

														</form>

													</c:if></li>
											</c:forEach>


										</ul>
										</div>
									</c:when>
								</c:choose>
							</c:if>


						</section>

						<section id="photos" class="hidden">
							<div id="blueimp-gallery-dialog" data-show="fade"
								data-hide="fade">
								<!-- The Gallery widget  -->
								<div
									class="blueimp-gallery blueimp-gallery-carousel blueimp-gallery-controls">
									<div class="slides"></div>
									<a class="prev"></a> <a class="next"></a> <a class="play-pause"></a>
								</div>
								<c:if test="${isModerator}">
								<div style="visibility: hidden" id="footer">
									<a class="update-event-avatar" href="updateUserAvatar.do"
										data-url="updateEventAvatar.do" id="updateAvatar"> <img
										src="icons/update-avatar-icon.png"
										style="width: 30px; height: 30px;"> Set on avatar
									</a> <a class="delete-event-photo" data-value="" href="#_"
										id="deletePhoto" style="float: right"> <img
										src="icons/delete-photo-icon.png"
										style="width: 30px; height: 30px;" /> Delete from gallery
									</a>
								</div>
								</c:if>

							</div>
							<p>
								Photos: <a href="upload.do?uploadtype=event"> <span
									style="float: right;">Upload new photo</span></a>
							</p>

							<div id="links">
								<c:forEach items="${photos}" var="photo">
									<a href="${photo.path}" title="${photo.path}" data-dialog>
										<img src="${photo.path}"
										style="height: 160px; width: 160px; object-fit: cover"
										alt="${photo.path}">
									</a>
								</c:forEach>
							</div>
							<!--       	</ul> -->
						</section>

						<section id="chat" class="hidden">

							<div class="col s12" style="margin-top: 10px;">
								<div class="table-wrapper" id="divTableMessages">
									<table id="messageEvent">
									</table>
								</div>
								<input type=text id="enterMessage"
									onkeydown="if (event.keyCode == 13) sendMessage();"> <input
									type="submit" value="РќР°РґС‹СЃР»Р°С‚Рё"
									onclick="sendMessage();" id="submitButton" />
							</div>
						</section>

						<section id="members" class="hidden">
							<p>Members:</p>


							<ul class="collection" id="collectionResults">
								<c:forEach items="${requestScope.members}" var="m">


									<form id="userFriendFormWithId${m.user.id}">
										<input type="hidden" id="userFriendId" class="userFriendId"
											name="userFriendId" value="${m.user.id}"> <input
											type="hidden" id="memberEventId" class="memberEventId"
											name="memberEventId" value="${event.id}">


										<li class="collection-item avatar"
											id="userFriendCard${m.user.id}"><img
											src="${m.user.avatar.path}" alt="" class="circle"> <span
											class="title"><a href="userProfile.do?id=${m.user.id}">
													${m.user.firstName} ${m.user.lastName}</a></span>


											<p>
												<c:if test="${m.bedCount > 0}">Can accept: ${m.bedCount} guest(s)</c:if>
												<c:if test="${m.bedCount < 0}">Need lodjing for: ${-m.bedCount} person(s)</c:if>


                                            <c:if test="${isModerator && (m.user.id != user.id)}">

                                                <br>
                                                <span style="margin-right: 10px;">Remove member</span>
                                                <a class="btn-floating light-blue removefriend"
                                                   data-id="${m.user.id}"> <i
                                                        class="mdi-navigation-close"></i>

                                                </a>

                                            </c:if>

												<br>

											</p> <a class="secondary-content"><i class="material-icons">${m.status}</i></a>
										</li>
									</form>
								</c:forEach>
							</ul>

						</section>
						<section id="edit" class="hidden">
							<p>Edit event:</p>

							<div class="row">
								<form action="adminEventRequest.do?action=edit" method="POST">
									<div class="row">
										<div class="input-field col s6">
											<input type="hidden" name="eventId" value="${event.id}">

											<input type="text" name="name" id="name"
												value="${requestScope.event.name}"> <label
												for="name"> editEvent.eventName:</label>


										</div>

									</div>
									<div class="row">
										<div class=" col s12">
											<table style="width: 100%;">
												<tr>

													<td style="width: 34%;"><label for="dateFrom">
															editEvent.dateFrom:</label> <input type="date" class="datepicker"
														id="dateFrom" name="dateFrom" value="${dateFrom}" /> <span
														id="dateMessage"></span></td>

													<td style="width: 8%;"><label for="hourFrom">
															hour:</label> <input type="number" name="hourFrom" min="00"
														max="23" id="hourFrom" value="${hourFrom}"></td>
													<td style="width: 0%;">:</td>

													<td style="width: 8%;"><label for="minuteFrom">
															minute:</label> <input type="number" name="minuteFrom" min="00"
														max="59" id="minuteFrom" value="${minuteFrom}"></td>

													<td style="width: 34%;"><label for="dateTo">
															editEvent.dateTo</label> <input type="date" class="datepicker"
														id="dateTo" name="dateTo" value="${dateTo}" /> <span
														id="dateMessage"></span></td>

													<td style="width: 8%;"><label for="hourTo">
															hour:</label> <input type="number" name="hourTo" min="00"
														max="23" id="hourTo" value="${hourTo}"></td>
													<td style="width: 0%;">:</td>

													<td style="width: 8%;"><label for="minuteTo">
															minute:</label> <input type="number" name="minuteTo" min="00"
														max="59" id="minuteTo" value="${minuteTo}"></td>


												</tr>
											</table>
										</div>
									</div>
									<div class="row">

										<label>Address</label>

										<div>
											<c:forEach items="${requestScope.languageList}" var="lang">

                                            ${lang.name} :
                                            <table style="width: 100%;">
													<tr>

														<td style="width: 30%;"><label>Country</label> <select
															id="countryByLang_${lang.id}" class="browser-default">
																<option selected disabled value="choose">
																	loginpage.country.choose</option>


																<c:forEach items="${requestScope.countryList}"
																	var="country">

																	<c:if test="${country.localId == lang.id}">


																		<option value="${country.id}">${country.name}</option>


																	</c:if>
																</c:forEach>

																<script>
																	$(
																			'#countryByLang_${lang.id}')
																			.change(
																					function() {
																						var destination = $(
																								'#cityByLang_${lang.id}')
																								.val();
																						var selectedValue = $(
																								this)
																								.val();
																						//change other countries
																						$
																								.ajax({
																									type : "post",
																									url : 'getLocalCountryAnalogs.do?value='
																											+ selectedValue,
																									success : function(
																											originCountryId) { //we're calling the response json array 'cities'
																										$
																												.each(
																														originCountryId,
																														function(
																																countryId,
																																country) { //here we're doing a foeach loop round each city with id as the key and city as the value
																															//$(countryId).val(country);
																															$(
																																	'#countryByLang_'
																																			+ countryId)
																																	.val(
																																			country);
																															//fill city list
																															$(
																																	'#cityByLang_'
																																			+ countryId
																																			+ " > option")
																																	.remove(); //first of all clear select items
																															$
																																	.ajax({
																																		type : "post",
																																		url : 'getCitiesByCountry.do?value='
																																				+ country,
																																		success : function(
																																				originCityId) { //we're calling the response json array 'cities'
																																			var optFirst = $('<option selected />'); // here we're creating a new select option with for each city
																																			optFirst
																																					.val('choose');
																																			$(
																																					'#cityId')
																																					.val(
																																							'choose');
																																			optFirst
																																					.text('loginpage.city.choose');
																																			$(
																																					'#cityByLang_'
																																							+ countryId)
																																					.append(
																																							optFirst);
																																			$
																																					.each(
																																							originCityId,
																																							function(
																																									id,
																																									city) { //here we're doing a foeach loop round each city with id as the key and city as the value
																																								var opt = $('<option />'); // here we're creating a new select option with for each city
																																								opt
																																										.val(id);
																																								opt
																																										.text(city);
																																								$(
																																										'#cityByLang_'
																																												+ countryId)
																																										.append(
																																												opt); //here we will append these new select options to a dropdown with the id 'cities'
																																							});
																																		}
																																	});
																														});
																									}
																								});
																					});
																</script>

														</select></td>


														<td style="width: 30%;"><label>City</label> <select
															id="cityByLang_${lang.id}" class="browser-default">
																<option selected value="choose">
																	loginpage.city.choosecountryfirst</option>
														</select> <script>
															$(
																	'#cityByLang_${lang.id}')
																	.change(
																			function() {
																				var selectedValue = $(
																						this)
																						.val();
																				$(
																						'#cityId')
																						.val(
																								selectedValue);
																				//change other cities
																				$
																						.ajax({
																							type : "post",
																							url : 'getLocalCityAnalogs.do?value='
																									+ selectedValue,
																							success : function(
																									originCityId) { //we're calling the response json array 'cities'
																								$
																										.each(
																												originCityId,
																												function(
																														cityId,
																														city) { //here we're doing a foeach loop round each city with id as the key and city as the value
																													$(
																															'#cityByLang_'
																																	+ cityId)
																															.val(
																																	city);
																												});
																							}
																						});
																			});
														</script></td>

														<td style="width: 40%;">
															<div class="input-field">
																<label for="addressByLang_${lang.id}">Adress</label> <input
																	type="text" id="addressByLang_${lang.id}"
																	name="addressByLang_${lang.id}"
																	value="${event.address.address}" /> <span
																	id="addressByLangMessage_${lang.id}"></span>
															</div> <script>
																$(document)
																		.ready(
																				function() {
																					$(
																							"#addressByLang_${lang.id}")
																							.change(
																									function() {
																										$
																												.ajax({
																													url : "registerAddressValidator.do",
																													type : "post",
																													dataType : "json",
																													data : $(
																															this)
																															.serialize(),
																													success : function(
																															data) {
																														$(
																																"#addressByLangMessage_${lang.id}")
																																.text(
																																		data.valid);
																													}
																												});
																									});
																				});
															</script>
														</td>
													</tr>
												</table>

											</c:forEach>

										</div>
										<input type="hidden" id="cityId" name="cityId" /> <span
											id="cityIdMessage"></span>

									</div>

									<div class="row">
										<div class="input-field col s12">
											<textarea id="description" name="description" rows="10"
												cols="45" maxlength="400" class="materialize-textarea">${requestScope.event.description}</textarea>
											<label for="description">editEvent.Description:</label>
										</div>
									</div>

									<div class="row">
										<div class="input-field col s12">
											<label for="videoLink"> editEvent.videoLink:</label> <input
												type="text" id="videoLink" name="videoLink"
												value="${requestScope.event.videoLink}" /> <span
												id="videoLinkMessage"></span><br>
										</div>
									</div>
									<div class="row">
										<div class="col s12">
											<ul class="collection z-depth-2 ">
												<li class="collection-item">

													<div class="row">
														<div class=" col s12">
															<table style="width: 100%;">
																<tr>


																	<td style="width: 25%;"><input type="number"
																		name="partisipant_limit" min="1"
																		id="partisipant_limit"
																		value="${requestScope.event.participants_limit}">
																		<label for="partisipant_limit">editEvent.Partisipant_limit</label>

																	</td>


																	<td style="width: 25%;"><input type="number"
																		name="age_limit_from" min="0" id="age_limit_from">
																		<label for="age_limit_from">editEvent.age_from</label>

																	</td>
																	<td style="width: 25%;"><input type="number"
																		name="age_limit_to" min="0" id="age_limit_to">
																		<label for="age_limit_to">editEvent.age_to</label></td>

																	<td style="width: 25%;"><select
																		id="gender_restriction" name="gender_restriction"
																		class="browser-default">
																			<option value="" disabled selected>
																				editEvent.gender_restriction</option>
																			<option value="male">only_male</option>
																			<option value="female">only_female</option>
																			<option value="all">all</option>
																	</select></td>


																</tr>
															</table>


														</div>
													</div>
												</li>
											</ul>
										</div>
									</div>
									<c:if test="${isModerator}">
                                    Status: <select
											class="browser-default" id="status" name="status"
											style="width: 50%; text-align: left; font-size: 100%; text-transform: capitalize">
											<c:if test="${getstatus=='guest'}">
												<option selected="" value="guest">Guest</option>
												<option value="resident">Resident</option>
											</c:if>
											<c:if test="${getstatus=='resident'}">
												<option value="guest">Guest</option>
												<option selected="" value="resident">Resident</option>
											</c:if>

										</select>

										<script>
											$('#status')
													.change(
															function() {
																var selectedValue = $(
																		this)
																		.val();
																if (selectedValue == "guest") {
																	$(
																			'#bedCountSelect')
																			.val(
																					'need');
																} else {
																	$(
																			'#bedCountSelect')
																			.val(
																					'accept');
																}
															});
										</script>


										<br>Apartments: <select class="browser-default"
											id="bedCountSelect" name="bedCountSelect"
											style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
											<c:if test="${apartments=='need'}">
												<option value="need" selected>Need apartments:</option>
												<option value="accept">Accepting guests:</option>
											</c:if>
											<c:if test="${apartments=='accept'}">
												<option value="need" selected>Need apartments:</option>
												<option value="accept" selected>Accepting guests:</option>
											</c:if>
										</select> Persons: <br>
										<input type="number" name="bad_count" min="1" id="bad_count"
											style="width: 10%;" value="${getbet_count}">
										<br>
									</c:if>
									<button class="btn light-blue waves-effect waves-light"
										type="submit" name="action"
										style="width: 20%; margin-top: 10px; text-align: center; font-size: 100%; text-transform: capitalize">
										Edit</button>
								</form>
							</div>
						</section>
						<section id="services" class="hidden">
							<jsp:include page="servicesInEvent.jsp" />
						</section>
						
						<section id="addservice" class="hidden">

							<jsp:include page="guideAddService.jsp" />


						</section>


						<c:if test="${isModerator}">
							<section id="requests" class="hidden">
								<p>Requests:</p>


								<ul class="collection">
									<c:forEach items="${requestScope.requests}" var="m">

										<form id="userFriendFormWithId${m.user.id}">
											<input type="hidden" id="userFriendId" class="userFriendId"
												name="userFriendId" value="${m.user.id}"> <input
												type="hidden" id="memberEventId" class="memberEventId"
												name="memberEventId" value="${event.id}">


											<li class="collection-item avatar"
												id="userFriendCard${m.user.id}"><img
												src="${m.user.avatar.path}" alt="" class="circle"> <span
												class="title"> <a
													href="userProfile.do?id=${m.user.id}">

														${m.user.firstName} ${m.user.lastName} </a>

											</span>


												<p>
													<c:if test="${m.bedCount > 0}">Can accept: ${m.bedCount} guest(s)</c:if>
													<c:if test="${m.bedCount < 0}">Need lodjing for: ${-m.bedCount} person(s)</c:if>


													<c:if test="${isModerator}">
														<br>
														<span style="margin-right: 10px;">Accept request</span>
														<a href="#_"
															class="btn-floating light-blue acceptfriendrequest"
															data-id="${m.user.id}"> <i
															class="mdi-navigation-check"></i>


														</a>

														<span style="margin-right: 10px;">Remove request</span>
														<a  class="btn-floating light-blue removefriend"
															data-id="${m.user.id}"> <i
															class="mdi-navigation-close"></i>

														</a>

													</c:if>

													<br>

												</p> <a class="secondary-content"><i class="material-icons">${m.status}</i></a>
											</li>
										</form>
									</c:forEach>

								</ul>

							</section>
						</c:if>

						<!--       <section id="settings" class="hidden"> -->
						<!--         <p>Edit your user settings:</p> -->
						<!--         <form action="AdminServletPost?action=edit" method="POST"> -->
						<%--         <input type="hidden" name = "userId" value ="${user.id}"> --%>
						<%--         <p class="setting"><span>E-mail Address </span><input type ="text" value = "${requestScope.user.email}"></p>        --%>
						<%--         <p class="setting"><span>Language </span><input type ="text" value = "${requestScope.user.firstName}"></p> --%>
						<%--         <p class="setting"><span>FirstName</span><input type ="text" value = "${requestScope.user.firstName}"name = "firstName"></p>    --%>
						<%--         <p class="setting"><span>Last name</span> <input type ="text" value = "${requestScope.user.lastName}" name = "lastName"></p>   --%>
						<!--         <p class="setting"><span>user Type</span>  -->
						<!--        			<select name="userTypeId" id = "userTypeId"> -->
						<%-- 					<c:forEach items="${userType}" var="userType"> --%>
						<%-- 						<option value="${userType.id}">${userType.name}</option> --%>
						<%-- 					</c:forEach> --%>
						<!-- 				</select> -->
						<!-- 		</p>   -->
						<!-- 		<p class="setting"><span>sex</span>  -->
						<!--        			<select name="sex" id = "sex"> -->
						<!-- 							<option value="male">male</option> -->
						<!-- 							<option value="female">female</option> -->
						<!-- 				</select> -->
						<!-- 		</p>   -->
						<!-- 		<input type="submit" value="Submit"> -->
						<!-- 		</form> -->
						<!--       </section> -->
					</div>
					<!-- @end #content -->
				</div>




				<form id="memberAjaxForm" name="memberAjaxForm">
					<input type="hidden" name="id" value="${event.id}">
				</form> <script type="text/javascript">
					function scrollToBottom() {
						var scrollBottom = Math.max($('#messageEvent').height()
								- $('#divTableMessages').height() + 20, 0);
						$('#divTableMessages').scrollTop(scrollBottom);
					}
					$(function() {
						$('#profiletabs ul li a')
								.on(
										'click',
										function(e) {
											e.preventDefault();
											var newcontent = $(this).attr(
													'href');
											if (newcontent == "#members") {
												$
														.ajax({
															url : 'getEventMembers.do',
															type : 'post',
															dataType : 'json',
															data : $(
																	"#memberAjaxForm")
																	.serialize(),
															success : function(
																	data) {
																//alert(data.isEmpty);
																var resultCollenction = $("#collectionResults");
																//resultCollenction.find("form").empty();
																resultCollenction
																		.empty();
																//resultCollenction = $("#collectionResults");
																$
																		.each(
																				data.results,
																				function(
																						counts,
																						m) {
																					var f = "<form id='userFriendFormWithId" + m.userId + "' >"
																							+ "<input type='hidden' id='userFriendId' class='userFriendId' name='userFriendId' " +
                                                                        "value='" + m.userId + "'>"
																							+
																							"<input type='hidden' id='memberEventId' class='memberEventId' name='memberEventId'" +
                                                                        "value='" + m.eventId + "'>"
																							+
																							"<li class='collection-item avatar' id='userFriendCard" + m.userId + "'>"
																							+ "<img src='" + m.user.avatar.path + "' alt='' class='circle'> "
																							+ "<span class='title'><a href='userProfile.do?id="
																							+ m.userId
																							+ "'>"
																							+ m.user.firstName
																							+ " "
																							+ m.user.lastName
																							+ "</a></span>";
																					if (m.bedCount > 0) {
																						f += "<br>Can accept: "
																								+ m.bedCount
																								+ " guest(s)";
																					} else if (m.bedCount < 0) {
																						f += "<br>Need lodjing for: "
																								+ (-m.bedCount)
																								+ " person(s)";
																					}
					                                                                <c:if test="${isModerator}">
																					
					                                                                if(data.moderatorId != m.userId) {
					                                                                
					                                                                    f += "<br><span style='margin-right: 10px;'>Remove member</span>"
					                                                                    + "<a   class='btn-floating light-blue removefriend' data-id='" + m.userId + "'> "
					                                                                    + "<i class='mdi-navigation-close'></i>"
					                                                                    + "</a>";
					                                                                 }    
					                                                                    
					                                                                  </c:if>
																					
																					f += "<a  class='secondary-content'><i class='material-icons'>"
																							+ m.status
																							+ "</i></a>"
																							+
																							"</form>";
																					resultCollenction
																							.append(f);
																				});
															}
														});
											}
											$('#profiletabs ul li a')
													.removeClass('sel');
											$(this).addClass('sel');
											$('#content section').each(
													function() {
														if (!$(this).hasClass(
																'hidden')) {
															$(this).addClass(
																	'hidden');
														}
													});
											$(newcontent).removeClass('hidden');
										});
					});
				</script>
			</td>

		</tr>
	</table>
	<div id="confirmDeleteModal" class="modal" style="width: 25%">
		<div class="modal-content">
			<h4>Delete photo</h4>

			<p>Are you sure ?</p>
		</div>
		<div class="modal-footer">
			<a href="#_" id="modalDeletePhotoAnchor" data-value=""
				class=" modal-action modal-close waves-effect waves-green btn-flat">Yes,
				delete it</a>
		</div>
	</div>

</body>
</html>

