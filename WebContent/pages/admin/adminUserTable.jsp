<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
[type="checkbox"]:not(:checked), [type="checkbox"]:checked {
  position: relative;
  left: 0px;
  }
</style>
<script>
	$(document).ready(function() {
		$('#userTable').DataTable();
		
		$("#userTable").on("click",".isActive",function(){
			var tr = $(this).parents("tr");
			id = tr.find("td:eq(0)").html();
			$.ajax({
                url: "adminUserRequest.do?action=active&id=" + id,
                type: "post",
                success: function () {
                }
            });
		});
	});
</script>
<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>
<form action="UserDetailReport" method="get">
<button type = "submit">
report
</button>
</form>
<div style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center;">
<table id="userTable" class="display" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th hidden>ID</th>
			<th>LastName</th>
			<th >FirstName </th>
			<th>email</th>
			<th>sex</th>
			<th>type</th>
			<th>is active</th>
			<th>city</th>
			<th>active</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${userList}" var="list">
			<tr>
				<td hidden>${list.id}</td>
				<td><a href="userProfile.do?id=${list.id}">	${list.lastName} </a></td>
				<td >${list.firstName}</td>
				<td>${list.email} </td>
				<td>${list.sex} </td>
				<td>${list.userType.name} </td>
				<td>${list.isActive} </td>
				<td>${list.address.city.name} </td>
				<td>
					<p>
						<input type="checkbox" name="isActive" class = "isActive"   ${list.isActive == 'true' ? 'checked' : ''}>
					</p>
				</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
</div>
