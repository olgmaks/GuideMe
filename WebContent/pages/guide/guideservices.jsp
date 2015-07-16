<!-- guide services page -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>



<!-- begin Localization -->
<jsp:include page="../localization.jsp" />
<ct:showLocale basename="locale.services.messages"
	from="guideservices.do" />

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.services.messages">
	<!-- end Localization -->
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.modal-trigger').leanModal();
		});
	</script>
	<br>
	<br>
	<jsp:include page="createservicemodal.jsp" />
	<a style="margin-left: 2%;"
		class="modal-trigger btn-floating btn-large waves-effect waves-light red"
		href="#createservicemodal" id="createserivece"> <i
		class="material-icons">+</i></a>
<fmt:message key="services.addnewservice" />
<br>


	<div class="row">
		<c:forEach items="${guideservices }" var="service">
			<div class="col s1 m3">
				<div class="card light-blue lighten-5">
					<div class="card-content black-text">
						<span class="card-title black-text"><c:out
								value="${service.getName() }" /> </span>
						<p class="truncate">
							<fmt:message key="services.desc" />
							
							<c:out value="${service.getDescription() }" />
						</p>
						<p><fmt:message key="services.description" /> ${service.getPrice() }</p>
					</div>
					<div class="card-action">
						<!-- 		<a class="modal-trigger" href="#editservicemodal">Edit</a> -->
						<a href="deleteeditservice.do?act=delete&id=${service.getId() }"><fmt:message key="services.delete" /></a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</fmt:bundle>