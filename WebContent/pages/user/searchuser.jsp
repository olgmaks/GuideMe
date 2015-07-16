<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>

	<!-- begin Localization -->
	<jsp:include page="../localization.jsp"/>
	<ct:showLocale basename="locale.searchuser.messages" from = "searchuser.do" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.searchuser.messages">
	<!-- end Localization -->
	

<script src="js/search-user-filter.js"></script>

<div class="row">
    <div class="col s12" style="margin-top:10px;">
        <ul class="collection z-depth-2"  style="height : 50px;">
            <%--<li class="collection-item" style="height : 50px;">--%>
            <%--<table style="width: 700px;height:30px; margin-top:-12px;">--%>
            <li class="collection-item" >
                <table>
                    <tr>

                        <%--<td><a class="black-text" href="userfriends.do">Friends</a></td>--%>
                        <%--<td><a class="black-text" href="userreceivedrequests.do">Incoming Requests</a></td>--%>
                        <%--<td><a class="black-text" href="usersentrequests.do">Sent Requests</a></td>--%>
                        <td>
                            <div class="input-field" style="margin-top:-15px;">
                                <input style="height: 30px;" placeholder="<fmt:message key="searchuser.userNameFilter" />" name="userNameInput"
                                       id="userNameInput"/>
                                <span style="float: right;margin-top: 3px;" > <fmt:message key="searchuser.Results" />: <span id ="resultsQuantity">${users.size()}</span></span>
                            </div>
                            <%--<input  class="browser-default" placeholder="Filter" name="friendfilter" id="friendfilter"/>--%>
                        </td>
                        <td>

                        </td>

                    </tr>

                </table>
            </li>
        </ul>

        <ul class="collection z-depth-2 " id="collectionResults">
            <li class="collection-item" id="inner-row">


                <c:forEach var="user" items="${users}">
                    <div class='card' id = 'card-${user.id}'style='height: 150px; width: 47%;min-width: 300px; max-width:350px; float: left; margin-left: 10px;'>
                        <table>
                            <tr>
                                <td style='width: 120px; vertical-align: top;'><a href="userProfile.do?id=${user.id}">
                                    <img class='circle' style='height: 120px; width: 120px; object-fit: cover' src='${user.avatar.path}'> </a></td>

                                <td>
                                    <div>
                                        <div style="height: 40px;"><a href="userProfile.do?id=${user.id}"
                                                                      class='black-text'>${user.firstName} ${user.lastName}</a>
                                        </div>
                                        <div style="height: 20px;"> <span> <fmt:message key="searchuser.Rate"/>: ${user.points}</span>
                                        </div>
                                        <div style="height: 20px;"><span>${user.address.city.name}</span></div>
                                        <div style="float: right; vertical-align: bottom; margin-bottom: 10px; margin-right: 10px;">
                                            <a href='#_' id='sendFriendRequestId${user.id}' data-userid='${user.id}' class='btn blue send-friend-request waves-effect waves-light {3}'>
                                                <fmt:message key="searchuser.ADD" />  </a>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    </c:forEach>


            </li>
        </ul>
    </div>
</div>

</fmt:bundle>