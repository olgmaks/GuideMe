<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/materialize.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var classname = document
								.getElementsByClassName("collapsible-header tooltipped");
						var classname2 = document
								.getElementsByClassName("collection with-header");

						var price = 0;
						var boughtArr = [];

						$(classname)
								.click(
										function() {
											var id = $(this).attr('id');
											var str = $(this).text();

											/* 	$('[id^="servppppp"]').each(
														function() {
															boughtArr[index++] = $(this)
																	.attr('idofserv');
														}); */

											var serviceprice = parseFloat($(
													this).attr('price'));
											var exist = 0;
											for (var int = 0; int < boughtArr.length; int++) {
												if (boughtArr[int] == id) {
													exist = 1;
												}
											}

											if (exist == 1) {
												Materialize
														.toast(
																'Already exist in bucket!',
																2000);
											} else {
												boughtArr.push(id);
												str = str.substring(0,
														str.length - 5);
												/* 	alert($('.' + id).length); */
												Materialize.toast('Good', 2000);

												$(classname2)
														.append(
																"<div id='servppppp' idofserv="+id+" price2="+serviceprice +"><li class='collection-item'>"
																		+ str
																		+ "<i class='mdi-content-remove-circle-outline right'></i></div></li>  ");

												price = price + serviceprice;

											}

											$('.black-text').text('bucket');
											$('.grey-text')
													.text(
															'Price '
																	+ Number((price)
																			.toFixed(1))
																	+ ' $');

										});
						$('body')
								.on(
										'click',
										'#servppppp',
										function() {
											var currentId = $(this).attr(
													"idofserv");

											var index = boughtArr
													.indexOf(currentId);
											if (index > -1) {
												boughtArr.splice(index, 1);
											}

											var serviceprice2 = $(this).attr(
													"price2");
											price = price - serviceprice2;
											$('.black-text').text('bucket');
											$('.grey-text')
													.text(
															'Price '
																	+ Number((price)
																			.toFixed(1))
																	+ ' $');
											$(this).remove();

										});

						/* 					$(classname3)
													.click(
															function() {
																Materialize.toast('sss', 2000);
																serviceprice2 = $(this).attr(
																		'price2');
																var index = boughtArr.indexOf(id);
																if (index > -1) {
																	boughtArr.splice(index, 1);
																}
																$(this).remove();

																var serviceprice2 = parseFloat();

																price = price - serviceprice2;

																$('.black-text').text('bucket');
																$('.grey-text')
																		.text(
																				'Price '
																						+ Number((price)
																								.toFixed(1))
																						+ ' $');
															}); */
						$("#buttonbuy").click(
								function() {
									$("#generalprice").text(
											Number((price).toFixed(1)) + " $");

									$.ajax({
										url : 'buyService.do',
										type : 'POST',
										data : {
											boughtArrval : JSON
													.stringify(boughtArr)
										},
										success : function(data) {

										}
									});

								});

					});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		$('.modal-trigger').leanModal();
	});
</script>
<ul class="collapsible" data-collapsible="accordion" style="width: 75%;">
	<c:forEach items="${servicesInEvent }" var="serviceinevent">
		<li>
			<div class="collapsible-header tooltipped"
				price="${serviceinevent.getService().getPrice() }"
				id="${serviceinevent.getService().getId() }" data-position="left"
				data-delay="50"
				data-tooltip="${serviceinevent.getService().getDescription() }">${serviceinevent.getService().getName() }
				${serviceinevent.getService().getPrice() }$ <i
					class="mdi-content-add-circle-outline right"></i>
			</div>
		</li>
	</c:forEach>
</ul>
<h4 class="green-text text-darken-4">bucket</h4>
<h4 class="grey-text text-darken-4">Price</h4>

<a id="buttonbuy" class="waves-light btn modal-trigger" href="#modal1">Buy</a>
<ul class="collection with-header">



</ul>


<!-- Modal Structure -->
<div id="modal1" class="modal">
	<div class="modal-content">
		<h4>Your total bill</h4>
		<p id="generalprice">$</p>
	</div>
	<div class="modal-footer">
		<a href="#!"
			class=" modal-action modal-close waves-effect waves-green btn-flat">Pay</a>
	</div>
</div>
