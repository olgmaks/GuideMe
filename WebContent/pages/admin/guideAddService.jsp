<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#dropdowndef').click(function() {
							$("#firstchoosen").prop('disabled', true);
							$("#sub").prop('disabled', false);
							var selectvalue = $('#dropdowndef').val();
							$.ajax({
								url : 'addServicesInEvents.do',
								type : 'GET',
								data : {
									select : selectvalue
								},
								success : function(data) {
									$('#desc').val(data.description);
									$('#price').val(data.price);
								}
							});
						});

						$('#sub')
								.click(
										function() {
											var serviceid = $('#dropdowndef')
													.val();
											var good = 1;
											var price = $('#price').val();
											if (!isNaN(price)) {
												if (price < -1) {
													good = 0;
													Materialize
															.toast(
																	"price can't be less than null",
																	1500);
												}
											} else if (isNaN(price)) {
												good = 0;
												Materialize
														.toast(
																"price must be a number",
																1500);
											}
											var positions = $('#positions')
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
											}
											var datefrom = $('#datefrom').val();
											var timefrom = $('#timefrom').val();
											var dateto = $('#dateto').val();
											var timeto = $('#timeto').val();
											var datefromformated = new Date(
													datefrom);
											var currentdate = new Date();

											if (!document
													.getElementById('test6').checked) {
												if (datefrom == ''
														|| timefrom == ''
														|| dateto == ''
														|| datefrom == '') {
													good = 0;
													Materialize
															.toast(
																	"date and time can't null",
																	1500);
												}
											}

											if (datefrom > dateto) {
												good = 0;
												Materialize
														.toast(
																"date to can't be less than date from",
																1500);
											} else if (currentdate.getYear() > datefromformated
													.getYear()) {
												good = 0;
												Materialize
														.toast(
																"date from can't be less than NOW!",
																1500);
											}

											else {
												if (timefrom > timeto) {
													good = 0;
													Materialize
															.toast(
																	"date to can't be less than date from",
																	1500);
												}
											}

											if (good == 1) {
												$
														.ajax({
															url : 'addGuideServiceToEvent.do',
															type : 'POST',
															data : {
																priceval : price,
																description : $(
																		'#desc')
																		.val(),
																datefromval : datefrom,
																timefromval : timefrom,
																datetoval : dateto,
																timetoval : datefrom,
																serviceidval : serviceid,
																positionsval : $(
																		'#positions')
																		.val()

															},

															success : function(
																	data) {
																if (data.isChanged) {
																	Materialize
																			.toast(
																					"Service Changed",
																					1500);
																}
																Materialize
																		.toast(
																				'<span>Service Added</span><a class=&quot;btn-flat yellow-text&quot; href="delete">Undo<a>',
																				5000)
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
<select id="dropdowndef" style="width: 20%;" class="browser-default">
	<option value="9999" id="firstchoosen">choose</option>
	<c:forEach items="${listOfServices }" var="service">
		<option value='${service.getId() }'>${ service.getName()}</option>
	</c:forEach>
</select>

desciption
<input type="text" style="width: 50%;" id="desc" />
price
<input type="text" style="width: 25%;" id="price" />
date from
<input type="date" style="width: 25%;" id="datefrom" />
<input type="time" style="width: 25%;" id="timefrom" />
<input type="checkbox" id="test6" />
<label for="test6">without dates</label>
<br>
date to
<input type="date" style="width: 25%;" id="dateto" />
<input type="time" style="width: 25%;" id="timeto" />
<br>
amount of positons
<input type="text" style="width: 25%;" id="positions" />
<input type="checkbox" id="test5" />
<label for="test5">unlimited</label>
<br>
<button disabled class="btn waves-effect waves-light" type="submit"
	id="sub" name="action">Add To Event</button>




<%-- addServiceToGuideEvent.do?serviceid=${service.getId() } --%>