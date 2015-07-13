<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>

     
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.js"></script>
    <script src="js/init.js"></script>
	<script src="js/moment.js"></script>
	
	
	<!-- begin Localization -->
	<jsp:include page="localization.jsp"/>
	<ct:showLocale basename="locale.home.messages"  from = "home.do" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.home.messages">
	<!-- end Localization -->
	
	<title><fmt:message key="index.title"/></title>
	
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
                            var helloMessage =   _('js.hello') + ', ' + data.sessionUser.firstName + ' ' + data.sessionUser.lastName;
                            console.log(helloMessage);
                            $('#helloMessageOnLogoutModal').text(helloMessage);
                            
                            Materialize.toast('Guide me! :)' , 1000,'',function(){   
                            	window.location.href = "home.do";
                            	window.location.reload(true);
                            });
                            
                        } else {
                        	
                            $('#password').val('');
                            $('#errorMessage').text(_('js.emailPassNotValid'));
                            
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


            		Materialize.toast(_('js.chooseCountry'), 3000,'',function(){})

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
                    	searchEventTitle.append(_("js.searchresults"));

                    	var resultCollenction = $("#collectionResults");
                        resultCollenction.find("li").empty();
                        resultCollenction = $("#inner-row");

                        if(data.isEmpty)  {
                        	resultCollenction.append("<span class='blue-text'> <h2> " + _("js.noResults") + "</h2> <h3>"
                        			+ _("js.chkParams")  + " </h3></span");

                        	Materialize.toast(_("js.noResults"), 3000,'',function(){})
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
                                          "<a href='eventDetail.do?id=" + currentEvent.id +"'>" +  moment(currentEvent.dateFrom ).format('DD.MM.YY hh:mm')   + " - " + moment(currentEvent.dateTo).format('DD.MM.YY hh:mm')   + ", " + _("js.rate")  + ": " + Math.round(currentEvent.points) + currentEvent.status  + "</a>" +
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
  

  
        
  </fmt:bundle>      
</head>
<body>

<fmt:bundle basename="locale.home.messages">


<jsp:include page="header.jsp"/>
<jsp:include page="home/loginmodal.jsp"/>
<jsp:include page="home/logoutmodal.jsp"/>

<script type="text/javascript">

</script>


<div class="row">
    <div class="col s12" style="margin-top: 20px;">
    
    	<input type = "hidden" id="logResult" name="logResult">
    
    	<form action="home.do" method="post" id="homeform" name="homeform">
    	
    	</form>
    	
   <form action="searchindexpage.do" method="post" id="searchform" name="searchform">
        
    <ul class="collapsible" data-collapsible="expandable">

    <li>
      <div class="collapsible-header active"><b><fmt:message key="index.searchIn" />  </b></div>
      <div class="collapsible-body">
      
                                    <c:forEach items="${requestScope.languageList}" var="lang">
                                    
                                        <table style="width: 100%;">
                                            <tr>

                                                <td style="width:25%;">
                                                    <fmt:message key = "index.Country"/>
                                                    <select id="countryByLang_${lang.id}" class="browser-default">
                                                        <option selected value="choose"  selected>
                                                             <fmt:message key = "loginpage.country.choose"/> 
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

                                                                                    optFirst.text(_('js.chooseCity'));
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
                                                    <fmt:message key="index.City" />
                                                    <select id="cityByLang_${lang.id}" class="browser-default">
                                                        <option selected value="choose">
                                                            <fmt:message key="js.chooseCountryFirst" />
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
      <div class="collapsible-header"><b><fmt:message key="index.search"/>:</b> </div>
      <div class="collapsible-body">
                <div class="input-field col s6"> 
                    <i class="mdi-action-search prefix"></i> 
                    <input id="icon_prefix" name = "icon_prefix" type="text" class="ligth-blue"> <label for="icon_prefix"><fmt:message key="index.entertext"/></label>
                    <input id="selCountryId" name="selCountryId" type = "hidden">
                    <input id="cityId" name="cityId" type = "hidden">
                 </div> 
                 
      </div>
    </li>    
    <li>
      <div class="collapsible-header"><b><fmt:message key="index.Event_status"/>...:</b></div>
      <div class="collapsible-body">
      		<p>
      		<input type="checkbox" id="status_active" name = "status_active" checked="checked" />
      		<label for="status_active"><fmt:message key="index.eventActive" />  </label>
    		    		
             <input type="checkbox" id="status_filled" name="status_filled" />
      		<label for="status_filled"><fmt:message key="index.eventFilled" /></label> 		
    		
    		<input type="checkbox" id="status_done" name="status_done"  />
      		<label for="status_done"><fmt:message key="index.Done" /></label> 
      		
    		<input type="checkbox" id="status_cancelled" name="status_cancelled"   />
      		<label for="status_cancelled"><fmt:message key="index.Cancelled" /></label>
       		 </p>
       		 
       		 <p>
       		<fmt:message key="index.Type" />:
    		<select class="browser-default" id= "moderator_type" name= "moderator_type">
      		<option selected value="(2,3)" selected><fmt:message key="index.EventsAndExcursions" /></option>   
      		<option value="(2)"><fmt:message key="index.Events" /> </option>
      		<option value="(3)"><fmt:message key="index.Excursions" /></option>
    		</select>            
            </p>
            
            <p>
            <fmt:message key="index.MaxNumber" />:</p>  
            
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
<div style="width: 98%;margin-left: 1%">
    <%--<div class="section">--%>


        <table >
            <thead>

            <tr>
                <th style="width: 20%; margin-left: 0%;"> <fmt:message key="index.TopEvents" /></th>
                <th style="width: 60%; margin-left: 20%;"  id = 'searchEventTitle' >${requestScope.searchEventTitle}</th>
                <th style="width: 20%; margin-left: 80%;"><fmt:message key="index.TopUsers" /></th>
            </tr>

            </thead>
            <tbody>
            <tr>
            
            
            
                <th style = "vertical-align: top;">
                <c:forEach items="${requestScope.topUserEvents}" var = "event">
                
                    <div class="card small" style="height: 150px; width: 270px;">
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
                <th style = "width: 60%; vertical-align: top" >
                
                <ul id="collectionResults">
                <c:forEach items="${requestScope.lastEvents}" var = "event">
                
                <li id="inner-row">
                <div style="display:inline; max-width: 360px; float: left; margin-left: 10px">
                    <div class="card small" style="">
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
                                 -  <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.dateTo}" />, <fmt:message key="js.rate" />: ${Math.round(event.points)}${event.status}
                                 
                                 
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
                

                        <%--</div>--%>
                    </div>
                 </div> 
                 </li>                  
                
                 
        
				</ul>
                    
                </th>
                
                <th style = "vertical-align: top;">
                    <c:forEach items="${requestScope.topUsers}" var = "usr">
                    <div class="card small" style="height: 150px; width: 270px;">
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

    <%--</div>--%>
</div>


<%--<div>--%>
<footer class="page-footer light-blue">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text"><fmt:message key="index.FooterLineFirst" /></h5>

                <p class="grey-text text-lighten-4"><fmt:message key="index.FooterLineSecond" /></p>


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
            &copy; <fmt:message key="index.footerCopyright" /> 
        </div>
    </div>
</footer>
<%--</div>--%>

<!--  Scripts-->

</fmt:bundle>
</body>
</html>
