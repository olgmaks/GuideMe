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
                <c:forEach var="userInEvent" items="${userInEvents}">
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" style="margin-top: 10px; vertical-align: top">
                            <table>
                                <tr>
                                    <td width="20%;">

                                        <img class="" style="height: 120px; width: 120px; object-fit: cover"
                                             src="${eventPhotosPathMap.get(userInEvent.eventId)}">
                                    </td>
                                    <td width="70%;">
                                        ${userInEvent.event.name}<br><br>
                                        ${userInEvent.event.description}<br><br>
                                        ${userInEvent.status}<br>
                                    </td>
                                    <td width="10%;">
                                        <a class="btn-floating btn-large waves-effect waves-light green"
                                           style="transform: scaleY(0.7) scaleX(0.7)"><i
                                                class="mdi-image-remove-red-eye"></i></a>
                                        <br><a class="btn-floating btn-large waves-effect waves-light red"
                                               style="transform: scaleY(0.7) scaleX(0.7)"><i
                                            class="mdi-content-remove"></i></a>
                                    </td>
                                </tr>
                            </table>
                        </li>
                    </ul>
                </c:forEach>

            </li>
        </ul>
    </div>
</div>