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
	var addedit = 'add';
	var id;
	
	 $('form[name=saveForm]').submit(function(){
			alert(1)
			return false;
	    });
	$(document).ready(
			
			function() {
				var dTable = $('#countryTable').DataTable(
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
				
				$("#countryTable").on("click",".edit",
						function() {
							var tr = $(this).parents("tr");
							$("#saveForm").css("visibility","visible");
							<c:forEach var="item" items="${languageList}" varStatus="loop">
								$('#langCountry${item.id}').val(tr.find("td:eq(${loop.index+1})").html());
								$('#id').val(tr.find("td:eq(0)").html());
								$('#action').val('edit');
							</c:forEach>
						
						});

				$("#countryTable").on("click", ".delete", function() {
					var tr = $(this).parents("tr");
					id = tr.find("td:eq(0)").html();
					$.ajax({
						url : "admincountryrequest.do?action=delete&id=" + id,
						type : "post",
						success : function() {
						}
					});
					location.reload();
				});
			});
</script>
</fmt:bundle>
<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>
<fmt:bundle basename="locale.admin.admin">
<div
	style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
	<table id="countryTable" class="display" cellspacing="0" width="80%">
		<thead>
			<tr>
				<th hidden>ID</th>
				<c:forEach var="item" items="${languageList}">
					<th><fmt:message key="country.country" />(${item.key})</th>
				</c:forEach>
				<th ></th>
				<th ></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="type" items="${countryLocal}">
				<tr>
					<td hidden id="tdId">${type.key.id}</td>
					<c:forEach var="lang" items="${type.value}">
						<td>${lang.name}</td>
					</c:forEach>
					<td width = "5%">
						<button name="edit" id="edit" class="edit"
							style="border: 0; background: transparent">
							<img src="icons/edit.png"
								style="height: 20px; width: 20px; object-fit: cover" />
						</button>
					</td>
					<td width = "5%">
						<button name="delete" id="delete" class="delete"
							style="border: 0; background: transparent">
							<img src="icons/delete-photo-icon.png"
								style="height: 20px; width: 20px; object-fit: cover" />
						</button>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

	<input type="submit" value="add" id="add" />
	<script>
		$("#add").on('click', function () {
			$("#saveForm").css("visibility","visible");
			$("#action").val('add');
		});
	</script>

	<form class="collection z-depth-2 " style="width: 50%; margin-right: 25%; margin-left: 25%; text-align: center; visibility: hidden"
		action="admincountryrequest.do" method="post" name="saveForm" id = "saveForm">
		<input hidden name="action" id="action" value="add"/> 
		
		<table>
			<c:forEach var="item" items="${languageList}">
				<input hidden name="id"	id="id" />
				<tr>
					<td>${item.name}</td>
				</tr>
				<tr>
					<td><input required type="text" name="langCountry${item.id}"
						id="langCountry${item.id}" />
					<td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="<fmt:message key="global.save" />" id="addbtn" />
	</form>
</div>
</fmt:bundle>


