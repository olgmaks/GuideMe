<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>



<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.services.messages"
	from="guideservices.do" />

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.services.messages">
	<!-- end Localization -->
	<div id="createservicemodal" class="modal"
		style="margin-right: 55%; width: 25%;">

		<div class="modal-content">
			<div class="row">
				<form class="col s12" method="post" action="addservice.do">
					<div class="row">
						<div class="row">
							<div class="input-field col s6">
								<input placeholder="" type="text" class="validate"
									required="required" name="name"> <label for="textarea1"><fmt:message key="services.name" /></label>
							</div>
							<div class="input-field col s6">
								<input placeholder="" type="text" class="validate"
									name="description"> <label for="textarea1"><fmt:message key="services.description" /></label>
							</div>
							<div class="input-field col s6">
								<input placeholder="" type="number" class="validate"
									required="required" step="any" name="price"> <label
									for="textarea1"><fmt:message key="services.priccee" /></label>
							</div>
						</div>
					</div>
					<button class="btn waves-effect waves-light" type="submit"
						name="action"><fmt:message key="services.add" /></button>
				</form>
			</div>
			<p style="margin-right: 40%;"></p>
		</div>
	</div>
	</fmt:bundle>