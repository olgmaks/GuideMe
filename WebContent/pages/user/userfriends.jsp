<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="row">
    <div class="col s12" style="margin-top:10px;">


        <ul class="collection z-depth-2">
            <li class="collection-item">
                <table style="width: 350px;">
                    <tr>
                        <td><a class="black-text" href="userfriends.do" >Friends</a></td>
                        <td><a class="black-text" href="userreceivedrequests.do">Incoming Requests</a></td>
                        <td><a class="black-text" href="usersentrequests.do">Sent Requests</a></td>
                    </tr>
                </table>
            </li>
        </ul>

        <ul class="collection z-depth-2 ">
            <li class="collection-item">

                <c:forEach var="userFriend" items="${userFriends}">

                    <c:choose>
                        <c:when test="${userFriendRequestType=='incoming'}">
                            <div class="card" style="height: 150px; width: 330px; float: left; margin-left: 10px;">
                                <table>
                                    <tr>
                                        <td style="width: 120px;">
                                            <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                                 src="${userFriend.user.avatar.path}">
                                        </td>
                                        <td>
                                            <div>
                                                <a href="#" class="black-text">${userFriend.user.firstName} ${userFriend.user.lastName}</a>
                                                <div>
                                                <br><a href="#" class="btn-floating light-blue"><i class="mdi-navigation-check"></i></a>
                                                    <a href="#" class="btn-floating light-blue"><i class="mdi-navigation-close"></i></a>
                                                <div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:when>

                        <c:when test="${userFriendRequestType=='sent'}">
                            <div class="card" style="height: 150px; width: 330px; float: left; margin-left: 10px;">
                                <table>
                                    <tr>
                                        <td style="width: 120px;">
                                            <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                                 src="${userFriend.friend.avatar.path}">
                                        </td>
                                        <td>
                                            <div>
                                                <a href="#" class="black-text">${userFriend.friend.firstName} ${userFriend.friend.lastName}</a>
                                                <div>
                                                    <br><a href="#" class="btn-floating light-blue"><i class="mdi-navigation-close"></i></a><label class="large">  Call back</label>
                                                    <div>
                                             </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="card" style="height: 150px; width: 330px; float: left; margin-left: 10px;">
                                <table>
                                    <tr>
                                        <td style="width: 120px;">
                                            <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                                 src="${userFriend.friend.avatar.path}">
                                        </td>
                                        <td>
                                            <div>
                                                <a href="#">${userFriend.friend.firstName} ${userFriend.friend.lastName}</a>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </li>
        </ul>


    </div>
</div>
