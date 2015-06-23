
<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
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

<title>New event</title>
</head>
<body>
	<jsp:include page="header.jsp" />


	<!-- <input type="image"	src="img/guide1.jpg"><br> -->



	<div class="row" style="margin-top: 15px;">
		<div class="col s12" style="width: 60%; margin-left: 20%">
			<ul class="collection z-depth-2 ">
				<li class="collection-item">
					<div class="row">

						<form class="col s12" action="submitEvent.do" method="post">

							<div class="file-field input-field">

								<div class="btn">
									<span>addEventpage.download_image</span> <input type="file"
										accept="image/*" name="avatar" id="avatar" />
								</div>
							</div>
							<br> <br> <br>
							<div class="row">
								<div class="input-field col s12">
									<label for="eventName"> addEventpage.eventName:</label> <input
										type="text" id="eventName" name="eventName" /> <span
										id="eventNameMessage"></span><br>
								</div>
							</div>
							<div class="row">
								<div class=" col s12">
									<table style="width: 100%;">
										<tr>

											<c:forTokens items="From,To" delims="," var="name">

												<td style="width: 34%;"><label for="date">
														addEventpage.date${name}:</label> <input type="date"
													class="datepicker" id="date" name="date${name}" /> <span
													id="dateMessage"></span></td>

												<td style="width: 8%;"><label for="hour"> hour:</label>
													<input type="number" name="hour${name}" min="00" max="23"
													id="hour"></td>
												<td style="width: 0%;">:</td>

												<td style="width: 8%;"><label for="minute">
														minute:</label> <input type="number" name="minute${name}" min="00"
													max="59" id="minute"></td>

											</c:forTokens>
										</tr>
									</table>
								</div>
							</div>
							<div class="row">
								<label>Address</label>

								<div>
									<c:forEach items="${requestScope.languageList}" var="lang">

                                        ${lang.name} :
                                        <table style="width: 100%;">
											<tr>

												<td style="width: 30%;"><label>Country</label> <select
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
																																data.valid);

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
								<input type="hidden" id="cityId" name="cityId" /> <span
									id="cityIdMessage"></span>

							</div>

							<div class="row">
								<div class="input-field col s12">
									<textarea id="description" name="description" rows="10"
										cols="45" maxlength="400" class="materialize-textarea"></textarea>
									<label for="description">addEventpage.Description:</label>
								</div>
							</div>
							<div class="row">
								<div class="col s12">
									<ul class="collection z-depth-2 ">
										<li class="collection-item">

											<div class="row">
												<div class=" col s12">
													<table style="width: 100%;">
														<tr>


															<td style="width: 25%;"><input type="number"
																name="partisipant_limit" min="1" id="partisipant_limit">
																<label for="partisipant_limit">addEventpage.Partisipant_limit</label>

															</td>

															<td style="width: 25%;"><input type="number"
																name="age_limit_from" min="0" id="age_limit_from">
																<label for="age_limit_from">addEventpage.age_from</label>

															</td>
															<td style="width: 25%;"><input type="number"
																name="age_limit_to" min="0"  id="age_limit_to"> <label
																for="age_limit_to">addEventpage.age_to</label></td>

															<td style="width: 25%;"><select id="gender_restriction" name="gender_restriction"
														class="browser-default">
														<option value="" disabled selected>addEventpage.gender_restriction</option>
														<option value="male">only_male</option>
														<option value="female">only_female</option>
														<option value="all">all</option>
													</select></td>


														</tr>
													</table>

													



												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							addEventpage.addition: <input type="file"
								accept="audio/*,video/*,image/*" name="image" multiple>
							<br>


							<button class="light-blue btn waves-effect waves-light"
								type="submit" name="action"
								style="margin-left: auto; margin-right: auto; width: 33%;">
								addEventpage.Add_event <i class="mdi-content-send right"></i>
							</button>


						</form>
					</div>
				</li>
			</ul>
		</div>
	</div>



</body>
</html>