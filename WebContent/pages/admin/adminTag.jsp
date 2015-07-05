<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
var addedit='add';
var id;
	$(document).ready(function() {

		var dTable = $('#tagTable').DataTable();
		$('#addbtn').on('click', function() {
			 $.ajax({
	                url: "adminTagRequest.do?action=" + addedit + "&name=" + $('#name').val() +"&id=" + id,
	                type: "post",
	                success: function () {
	                }
	            });
				
			if(addedit =='edit'){
				location.reload();	
			}
			else{
				location.reload();
				//dTable.row.add([$('#name').val(),'<button id ="edit">edit</button>' ]).draw();
			} 
			addedit = 'add';
		});

		$("#tagTable").on("click",".edit",function(){
			addedit = 'edit';
			var tr = $(this).parents("tr");
			$('#name').val(tr.find("td:eq(1)").html());
			id = tr.find("td:eq(0)").html();
		});
		$("#tagTable").on("click",".delete",function(){
			var tr = $(this).parents("tr");
			id = tr.find("td:eq(0)").html();
			$.ajax({
                url: "adminTagRequest.do?action=delete&id=" + id,
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
	<table id="tagTable" class="display" cellspacing="0" width="80%">
		<thead>
			<tr>
				<th hidden>ID</th>
				<th>Name</th>
				<th>edit</th>
				<th>delete</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${tagList}" var="list">
				<tr>
					<td hidden id = "tdId" >${list.id}</td>
					<td id = "tdName">${list.name}</td>
					<td><button name ="edit" id = "edit" class ="edit">Edit</button></td>
					<td><button name ="delete" id = "delete" class ="delete">delete</button></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<div style="width: 50%; margin-left: 20%">
		Name: <br />
		<input  type="text" name="name" id="name" /> <br />
	</div>
	<input type="button" value="Save" id="addbtn" />
</div>

