<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>





<div class="row">
	<c:forEach var="entry" items="${myusersandorders }">
		<div class="col s12 m3">
			<div class="card-panel">
				<span class="black-text">${entry.key.getFirstName() }
					${entry.key.getLastName() }</span>
				<p>
					<span class="black-text"> ${entry.value } </span>
			</div>
		</div>


	</c:forEach>
</div>


<!-- Modal Trigger -->
