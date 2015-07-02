<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function() {
		$('#countryTable').DataTable();
	});
</script>
<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>
<div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
<table id="countryTable" class="display" cellspacing="0" width="80%">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${countryList}" var="list">
			<tr>
				<td>${list.id}</td>
				<td>${list.name}</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
</div>
