<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#firstName").change(function() {
                $.ajax({
                		url: "registervalidator.do", 
                		type : "post",
                		dataType: "json",
                		data:  $(this).serialize(),
                		success:  function(data) {
                    		$("#firstNameMessage").text(data.valid);
							
                		}	
                });
            });            
            
            
            $("#lastName").change(function() {
                $.ajax({
                		url: "registervalidator.do", 
                		type : "post",
                		dataType: "json",
                		data:  $(this).serialize(),
                		success:  function(data) {
                    		$("#lastNameMessage").text(data.valid);
							
                		}	
                });
            });            
            
//             $("#email").change(function() {
//                 $.get("ValidateUsernameServlet", $(this).serialize(), function(data) {
//                     $("#emailMessage").text(data.valid);
//                 });
//             });
            
            $("#email").change(function() {
                $.ajax({
                		url: "registervalidator.do", 
                		type : "post",
                		dataType: "json",
                		data:  $(this).serialize(),
                		success:  function(data) {
                    		$("#emailMessage").text(data.valid);
							
                		}	
                });
            });
            
            $("#password").change(function() {
                $.ajax({
                		url: "registervalidator.do", 
                		type : "post",
                		dataType: "json",
                		data:  $(this).serialize(),
                		success:  function(data) {
                    		$("#passwordMessage").text(data.valid);
							
                		}	
                });
            });            
            
            
        });
    </script>
</head>
<body>

    <form action="register" method="post">
        loginpage.firstName: <input type="text" id="firstName" name="firstName" /> <span id="firstNameMessage"></span>
        <br>
        loginpage.lastName: <input type="text" id="lastName" name="lastName" /> <span id="lastNameMessage"></span>
        <br>
        
        loginpage.email: <input type="text" id="email" name="email" /> <span id="emailMessage"></span>
        <br>
                
        loginpage.password: <input type="password" id="password" name="password" /> <span id="passwordMessage"></span>
        
    </form>


</body>
</html>