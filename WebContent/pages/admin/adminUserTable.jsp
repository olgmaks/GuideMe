<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>
<style>
[type="checkbox"]:not(:checked), [type="checkbox"]:checked {
  position: relative;
  left: 0px;
  }
</style>

	<!-- begin Localization -->
	<jsp:include page="../localization.jsp"/>
	<ct:showLocale basename="locale.dataTable.dataTable"  from = "adminuserservlet.do" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.dataTable.dataTable">
	<!-- end Localization -->
	
<script>
	$(document).ready(function() {
		$('#userTable').DataTable({
	        "language": {
	            "lengthMenu":    '<fmt:message key="show" />',
	            "zeroRecords": "Nothing found - sorry",
	            "info": "<fmt:message key='info' />",
	            "infoEmpty": "No records available",
	            "infoFiltered": "(filtered from _MAX_ total records)",
	            "search":"<fmt:message key='search' />",
	            	"paginate": {
	                    "first":      "First",
	                    "last":       "Last",
	                    "next":       "<fmt:message key='next' />",
	                    "previous":   "<fmt:message key='previous' />"
	                },
	        }
	    } );

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
</fmt:bundle>

<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>

<!-- begin Localization -->
	<jsp:include page="../localization.jsp"/>
	<ct:showLocale basename="locale.admin.admin"  from = "adminuserservlet.do" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.admin.admin">
	<!-- end Localization -->
</fmt:bundle>


<fmt:bundle basename="locale.admin.admin">
	report for all user
	<form action="UserDetailReport" method="get">
		<button type="submit"
		style="border: 0; background: transparent">
							<img src="icons/report-user.png"
								style="height: 40px; width: 40px; object-fit: cover" /></button>
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
						<td><fmt:message key='user.${list.sex}' /></td>
						<td>${list.userType.name}</td>
					
						<td>${list.address.city.name}</td>
						<td width = "5%">
						<form action="UserDetailReport" method="post">
						<button type="submit"
							style="border: 0; background: transparent">
							<img src="icons/report-user.png"
								style="height: 40px; width: 40px; object-fit: cover" />
						</button>
						<input type="text" hidden name="userId" value="${list.id}">
						</form>
						</td>
						<td width="5%">
						<c:choose>
						<c:when test="${list.userType.name == 'admin'}">								
								
						</c:when>
						<c:otherwise>
							
								<input type="checkbox" name="isActive" class="isActive"
									${list.isActive == 'true' ? 'checked' : ''}>
							
						</c:otherwise>
					</c:choose>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</fmt:bundle>
