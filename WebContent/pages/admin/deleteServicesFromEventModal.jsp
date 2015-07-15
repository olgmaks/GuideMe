<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		var classname3 = document.getElementsByClassName("collection-item");
		$(classname3).click(function() {
			/* 		alert($(this).attr("idofcurrentservice")); */
			if ($(this).attr("id") == "addedserviceinevent") {

				$(this).remove();
				$.ajax({
					url : 'deleteServiceFromEvent.do',
					type : 'POST',
					data : {
						idval : $(this).attr("idofcurrentservice")
					},
					success : function(data) {

					}
				});
			}

		});
	});
</script>

<div id="modal2" class="modal">
	<div class="modal-content">
		<h5>All Services</h5>
		<ul class="collection">
			<c:forEach items="${allServicesInEvent }" var="serviceInEvent">
				<li id="addedserviceinevent"
					idofcurrentservice="${serviceInEvent.getId() }"
					class="collection-item">${serviceInEvent.getService().getName() }
					${serviceInEvent.getService().getPrice()  }<i
					class='mdi-content-remove-circle-outline right'></i>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>