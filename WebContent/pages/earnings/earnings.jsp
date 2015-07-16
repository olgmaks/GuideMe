<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>
	
	<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.earnings.messages"
	from="earnings.do" />

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.earnings.messages">
	<!-- end Localization -->
<jsp:include page="withdrawmoneymodal.jsp" />
<br>
<br>
<blockquote><fmt:message
				key="earnings.estimated" /></blockquote>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#payoutmoney")
								.click(
										function() {
											var amountofmoney = $("#amountofmoney");
											var recipientnumber = $("recipientnumber");
											var good = 1;

											if (isNaN(amountofmoney.val())) {
												Materialize.toast(
														'Must be number', 2000);
												good = 0;
											} else if (amountofmoney.val() < 25) {
												good = 0;
												Materialize
														.toast(
																_('js.notbigger'),
																2000);

											} else {

												var totalamountofmoney = '<c:out value="${allNotWithDrawEarnings}"/>';

												if (amountofmoney.val() > parseFloat(totalamountofmoney)) {
													Materialize
															.toast(
																	_('js.nosuchmoney'),
																	2000);
													good = 0;
												}
											}
											if (good == 1) {
												$
														.ajax({
															url : 'makeWithDraw.do',
															type : 'POST',
															data : {
																amountofmoneyval : parseFloat(
																		amountofmoney
																				.val())
																		.toFixed(
																				1)

															},
															success : function(
																	data) {
																location
																		.reload(true);
															}

														});

												Materialize.toast(
														_('js.successpayout'),
														6000);
												$('#withdrawmoneymodal')
														.closeModal();
											}
										});

					});
</script>

<div class="row">
	<div class="col s5 m4">
		<div class="card-panel grey lighten-4">
			<span class="black-text">$${todayEarnings }</span><br> <br>
			<span class="black-text"><fmt:message
				key="earnings.today" /></span>
		</div>
	</div>
	<div class="col s5 m4">
		<div class="card-panel grey lighten-4">
			<span class="black-text">$${yesterdayEarnings }</span><br> <br>
			<span class="black-text"><fmt:message
				key="earnings.yesterday" /></span>
		</div>
	</div>
	<div class="col s5 m4">
		<div class="card-panel grey lighten-4">
			<span class="black-text">$${thisMonthEarnings }</span><br> <br>
			<span class="black-text"><fmt:message
				key="earnings.thismonth" /></span>
		</div>
	</div>
	<div class="col s5 m4">
		<div class="card-panel grey lighten-4">
			<span class="black-text">$${lastMonthEarnings }</span><br> <br>
			<span class="black-text"><fmt:message
				key="earnings.lastmonth" /></span>
		</div>
	</div>
</div>
<blockquote><fmt:message
				key="earnings.finalized" /></blockquote>
<div class="row teal accent-1">
	<div class="col s5 m4">
		<div class="card-panel grey lighten-4">
			<span id="totalmountofmoney" totalamount="${allNotWithDrawEarnings }"
				class="black-text">$<fmt:formatNumber
					value="${allNotWithDrawEarnings }" maxFractionDigits="1" /></span><br>
			<br> <span class="black-text"><fmt:message
				key="earnings.total" /></span>
			<!-- <div class="center-align"> -->
			<p>
				<a class="modal-trigger waves-effect waves-light btn"
					href="#withdrawmoneymodal"><fmt:message
				key="earnings.withdraw" /></a>
		</div>

	</div>

</div>
<br>
<blockquote><fmt:message
				key="earnings.history" /></blockquote>
<ul class="collection">
	<c:forEach items="${allWithdraws }" var="withdrawmoney">
		<li class="collection-item"><span><fmt:formatDate
					type="both" dateStyle="short" timeStyle="short"
					value="${ withdrawmoney.getWithdrawDate()}" /></span><span
			class="purple lighten-5" style="margin-left: 78%;">-${ withdrawmoney.getMoneyAmount()}$</span></li>
	</c:forEach>
</ul>
</fmt:bundle>