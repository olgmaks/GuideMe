<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>
<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.dataTable.dataTable"
	from="adminTag.do" />
<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.dataTable.dataTable">
	<!-- end Localization -->
	<script>
		var id;
		var nameValid;

		$(document)
				.ready(
						function() {
							var dTable = $('#tagTable')
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

							$("#tagTable").on("click", ".edit", function() {
								var tr = $(this).parents("tr");
								$("#saveForm").css("visibility","visible");
								$('#name').val(tr.find("td:eq(1)").html());
								$('#id').val(tr.find("td:eq(0)").html());
								$('#action').val('edit');
							});

							$("#tagTable")
									.on(
											"click",
											".delete",
											function() {
												var tr = $(this).parents("tr");
												id = tr.find("td:eq(0)").html();
												$.ajax({
															url : "adminTagRequest.do?action=delete&id="
																	+ id,
															type : "post",
															success : function() {
															}
														});
												location.reload();
											});

							$("#name").change(
											function() {
												if (!this.value) {
													nameValid = false
													$('#namefalse').show();
													$('#nameOk').hide();
												}
												$.ajax({
															url : "adminTagRequest.do?action=isPresentName&name="
																	+ $('#name')
																			.val(),
															type : "post",
															async : false,
															success : function(
																	data) {
																if ((!data)) {
																	nameValid = true;
																	$('#namefalse').hide();
																	$('#nameOk').show();
																} else {
																	nameValid = false;
																	$(
																			'#namefalse')
																			.show();
																	$('#nameOk')
																			.hide();
																}

															}
														});
											});

							$('form[name=saveForm]').submit(function() {
								if (!nameValid)
									return false;
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
		style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
		<table id="tagTable" class="display" cellspacing="0" width="80%">
			<thead>
				<tr>
					<th hidden>ID</th>
					<th><fmt:message key="global.name" /></th>
					<th width="5%"></th>
					<th width="5%"></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${tagList}" var="list">
					<tr>
						<td hidden id="tdId">${list.id}</td>
						<td id="tdName"><c:out value="${list.name}" /></td>
						<td width="5%">
							<button name="edit" id="edit" class="edit"
								style="border: 0; background: transparent">
								<img src="icons/edit.png"
									style="height: 20px; width: 20px; object-fit: cover" />
							</button>
						</td>
						<td width="5%">
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
	
	<button name="add" id="add" class="add"
							style="border: 0; background: transparent">
							<img src="icons/add.jpg"
								style="height: 40px; width: 40px; object-fit: cover" />
						</button>
	<script>
		$("#add").on('click', function () {
			$("#saveForm").css("visibility","visible");
			$("#action").val('add');
		});
	</script>
	<form class="collection z-depth-2 " style="width: 50%; margin-right: 25%; margin-left: 25%; text-align: center; visibility: hidden"
		action="adminTagRequest.do" method="post" name="saveForm" id = "saveForm">
		<input hidden name="action" id="action" value="add"/> 
		<input hidden type="text" name="id" id="id">
		<div align="right">
		<button name="close" id="close" class="close"
							style="border: 0; background: transparent" onclick ='$("#saveForm").css("visibility","hidden")'>
							<img src="icons/delete-photo-icon.png"
								style="height: 20px; width: 20px; object-fit: cover"/>
		</button>
		</div>
			<table>
				<tr>
					<td width="50%"><input required type="text" name="name"
						id="name" /></td>
					<td width="10%"><a hidden href="" id="nameOk"><img
							src="img/ok.ico" id="contentImage" border="0" width="50px"
							height="50px"></a> <a hidden href="" id="namefalse"><img
							src="img/error.jpg" id="contentImage" border="0" width="50px"
							height="50px"></a></td>
			</table>
			<input type="submit" value="<fmt:message key="global.save" />" id="save">
		</form>
	</div>
</fmt:bundle>

