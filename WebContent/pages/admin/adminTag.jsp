<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	var id;
	var nameValid;
	
	$(document).ready(
			  function() {
				var dTable = $('#tagTable').DataTable();
		
				$("#tagTable").on("click", ".edit", function() {
					var tr = $(this).parents("tr");
					$('#name').val(tr.find("td:eq(1)").html());
					$('#id').val(tr.find("td:eq(0)").html());
					$('#action').val('edit');
				});
				
				$("#tagTable").on("click", ".delete", function() {
					var tr = $(this).parents("tr");
					id = tr.find("td:eq(0)").html();
					$.ajax({
						url : "adminTagRequest.do?action=delete&id=" + id,
						type : "post",
						success : function() {
						}
					});
					location.reload();
				});
				
				$("#name").change(function(){
					if (!this.value){
						 nameValid = false
						 $('#namefalse').show();
		        		 $('#nameOk').hide();
					}
					$.ajax({
		                url: "adminTagRequest.do?action=isPresentName&name=" + $('#name').val(),
		                type: "post",
		                async: false,
		                success: function (data) {
		                	if((!data)){
		                		nameValid = true;
		                		 $('#namefalse').hide();
		                		 $('#nameOk').show();
		                	}else{
		                		 nameValid = false;
		                		 $('#namefalse').show();
		                		 $('#nameOk').hide();
		                	}
		   	          
		                }
		            });
				});
					
				 $('form[name=saveForm]').submit(function(){
						if(!nameValid)
				        	return false;
				    });
				 
				  
			});
	$(document).ready(function() {
	    // Select - Single
	    $('select:not([multiple])').material_select();
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
				<th width = "5%"></th>
				<th width = "5%"></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${tagList}" var="list">
				<tr>
					<td hidden id="tdId">${list.id}</td>
					<td id="tdName"><c:out value="${list.name}"/></td>
					<td width = "5%">
						<button name="edit" id="edit" class="edit"
							style="border: 0; background: transparent">
							<img src="icons/edit.png"
								style="height: 20px; width: 20px; object-fit: cover" />
						</button>
					</td>
					<td width = "5%">
						<button  name="delete" id="delete" class="delete"
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
		action="adminTagRequest.do" method="post" name="saveForm">
		<input hidden type="text" name="action" id="action" value="add">
		<input hidden type="text" name="id" id="id">
			
			<table>
			<tr>
			<td width = "50%">
				<input required  type="text" name="name" id="name" />
			</td>
			<td  width = "10%">
				<a hidden  href="" id = "nameOk"><img src="img/ok.ico" id="contentImage" border="0" width="50px" height="50px"></a>
				<a hidden  href="" id = "namefalse"><img src="img/error.jpg" id="contentImage" border="0" width="50px" height="50px"></a>
			</td>
				
			</table>
		<input type="submit" value="Save" id="save">
	</form>
</div>

