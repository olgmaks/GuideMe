<!-- events where user moderator -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<table>
	<thead>
		<tr>
			<th data-field="id">Your own events. </th>

		</tr>
	</thead>
</table>

<br>
<div class="row">
	<c:forEach items="${listOfModeratorEvents }" var="moderatorevent">
		<div class="col s4 m3">
			<div class="card">
				<div class="card-image">
					<img style="height: 140px; width: 100%;"
						src="${moderatorevent.avatar.getPath() }">
				</div>
				<div class="card-content">
					<p>${moderatorevent.getName() }</p>
					<br> <span class="grey-text text-darken-1">Date from: </span>
					<p>${moderatorevent.getDateFrom() }</p>
					<br> <span class="grey-text text-darken-2">Date to: </span>
					<p>${moderatorevent.getDateTo() }</p>
				</div>
				<div class="card-action">
					<a href="eventDetail.do?id=${moderatorevent.getId() } ">Event
						Details</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>