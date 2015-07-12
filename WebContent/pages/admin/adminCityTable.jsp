<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>

<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.dataTable.dataTable"
	from="admincity.do" />
<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.dataTable.dataTable">
	<!-- end Localization -->
<script>
var addedit='add';
var id;

	$(document).ready(function() {
		var dTable = $('#cityTable').DataTable(
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
		$("#cityTable").on("click",".edit",function(){
			 $(".browser-default").prop('disabled', 'disabled');
			var tr = $(this).parents("tr");
			var i= 1;   
			<c:forEach var="item" items="${languageList}" varStatus="loop">
					$('#langCity${item.id}').val(tr.find("td:eq("+i+")").html());
					i=i+2;	
			</c:forEach>
			$('#id').val(tr.find("td:eq(0)").html());
			$('#action').val('edit');
		});
		
		$("#cityTable").on("click",".delete",function(){
			var tr = $(this).parents("tr");
			id = tr.find("td:eq(0)").html();
			$.ajax({
                url: "admincityrequest.do?action=delete&id=" + id,
                type: "post",
                success: function () {
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
	<table id="cityTable" class="display" cellspacing="0" width="80%">
		<thead>
			<tr>
				<th hidden>ID</th>
				<c:forEach var="item" items="${languageList}">
		            <th><fmt:message key="city.city" />(${item.key})</th>
		            <th><fmt:message key="country.country" />(${item.key})</th>
		          </c:forEach>
				<th></th>
				<th></th>
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
	
	<input type="submit" value="add" id="add" />
	<script>
		$("#add").on('click', function () {   
			$("#add").val("add");
			 $(".browser-default").removeAttr("disabled");
		});
	</script>
	<div style="width: 50%; margin-left: 25% margin-right: 25% text-align: center;">
	<form  action="admincityrequest.do" method="post">
		<input hidden name="action" id = "action" value = "add"/> 
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
		         		<input required  type="text" name="langCity${item.key.id}" id="langCity${item.key.id}" /> 
			      	</td>
			      	<td>
			      		<select id="country" name="country${item.key.id}" class="browser-default">
			      			<c:forEach items="${item.value}" var="countries">
						       <option value="${countries.id}">${countries.name}</option>
						   </c:forEach>
			      		</select>
			      	</td>
		      	</tr>
	      	</table>
	      </c:forEach>
	      
	      
		<br/>
	<input type="submit" value="<fmt:message key="global.save" />" id="addbtn" />
	</form>
	</div>
</div>
</fmt:bundle>