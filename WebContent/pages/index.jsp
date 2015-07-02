<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Guide ME</title>
    
 
 
     
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.js"></script>
    <script src="js/init.js"></script>
    






	<script src="js/moment.js"></script>
	
    <script type="text/javascript">
        $(document).ready(function () {
        	window.onload = function () {
 //       		$("#searchform").submit();
//             	var countryVal = $("#selCountryId").val();
//             	countryVal = "choose";        		
        		
        	}
        });
        
      
	</script>

    <script type="text/javascript">
    
        $(document).ready(function () {
            $('.modal-trigger').leanModal();
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginform").submit(function () {
            	
            	
            	
                $.ajax({
                    url: 'login.do',
                    type: 'GET',
                    dataType: 'json',
                    data: $("#loginform").serialize(),
                    success: function (data) {

                        if (data.isValid) {      
                        	
                            console.log(data);
                            $('#userphoto').attr("src", data.sessionUser.avatar.path);
                            $('#signinlabel').text(
                                    data.sessionUser.email);
                            $('#signinlabel').attr("href", "#logoutModal");
                            $('#guide-me-label').attr("href", "userCabinet.do");
                            $('#signInModal').closeModal();
                            var helloMessage = 'Hello, ' + data.sessionUser.firstName + ' ' + data.sessionUser.lastName;
                            console.log(helloMessage);
                            $('#helloMessageOnLogoutModal').text(helloMessage);

                            Materialize.toast('Guide me! :)' , 1000,'',function(){   
                            	window.location.href = "home.do";
                            	window.location.reload(true);
                            });
                            
                        } else {
                        	
                            $('#password').val('');
                            $('#errorMessage').text('Email or/and password are not valid. Please try again');
                            
                        }
                    }
                });
                
                
                //window.location.href = "home.do";
                //window.location.reload(true);
                //alert('Logged!');
//                 Materialize.toast('Hi!', 3000,'',function(){   
//                 	window.location.href = "home.do";
//                 	window.location.reload(true);
//                 });
                
                //$("#homeform").submit();
                
                return false;
            });
        });
    </script>    
    <script type="text/javascript">
        $(document).ready(function () {
            $("#searchform").submit(function () {
            	
            	var countryVal = $("#selCountryId").val();
            	
            	
            	if(countryVal == "" || countryVal == "choose") {
            		
            		Materialize.toast('Choose country first !', 3000,'',function(){})
            		return false;
            	}
            	
                $.ajax({
                    url: 'searchindexpage.do',
                    type: 'post',
                    dataType: 'json',
                    data: $("#searchform").serialize(),
                    success: function (data) {
                    	
                    	var searchEventTitle =  $("#searchEventTitle");
                    	searchEventTitle.empty();
                    	searchEventTitle.append("Search results:");
						
                    	var resultCollenction = $("#collectionResults");
                        resultCollenction.find("li").empty();   
                        resultCollenction = $("#inner-row");
                        
                        if(data.isEmpty)  {
                        	resultCollenction.append("<span class='blue-text'> <h2> No results!</h2> <h3>Change parameters  and try again . </h3></span");
                        	
                        	Materialize.toast('No results !', 3000,'',function(){})
                        }
                        
                        
                        $.each(data.results, function (counts, currentEvent) {
                        	
                        	var str = "";
                        	
                        	$.each(currentEvent.tagList, function (countsT, currentTag) {
                        		str +=  "<a href='home.do?tag=" + currentTag + "'> #" + currentTag + "</a>";
                        	});
                        	
                      resultCollenction.append(
                    		  "<form id='eventFormWithId"
                              + currentEvent.id
                              + "'><input type='hidden' id='eventId' class='eventId' name='eventId' value='"
                              + currentEvent.id
                              + "'></form> " +                   		  

                              
                              "<div style='display:inline; width: 45%; float: left; margin-left: 10px'> " +
                              "<div class='card small'  id='eventCard" + currentEvent.id  + "'> " + 
                                  "<div class='card-image waves-effect waves-block waves-light'>" +
                                      "<img class='activator' src='" + currentEvent.avatar.path + "'>" +
                                  "</div>" +
                                  "<div class='card-content'>" + 
                                  
                  				"<p><span class='blue-text'>" + str + 

                  				
                  				"</span></p>" +
                                  
								  "<span class='black-text'>" + 
								  currentEvent.name + ", " + currentEvent.moderator.firstName + 
                                  "<i class='mdi-navigation-more-vert right'></i>" + 
                                  "</span>" + 

                                      "<p>" +
                                          "<a href='eventDetail.do?id=" + currentEvent.id +"'>" +  moment(currentEvent.dateFrom ).format('DD.MM.YY hh:mm')   + " - " + moment(currentEvent.dateTo).format('DD.MM.YY hh:mm')   + ", rate: " + Math.round(currentEvent.points) + currentEvent.status  + "</a>" +
                                      "</p>" +
                                  "</div>" + 
                                  "<div class='card-reveal'>" +
          									"<span class='card-title grey-text text-darken-4'>" + currentEvent.name  +
          										"<i class='mdi-navigation-close right'></i>" +
          									"</span>" +

                                      "<p>" + currentEvent.description + "</p>" +
                                  "</div>" +
                              "</div>" +
                           "</div>"                               
                      );
                  });
                  resultCollenction.append("</li>");
                  resultCollenction.append("</ul>");                        
                        
                  //alert(data.Valid);
                    	
                    }
                });
                return false;
            });
        });
    </script>
   
  <script>  
  $(document).ready(function(){
    $('.collapsible').collapsible({
      accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });
  });
  </script>    
        
</head>
<body>

<jsp:include page="header.jsp"/>
<jsp:include page="home/loginmodal.jsp"/>
<jsp:include page="home/logoutmodal.jsp"/>




<div class="row">
    <div class="col s12" style="margin-top: 20px;">
    
    	<input type = "hidden" id="logResult" name="logResult">
    
    	<form action="home.do" method="post" id="homeform" name="homeform">
    	
    	</form>
    	
   <form action="searchindexpage.do" method="post" id="searchform" name="searchform">
        
    <ul class="collapsible" data-collapsible="expandable">

    <li>
      <div class="collapsible-header active"><b>Search in:</b></div>
      <div class="collapsible-body">
      
                                    <c:forEach items="${requestScope.languageList}" var="lang">

                                        <table style="width: 100%;">
                                            <tr>

                                                <td style="width:25%;">
                                                    Country
                                                    <select id="countryByLang_${lang.id}" class="browser-default">
                                                        <option selected value="choose"  selected>
                                                            loginpage.country.choose
                                                        </option>

                                                        <c:forEach items="${requestScope.countryList}" var="country">
                                                            <c:if test="${country.localId == lang.id}">
                                                                <option value="${country.id}">${country.name}</option>
                                                            </c:if>
                                                        </c:forEach>

                                                        <script>
                                                            $('#countryByLang_${lang.id}').change(function () {

                                                                var destination = $('#cityByLang_${lang.id}').val();
                                                                var selectedValue = $(this).val();
                                                                $("#selCountryId").val(selectedValue);
                                                                
                                                                
                                                                if(selectedValue == "choose") {
                                                                	$('#cityByLang_${lang.id}').val('choose');
                                                                	$('#cityId').val('choose');
                                                                }
                                                                
                                                                
 
                                                              //  $(countryId).val(country);
																
                                                                //change other countries
                                                                $.ajax({
                                                                    type: "post",
                                                                    url: 'getLocalCountryAnalogs.do?value=' + selectedValue,
                                                                    success: function (originCountryId) { //we're calling the response json array 'cities'
                                                                    	
                                                                    
                                                                        $.each(originCountryId, function (countryId, country) { //here we're doing a foeach loop round each city with id as the key and city as the value
                                                                        	
                                                                        			
                                                                            
                                                                        	//$(countryId).val(country);
                                                                        	//alert(country);

                                                                            $('#countryByLang_' + countryId).val(country);

                                                                            //fill city list
                                                                            $('#cityByLang_' + countryId + " > option").remove(); //first of all clear select items

                                                                            $.ajax({
                                                                                type: "post",
                                                                                url: 'getCitiesByCountry.do?value=' + country,
                                                                                success: function (originCityId) { //we're calling the response json array 'cities'

                                                                                    var optFirst = $('<option selected />'); // here we're creating a new select option with for each city
                                                                                    optFirst.val('choose');
                                                                                    
                                                                                    $('#countryId').val('choose');
                                                                                    $('#cityId').val('choose');

                                                                                    optFirst.text('loginpage.city.choose');
                                                                                    $('#cityByLang_' + countryId).append(optFirst);

                                                                                    $.each(originCityId, function (id, city) { //here we're doing a foeach loop round each city with id as the key and city as the value


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
                                                </td>


                                                <td style="width:25%;">
                                                    City
                                                    <select id="cityByLang_${lang.id}" class="browser-default">
                                                        <option selected value="choose">
                                                            loginpage.city.choosecountryfirst
                                                        </option>
                                                        
                                                    <script>
                                                        $('#cityByLang_${lang.id}').change(function () {
                                                            var selectedValue = $(this).val();
                                                            $('#cityId').val(selectedValue);

                                                        });
                                                    </script>                                                        
                                                    </select>

                                                </td>
                                                
                                                <td style="width:20%;">
                 									<button class="light-blue btn waves-effect waves-light" type="submit"
                        							name="action"  style="  margin-left: auto; margin-right: auto; width: 100%;">
                 									Guide me ! <i class="mdi-content-send right"></i>
                 									</button>     
                                                </td>                                                
                                            </tr>
                                        </table>

                                    </c:forEach>
       </div>
    </li>
    
    <li>
      <div class="collapsible-header"><b>By word:</b> </div>
      <div class="collapsible-body">
                <div class="input-field col s6"> 
                    <i class="mdi-action-search prefix"></i> 
                    <input id="icon_prefix" name = "icon_prefix" type="text" class="ligth-blue"> <label for="icon_prefix">Enter text...</label>
                    <input id="selCountryId" name="selCountryId" type = "hidden">
                    <input id="cityId" name="cityId" type = "hidden">
                 </div> 
                 
      </div>
    </li>    
    <li>
      <div class="collapsible-header"><b>Event status...:</b></div>
      <div class="collapsible-body">
      		<p>
      		<input type="checkbox" id="status_active" name = "status_active" checked="checked" />
      		<label for="status_active">Active</label>
    		    		
             <input type="checkbox" id="status_filled" name="status_filled" />
      		<label for="status_filled">Filled</label> 		
    		
    		<input type="checkbox" id="status_done" name="status_done"  />
      		<label for="status_done">Done</label> 
      		
    		<input type="checkbox" id="status_cancelled" name="status_cancelled"   />
      		<label for="status_cancelled">Cancelled</label>
       		 </p>
       		 
       		 <p>
       		Type:
    		<select class="browser-default" id= "moderator_type" name= "moderator_type">
      		<option selected value="(2,3)" selected>Events and Excursions</option>
      		<option value="(2)">Events</option>
      		<option value="(3)">Excursions</option>
    		</select>            
            </p>
            
            <p>
            Max. number of members:</p>
            
    		<p class="range-field">
      		<input type="range" id="max_members" name="max_members" min="1"   max="100"  value = "100"  />
    		</p>

 	</div>
    </li>
        </ul>
         </form>
    </div>


<!--     <div class="col s6">This div is 6-columns wide</div> -->
<!--     <div class="col s6">This div is 6-columns wide</div> -->
</div>
<div class="container" style="width: 100%; margin-left: 0%;">
    <div class="section">


        <table>
            <thead>
            <tr>
                <th style="width: 20%; margin-left: 0%;">Recommended</th>
                <th style="width: 60%; margin-left: 20%;" id = 'searchEventTitle' >${requestScope.searchEventTitle}</th>
                <th style="width: 20%; margin-left: 80%;">Top users</th>
            </tr>
            </thead>
            <tbody>
            <tr>
            
            
            
                <th style = "vertical-align: top;">
                <c:forEach items="${requestScope.topUserEvents}" var = "event">
                
                    <div class="card small" style="height: 150px; ">
                        <table>
                            <tr>
                                <td style="width: 120px;">
                                    <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                         src="${event.avatar.path}">
                                </td>
                                <td>
                                <div><a href='eventDetail.do?id=${event.id}'>${event.getNameCityPoints()}</a></div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>    
                    
             
                               
                </th>
                <th style = "width: 60%; vertical-align: top">
                
                <ul id="collectionResults">
                <c:forEach items="${requestScope.lastEvents}" var = "event">
                
                <li id="inner-row">
                <div style="display:inline; width: 45%; float: left; margin-left: 10px">
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="${event.avatar.path}">
                        </div>
                        <div class="card-content">
                        				<p><span class="blue-text">
                        				
                        				<c:forEach items="${event.tagList}" var="tagElem">
                        				
                        				<a href='home.do?tag=${tagElem}'>#${tagElem}</a> 
                        				
                        				</c:forEach>
                        				
                        				</span></p>
                        				
									  <span class="black-text">
                                          ${event.name}, ${event.moderator.firstName}  
                                          <i class="mdi-navigation-more-vert right"></i>
                                      </span>

                            <p>
                                <a href='eventDetail.do?id=${event.id}'>
                                
                                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.dateFrom}" />
                                 -  <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.dateTo}" />, rate: ${Math.round(event.points)}${event.status}
                                 
                                 
                                 </a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">${event.name}
										<i class="mdi-navigation-close right"></i>
									</span>

                            <p>${event.description}</p>
                        </div>
                    </div>
                 </div> 
                  </c:forEach>    
                 
                 </li>  
                

                        </div>
                    </div>
                 </div> 
                 </li>                  
                
                 
        
				</ul>
                    
                </th>
                
                <th style = "vertical-align: top;">
                    <c:forEach items="${requestScope.topUsers}" var = "usr">
                    <div class="card small" style="height: 150px; ">
                        <table>
                            <tr>
                                <td style="width: 120px;">
                                    <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                         src="${usr.avatar.path}">
                                </td>
                                <td>
                                <div><a href='userProfile.do?id=${usr.id}'>${usr.getNameCityPoints()}</a></div>
                                </td>
                            </tr>
                        </table>
                    </div>                      
                    </c:forEach>                    
                               
                </th>                  
                
                
            </tr>

            </tbody>
        </table>

    </div>
</div>



<footer class="page-footer teal">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Hello from GuideMe Team !</h5>

                <p class="grey-text text-lighten-4">We hope you spend wonderful time with us and find new friends !</p>


            </div>
<!--             <div class="col l3 s12"> -->
<!--                 <h5 class="white-text">Connect us</h5> -->
<!--                 <ul> -->
<!--                     <li><a class="white-text" href="#!">Link 1</a></li> -->
<!--                     <li><a class="white-text" href="#!">Link 2</a></li> -->
<!--                     <li><a class="white-text" href="#!">Link 3</a></li> -->
<!--                     <li><a class="white-text" href="#!">Link 4</a></li> -->
<!--                 </ul> -->
<!--             </div> -->
<!--             <div class="col l3 s12"> -->
<!--                 <h5 class="white-text">Connect</h5> -->
<!--                 <ul> -->
<!--                     <li><a class="white-text" href="#!">Link 1</a></li> -->
<!--                     <li><a class="white-text" href="#!">Link 2</a></li> -->
<!--                     <li><a class="white-text" href="#!">Link 3</a></li> -->
<!--                     <li><a class="white-text" href="#!">Link 4</a></li> -->
<!--                 </ul> -->
<!--             </div> -->
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            &copy; 2015, GuideMe Team 
        </div>
    </div>
</footer>


<!--  Scripts-->

</body>
</html>
