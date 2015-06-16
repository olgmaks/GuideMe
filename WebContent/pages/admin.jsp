<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="css/bootstrap-checkbox.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="js/bootstrap-checkbox.js"></script>
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Free CSS template by ChocoTemplates.com</title>
	<link rel="stylesheet" href="css/styleForAdmin.css" type="text/css" media="all" />
<script>
$(document).ready(function() {
	$('input[type= "checkbox"]').change(function () {
	    if ($('input[type= "checkbox').is(':checked')) {
	    	var currentRow = this.parentNode.parentNode;
            var userId = currentRow.getElementsByTagName("td")[0];
            $.ajax({
		        type: "POST",
		        url: 'AdminServletPost?action=activeUser' +'&userId='  + userId.textContent
            });
	    } else {
	        $('input[type= "checkbox').removeClass('checked');
	    }
	});
	
	$('#userTypeId').change(function() {
	        var selectedValue = $(this).val();	
	        $.ajax({
	        type: "POST",
	        url: 'AdminServletPost?action=filterByUserType' + "&userTypeId=" + selectedValue,
	        contentType: "application/json; charset=utf-8",
            dataType: 'json',
	          success: function(data){
	        	  var table = $("#userTable");
	                table.find("tr").remove();
	                table.append("<tr><th>Id</th><th>firstName</th> <th>lastName</th><th>email</th><th>typeId</th><th>active</th></tr>")
	        	  $.each(data,function(counts, user){
	        		  table.append("<tr>"
	        		  			 +"<td>"+user.firstName + "</td>"
	                             +"<td>"+user.lastName +  "</td>"
	                             +"<td>"+user.email +     "</td>" 
	                             +"<td>"+user.userType.name +"</td>" 
	                             +"<td>"+user.sex  +      "</td>"
	                     	     +"</tr>");
			        });
	              
	     
	          }
	         	
	    });
	 });
	
	$('#deleteId').on('click', function() {	
		var currentRow = this.parentNode.parentNode;
        var userId = currentRow.getElementsByTagName("td")[0];
	        $.ajax({
	        type: "POST",
	        url: 'AdminServletPost?action=delete' + "&userId=" + userId.textContent + "&userTypeId=" + $("#userTypeId").val(),
	        contentType: "application/json; charset=utf-8",
         	dataType: 'json',
	          success: function(data){
	        	  var table = $("#userTable");
	                table.find("tr").remove();
	                table.append("<tr><th>Id</th><th>firstName</th> <th>lastName</th><th>email</th><th>typeId</th><th>active</th></tr>")
	        	  $.each(data,function(counts, user){
	        		  table.append("<tr>"
	        		  			 +"<td>"+user.firstName + "</td>"
	                             +"<td>"+user.lastName +  "</td>"
	                             +"<td>"+user.email +     "</td>" 
	                             +"<td>"+user.userType.name +"</td>" 
	                             +"<td>"+user.sex  +      "</td>"
	                     	     +"</tr>");
			        });
	          }
	         	
	    });
 });
});
</script>

</head>
<body>
<!-- Header -->
<div id="header">
	<div class="shell">
		<!-- Logo + Top Nav -->
		<div id="top">
			<h1><a href="#">SpringTime</a></h1>
			<div id="top-navigation">
				Welcome <a href="#"><strong>Administrator</strong></a>
				<span>|</span>
				<a href="#">Help</a>
				<span>|</span>
				<a href="#">Profile Settings</a>
				<span>|</span>
				<a href="#">Log out</a>
			</div>
		</div>
		<!-- End Logo + Top Nav -->
		
		<!-- Main Nav -->
		<div id="navigation">
			<ul>
			    <li><a href="#" class="active"><span>Dashboard</span></a></li>
			    <li><a href="#"><span>New Articles</span></a></li>
			    <li><a href="#"><span>User Management</span></a></li>
			    <li><a href="#"><span>Photo Gallery</span></a></li>
			    <li><a href="#"><span>Products</span></a></li>
			    <li><a href="#"><span>Services Control</span></a></li>
			</ul>
		</div>
		<!-- End Main Nav -->
	</div>
</div>
<!-- End Header -->

<!-- Container -->
<div id="container">
	<div class="shell">
		
		<!-- Small Nav -->
		<div class="small-nav">
			<a href="#">Dashboard</a>
			<span>&gt;</span>
			Current Articles
		</div>
		<!-- End Small Nav -->
		
		<!-- End Message Error -->
		<br />
		<!-- Main -->
		<div id="main">
			<div class="cl">&nbsp;</div>
			
			<!-- Content -->
			<div id="content">
				
				<!-- Box -->
				<div class="box">
					<!-- Box Head -->
					<div class="box-head">
						<h2 class="left">Current Articles</h2>
						<div class="right">
							<label>search articles</label>
							<input type="text" class="field small-field" />
							<input type="submit" class="button" value="search" />
						</div>
					</div>
					<!-- End Box Head -->	
					<select name="userTypeId" id = "userTypeId">
						<option value = 0> Select userType </option>
						<c:forEach items="${userType}" var="userType">
							<option value="${userType.id}">${userType.name}</option>
						</c:forEach>
					</select>
					<!-- Table -->
					<div class="table">
						<table  id = "userTable">
			                <tr>
							    <th>Id</th>
							    <th>firstName</th> 
							    <th>lastName</th>
							    <th>email</th>
							    <th>typeId</th>
							    <th>active</th>
							  </tr>
			                <c:forEach items="${userList}" var="user">
			                    <tr>
			                        <td>${user.id}</td>
			                        <td>${user.firstName}</td>
			                        <td><a href="adminUserProfile.do?id=${user.id}">   ${user.lastName}</a></td>
			                        <td>${user.email}</td>
			                        <td>${user.userTypeId}</td>
			                        <td><input type="checkbox" class="style2" id = "activeUser" ${user.userTypeId == '2' ? 'checked' : ''}/></td> 
			                        <td><a class="ico del" id = "deleteId">Delete</a>
			                        <a href="#" class="ico edit">Edit</a></td>              
			                    </tr>
			                </c:forEach>
			            </table>
						
						<!-- Pagging -->
						<div class="pagging">
							<div class="left">Showing 1-12 of 44</div>
							<div class="right">
								<a href="#">Previous</a>
								<a href="#">1</a>
								<a href="#">2</a>
								<a href="#">3</a>
								<a href="#">4</a>
								<a href="#">Next</a>
								<a href="#">View all</a>
							</div>
						</div>
						<!-- End Pagging -->
						
					</div>
					<!-- Table -->
					
				</div>
				<!-- End Box -->
				
				<!-- Box -->
				<div class="box">
					<!-- Box Head -->
					<div class="box-head">
						<h2>Add New Article</h2>
					</div>
					<!-- End Box Head -->
					
					<form action="" method="post">
						
						<!-- Form -->
						<div class="form">
								<p>
									<span class="req">max 100 symbols</span>
									<label>Article Title <span>(Required Field)</span></label>
									<input type="text" class="field size1" />
								</p>	
								<p class="inline-field">
									<label>Date</label>
									<select class="field size2">
										<option value="">23</option>
									</select>
									<select class="field size3">
										<option value="">July</option>
									</select>
									<select class="field size3">
										<option value="">2009</option>
									</select>
								</p>
								
								<p>
									<span class="req">max 100 symbols</span>
									<label>Content <span>(Required Field)</span></label>
									<textarea class="field size1" rows="10" cols="30"></textarea>
								</p>	
							
						</div>
						<!-- End Form -->
						
						<!-- Form Buttons -->
						<div class="buttons">
							<input type="button" class="button" value="preview" />
							<input type="submit" class="button" value="submit" />
						</div>
						<!-- End Form Buttons -->
					</form>
				</div>
				<!-- End Box -->

			</div>
			<!-- End Content -->
			
			<!-- Sidebar -->
			<div id="sidebar">
				
				<!-- Box -->
				<div class="box">
					
					<!-- Box Head -->
					<div class="box-head">
						<h2>Management</h2>
					</div>
					<!-- End Box Head-->
					
					<div class="box-content">
						<a href="#" class="add-button"><span>Add new Article</span></a>
						<div class="cl">&nbsp;</div>
						
						<p class="select-all"><input type="checkbox" class="checkbox" /><label>select all</label></p>
						<p><a href="#">Delete Selected</a></p>
						
						<!-- Sort -->
						<div class="sort">
							<label>Sort by</label>
							<select class="field">
								<option value="">Title</option>
							</select>
							<select class="field">
								<option value="">Date</option>
							</select>
							<select class="field">
								<option value="">Author</option>
							</select>
						</div>
						<!-- End Sort -->
						
					</div>
				</div>
				<!-- End Box -->
			</div>
			<!-- End Sidebar -->
			
			<div class="cl">&nbsp;</div>			
		</div>
		<!-- Main -->
	</div>
</div>
<!-- End Container -->

<!-- Footer -->
<div id="footer">
	<div class="shell">
		<span class="left">&copy; 2010 - CompanyName</span>
		<span class="right">
			Design by <a href="http://chocotemplates.com" target="_blank" title="The Sweetest CSS Templates WorldWide">Chocotemplates.com</a>
		</span>
	</div>
</div>
<!-- End Footer -->
</html>