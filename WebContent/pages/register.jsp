<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).ready(function() {
        	
           
                $("#registerform").submit(function () {
                	$("#firstName").change();
                	$("#lastName").change();
                	$("#email").change();
                	$("#password").change();
                	$("#confirm").change();
                	$("#sex").change();
                	$("#cellNumber").change();
                	
                    
                        $.ajax({
                        		url: "registervalidator.do", 
                        		type : "post",
                        		dataType: "json",
                        		data:  $("#cityId").serialize(),
                        		success:  function(data) {
                            		$("#cityIdMessage").text(data.valid);
        							
                        		}	
                        });
                    
                	
                	$.ajax({
                    	
                        url: 'submitRegister.do',
                        type: 'post',
                        dataType: 'json',
                        data: $("#registerform").serialize(),
                        success: function (data) {
                        	alert('Ok!!!');
                        	

//                             if (data.isValid) {
//                                 console.log(data);
//                                 $('#signinlabel').text(
//                                         data.sessionUser.email);
//                                 $('#signinlabel').attr("href", "#logoutModal");
//                                 $('#signInModal').closeModal();
//                                 var helloMessage = 'Hello, ' + data.sessionUser.firstName + ' ' + data.sessionUser.lastName;
//                                 console.log(helloMessage);
//                                 $('#helloMessageOnLogoutModal').text(helloMessage);
//                             } else {
//                                 $('#password').val('');
//                                 $('#errorMessage').text('Email or/and password are not valid. Please try again');
//                             }
                            
                            
                            
                        }
                    });
                    return false;
                });
        	
        	
        	
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
            
            $('#confirm').change(function() {
                	  var pass = $('#password').val();
                      var conf = $('#confirm').val();
                      
                      $.ajax({
                      type: "post",
                      url: 'confirmValidator.do?password=' + pass + '&confirm=' + conf, 
                      success: function(data){ //we're calling the response json array 'cities'

                    	  $("#confirmMessage").text(data.valid);
                    	  
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

    <form action="submitRegister.do" method="post" id = "registerform"  name = "registerform" >
        loginpage.firstName: <input type="text" id="firstName" name="firstName" /> <span id="firstNameMessage"></span>
        <br>
        loginpage.lastName: <input type="text" id="lastName" name="lastName" /> <span id="lastNameMessage"></span>
        <br>
        
        loginpage.email: <input type="text" id="email" name="email" /> <span id="emailMessage"></span>
        <br>
                
        loginpage.password: <input type="password" id="password" name="password" /> <span id="passwordMessage"></span>
        <br>
        
        loginpage.confirm: <input type="password" id="confirm" name="confirm" /> <span id="confirmMessage"></span>
        <br>
        
        loginpage.sex: 
        <select id = "sex" name = "sex">
        	<option selected value = "choose" >loginpage.sex.choose</option>
  			<option value = "male">loginpage.sex.male</option>
  			<option value = "female">loginpage.sex.female</option>
		</select>
		<span id="sexMessage"></span>
		<br>
		
		loginpage.cellNumber: <input type="text" id="cellNumber" name="cellNumber" /> <span id="cellNumberMessage"></span>
        <br>
              
        loginpage.adress: 
        
        <c:forEach items="${requestScope.languageList}" var = "lang">
   			<br><br>
   			${lang.name} :
   			<br>     
   			loginpage.country: 
   			<select id = "countryByLang_${lang.id}" >
   				<option selected value = "choose"  disabled selected>loginpage.country.choose</option>
   				
   				<c:forEach items="${requestScope.countryList}" var = "country">
   					<c:if test="${country.localId == lang.id}">
   					<option value = "${country.id}">${country.name}</option>	
   					</c:if>
   				</c:forEach>
   				
				<script>
				$('#countryByLang_${lang.id}').change(function() {
	
						var destination = $('#cityByLang_${lang.id}').val();
          				var selectedValue = $(this).val();
          				
          				
          				//change other countries
          				$.ajax({
              				type: "post",
              				url: 'getLocalCountryAnalogs.do?value=' + selectedValue, 
              				success: function(originCountryId){ //we're calling the response json array 'cities'
               				
              				$.each(originCountryId,function(countryId,country){ //here we're doing a foeach loop round each city with id as the key and city as the value
               					
              					//$(countryId).val(country);
              				
              					$('#countryByLang_' + countryId).val(country);
              					
                  				//fill city list
                  				$('#cityByLang_' + countryId  + " > option").remove(); //first of all clear select items           
                  				
                  				$.ajax({
                  				type: "post",
                  				url: 'getCitiesByCountry.do?value=' + country, 
                  				success: function(originCityId){ //we're calling the response json array 'cities'
                   				
                   				var optFirst = $('<option selected />'); // here we're creating a new select option with for each city
                   				optFirst.val('choose');
                   				$('#cityId').val('choose');
                   				
                   				optFirst.text('loginpage.city.choose');
                   				$('#cityByLang_' + countryId).append(optFirst); 
                  					
                  				$.each(originCityId,function(id,city){ //here we're doing a foeach loop round each city with id as the key and city as the value
                   					
                   					
                   					var opt = $('<option />'); // here we're creating a new select option with for each city
                    				opt.val(id);
                    				opt.text(city);
                    				
                    				$('#cityByLang_' + countryId).append(opt); //here we will append these new select options to a dropdown with the id 'cities'
                    				});
                   				}
                  				});              					
              					
              					
              				
                			});
               				}
              			});
          				
      			}); 
				</script>   				
   				
   			</select>
   			
   			<br>
   			loginpage.city:
   			<select id = "cityByLang_${lang.id}" >
   				<option selected value = "choose"  >loginpage.city.choosecountryfirst</option>
   			</select> 
   			<script>
			$('#cityByLang_${lang.id}').change(function() {
				
  				var selectedValue = $(this).val();
  				
  				$('#cityId').val(selectedValue);
  				
  				//change other cities
  				$.ajax({
      				type: "post",
      				url: 'getLocalCityAnalogs.do?value=' + selectedValue, 
      				success: function(originCityId){ //we're calling the response json array 'cities'
       				
      				$.each(originCityId,function(cityId,city){ //here we're doing a foeach loop round each city with id as the key and city as the value
      				
      					$('#cityByLang_' + cityId).val(city);
      				
        			});
       				}
      			});
  				
			});    			
   			</script>

        <br>
        loginpage.address: <input type="text" id="addressByLang_${lang.id}" name="addressByLang_${lang.id}"   /> <span id="addressByLangMessage_${lang.id}"></span>
        
        <script>    
        $(document).ready(function() {
            $("#addressByLang_${lang.id}").change(function() {
                $.ajax({
                		url: "registerAddressValidator.do", 
                		type : "post",
                		dataType: "json",
                		data:  $(this).serialize(),
                		success:  function(data) {
                    		$("#addressByLangMessage_${lang.id}").text(data.valid);
							
                		}	
                });
            });   
        });    
            
            </script>
            
        </c:forEach>
        
         <br>
         <br>
         <input type="hidden" id="cityId" name="cityId" /> <span id="cityIdMessage"></span>
         <br>
           
        
        <br>
		loginpage.isGuide: <input type="checkbox" id="isGuide" name="isGuide" />
        <br>
        
        
             <button type = "submit">
                Submit 
            </button>       
    </form>


</body>
</html>