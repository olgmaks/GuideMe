<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
var addedit='add';
var id;
	$(document).ready(function() {
		var dTable = $('#languageTable').DataTable();
		$("#languageTable").on("click",".edit",function(){
			$('#action').val('edit');
			var tr = $(this).parents("tr");
			   <c:forEach var="item" items="${languageList}" varStatus="loop">
					$('#langCountry${item.id}').val(tr.find("td:eq(${loop.index+1})").html());
			   </c:forEach>
			$('#action').val(tr.find("td:eq(0)").html());
			$('#action').val('add');
		});
		
		$("#languageTable").on("click",".delete",function(){
			var tr = $(this).parents("tr");
			id = tr.find("td:eq(0)").html();
			$.ajax({
                url: "adminCountryRequest.do?action=delete&id=" + id,
                type: "post",
                success: function () {
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
	<table id="languageTable" class="display" cellspacing="0" width="80%">
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
					<td hidden id = "tdId" >${type.key.id}</td>
					<c:forEach var="lang" items="${type.value}">
						<td>${lang.name}</td>
					</c:forEach>
					<td>
						<button name ="edit" id = "edit" class ="edit">
							<img src="icons/edit.jpg" style="height: 20px; width: 20px; object-fit: cover" />
						</button>
					</td>
					<td>
						<button name ="delete" id = "delete" class ="delete">
							<img src="icons/button-delete.ico" style="height: 20px; width: 20px; object-fit: cover" />
						</button>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	
	<div style="width: 50%; margin-left: 25% margin-right: 25% text-align: center;">
	<form  action="adminlanuagerequest.do" method="post">
		<input hidden name="action" id = "action" /> 
		<input hidden name="id"  id = "id"/> 
	      <c:forEach var="item" items="${languageList}">
         	<label> ${item.name} </label>
         	<input required  type="text" name="langCountry${item.id }" id="langCountry${item.id}" /> 
          </c:forEach>
		<br/>
	<input type="submit" value="Save" id="addbtn" />
	</form>
	</div>
</div>
