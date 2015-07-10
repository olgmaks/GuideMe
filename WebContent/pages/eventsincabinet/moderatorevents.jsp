<!-- events where user moderator -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<br>
<table>
	<thead>
		<tr>
			<th data-field="id">Your own events.</th>

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
					<p>${moderatorevent.getCutName(25) }</p>
					<br> <span class="grey-text text-darken-1">Date from: </span>
					<p>
						<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
							value="${moderatorevent.getDateFrom()}" />
					</p>
					<br> <span class="grey-text text-darken-2">Date to: </span>
					<p>
						<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
							value="${moderatorevent.dateTo}" />
					</p>
				</div>
				<div class="card-action">
					<a href="eventDetail.do?id=${moderatorevent.getId() } ">Event
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
	<c:forEach items="${listOfOldModeratorEvents }" var="moderatorevent">
		<div class="col s4 m3">
			<div class="card">
				<div class="card-image">
					<img style="height: 140px; width: 100%;"
						src="${moderatorevent.avatar.getPath() }">
				</div>
				<div class="card-content">
					<p>${moderatorevent.getCutName(25) }</p>
					<br> <span class="grey-text text-darken-1">Date from: </span>
					<p>
						<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
							value="${moderatorevent.getDateFrom()}" />
					</p>
					<br> <span class="grey-text text-darken-2">Date to: </span>
					<p>
						<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
							value="${moderatorevent.dateTo}" />
					</p>
				</div>
				<div class="card-action">
					<a href="eventDetail.do?id=${moderatorevent.getId() } ">Event
						Details</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>