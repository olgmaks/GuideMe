<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/materialize.js"></script>
<div class="row">

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var modalinuserorder = document
									.getElementsByClassName("waves-light btn modal-trigger");
							$(modalinuserorder)
									.click(
											function() {
												if ($(this).attr("id") == "modalinuserorders") {

													$
															.ajax({
																url : 'getUserOrders.do',
																type : 'POST',
																data : {
																	idUser : $(
																			this)
																			.attr(
																					"idofuser")
																},
																success : function(
																		data) {
																	Materialize
																			.toast(
																					data.ids,
																					2000);
																	var yeaaa = data.anarray;

																	for (var int = 0; int < yeaaa.length; int++) {
																		Materialize
																				.toast(
																						yeaaa[1],
																						2000);
																	}

																}
															});
													$('#modal122').openModal();
												}

											});
						});
	</script>

	<c:forEach items="${allMembersOrders }" var="member">

		<%-- 		<c:if test="${member.value.get(0) == null}">no

	</c:if>
		<h4>${member.value.get(0)}</h4> --%>



		<div class="col s12 m5">
			<div class="card-panel">
				<span class="black-text">${member.key.getFirstName()  }
					${member.key.getLastName() }</span>
				<p>
					<span class="blue-text text-darken-4">Order
						${member.value.get(0) }</span>
				<p>
					<span class="green-text text-darken-4">Accepted
						${member.value.get(1) }</span> <a id="modalinuserorders"
						idofuser="${member.key.getId()  }"
						class="waves-light btn modal-trigger" href="#modal122">more</a>
			</div>

		</div>

	</c:forEach>
</div>

