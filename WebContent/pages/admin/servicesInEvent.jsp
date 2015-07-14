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

						var price = parseFloat($("#priceofnecessary").attr(
								"pricenecessary"));

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

												$("#touchable")
														.append(
																"<div id='servppppp' idofserv="+id+" price2="+serviceprice +"><li class='collection-item green lighten-3'>"
																		+ str
																		+ "<i class='mdi-content-remove-circle-outline right'></i></div></li>");

												price = price + serviceprice;
												/* + Number((necessprice)
														.toFixed(1)); */

											}

											/* 	$('.black-text').text('bucket'); */
											$('.grey-text')
													.text(
															'Price '
																	+ Number((price)
																			.toFixed(1))
																	+ ' $');

										});
						$('#touchable').on(
								'click',
								'#servppppp',
								function() {
									if ($(this).attr("id") == "servppppp") {
										var currentId = $(this)
												.attr("idofserv");

										var index = boughtArr
												.indexOf(currentId);
										if (index > -1) {
											boughtArr.splice(index, 1);
										}
										Materialize.toast("Removed", 2000);
										var serviceprice2 = $(this).attr(
												"price2");
										price = price - serviceprice2;
										/* $('.black-text').text('bucket'); */
										$('.grey-text').text(
												'Price '
														+ Number((price)
																.toFixed(1))
														+ ' $');

										$(this).remove();
									}
								});

						$("#buttonbuy")
								.click(
										function() {
											if (price != 0) {
												$("#buyservices").text("buy");
												$("#buyservices").attr("href",
														"buyServices.do");
												$("#generalprice").text(
														Number((price)
																.toFixed(1))
																+ " $");

												$
														.ajax({
															url : 'buyService.do',
															type : 'POST',
															data : {
																boughtArrval : JSON
																		.stringify(boughtArr)
															},
															success : function(
																	data) {

															}
														});
											} else {
												$("#generalprice")
														.text(
																"you didn't add services to buy");
												$("#buyservices")
														.text("cancel");
												$("#buyservices").attr("href",
														"#");
											}
										});

					});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		$('.modal-trigger').leanModal();
	});
</script>
ORDERED SERVICES

<ul class="collection with-header" id="paid"
	data-collapsible="accordion" style="width: 75%;">
	<c:forEach items="${allPaid }" var="service">
		<div id='untouchable'
			idofserv="${service.getServiceInEvent().getService().getId() }"
			price2="${service.getServiceInEvent().getService().getPrice() }">
			<li class='collection-item lime lighten-3'>${service.getServiceInEvent().getService().getName() }
				${service.getServiceInEvent().getService().getPrice() }$ <c:if
					test="${service.isAccepted() }">
					<i class='mdi-toggle-check-box right'></i>
				</c:if>
		</div>
		</li>

	</c:forEach>


</ul>
AVAILABLE SERVICES
<ul class="collapsible" data-collapsible="accordion" style="width: 75%;">
	<c:forEach items="${servicesInEvent }" var="serviceinevent">
		<li>
			<div class="collapsible-header tooltipped green lighten-3"
				price="${serviceinevent.getService().getPrice() }"
				id="${serviceinevent.getId() }" data-position="left" data-delay="50"
				data-tooltip="${serviceinevent.getService().getDescription() }">${serviceinevent.getService().getName() }
				${serviceinevent.getService().getPrice() }$ <i
					class="mdi-action-add-shopping-cart right"></i>
			</div>
		</li>
	</c:forEach>
</ul>
<h4 class="green-text text-darken-4">
	bucket<i class='mdi-action-shopping-basket left'></i>
</h4>

<h4 class="grey-text text-darken-4" id="priceofnecessary"
	pricenecessary=${sumOfAllNecessary }>Price ${sumOfAllNecessary } $</h4>


<a id="buttonbuy" class="waves-light btn modal-trigger" href="#modal1">Order</a>


<ul id="touchable" class="collection with-header">
	<c:forEach items="${neseccaryServices }" var="service">
		<div id='untouchable' idofserv="${service.getId() }"
			price2="${service.getService().getPrice() }">
			<li class='collection-item'>${service.getService().getName() }
				${service.getService().getPrice() }$ <i
				class='mdi-content-circle-outline right'>necessary</i>
		</div>
		</li>
	</c:forEach>




</ul>


<!-- Modal Structure -->
<div id="modal1" class="modal">
	<div class="modal-content">
		<h4>Your total bill</h4>
		<p id="generalprice">$</p>
	</div>
	<div class="modal-footer">
		<a id="buyservices" href="buyServices.do"
			class=" modal-action modal-close waves-effect waves-green btn-flat">Order</a>
	</div>
</div>
