<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
});
</script>

</head>
<body>
<div class="container">
  	<form method="POST" action="controller">
	  	
	  	  <table class="table table-hover">
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
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.userTypeId}</td>
                        <td><input type="checkbox" class="style2" id = "activeUser" ${user.userTypeId == '2' ? 'checked' : ''}/></td>                       
	  					<td><input type="button" value="Active" id = "btnActive" /></td>
	  					<td><input type="submit" value="Delete" /></td>
                    </tr>
                </c:forEach>
            </table>
	  </form>  

</div>
</body>
</html>