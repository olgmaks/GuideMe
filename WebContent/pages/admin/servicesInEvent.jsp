<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/materialize.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var classname = document
								.getElementsByClassName("collapsible-header tooltipped");
						$(classname).click(function() {
							alert($(this).attr('id'));
						});
					});
</script>
<ul class="collapsible" data-collapsible="accordion" style="width: 30%;">
	<c:forEach items="${servicesInEvent }" var="serviceinevent">
		<li>
			<div class="collapsible-header tooltipped"
				id="${serviceinevent.getService().getId() }" data-position="left"
				data-delay="50"
				data-tooltip="${serviceinevent.getService().getDescription() }">${serviceinevent.getService().getName() }
				${serviceinevent.getService().getPrice() }$ <a
					class="btn-floating btn-medium waves-effect waves-lightgreen"><i
					class="material-icons">+</i></a>
			</div>
		</li>
	</c:forEach>
</ul>