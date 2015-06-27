<!-- events where user moderator -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table>
	<thead>
		<tr>
			<th data-field="id">Your own events. <a href="#"
				class="indigo-text text-darken-4">More...</a></th>

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
					<p>Data from:</p>
					<p>To</p>
				</div>
				<div class="card-action">
					<a href="#">This is a link</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>