<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col s12" style="margin-top:10px;">
        <ul class="collection z-depth-2">
            <%--<li class="collection-item" style="height : 50px;">--%>
            <%--<table style="width: 700px;height:30px; margin-top:-12px;">--%>
            <li class="collection-item">
                <table>
                    <tr>

                        <%--<td><a class="black-text" href="userfriends.do">Friends</a></td>--%>
                        <%--<td><a class="black-text" href="userreceivedrequests.do">Incoming Requests</a></td>--%>
                        <%--<td><a class="black-text" href="usersentrequests.do">Sent Requests</a></td>--%>
                        <td>
                            <div class="input-field">
                                <input style="height: 30px;" placeholder="User name" name="userNameInput"
                                       id="userNameInput"/>
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
                    <form id="userFriendFormWithId${user.id}">
                        <input type="hidden" id="userFriendId" class="userFriendId" name="userFriendId"
                               value="${user.id}"></form>
                    <span>${user.id} ${user.firstName} ${user.lastName}</span>

                </c:forEach>


            </li>
        </ul>
    </div>
</div>