<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>

<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.dataTable.dataTable"
	from="admin.do" />
<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.dataTable.dataTable">
	<!-- end Localization -->
	<script>
		$(document).ready(function() {
			$('#eventTable').DataTable(
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
<div style="width: 90%; margin-right: 5%; margin-left: 5%; text-align: center;">
<table id="eventTable" class="display" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th hidden>ID</th>
			<th><fmt:message key="event.moderator" /></th>
			<th><fmt:message key="global.name" /></th>
			<th><fmt:message key="event.description" /></th>
			<th><fmt:message key="event.dateFrom" /></th>
			<th><fmt:message key="event.dateTo" /></th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${eventList}" var="list">
			<tr>
				<td hidden>${list.id}</td>
				<td><a href="userProfile.do?id=${list.moderator.id}"> ${list.moderator.lastName} ${list.moderator.firstName}	 </a></td>
				<td><a href="eventDetail.do?id=${list.id}"><c:out value="${list.name}"/></a></td>
				
				<td><c:out value="${list.description}"/></td>
				<td><fmt:formatDate type="both" 
            		dateStyle="short" timeStyle="short" 
            		value="${list.dateFrom}" />
            	</td>
				<td><fmt:formatDate type="both" 
            		dateStyle="short" timeStyle="short" 
            		value="${list.dateTo}" />
            	</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
</div>
</fmt:bundle>
