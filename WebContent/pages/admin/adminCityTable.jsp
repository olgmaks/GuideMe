<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
var addedit='add';
var id;
	$(document).ready(function() {
		var dTable = $('#cityTable').DataTable();
		$("#cityTable").on("click",".edit",function(){
			$('#action').val('edit');
			var tr = $(this).parents("tr");
			   <c:forEach var="item" items="${languageList}" varStatus="loop">
					$('#langCity${item.id}').val(tr.find("td:eq(${loop.index+1})").html());
			   </c:forEach>
			$('#action').val(tr.find("td:eq(0)").html());
			$('#action').val('add');
		});
		
		$("#cityTable").on("click",".delete",function(){
			var tr = $(this).parents("tr");
			id = tr.find("td:eq(0)").html();
			$.ajax({
                url: "adminCityRequest.do?action=delete&id=" + id,
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
	<table id="cityTable" class="display" cellspacing="0" width="80%">
		<thead>
			<tr>
				<th hidden>ID</th>
				<c:forEach var="item" items="${languageList}">
		            <th>${item.name}</th>
		            <th>country</th>
		          </c:forEach>
				<th>edit</th>
				<th>delete</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="type" items="${cityLocal}">
				<tr>
					<td hidden id = "tdId" >${type.key.id}</td>
					<c:forEach var="lang" items="${type.value}">
						<td>${lang.name}</td>
						<td>${lang.country.name}</td>
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
	<form  action="admincityrequest.do" method="post">
		<input hidden name="action" id = "action" /> 
		<input hidden name="id"  id = "id"/> 
	     
	      <c:forEach var="item" items="${langCountry}">
	      	<table>
	      		<tr>
		      		<td>
		      			${item.key.name}
		      		</td>
	      		</tr>
		      	<tr>
			      	<td>				      	
		         		<input required  type="text" name="langCountry${item.key.id}" id="langCity${item.key.id}" /> 
			      	</td>
			      	<td>
			      		<select id="country" name="country" class="browser-default">
			      			<c:forEach items="${item.value}" var="countries">
						       <option value="${countries.id}">${countries.name}</option>
						   </c:forEach>
			      		</select>
			      	</td>
		      	</tr>
	      	</table>
	      </c:forEach>
	      
	      
		<br/>
	<input type="submit" value="Save" id="addbtn" />
	</form>
	</div>
</div>
