<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>
<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.dataTable.dataTable"
	from="adminlanguage.do" />
<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.dataTable.dataTable">
	<!-- end Localization -->

<style>
[type="checkbox"]:not(:checked), [type="checkbox"]:checked {
  position: relative;
  left: 40px;
  }
</style>

<script>
var id;
var nameValid;
var keyValid;
	$(document).ready(function() {
		var dTable = $('#languageTable').DataTable(
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
		$("#name").change(function(){
			if (!this.value){
				 nameValid = false
				 $('#namefalse').show();
        		 $('#nameOk').hide();
			}
			$.ajax({
                url: "adminlanguagerequest.do?action=isPresentName&name=" + $('#name').val(),
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
		
		$("#key").change(function(){
			if (!this.value){
				keyValid = false;
				$('#keyfalse').show();
       		 	$('#keyeOk').hide();
			}
			$.ajax({
                url: "adminlanguagerequest.do?action=isPresentShortName&key=" + $('#key').val(),
                type: "post",
                async: false,
                success: function (data) {
                	if((!data)){
                		 keyValid = true;
                		 $('#keyfalse').hide();
                		 $('#keyOk').show();
                	}
                	else{
                		keyValid = false;
                		 $('#keyfalse').show();
                		 $('#keyOk').hide();
                	}
   	          
                }
            });
		});
		
		 $('form[name=saveForm]').submit(function(){
				if(!(nameValid) || (!keyValid))
		        	return false;
		    });

		$("#languageTable").on("click",".edit",function(){
			var tr = $(this).parents("tr");
			$("#saveForm").css("visibility","visible");
			$('#name').val(tr.find("td:eq(1)").html());
			$('#key').val(tr.find("td:eq(2)").html());
			$('#id').val(tr.find("td:eq(0)").html());
			$('#action').val('edit')
		});
		
		$("#languageTable").on("click",".delete",function(){
			var tr = $(this).parents("tr");
			id = tr.find("td:eq(0)").html();
			$.ajax({
                url: "adminlanguagerequest.do?action=delete&id=" + id,
                type: "post",
                success: function () {
                }
            });
			location.reload();
		});
		
		$("#languageTable").on("click",".localized",function(){
			var tr = $(this).parents("tr");
			id = tr.find("td:eq(0)").html();
			$.ajax({
                url: "adminlanguagerequest.do?action=localized&id=" + id,
                type: "post",
                success: function () {
                }
            });
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
	<table id="languageTable" class="display" cellspacing="0" width="80%">
		<thead>
			<tr>
				<th hidden>ID</th>
				<th><fmt:message key="global.name" /></th>
				<th><fmt:message key="language.key" /></th>
				<th><fmt:message key="language.localized" /></th>
				<th  width = "5%"></th>
				<th width = "5%"></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${languageList}" var="list">
				<tr>
					<td hidden id = "tdId" >${list.id}</td>
					<td id = "tdName">${list.name}</td>
					<td id = "tdKey">${list.key}</td>
					<td id = "tdLocalized" width = "5%">
						<p>
							<input type="checkbox" name="Localized" class = "localized"   ${list.localized == 'true' ? 'checked' : ''}>
						</p>
					</td>
					<td width = "5%">
						<button name ="edit" id = "edit" class ="edit" style="border: 0; background: transparent">							
							<img src="icons/edit.png" style="height: 20px; width: 20px; object-fit: cover"/>
						</button>
					</td>
					<td width = "5%">
						<button name ="delete" id = "delete" class ="delete" style="border: 0; background: transparent">
							<img src="icons/delete-photo-icon.png" style="height: 20px; width: 20px; object-fit: cover"/>
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
		action="adminlanguagerequest.do" method="post" name="saveForm" id = "saveForm">
		<input hidden name="action" id="action" value="add"/> 
		<input hidden type="text" name="id" id="id">
		<div align="right">
		<button name="close" id="close" class="close" 
							style="border: 0; background: transparent" onclick ='$("#saveForm").css("visibility","hidden")'>
							<img src="icons/delete-photo-icon.png"
								style="height: 20px; width: 20px; object-fit: cover"/>
		</button>
		</div>
			<div
	style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
	<table>
		<tr>
			<th><fmt:message key="global.name" /><th>
			<th><fmt:message key="language.key" /></th>
		</tr>
		<tr>
			<td width = "50%">
				<input required  type="text" name="name" id="name" />
			</td>
			<td  width = "10%">
				<a hidden  href="" id = "nameOk"><img src="img/ok.ico" id="contentImage" border="0" width="50px" height="50px"></a>
				<a hidden  href="" id = "namefalse"><img src="img/error.jpg" id="contentImage" border="0" width="50px" height="50px"></a>
			</td>
			<td width = "30%">
				<input required  type="text" name="key"  id="key" /> 
			</td>
			<td width = 10%>
				<a hidden href="" id = "keyOk"><img src="img/ok.ico" id="contentImage" border="0" width="50px" height="50px"></a>
				<a hidden  href="" id = "keyfalse"><img src="img/error.jpg" id="contentImage" border="0" width="50px" height="50px"></a>
			</td>
		</tr>	
	</table>	
	</div>
		<input type = "submit" value = "<fmt:message key='global.save' />" id = "save" >
	</form></div>
</div>
</fmt:bundle>

	