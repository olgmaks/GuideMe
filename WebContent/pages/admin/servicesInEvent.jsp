<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>
<script src="js/moment.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('a').click(function() {
			alert($(this).attr('value'));
		});
	});
</script>
<ul class="collapsible" data-collapsible="accordion" style="width: 30%;">
	<c:forEach items="${servicesInEvent }" var="serviceinevent">
		<li value="${serviceinevent.getService().getId() }">
			<div class="collapsible-header tooltipped" data-position="left"
				data-delay="50"
				data-tooltip="${serviceinevent.getService().getDescription() }">${serviceinevent.getService().getName() }
				${serviceinevent.getService().getPrice() }$ <a
					class="btn-floating btn-medium waves-effect waves-light green"><i
					class="material-icons">+</i></a>
			</div>
		</li>
	</c:forEach>

</ul>