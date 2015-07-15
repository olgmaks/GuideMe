<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<script type="text/javascript">
	$(document).ready(
			function() {
				var butt = document.getElementsByClassName("btn blue");
				$(butt).click(
						function() {
							if ($(this).attr("id") == "cancelrequestinevent"
									&& $(this).text() != "canceled") {

								Materialize.toast("deleted", 2000);
								$(this).attr("class", "btn red disabled");
								$(this).text("canceled");
								$.ajax({

									url : 'deleteRequestToEvent.do',
									type : 'POST',
									data : {
										requestidval : $(this).attr(
												"userineventcurrentid")
									},
									success : function(data) {

									}
								});
							}
						});

			});
</script>
<table>
	<thead>
		<tr>
			<th data-field="id">Your requests.</th>
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
								href="http://localhost:8080/GuideMe/eventDetail.do?id=${userinservice.getEvent().getId() }">Event
								Detail</a>
						</div>

						<button id="cancelrequestinevent"
							userineventcurrentid="${userinservice.getEvent().getId() }"
							class="btn blue" style="width: 100%;" type="button" name="action">Cancel</button>

					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<span>No requests yet</span>
		</c:otherwise>
	</c:choose>
</div>
