<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>


<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.eventsincabinet.messages"
	from="eventsincabinet.do" />

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.eventsincabinet.messages">
	<!-- end Localization -->
	<br>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var butt = document
									.getElementsByClassName("btn blue");
							$(butt)
									.click(
											function() {
												if ($(this).attr("id") == "cancelrequestinevent"
														&& $(this).text() != "canceled") {

													Materialize.toast(
															"deleted", 2000);
													$(this).attr("class",
															"btn red disabled");
													$(this).text(_("js.canceled"));
													$
															.ajax({

																url : 'deleteRequestToEvent.do',
																type : 'POST',
																data : {
																	requestidval : $(
																			this)
																			.attr(
																					"userineventcurrentid")
																},
																success : function(
																		data) {

																}
															});
												}
											});

						});
	</script>
	<table>
		<thead>
			<tr>
				<th data-field="id"><fmt:message
						key="eventinservice.yourrequests" /></th>
			</tr>
		</thead>
	</table>
	<br>

	<div class="row">
		<c:choose>
			<c:when test="${!empty userRequestsToEvents }">
				<c:forEach items="${ userRequestsToEvents}" var="userinservice">
					<div class="col s1 m2">
						<div class="card">
							<div class="card-image">
								<img src="${userinservice.getEvent().getAvatar().getPath() }">

							</div>
							<div class="card-content">
								<p>${userinservice.getEvent().getCutName(25)}</p>
							</div>
							<div class="card-action">
								<a
									href="http://localhost:8080/GuideMe/eventDetail.do?id=${userinservice.getEvent().getId() }"><fmt:message
										key="eventinservice.eventdetail" /></a>
							</div>

							<button id="cancelrequestinevent"
								userineventcurrentid="${userinservice.getEvent().getId() }"
								class="btn blue" style="width: 100%;" type="button"
								name="action">
								<fmt:message key="eventinservice.cancel" />
							</button>

						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<span><fmt:message key="eventinservice.norequests" /></span>
			</c:otherwise>
		</c:choose>
	</div>
</fmt:bundle>