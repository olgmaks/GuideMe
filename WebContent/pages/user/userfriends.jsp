<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col s12" style="margin-top:10px;">



        <ul class="collection z-depth-2 ">
            <li class="collection-item">
                User modification panel
            </li>
        </ul>




        <ul class="collection z-depth-2 ">
            <li class="collection-item">

            <c:forEach var="userFriend" items="${userFriends}">

            </c:forEach>

            </li>
        </ul>


    </div>
</div>
