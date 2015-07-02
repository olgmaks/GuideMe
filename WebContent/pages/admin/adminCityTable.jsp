<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function() {
		$('#cityTable').DataTable({
		  "order": [[ 2, "asc" ]]
		});
	});
</script>
<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>
<div style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center;">
<table id="cityTable" class="display" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th hidden>pureID </th>
			<th>country</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${cityList}" var="list">
			<tr>
				<td>${list.id}</td>
				<td>${list.name}</td>
				<td hidden>${list.pureId }</td>
				<td>${list.country.name} </td>
			</tr>
		</c:forEach>

	</tbody>
</table>
</div>
