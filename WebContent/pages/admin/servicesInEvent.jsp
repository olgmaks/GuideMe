<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>
<script src="js/materialize.js"></script>
<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.services.messages"
	from="eventsincabinet.do" />

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.services.messages">
	<!-- end Localization -->
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
																	_('js.alreadyexistinbag'),
																	2000);
												} else {
													boughtArr.push(id);
													str = str.substring(0,
															str.length - 5);
													/* 	alert($('.' + id).length); */
													Materialize
															.toast(
																	_('js.added'),
																	2000);

													$("#touchable")
															.append(
																	"<div id='servppppp' idofserv="+id+" price2="+serviceprice +"><li class='collection-item green lighten-3'>"
																			+ str
																			+ "<i class='mdi-content-remove-circle-outline right'></i></div></li>");

													price = price
															+ serviceprice;
													/* + Number((necessprice)
															.toFixed(1)); */

												}

												/* 	$('.black-text').text('bucket'); */
												$('.grey-text')
														.text(
																_('js.price') +" "
																		+ Number((price)
																				.toFixed(1))
																		+ ' $');

											});
							$('#touchable')
									.on(
											'click',
											'#servppppp',
											function() {
												if ($(this).attr("id") == "servppppp") {
													var currentId = $(this)
															.attr("idofserv");

													var index = boughtArr
															.indexOf(currentId);
													if (index > -1) {
														boughtArr.splice(index,
																1);
													}
													Materialize.toast(
															_('js.removed'), 2000);
													var serviceprice2 = $(this)
															.attr("price2");
													price = price
															- serviceprice2;
													/* $('.black-text').text('bucket'); */
													$('.grey-text')
															.text(
																	_('js.price')+" "
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
													$("#buyservices").text(
															"Buy");
													$("#buyservices").attr(
															"href",
															"buyServices.do");
													$("#generalprice")
															.text(
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
																	_('js.noservicesadded'));
													$("#buyservices").text(
															"cancel");
													$("#buyservices").attr(
															"href", "#");
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
	<fmt:message key="servicesinevent.paidservices" />

	<ul class="collection with-header" id="paid"
		data-collapsible="accordion" style="width: 75%;">
		<c:forEach items="${allPaid }" var="service">
			<div id='untouchable'
				idofserv="${service.getServiceInEvent().getService().getId() }"
				price2="${service.getServiceInEvent().getService().getPrice() }">
				<li class='collection-item lime lighten-3'>${service.getServiceInEvent().getService().getName() }
					${service.getServiceInEvent().getService().getPrice() }$ <i
					class='mdi-toggle-check-box right'></i>
			</div>
			</li>

		</c:forEach>


	</ul>
	<fmt:message key="servicesinevent.availableservices" />
	<ul class="collapsible" data-collapsible="accordion"
		style="width: 75%;">
		<c:forEach items="${servicesInEvent }" var="serviceinevent">
			<li>
				<div class="collapsible-header tooltipped green lighten-3"
					price="${serviceinevent.getService().getPrice() }"
					id="${serviceinevent.getId() }" data-position="left"
					data-delay="50"
					data-tooltip="${serviceinevent.getService().getDescription() }  ">${serviceinevent.getService().getName() }
					${serviceinevent.getService().getPrice() }$
					<c:if test="${!empty serviceinevent.getDateFrom()}">
						<fmt:message key="services.datefrom" />:
				<fmt:formatDate type='both' dateStyle='short' timeStyle='short'
							value='${serviceinevent.getDateFrom()}' />
						<fmt:message key="services.dateto" />:
				
				</c:if>
					<fmt:formatDate type='both' dateStyle='short' timeStyle='short'
						value='${serviceinevent.getDateTo()}' />
					<i class="mdi-action-add-shopping-cart right"></i>
				</div>
			</li>
		</c:forEach>
	</ul>

	<h4 class="green-text text-darken-4">
		<fmt:message key="servicesinevent.bag" />
		<i class='mdi-action-shopping-basket left'></i>
	</h4>

	<h4 class="grey-text text-darken-4" id="priceofnecessary"
		pricenecessary=${sumOfAllNecessary }>
		<fmt:message key="servicesinevent.price" />
		${sumOfAllNecessary } $
	</h4>


	<a id="buttonbuy" class="waves-light btn modal-trigger" href="#modal1">
		<fmt:message key="servicesinevent.buy" />
	</a>


	<ul id="touchable" class="collection with-header">
		<c:forEach items="${neseccaryServices }" var="service">
			<div id='untouchable' idofserv="${service.getId() }"
				price2="${service.getService().getPrice() }">
				<li class='collection-item'>${service.getService().getName() }
					${service.getService().getPrice() }$ <i
					class='mdi-content-circle-outline right'><fmt:message
							key="servicesinevent.necessary" /></i>
			</div>
			</li>
		</c:forEach>




	</ul>


	<!-- Modal Structure -->
	<div id="modal1" class="modal">
		<div class="modal-content">
			<h4>
				<fmt:message key="servicesinevent.totalbill" />
			</h4>
			<p id="generalprice">$</p>
		</div>
		<div class="modal-footer">
			<a id="buyservices" href="buyServices.do"
				class=" modal-action modal-close waves-effect waves-green btn-flat">
				<fmt:message key="servicesinevent.buy" />
			</a>
		</div>
	</div>
</fmt:bundle>