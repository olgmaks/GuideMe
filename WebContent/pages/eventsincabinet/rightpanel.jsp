
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
<script type="text/javascript">
	$(document).ready(function() {
		$("#resident").click(function() {
			$(this).attr("class", "collection-item active");
			$("#all").attr("class", "collection-item");
			$("#own").attr("class", "collection-item");
			$("#yourrequests").attr("class", "collection-item");
			$("#membereventjsp").removeAttr("hidden");
			$("#alleventjsp").attr("hidden", "");
			$("#moderatoreventjsp").attr("hidden", "");
			$("#requestsjsp").attr("hidden", "");
		});
		$("#all").click(function() {
			$(this).attr("class", "collection-item active");
			$("#own").attr("class", "collection-item");
			$("#resident").attr("class", "collection-item");
			$("#yourrequests").attr("class", "collection-item");
			$("#alleventjsp").removeAttr("hidden");
			$("#membereventjsp").attr("hidden", "");
			$("#moderatoreventjsp").attr("hidden", "");
			$("#requestsjsp").attr("hidden", "");
		});
		$("#own").click(function() {
			$(this).attr("class", "collection-item active");
			$("#resident").attr("class", "collection-item");
			$("#all").attr("class", "collection-item");
			$("#yourrequests").attr("class", "collection-item");

			$("#moderatoreventjsp").removeAttr("hidden");
			$("#alleventjsp").attr("hidden", "");
			$("#membereventjsp").attr("hidden", "");
			$("#requestsjsp").attr("hidden", "");
		});
		$("#yourrequests").click(function() {
			$(this).attr("class", "collection-item active");
			$("#resident").attr("class", "collection-item");
			$("#all").attr("class", "collection-item");
			$("#own").attr("class", "collection-item");

			$("#requestsjsp").removeAttr("hidden");
			$("#alleventjsp").attr("hidden", "");
			$("#membereventjsp").attr("hidden", "");
			$("#moderatoreventjsp").attr("hidden", "");
		});
	});
</script>
<br>
<c:if test="${centralContent == 'eventsincabinet' }">
</c:if>
<div id="alleventjsp">
	<jsp:include page="allevents.jsp" /></div>
<div hidden="" id="moderatoreventjsp">
	<jsp:include page="moderatorevents.jsp" /></div>
<div hidden="" id="membereventjsp">
	<jsp:include page="memberevents.jsp" /></div>


<div hidden="" id="requestsjsp">
	<jsp:include page="requests.jsp" /></div>


<%-- 
<a href="eventsincabinet.do"
	class="collection-item <c:if test="${centralContent == 'eventsincabinet' }">active</c:if>"
	id="test">All</a>
<a href="moderatoEvents.do"
	class="collection-item  <c:if test="${centralContent == 'moderatorevent' }">active</c:if>">Own</a>

<a href="memberEvents.do"
	class="collection-item  <c:if test="${centralContent == 'memberevent' }">active</c:if>">Member</a>
 --%>



<td style="width: 20%; vertical-align: top;">

	<ul class="collection with-header">
		<li class="collection-header"><h4><fmt:message
				key="eventinservice.menu" /></h4></li>
		<li id="all" class="collection-item active"><a href="#"><span class="black-text"> <fmt:message
				key="eventinservice.all" /></span></a></li>
		<li id="resident" class="collection-item"><a href="#"><span class="black-text"><fmt:message
				key="eventinservice.resident" /></span></a></li>
		<li id="own" class="collection-item"><a href="#"><span class="black-text"><fmt:message
				key="eventinservice.own" /></span></a></li>

	</ul> <br>
	<ul class="collection with-header">
		<li id="yourrequests" class="collection-item"><a href="#"><fmt:message
				key="eventinservice.yourrequests" /></a></li>
	</ul>
</td>
</fmt:bundle>