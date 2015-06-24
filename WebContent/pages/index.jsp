<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        } else {
                            $('#password').val('');
                            $('#errorMessage').text('Email or/and password are not valid. Please try again');
                        }
                    }
                });
                return false;
            });
        });
    </script>    
    <script type="text/javascript">
        $(document).ready(function () {
            $("#searchform").submit(function () {
                $.ajax({
                    url: 'searchindexpage.do',
                    type: 'post',
                    dataType: 'json',
                    data: $("#searchform").serialize(),
                    success: function (data) {
						
                    	var resultCollenction = $("#collectionResults");
                        resultCollenction.find("li").empty();   
                        resultCollenction = $("#inner-row");
                        
                        
                        $.each(data.results, function (counts, currentEvent) {
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
                                  
								  "<span class='black-text'>" + 
								  currentEvent.name +
                                  "<i class='mdi-navigation-more-vert right'></i>" + 
                                  "</span>" + 

                                      "<p>" +
                                          "<a href='#'>" + currentEvent.dateFrom + " - " + currentEvent.dateTo  + "</a>" +
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
</head>
<body>

<jsp:include page="header.jsp"/>
<jsp:include page="home/loginmodal.jsp"/>
<jsp:include page="home/logoutmodal.jsp"/>

<div class="row">
    <div class="col s12" style="margin-top: 20px;">
        
        <form action="searchindexpage.do" method="post" id="searchform" name="searchform">
        <ul class="collection">
            <li class="collection-item" style="margin-left: 30%;">
                <div class="input-field col s6">
                    <i class="mdi-action-search prefix"></i> 
                    <input id="icon_prefix" name = "icon_prefix" type="text" class="ligth-blue"> <label for="icon_prefix">Search</label>
                    <input id="selCountryId" name="selCountryId" type = "hidden">
                    <input id="cityId" name="cityId" type = "hidden">
                </div>

            </li>
            
            <li class="collection-item" style="margin-left: 30%;">
                <div class="input-field col s6">
                    
   
                                <br>

                                <div>
                                    <c:forEach items="${requestScope.languageList}" var="lang">

                                        <table style="width: 100%;">
                                            <tr>

                                                <td style="width:30%;">
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


                                                <td style="width:30%;">
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

                                            </tr>
                                        </table>

                                    </c:forEach>

                                </div>
                    
                </div>
            </li>
            
            
            <li class="collection-item" style="margin-left: 30%;">
            </li>
            
            <li class="collection-item" style="margin-left: 30%;">
            </li>
            
            
            <li class="collection-itenm" style="margin-left: 30%;">

                <button class="light-blue btn waves-effect waves-light" type="submit"
                        name="action"  style="  margin-left: auto; margin-right: auto; width: 15%;">
                 Guide me ! <i class="mdi-content-send right"></i>
                 </button>
                 
            </li>
        </ul>
        

                         
        </form>
    </div>


    <div class="col s6">This div is 6-columns wide</div>
    <div class="col s6">This div is 6-columns wide</div>
</div>
<div class="container" style="width: 100%; margin-left: 0%;">
    <div class="section">

        <p class="light">Events</p>

        <table>
            <thead>
            <tr>
                <th style="width: 20%; margin-left: 0%;">Top  events</th>
                <th style="width: 60%; margin-left: 20%;">Events</th>
                <th style="width: 20%; margin-left: 80%;">Guide events</th>
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
                                <div><a href="#">"${event.getEventNameAndCity()}"</a></div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>    
                    
             
                               
                </th>
                <th style = "width: 60%; vertical-align: top">
                
                <ul id="collectionResults">
                
                <li id="inner-row">
                <div style="display:inline; width: 45%; float: left; margin-left: 10px">
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									  <span class="black-text">
                                          Event Name
                                          <i class="mdi-navigation-more-vert right"></i>
                                      </span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                 </div> 
                 </li>  
                 
                 <li id="inner-row">   
                   <div style="display:inline; width: 45%; float: left; margin-left: 10px"> 
                   <div class="card small" >
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									  <span class="black-text">
                                          Event Name
                                          <i class="mdi-navigation-more-vert right"></i>
                                      </span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                    </div>  
               </li>     
                 
               <li id="inner-row">  
               <div style="display:inline; width: 45%; float: left; margin-left: 10px">
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									  <span class="black-text">
                                          Event Name
                                          <i class="mdi-navigation-more-vert right"></i>
                                      </span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                 </div> 
                 </li>  
                    
                 <li id="inner-row">   
                   <div style="display:inline; width: 45%; float: left; margin-left: 10px"> 
                   <div class="card small" >
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									  <span class="black-text">
                                          Event Name
                                          <i class="mdi-navigation-more-vert right"></i>
                                      </span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                    </div> 
                </li>                         
				</ul>
                    
                </th>
                
                
                <th style = "vertical-align: top;">
                    <c:forEach items="${requestScope.topGuideEvents}" var = "event">
                    <div class="card small" style="height: 150px; ">
                        <table>
                            <tr>
                                <td style="width: 120px;">
                                    <img class="circle" style="height: 120px; width: 120px; object-fit: cover"
                                         src="${event.avatar.path}">
                                </td>
                                <td>
                                <div><a href="#">${event.getEventNameAndCity()}</a></div>
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

<div class="container">
    <div class="section">

        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text">
                        <i class="mdi-image-flash-on"></i>
                    </h2>
                    <h5 class="center">Speeds up development</h5>

                    <p class="light">We did most of the heavy lifting for you to
                        provide a default stylings that incorporate our custom
                        components. Additionally, we refined animations and transitions
                        to provide a smoother experience for developers.</p>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text">
                        <i class="mdi-social-group"></i>
                    </h2>
                    <h5 class="center">User Experience Focused</h5>

                    <p class="light">By utilizing elements and principles of
                        Material Design, we were able to create a framework that
                        incorporates components and animations that provide more feedback
                        to users. Additionally, a single underlying responsive system
                        across all platforms allow for a more unified user experience.</p>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text">
                        <i class="mdi-action-settings"></i>
                    </h2>
                    <h5 class="center">Easy to work with</h5>

                    <p class="light">We have provided detailed documentation as
                        well as specific code examples to help new users get started. We
                        are also always open to feedback and can answer any questions a
                        user may have about Materialize.</p>
                </div>
            </div>
        </div>

    </div>
</div>


<div class="container">
    <div class="section">

        <div class="row">
            <div class="col s12 center">
                <h3>
                    <i class="mdi-content-send brown-text"></i>
                </h3>
                <h4>Contact Us</h4>

                <p class="left-align light">Lorem ipsum dolor sit amet,
                    consectetur adipiscing elit. Nullam scelerisque id nunc nec
                    volutpat. Etiam pellentesque tristique arcu, non consequat magna
                    fermentum ac. Cras ut ultricies eros. Maecenas eros justo,
                    ullamcorper a sapien id, viverra ultrices eros. Morbi sem neque,
                    posuere et pretium eget, bibendum sollicitudin lacus. Aliquam
                    eleifend sollicitudin diam, eu mattis nisl maximus sed. Nulla
                    imperdiet semper molestie. Morbi massa odio, condimentum sed ipsum
                    ac, gravida ultrices erat. Nullam eget dignissim mauris, non
                    tristique erat. Vestibulum ante ipsum primis in faucibus orci
                    luctus et ultrices posuere cubilia Curae;</p>
            </div>
        </div>

    </div>
</div>


<div class="parallax-container valign-wrapper">
    <div class="section no-pad-bot">
        <div class="container">
            <div class="row center">
                <h5 class="header col s12 light">A modern responsive front-end
                    framework based on Material Design</h5>
            </div>
        </div>
    </div>
    <div class="parallax">
        <img src="background2.jpg" alt="Unsplashed background img 3">
    </div>
</div>

<footer class="page-footer teal">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Company Bio</h5>

                <p class="grey-text text-lighten-4">We are a team of college
                    students working on this project like it's our full time job. Any
                    amount would help support and continue development on this project
                    and is greatly appreciated.</p>


            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Settings</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Connect</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            Made by <a class="brown-text text-lighten-3"
                       href="http://materializecss.com">Materialize</a>
        </div>
    </div>
</footer>


<!--  Scripts-->

</body>
</html>
