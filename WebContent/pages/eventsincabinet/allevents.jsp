<!-- events in cabinet page -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<table>
	<thead>
		<tr>
			<th data-field="id">Your own events. <a href="moderatoEvents.do"
				class="indigo-text text-darken-4">More...</a></th>

		</tr>
	</thead>
</table>

<br>
<div class="row">
	<c:forEach items="${listOfModeratorEvents }" begin="0" end="3"
		var="moderatorevent">
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
<table>
	<thead>
		<tr>
			<th data-field="id">Events where you resident. <a href="#"
				class="indigo-text text-darken-4">More...</a></th>

		</tr>
	</thead>
</table>

<div class="row">

	<div class="col s4 m3">
		<div class="card">
			<div class="card-image">
				<img
					src="http://hdwallpaperd.com/wp-content/uploads/new-wallpaper-16.jpg">
				<span class="card-title">Card Title</span>
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
</div>