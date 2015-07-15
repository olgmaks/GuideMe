<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>
<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.dataTable.dataTable"
	from="messageAdmin.do" />
<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.dataTable.dataTable">
	<!-- end Localization -->
	<script>
		var id;
		var nameValid;

		$(document)
				.ready(
						function() {
							var dTable = $('#messageTable')
									.DataTable(
											{
												"language" : {
													"lengthMenu" : '<fmt:message key="show" />',
													"zeroRecords" : "Nothing found - sorry",
													"info" : "<fmt:message key='info' />",
													"infoEmpty" : "No records available",
													"infoFiltered" : "(filtered from _MAX_ total records)",
													"search" : "<fmt:message key='search' />",
													"paginate" : {
														"first" : "First",
														"last" : "Last",
														"next" : "<fmt:message key='next' />",
														"previous" : "<fmt:message key='previous' />"
													},
												}
											});

						});
	</script>
</fmt:bundle>
<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>

<fmt:bundle basename="locale.admin.admin">
	<!-- end Localization -->

	<div
		style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center;">
		<table id="messageTable" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th hidden>ID</th>
					<th><fmt:message key="user.user" /></th>
					<th><fmt:message key="message.message" /></th>
					<th><fmt:message key="message.date" /></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${messageList}" var="list">
					<tr>
						<td hidden id="tdId">${list.id}</td>
						<td>
							<a href="userProfile.do?id=${list.sender.id}"> ${list.sender.lastName} ${list.sender.firstName}	 </a>
						</td>
						<td id="tdName"><c:out value="${list.message}" /></td>
						<td id="tdName"><c:out value="${list.createdOn}" /></td>
						
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</fmt:bundle>