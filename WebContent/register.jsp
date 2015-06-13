<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
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
            
            $("#sex").change(function() {
                $.ajax({
                		url: "registervalidator.do", 
                		type : "post",
                		dataType: "json",
                		data:  $(this).serialize(),
                		success:  function(data) {
                    		$("#sexMessage").text(data.valid);
							
                		}	
                });
            });  
            
            $("#cellNumber").change(function() {
                $.ajax({
                		url: "registervalidator.do", 
                		type : "post",
                		dataType: "json",
                		data:  $(this).serialize(),
                		success:  function(data) {
                    		$("#cellNumberMessage").text(data.valid);
							
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
        <br>
        
        loginpage.sex: 
        <select id = "sex" name = "sex">
        	<option selected value = "choose"  disabled>loginpage.sex.choose</option>
  			<option value = "male">loginpage.sex.male</option>
  			<option value = "female">loginpage.sex.female</option>
		</select>
		<span id="sexMessage"></span>
		<br>
		
		loginpage.cellNumber: <input type="text" id="cellNumber" name="cellNumber" /> <span id="cellNumberMessage"></span>
        <br>
              
        loginpage.adress: 
        
        <c:forEach items="${requestScope.languageList}" var = "lang">
   			<br>
   			${lang.name} :
   			<br>     
        </c:forEach>
        
    </form>


</body>
</html>