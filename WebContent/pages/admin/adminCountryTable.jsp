<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	var addedit = 'add';
	var id;
	
	 $('form[name=saveForm]').submit(function(){
			alert(1)
			return false;
	    });
	$(document).ready(
			
			function() {
				var dTable = $('#countryTable').DataTable();
				$("#countryTable").on("click",".edit",
						function() {
							var tr = $(this).parents("tr");
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
<link rel="StyleSheet" href="css/dataTables.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/dataTables.js"></script>
<div
	style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
	<table id="countryTable" class="display" cellspacing="0" width="80%">
		<thead>
			<tr>
				<th hidden>ID</th>
				<c:forEach var="item" items="${languageList}">
					<th>${item.name}</th>
				</c:forEach>
				<th>edit</th>
				<th>delete</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="type" items="${countryLocal}">
				<tr>
					<td hidden id="tdId">${type.key.id}</td>
					<c:forEach var="lang" items="${type.value}">
						<td>${lang.name}</td>
					</c:forEach>
					<td>
						<button name="edit" id="edit" class="edit"
							style="border: 0; background: transparent">
							<img src="icons/edit.png"
								style="height: 20px; width: 20px; object-fit: cover" />
						</button>
					</td>
					<td>
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


	<form class="collection z-depth-2 " style="width: 50%; margin-right: 25%; margin-left: 25%; text-align: center;"
		action="admincountryrequest.do" method="post" name="saveForm">
		<input hidden name="action" id="action" value="add"/> 
		
		<table>
			<c:forEach var="item" items="${languageList}">
				<tr>
					<td>${item.name}</td>
				</tr>
				<tr>
				<td><input hidden name="id"	id="id" /></td>
					<td><input required type="text" name="langCountry${item.id}"
						id="langCountry${item.id}" />
					<td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Save" id="addbtn" />
	</form>
</div>



