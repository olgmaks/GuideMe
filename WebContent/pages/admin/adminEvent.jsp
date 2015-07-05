<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function() {
		$('#eventTable').DataTable();
	});
</script>
<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>
<div style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center;">
<table id="eventTable" class="display" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>ID</th>
			<th>moderator</th>
			<th>name</th>
			<th>description</th>
			
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${eventList}" var="list">
			<tr>
				<td>${list.id}</td>
				<td><a href="userProfile.do?id=${list.moderator.id}">	${list.moderator.lastName} ${list.moderator.firstName} </a></td>
				<td><a href="eventDetail.do?id=${list.id}">${list.name}</a></td>
				<td>${list.description}</td>
		
			</tr>
		</c:forEach>

	</tbody>
</table>
</div>
