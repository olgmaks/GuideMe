<!-- guide services page -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
Add new service
<br>


<div class="row">
	<c:forEach items="${guideservices }" var="service">
		<div class="col s1 m3">
			<div class="card light-blue lighten-5">
				<div class="card-content black-text">
					<span class="card-title black-text"><c:out
							value="${service.getName() }" /> </span>
					<p class="truncate">
						Description:
						<c:out value="${service.getDescription() }" />
					</p>
					<p>One Person Price: ${service.getPrice() }</p>
				</div>
				<div class="card-action">
					<!-- 		<a class="modal-trigger" href="#editservicemodal">Edit</a> -->
					<a href="deleteeditservice.do?act=delete&id=${service.getId() }">delete</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
