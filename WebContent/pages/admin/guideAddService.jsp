<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>

<br>
<jsp:include page="deleteServicesFromEventModal.jsp" />
<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.services.messages"
	from="eventsincabinet.do" />

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.services.messages">
	<!-- end Localization -->

	<script type="text/javascript">
		$(document).ready(function() {
			$("#deleteservices").click(function() {

				$('#modal2').openModal();
			});
		});
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var currenteventdateto;
							$('#sourcefrom').click(function() {

								$("#name2").prop('disabled', false);
								if ($('#fromtemplates').is(':checked')) {
									$("#dropdowndef").prop('disabled', false);
									$("#sub").prop('disabled', true);
								} else if ($('#newservice').is(':checked')) {
									$("#dropdowndef").prop('disabled', true);
									$('#name2').val(null);
									$('#desc').val(null);
									$('#price').val(null);
									$("#sub").prop('disabled', false);
								}

								/* 			$.ajax({
																		url : 'infoServlet.do',
																		type : 'GET',
																		success : function(data) {
																			currenteventdateto = data.eventId;
																		}
																	}); */

							});

							$('#dropdowndef').click(function() {
								$("#firstchoosen").prop('disabled', true);
								$("#sub").prop('disabled', false);
								$("#name2").prop('disabled', false);
								var selectvalue = $('#dropdowndef').val();
								$.ajax({
									url : 'addServicesInEvents.do',
									type : 'GET',
									data : {
										select : selectvalue
									},
									success : function(data) {
										$('#name2').val(data.name);
										$('#desc').val(data.description);
										$('#price').val(data.price);
										currenteventdateto = data.eventId;
									}
								});

							});

							$('#sub')
									.click(
											function() {

												var serviceid = $(
														'#dropdowndef').val();
												var good = 1;
												var price = $('#price').val();
												var name = $('#name2').val();

												if (!name) {
													good = 0;
													Materialize
															.toast(
																	_('js.namecantnull'),
																	1500);
												} else if (name.length > 25) {
													good = 0;
													Materialize
															.toast(
																	_('js.namecanttobig'),
																	1500);
												}
												if ($('#desc').val().length > 40) {
													good = 0;
													Materialize
															.toast(
																	_('js.descriptiontobig'),
																	1500);
												}

												if (!isNaN(price)) {
													if (price < -1) {
														good = 0;
														Materialize
																.toast(
																		_('js.pricecantlessnull'),
																		1500);
													}
												} else if (isNaN(price)) {
													good = 0;
													Materialize
															.toast(
																	_('js.pricemustnumber'),
																	1500);
												}
												/* 			var positions = $('#positions')
																	.val();
															if (!isNaN(positions)) {

																if (positions < -1) {
																	good = 0;
																	Materialize
																			.toast(
																					"positions can't be less than null",
																					1500);
																}
															} else if (isNaN(positions)) {
																good = 0;
																Materialize
																		.toast(
																				"positions must be a number",
																				1500);
															} */
												if (document
														.getElementById('test6').checked) {
												} else {

													var datefrom = $(
															'#datefrom').val();
													var timefrom = $(
															'#timefrom').val();
													var dateto = $('#dateto')
															.val();
													var timeto = $('#timeto')
															.val();

													var today = new Date();
													var dd = today.getDate();
													var mm = today.getMonth() + 1; //January is 0!

													var yyyy = today
															.getFullYear();
													if (dd < 10) {
														dd = '0' + dd
													}
													if (mm < 10) {
														mm = '0' + mm
													}
													var today = yyyy + '-' + mm
															+ '-' + dd;

													if (!document
															.getElementById('test6').checked) {

														if (datefrom == ''
																|| timefrom == ''
																|| dateto == ''
																|| datefrom == '') {

															good = 0;
															Materialize
																	.toast(
																			_('js.datecantnull'),
																			1500);
														}
													}

													if (datefrom > dateto) {
														good = 0;
														Materialize
																.toast(
																		_('js.datecantlessto'),
																		1500);
													} else if (datefrom < today) {
														good = 0;
														Materialize
																.toast(
																		_('js.datecantlessnow'),
																		1500);
													} else if (dateto > currenteventdateto) {
														good = 0;
														Materialize
																.toast(
																		_('js.datecantbiggerfrom'),
																		1500);
													}

													else if (datefrom == dateto) {
														if (timefrom > timeto) {
															good = 0;
															Materialize
																	.toast(
																			_('js.datetocantlessfrom'),
																			1500);
														} else if (timefrom == timeto) {
															good = 0;
															Materialize
																	.toast(
																			_('js.datetocantequelsdateto'),
																			1500);
														}
													}

												}
												if (good == 1) {
													$
															.ajax({
																url : 'addGuideServiceToEvent.do',
																type : 'POST',
																data : {
																	priceval : parseFloat(
																			price)
																			.toFixed(
																					1),
																	description : $(
																			'#desc')
																			.val(),
																	datefromval : datefrom,
																	timefromval : timefrom,
																	datetoval : dateto,
																	timetoval : timeto,
																	serviceidval : serviceid,
																	nameval : name,

																	positionsval : $(
																			'#positions')
																			.val(),
																	isnecessaryval : document
																			.getElementById('isNecessary').checked

																},

																success : function(
																		data) {
																	var stringToShow = _('js.serviceadded')
																	if (data.isChanged) {
																		stringToShow = _('js.newserviceadded');
																	}
																	Materialize
																			.toast(
																					stringToShow,
																					5000);
																	$(
																			"#dropdowndef")
																			.prop(
																					'disabled',
																					true);
																	$('#name2')
																			.val(
																					null);
																	$('#desc')
																			.val(
																					null);
																	$('#price')
																			.val(
																					null);
																	$("#sub")
																			.prop(
																					'disabled',
																					false);
																}
															});

												}

											});

							$('#test5').click(function() {
								if (document.getElementById('test5').checked) {
									$("#positions").prop('disabled', true);
								} else {
									$("#positions").prop('disabled', false);
								}
							});
							$('#test6').click(function() {
								if (document.getElementById('test6').checked) {
									$("#datefrom").prop('disabled', true);
									$("#dateto").prop('disabled', true);
									$("#timefrom").prop('disabled', true);
									$("#timeto").prop('disabled', true);
								} else {
									$("#datefrom").prop('disabled', false);
									$("#dateto").prop('disabled', false);
									$("#timefrom").prop('disabled', false);
									$("#timeto").prop('disabled', false);
								}
							});

						});
	</script>
	<div id="sourcefrom">
		<input name="group1" type="radio" id="fromtemplates" checked="checked" />
		<label for="fromtemplates"><fmt:message
				key="services.fromtemplates" /></label> <input name="group1" type="radio"
			id="newservice" /> <label for="newservice"><fmt:message
				key="services.new" /></label>

	</div>
	<select id="dropdowndef" style="width: 20%;" class="browser-default">
		<option id="firstchoosen"><fmt:message key="services.choose" /></option>
		<c:forEach items="${listOfServices }" var="service">
			<option value='${service.getId() }'>${ service.getName()}</option>
		</c:forEach>
	</select>

	<fmt:message key="services.name" />
	<input type="text" style="width: 50%;" disabled id="name2" />
	<br>
	<fmt:message key="services.description" />
	<input type="text" style="width: 50%;" id="desc" />
	<fmt:message key="services.price" />
	<input type="text" style="width: 20%;" id="price" />
	<br>
	<fmt:message key="services.datefrom" />
	<input type="date" style="width: 25%;" id="datefrom" />
	<input type="time" style="width: 25%;" id="timefrom" />
	<input type="checkbox" id="test6" />
	<label for="test6"><fmt:message key="services.withoutdates" /></label>
	<br>

	<fmt:message key="services.dateto" />
	<input type="date" style="width: 25%;" id="dateto" />
	<input type="time" style="width: 25%;" id="timeto" />
	<br>
	<!-- amount of positons
<input type="text" style="width: 25%;" id="positions" />
<input type="checkbox" id="test5" />
<label for="test5">unlimited</label> -->
	<p>
		<fmt:message key="services.isnecessary" />
	<div class="switch">
		<label> <fmt:message key="services.no" /> <input
			id="isNecessary" type="checkbox"> <span class="lever"></span>
			<fmt:message key="services.yes" />
		</label>
	</div>

	<br>
	<button disabled class="waves-light btn" type="submit" id="sub"
		name="action">
		<fmt:message key="services.addtoevent" />
	</button>
	<br>
	<br>
	<a id="deleteservices" class="waves-light btn modal-trigger"
		href="#modal2"><fmt:message key="services.deletefromevent" /></a>

</fmt:bundle>

<%-- addServiceToGuideEvent.do?serviceid=${service.getId() } --%>