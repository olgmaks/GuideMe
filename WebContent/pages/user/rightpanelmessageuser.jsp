<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.table-wrapper-friend
{
    width: 100%;
    height: 500px;
    overflow: auto;
}

</style>
<div class="table-wrapper-friend">
<div class="row" style="width: 280px;">
	<div class="col s12" style="margin-top: 10px;">
		<ul class="collection z-depth-2 " id="collectionResults">
			<li class="collection-item" id="inner-row"><c:forEach
					var="userFriend" items="${userFriends}">

					<form id="userFriendFormWithId${userFriend.id}">
						<input type="hidden" id="userFriendId" class="userFriendId"
							name="userFriendId" value="${userFriend.id}">
					</form>
					<div class="card" style="height: 70px; width: 200px;"
						id="userFriendCard${userFriend.id}">
						<table>
							<tr>
								<td style="width: 50px;"><img class="circle"
									style="height: 50px; width: 50px; object-fit: cover"
									src="${userFriend.friend.avatar.path}"></td>
								<td>
									<div>
										<a href="#"
											onclick="getMessageByUser( ${userFriend.friend.id} ,'${userFriend.friend.lastName}','${userFriend.friend.firstName}','${userFriend.friend.avatar.path}'  ) "
											class="black-text">${userFriend.friend.firstName}
											${userFriend.friend.lastName} </a> <br> <span class="new badge"
											id="numberNewMessage${userFriend.friend.id}"> <c:set
												var="map" value="${numberNewMessage}" /> <c:set
												var="friendId" value="${userFriend.friendId}" /> <c:if
												test="${ empty numberNewMessage.get(friendId)}">
												<c:out value="0" />
											</c:if> <c:if test="${not empty numberNewMessage.get(friendId)}">
												<c:out value="${numberNewMessage.get(friendId)}" />
											</c:if>
										</span> 
									</div>
								</td>
							</tr>
						</table>
					</div>
				</c:forEach></li>
		</ul>
	</div>
</div>
</div>