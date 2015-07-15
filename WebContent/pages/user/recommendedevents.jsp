<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" style="width: 280px;">
  <div class="col s12" style="margin-top:10px;">
    <ul class="collection z-depth-2 ">
      <li class="collection-item">
        Recommended events
      </li>
    </ul>
    <ul class="collection z-depth-2 ">
      <li class="collection-item">

        <c:forEach var="recommendedEvent" items="${recommendedEvents}" begin="0" end="9">

          <%--<span>${recommendedFriend.firstName} ${recommendedFriend.lastName}</span>--%>

          <div class="card" style="height: 60px; width: 200px; float: left; margin-left: 10px;"
               id="userFriendCard${recommendedEvent.id}">
            <table>
              <tr>
                <td style="width: 40px;"><a href="eventDetail.do?id=${recommendedEvent.id}">
                  <img class="circle" style="margin-top:-5px;height: 40px; width: 40px; object-fit: cover"
                       src="${recommendedEvent.avatar.path}"></a>
                </td>
                <td>
                  <div>
                    <a href="eventDetail.do?id=${recommendedEvent.id}"
                       class="black-text">${recommendedEvent.name}</a>
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
