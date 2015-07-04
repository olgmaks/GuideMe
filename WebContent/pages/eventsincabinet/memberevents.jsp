<!-- events where user member -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table>
	<thead>
		<tr>
			<th data-field="id">Events where you member. Actual</th>

		</tr>
	</thead>
</table>

<div class="row">
	<c:forEach items="${listOfUserInEvent }" var="usersevent">

		<div class="col s4 m3">
			<div class="card">
				<div class="card-image">
					<img style="height: 140px; width: 100%;"
						src="${usersevent.getEvent().avatar.getPath() }">
				</div>
				<div class="card-content">
					<p>${usersevent.getEvent().getCutName(25) }</p>
					<br> <span class="grey-text text-darken-1">Date from: </span>
					<p><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
							value="${usersevent.getEvent().getDateFrom()}" /></p>
					<br> <span class="grey-text text-darken-2">Date to: </span>
					<p><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
							value="${usersevent.getEvent().getDateTo()}" /></p>
				</div>
				<div class="card-action">
					<a href="eventDetail.do?id=${usersevent.getEvent().getId() } ">Event
						Details</a>
				</div>
			</div>
		</div>

	</c:forEach>
</div>
<table>
	<thead>
		<tr>
			<th data-field="id">Old</th>
		</tr>
	</thead>
</table>
<div class="row">
	<c:forEach items="${listOfOldUserInEvent }" var="usersevent">

		<div class="col s4 m3">
			<div class="card">
				<div class="card-image">
					<img class="square"
						style="height: 140px; width: 100%; object-fit: cover"
						src="${usersevent.getEvent().avatar.getPath() }">
				</div>
				<div class="card-content">
					<p>${usersevent.getEvent().getCutName(25) }</p>
					<br> <span class="grey-text text-darken-1">Date from: </span>
					<p><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
							value="${usersevent.getEvent().getDateFrom()}" /></p>
					<br> <span class="grey-text text-darken-2">Date to: </span>
					<p><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
							value="${usersevent.getEvent().getDateTo()}" /></p>
				</div>
				<div class="card-action">
					<a href="eventDetail.do?id=${usersevent.getEvent().getId() } ">Event
						Details</a>
				</div>
			</div>
		</div>

	</c:forEach>
</div>