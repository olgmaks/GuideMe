<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>
<style>
[type="checkbox"]:not (:checked ), [type="checkbox"]:checked {
	position: relative;
	left: 0px;
}
</style>
<script>
	$(document).ready(function() {
		$('#userTable').DataTable();

		$("#userTable").on("click", ".isActive", function() {
			var tr = $(this).parents("tr");
			id = tr.find("td:eq(0)").html();
			$.ajax({
				url : "adminUserRequest.do?action=active&id=" + id,
				type : "post",
				success : function() {
				}
			});
		});
	});
</script>
<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>
<fmt:bundle basename="locale.admin.admin">
	<form action="UserDetailReport" method="get">
		<button type="submit">report</button>
	</form>
	<div
		style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center;">
		<table id="userTable" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th hidden>ID</th>
					<th><fmt:message key="user.lastName" /></th>
					<th><fmt:message key="user.firstName" /></th>
					<th><fmt:message key="user.email" /></th>
					<th><fmt:message key="user.sex" /></th>
					<th><fmt:message key="user.type" /></th>
					<th><fmt:message key="user.active" /></th>
					<th><fmt:message key="user.city" /></th>
					<th>report</th>
					<th width="5%">active</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${userList}" var="list">
					<tr>
						<td hidden>${list.id}</td>
						<td><a href="userProfile.do?id=${list.id}">
								${list.lastName} </a></td>
						<td>${list.firstName}</td>
						<td>${list.email}</td>
						<td>${list.sex}</td>
						<td>${list.userType.name}</td>
						<td>${list.isActive}</td>
						<td>${list.address.city.name}</td>
						<td>
							<form action="UserDetailReport" method="post">
								<button type="submit">report</button>
								<input type="text" hidden name="userId" value="${list.id}">
							</form>
						</td>
						<td width="5%">
							<p>
								<input type="checkbox" name="isActive" class="isActive"
									${list.isActive == 'true' ? 'checked' : ''}>
							</p>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</fmt:bundle>
