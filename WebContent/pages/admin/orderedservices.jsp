<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>



<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.services.messages"
	from="eventsincabinet.do" />

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.services.messages">
	<!-- end Localization -->
	<div class="row">
		<c:forEach var="entry" items="${myusersandorders }">
			<div class="col s12 m3">
				<div class="card-panel">
					<span class="black-text">${entry.key.getFirstName() }
						${entry.key.getLastName() }</span>
					<p>
						<c:choose>
							<c:when test="${!empty entry.value }">
								<span class="black-text"> ${entry.value } </span>
							</c:when>
							<c:otherwise>
								<span><fmt:message key="orderedservices.noorders" /></span>
							</c:otherwise>
						</c:choose>
				</div>
			</div>


		</c:forEach>
	</div>
</fmt:bundle>

<!-- Modal Trigger -->
