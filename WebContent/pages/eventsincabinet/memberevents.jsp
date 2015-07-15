<!-- events where user member -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>


<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.eventsincabinet.messages"
	from="eventsincabinet.do" />

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.eventsincabinet.messages">
	<!-- end Localization -->
	<br>
	<table>
		<thead>
			<tr>
				<th data-field="id"><fmt:message
						key="eventinservice.eventwheremember" /> <fmt:message
						key="eventinservice.actual" /></th>

			</tr>
		</thead>
	</table>
	<br>
	<div class="row">
		<c:choose>
			<c:when test="${!empty listOfUserInEvent }">
				<c:forEach items="${listOfUserInEvent }" var="usersevent">

					<div class="col s4 m3">
						<div class="card">
							<div class="card-image">
								<img style="height: 140px; width: 100%; object-fit: cover;"
									src="${usersevent.getEvent().avatar.getPath() }">
							</div>
							<div class="card-content">
								<p>${usersevent.getEvent().getCutName(25) }</p>
								<br> <span class="grey-text text-darken-1"> <fmt:message
										key="eventinservice.datefrom" />
								</span>
								<p>
									<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
										value="${usersevent.getEvent().getDateFrom()}" />
								</p>
								<br> <span class="grey-text text-darken-2"> <fmt:message
										key="eventinservice.dateto" />
								</span>
								<p>
									<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
										value="${usersevent.getEvent().getDateTo()}" />
								</p>
							</div>
							<div class="card-action">
								<a href="eventDetail.do?id=${usersevent.getEvent().getId() } "><fmt:message
										key="eventinservice.eventdetail" /></a>
							</div>
						</div>
					</div>

				</c:forEach>
			</c:when>
			<c:otherwise>
				<span><fmt:message key="eventinservice.noneevent" /></span>
			</c:otherwise>
		</c:choose>
	</div>
	<table>
		<thead>
			<tr>
				<th data-field="id"><fmt:message key="eventinservice.old" /></th>
			</tr>
		</thead>
	</table>
	<div class="row">
		<c:choose>
			<c:when test="${!empty listOfUserInEvent }">
				<c:forEach items="${listOfOldUserInEvent }" var="usersevent">

					<div class="col s4 m3">
						<div class="card">
							<div class="card-image">
								<img class="square"
									style="height: 140px; width: 100%; object-fit: cover"
									src="${usersevent.getEvent().avatar.getPath() }">
							</div>
							<div class="card-content">
								<p>${usersevent.getEvent().getCutName(25) }</p>
								<br> <span class="grey-text text-darken-1"><fmt:message
										key="eventinservice.datefrom" /> </span>
								<p>
									<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
										value="${usersevent.getEvent().getDateFrom()}" />
								</p>
								<br> <span class="grey-text text-darken-2"><fmt:message
										key="eventinservice.dateto" /> </span>
								<p>
									<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
										value="${usersevent.getEvent().getDateTo()}" />
								</p>
							</div>
							<div class="card-action">
								<a href="eventDetail.do?id=${usersevent.getEvent().getId() } "><fmt:message
										key="eventinservice.eventdetail" /></a>
							</div>
						</div>
					</div>

				</c:forEach>
			</c:when>
			<c:otherwise>
				<br>
				<span><fmt:message key="eventinservice.noneevent" /></span>
			</c:otherwise>
		</c:choose>
	</div>
</fmt:bundle>