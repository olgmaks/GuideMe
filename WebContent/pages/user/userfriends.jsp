<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
    $(document).ready(function () {

        $(".acceptfriendrequest").click(function () {
            console.log("acceptfriendrequest ajax call");
            var value = $(this).data('id');
            var strSelector = "#userFriendCard" + value;
            var formSelector = "#userFriendFormWithId" + value;
            $.ajax({
                url: "acceptfriendrequest.do",
                type: "post",
                dataType: "json",
                data: $(formSelector).serialize()
            });
            console.log(strSelector);
            $(strSelector).remove();
        });

        $(".declinefriendrequest").click(function () {
            console.log("declinefriendrequest ajax call");
            var value = $(this).data('id');
            var strSelector = "#userFriendCard" + value;
            var formSelector = "#userFriendFormWithId" + value;
            $.ajax({
                url: "declinefriendrequest.do",
                type: "post",
                dataType: "json",
                data: $(formSelector).serialize()
            });
            console.log(strSelector);
            $(strSelector).remove();
        });

        $(".callbackfriendrequest").click(function () {
            console.log("call back request ajax call");
            var value = $(this).data('id');
            var strSelector = "#userFriendCard" + value;
            var formSelector = "#userFriendFormWithId" + value;
            $.ajax({
                url: "callbackfriendrequest.do",
                type: "post",
                dataType: "json",
                data: $(formSelector).serialize()
            });
            console.log(strSelector);
            $(strSelector).remove();
        });

//        $(document).on('click','.removefriend', function () {
//        $(".removefriend").click(function () {
//        $(".removefriend").on("click", function () {
//            console.log("removefriend ajax call");
//            var value = $(this).data('id');
//            var strSelector = "#userFriendCard" + value;
//            var formSelector = "#userFriendFormWithId" + value;
//            $.ajax({
//                url: "removefriend.do",
//                type: "post",
//                dataType: "json",
//                data: $(formSelector).serialize()
//            });
//            console.log(strSelector);
//            $(strSelector).remove();
//        });
        updateAnchors();


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

    function updateAnchors() {
        $(".removefriend").on("click", function () {
            
        	console.log("removefriend ajax call");
            var value = $(this).data('id');
            var strSelector = "#userFriendCard" + value;
            var formSelector = "#userFriendFormWithId" + value;
            $.ajax({
                url: "removefriend.do",
                type: "post",
                dataType: "json",
                data: $(formSelector).serialize()
            });
            console.log(strSelector);
            $(strSelector).remove();
        });
    }
</script>

<div class="row">
    <div class="col s12" style="margin-top:10px;">


        <ul class="collection z-depth-2">
            <li class="collection-item" style="height : 50px;">
                <table style="width: 700px;height:30px; margin-top:-12px;">
                    <tr>
                        <td><a class="black-text" href="userfriends.do">Friends</a></td>
                        <td><a class="black-text" href="userreceivedrequests.do">Incoming Requests</a></td>
                        <td><a class="black-text" href="usersentrequests.do">Sent Requests</a></td>
                        <td>
                            <input placeholder="Filter" name="friendfilter" id="friendfilter" />
                        </td>
                    </tr>
                </table>

            </li>
        </ul>

        <ul class="collection z-depth-2 " id="collectionResults">
            <li class="collection-item" id="inner-row">

                <c:forEach var="userFriend" items="${userFriends}">
                    <form id="userFriendFormWithId${userFriend.id}">
                        <input type="hidden" id="userFriendId" class="userFriendId" name="userFriendId"
                               value="${userFriend.id}">
                    </form>
                    <c:choose>
                        <c:when test="${userFriendRequestType=='incoming'}">
                            <div class="card" style="height: 150px; width: 330px; float: left; margin-left: 10px;"
                                 id="userFriendCard${userFriend.id}">
                                <table>
                                    <tr>
                                        <td style="width: 120px;">
                                            <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                                 src="${userFriend.user.avatar.path}">
                                        </td>
                                        <td>
                                            <div>
                                                <a href="#"
                                                   class="black-text">${userFriend.user.firstName} ${userFriend.user.lastName}</a>

                                                <div>
                                                    <br><a href="#_" id="acceptfriendrequest"
                                                           class="btn-floating light-blue acceptfriendrequest"
                                                           data-id="${userFriend.id}"><i
                                                        class="mdi-navigation-check"></i></a>
                                                    <a href="#_" id="declinefriendrequest"
                                                       class="btn-floating light-blue declinefriendrequest"
                                                       data-id="${userFriend.id}"><i
                                                            class="mdi-navigation-close"></i></a>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:when>

                        <c:when test="${userFriendRequestType=='sent'}">
                            <div class="card" style="height: 150px; width: 330px; float: left; margin-left: 10px;"
                                 id="userFriendCard${userFriend.id}">
                                <table>
                                    <tr>
                                        <td style="width: 120px;">
                                            <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                                 src="${userFriend.friend.avatar.path}">
                                        </td>
                                        <td>
                                            <div>
                                                <a href="#"
                                                   class="black-text">${userFriend.friend.firstName} ${userFriend.friend.lastName}</a>

                                                <div>
                                                    <br>
                                                    <br>

                                                    <div style="float: right; margin-right: 10px;">
                                                        <span style="margin-right: 10px;">Call Back</span>
                                                        <a href="#_"
                                                           class="btn-floating light-blue callbackfriendrequest"
                                                           data-id="${userFriend.id}">
                                                            <i class="mdi-navigation-close"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="card" style="height: 150px; width: 330px; float: left; margin-left: 10px;"
                                 id="userFriendCard${userFriend.id}">
                                <table>
                                    <tr>
                                        <td style="width: 120px;">
                                            <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                                 src="${userFriend.friend.avatar.path}">
                                        </td>
                                        <td>
                                            <div>
                                                <a href="#"
                                                   class="black-text">${userFriend.friend.firstName} ${userFriend.friend.lastName}</a>
                                                <br><br>

                                                <div style="float: right; margin-right: 10px;">
                                                    <span style="margin-right: 10px;">Remove friend</span>
                                                    <a href="#_" class="btn-floating light-blue removefriend"
                                                       data-id="${userFriend.id}">
                                                        <i class="mdi-navigation-close"></i>
                                                    </a>
                                                </div>
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
