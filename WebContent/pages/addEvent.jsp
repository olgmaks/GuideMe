
<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>


	<!-- begin Localization -->
	<jsp:include page="localization.jsp"/>
	<ct:showLocale basename="locale.register.messages" from = "register.do" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.register.messages">
	<!-- end Localization -->

	<title> <fmt:message key="addevent.title" /></title>
	</fmt:bundle>

<script>
        $(document).ready(function () {


            $("#registerform").submit(function () {
            	
            	//alert('start subm');
            	
            	var res = true;
            	
            	$("#eventName").keyup();
            	$("#description").keyup();
            	$("#partisipant_limit").keyup();
            	$("#bad_count").keyup();
            	
            	
            	var cityVal = $("#cityId").val();
            	
            	
            	if(cityVal.trim() == '' || cityVal == 'choose') {
            		$("#cityIdMessage").attr('class','red-text');
            		$("#cityIdMessage").text(_("js.cityEmpty") );
            		res = false;
            	} else {
            		$("#cityIdMessage").attr('class','green-text');
            		$("#cityIdMessage").text("");
            	}
            	
            	if($("#eventNameMessage").val().indexOf(".ok") >= 0 || $("#eventNameMessage").val().trim() == 0) {
            	} else res = false;
            	
            	if($("#descriptionMessage").val().indexOf(".ok") >= 0 || $("#descriptionMessage").val().trim() == 0) {
            	} else res = false; 
            	
            
            	if($("#partisipant_limitMessage").val().indexOf(".ok") >= 0 || $("#partisipant_limitMessage").val().trim() == 0) {
            	} else res = false;             	
            	
            	
            	if($("#bad_countMessage").val().indexOf(".ok") >= 0 || $("#bad_countMessage").val().trim() == 0) {
            	} else res = false;             	
            	
            	
            	//************************************************
            	
				var datefrom = $('#dateFrom').val();
				var dateto = $('#dateTo').val();
				
				var today = new Date();
				var dd = today.getDate();
				var mm = today.getMonth() + 1; //January is 0!

				var yyyy = today.getFullYear();
				if (dd < 10) {
					dd = '0' + dd
				}
				if (mm < 10) {
					mm = '0' + mm
				}
				var today = yyyy + '-' + mm
						+ '-' + dd;
				
				
				
				var dateMes = "";
				
				if (new Date(datefrom) == 'Invalid Date') {
					dateMes = "js.valid.datefrominv"; 
					res = false;
				}
				
				if(res) {
					if (new Date(dateto) == 'Invalid Date') {
						dateMes = "js.valid.datetoinv"; 
						res = false;
					}
				}
				
				
				
				
				var hourfrom = parseInt($('#hourFrom').val(), 10) * 3600;
				var minutefrom = parseInt($('#minuteFrom').val(), 10) * 60;
				var timefrom = hourfrom + minutefrom;
				
				var hourto = parseInt($('#hourTo').val(), 10) * 3600;
				var minuteto = parseInt($('#minuteTo').val(), 10) * 60;
				var timeto = hourto + minuteto;				
				
				
				if(res) {
				
				if (new Date(datefrom) > new Date(dateto)) {
					res = false;
					
					dateMes = "js.valid.datefromtoinv";
 				
				} else if (new Date(datefrom) < new Date(today)) {
 					res = false;
 					dateMes = "js.valid.datenow";
				}

 				else if (new Date(datefrom) == new Date(dateto)) {
 					if (timefrom > timeto) {
 						res = false;
 						dateMes = "js.valid.datefromtoinv";
 					}
 				}
				}
				

            	
                if(dateMes.indexOf(".ok") >= 0)
                	$("#dateMessage").attr('class','green-text');
                else
                	$("#dateMessage").attr('class','red-text');
                
                $("#dateMessage").text(_(dateMes) );            	
            	
                //alert(res);
                
             	if(res) {
             		//alert('sttart aj');
             		
                    $.ajax({
                    	
                        url: 'submitEvent.do',
                        type: 'post',
                        dataType: 'json',
                        data: $("#registerform").serialize(),
                        success: function (data) {
                            //alert('sucess!!!!1');     
                        	
                        	if (data.isValid) {

                                	 Materialize.toast(_('js.success') , 1000,'',function(){   
                                     	window.location.href = "userCabinet.do";
                                     });

                                 } else{
                                	 
                                	 Materialize.toast(_('js.fail') , 1000,'',function(){});                            	 
                                 }

                        }
                    });             		
             	}
                
                
                
            	return false;
				});
            
            
            $("#eventName").keyup(function () {
                $.ajax({
                    url: "registervalidatorevent.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        //$("#firstNameMessage").text(data.valid);
                        
                        if(data.valid.indexOf(".ok") >= 0)
                        	$("#eventNameMessage").attr('class','green-text');
                        else
                        	$("#eventNameMessage").attr('class','red-text');
                        
                        $("#eventNameMessage").text(_(data.valid) );
                        //$("#eventNameMessage").text(data.valid );

                    }
                });
            });
            
            
            $("#description").keyup(function () {
                $.ajax({
                    url: "registervalidatorevent.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        
                        if(data.valid.indexOf(".ok") >= 0)
                        	$("#descriptionMessage").attr('class','green-text');
                        else
                        	$("#descriptionMessage").attr('class','red-text');
                        
                        $("#descriptionMessage").text(_(data.valid) );
                        //$("#eventNameMessage").text(data.valid );

                    }
                });
                
            });     
            
            
            $("#partisipant_limit").keyup(function () {
                $.ajax({
                    url: "registervalidatorevent.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        
                        if(data.valid.indexOf(".ok") >= 0)
                        	$("#partisipant_limitMessage").attr('class','green-text');
                        else
                        	$("#partisipant_limitMessage").attr('class','red-text');
                        
                        $("#partisipant_limitMessage").text(_(data.valid) );
                        //$("#eventNameMessage").text(data.valid );

                    }
                });
                
            }); 
            
            $("#bad_count").keyup(function () {
                $.ajax({
                    url: "registervalidatorevent.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        
                        if(data.valid.indexOf(".ok") >= 0)
                        	$("#bad_countMessage").attr('class','green-text');
                        else
                        	$("#bad_countMessage").attr('class','red-text');
                        
                        $("#bad_countMessage").text(_(data.valid) );

                    }
                });
                
            });            
            
            
            
        }); 
        
        

</script>

</head>
<body>
<fmt:bundle basename="locale.register.messages">
	


	<!-- <input type="image"	src="img/guide1.jpg"><br> -->



	<div class="row" style="margin-top: 15px;">
		<div class="col s12" style="width: 90%; margin-left: 10%">
			<ul class="collection z-depth-2 ">
				<li class="collection-item">
					<div class="row">

						<form class="col s12" action="submitEvent.do" method="post" name = "registerform" id = "registerform">

							
							
							<div class="row">
								<div class="input-field col s12">
									<label for="eventName">  <fmt:message key="addEventpage.eventName" />:</label> <input 
										type="text" id="eventName" name="eventName" /> 
										<span id="eventNameMessage"></span><br>
								</div>
							</div>
							<div class="row">
								<div class=" col s12">
									<table style="width: 100%;">
										<tr>

											<c:forTokens items="From,To" delims="," var="name">

												<td style="width: 34%;"><label for="date">
														<fmt:message key="addEventpage.date" /> <fmt:message key="${name}" />:</label> <input type="date"
													class="datepicker" id="date${name}" name="date${name}" /> <span
													id="dateMessage"></span></td>

												<td style="width: 8%;"><label for="hour"> <fmt:message key="hour" />:</label>
													<input required="required" type="number" id="hour${name}"  name="hour${name}" min="00" max="23"
													id="hour"></td>
												<td style="width: 0%;">:</td>

												<td style="width: 8%;"><label for="minute">
														<fmt:message key="minute" />:</label> <input required="required" type="number" name="minute${name}"  id="minute${name}"   min="00"
													max="59" id="minute"></td>

											</c:forTokens>
										</tr>
									</table>
									<span id="dateMessage"></span><br>
								</div>
							</div>
							<div class="row">
								<label><fmt:message key="Address" /></label>

								<div>
									<c:forEach items="${requestScope.languageList}" var="lang">

                                        ${lang.name} :
                                        <table style="width: 100%;">
											<tr>

												<td style="width: 30%;"><label> <fmt:message key="register.Country" /></label> <select
													id="countryByLang_${lang.id}" class="browser-default">
														<option selected value="choose" disabled selected>
															loginpage.country.choose</option>

														<c:forEach items="${requestScope.countryList}"
															var="country">
															<c:if test="${country.localId == lang.id}">
																<option value="${country.id}">${country.name}</option>
															</c:if>
														</c:forEach>

														<script>
															$(
																	'#countryByLang_${lang.id}')
																	.change(
																			function() {

																				var destination = $(
																						'#cityByLang_${lang.id}')
																						.val();
																				var selectedValue = $(
																						this)
																						.val();

																				//change other countries
																				$
																						.ajax({
																							type : "post",
																							url : 'getLocalCountryAnalogs.do?value='
																									+ selectedValue,
																							success : function(
																									originCountryId) { //we're calling the response json array 'cities'

																								$
																										.each(
																												originCountryId,
																												function(
																														countryId,
																														country) { //here we're doing a foeach loop round each city with id as the key and city as the value

																													//$(countryId).val(country);

																													$(
																															'#countryByLang_'
																																	+ countryId)
																															.val(
																																	country);

																													//fill city list
																													$(
																															'#cityByLang_'
																																	+ countryId
																																	+ " > option")
																															.remove(); //first of all clear select items

																													$
																															.ajax({
																																type : "post",
																																url : 'getCitiesByCountry.do?value='
																																		+ country,
																																success : function(
																																		originCityId) { //we're calling the response json array 'cities'

																																	var optFirst = $('<option selected />'); // here we're creating a new select option with for each city
																																	optFirst
																																			.val('choose');
																																	$(
																																			'#cityId')
																																			.val(
																																					'choose');

																																	optFirst
																																			.text('loginpage.city.choose');
																																	$(
																																			'#cityByLang_'
																																					+ countryId)
																																			.append(
																																					optFirst);

																																	$
																																			.each(
																																					originCityId,
																																					function(
																																							id,
																																							city) { //here we're doing a foeach loop round each city with id as the key and city as the value

																																						var opt = $('<option />'); // here we're creating a new select option with for each city
																																						opt
																																								.val(id);
																																						opt
																																								.text(city);

																																						$(
																																								'#cityByLang_'
																																										+ countryId)
																																								.append(
																																										opt); //here we will append these new select options to a dropdown with the id 'cities'
																																					});
																																}
																															});

																												});
																							}
																						});

																			});
														</script>

												</select></td>


												<td style="width: 30%;"><label>City</label> <select
													id="cityByLang_${lang.id}" class="browser-default">
														<option selected value="choose">
															loginpage.city.choosecountryfirst</option>
												</select> <script>
													$('#cityByLang_${lang.id}')
															.change(
																	function() {

																		var selectedValue = $(
																				this)
																				.val();

																		$(
																				'#cityId')
																				.val(
																						selectedValue);

																		//change other cities
																		$
																				.ajax({
																					type : "post",
																					url : 'getLocalCityAnalogs.do?value='
																							+ selectedValue,
																					success : function(
																							originCityId) { //we're calling the response json array 'cities'

																						$
																								.each(
																										originCityId,
																										function(
																												cityId,
																												city) { //here we're doing a foeach loop round each city with id as the key and city as the value

																											$(
																													'#cityByLang_'
																															+ cityId)
																													.val(
																															city);

																										});
																					}
																				});

																	});
												</script></td>

												<td style="width: 40%;">
													<div class="input-field">
														<label for="addressByLang_${lang.id}">Adress</label> <input
															type="text" id="addressByLang_${lang.id}"
															name="addressByLang_${lang.id}" /> <span
															id="addressByLangMessage_${lang.id}"></span>
													</div> <script>
														$(document)
																.ready(
																		function() {
																			$(
																					"#addressByLang_${lang.id}")
																					.change(
																							function() {
																								$
																										.ajax({
																											url : "registerAddressValidator.do",
																											type : "post",
																											dataType : "json",
																											data : $(
																													this)
																													.serialize(),
																											success : function(
																													data) {
																												$(
																														"#addressByLangMessage_${lang.id}")
																														.text(
																																_(data.valid));

										                                                                    	
										                                                                    	if(data.valid.indexOf(".ok") >= 0)
										                                                                        	$("#addressByLangMessage_${lang.id}").attr('class','green-text');
										                                                                        else
										                                                                        	$("#addressByLangMessage_${lang.id}").attr('class','red-text');																												
																												
																												
																												

																											}
																										});
																							});
																		});
													</script>
												</td>
											</tr>
										</table>

									</c:forEach>

								</div>

									
									<span id="eventNameMessage" ></span>

							</div>

							<div class="row">
								<div class="input-field col s12">
									<textarea id="description" name="description" rows="10"
										cols="45" maxlength="400" class="materialize-textarea"></textarea>
									<label for="description"><fmt:message key="addEventpage.Description"/>:</label>
								</div>
								<span id="descriptionMessage"></span>
							</div>

<!-- 							<div class="row"> -->
<!-- 								<div class="input-field col s12"> -->
<!-- 									<label for="videoLink"> addEventpage.videoLink:</label> <input -->
<!-- 										type="text" id="videoLink" name="videoLink" /> <span -->
<!-- 										id="videoLinkMessage"></span> -->
<!-- 								</div> -->
<!-- 							</div> -->
						
							<div class="row">
								<div class="col s12">
									

										<input type="text"
												name="partisipant_limit"  id="partisipant_limit">
												<label for="partisipant_limit"> <fmt:message  key="addEventpage.Partisipant_limit"/>  </label>
								</div>
								<span id="partisipant_limitMessage"></span>
								
							</div>


							<fmt:message key="addEventpage.Status"/> : <select class="browser-default" id="status" name="status"
								style="width: 50%; text-align: left; font-size: 100%; text-transform: capitalize">
								<option selected value="guest"><fmt:message key="addEventpage.Guest"/>  </option>
								<option value="resident"><fmt:message key="addEventpage.Resident"/> </option>
							</select>

							<script>
								$('#status').change(function() {
									var selectedValue = $(this).val();

									if (selectedValue == "guest") {

										$('#bedCountSelect').val('need');
									} else {

										$('#bedCountSelect').val('accept');
									}
								});
							</script>



							<br><fmt:message key="addEventpage.Apartments"/>: <select class="browser-default"
								id="bedCountSelect" name="bedCountSelect"
								style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
								<option value="need"><fmt:message key="addEventpage.need"/></option>
								<option value="accept"><fmt:message key="addEventpage.Accepting"/></option>
							</select> <fmt:message key="addEventpage.Persons"/>: <br> 
							<input type="text" name="bad_count" id="bad_count" value="0" style="width: 10%;"> <br>
							<span id="bad_countMessage"></span>
							
							
							<p>
								<input type="hidden" id="cityId" name="cityId" /> 
								<span id="cityIdMessage"></span>							
							</p>



							<p>
							<button class="light-blue btn waves-effect waves-light"
								type="submit" name="action"
								style="margin-left: auto; margin-right: auto; width: 33%;">
								<fmt:message key="addEventpage.Add_event" />   <i class="mdi-content-send right"></i>
							</button>
							</p>


						</form>
					</div>
				</li>
			</ul>
		</div>
	</div>


</fmt:bundle>
</body>
</html>