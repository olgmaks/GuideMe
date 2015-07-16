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
<!-- Modal Structure -->
<div id="withdrawmoneymodal" class="modal">
	<div class="modal-content">
		<h4>Money Withdraw</h4>
		<div class="row">
			<form class="col s12">
				<div class="row">
					<div class="input-field col s12">
						<input id="recipientnumber" type="text" class="validate" disabled="disabled">
						<label for="recipientnumber"><fmt:message
				key="earnings.account" /></label>
					</div>
					<div class="input-field col s12">
						<input id="amountofmoney" type="text" class="validate"
							value="${allNotWithDrawEarnings }"> <label
							for="amountofmoney"><fmt:message key="earnings.sum" /></label>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn waves-effect waves-light" type="submit"
			name="action" id="payoutmoney"><fmt:message key="earnings.payout" /></button>
		<a href="#!"
			class="modal-action modal-close waves-effect waves-green btn-flat "><fmt:message key="earnings.close" /></a>

	</div>

</div>
</fmt:bundle>