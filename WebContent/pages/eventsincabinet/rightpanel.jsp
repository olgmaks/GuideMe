
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<br>
<c:if test="${centralContent == 'eventsincabinet' }">
</c:if>
<div class="collection" style="margin-top: 5%;">
	<a href="eventsincabinet.do"
		class="collection-item <c:if test="${centralContent == 'eventsincabinet' }">active</c:if>
		"
		id="test">All</a> <a href="moderatoEvents.do"
		class="collection-item  <c:if test="${centralContent == 'moderatorevent' }">active</c:if>">Own</a>


	<a href="memberEvents.do"
		class="collection-item  <c:if test="${centralContent == 'memberevent' }">active</c:if>">Member</a>

</div>