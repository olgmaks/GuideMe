<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../paymentModal.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#someinput").click(function() {
			var inputed = $(this).val();
			$("#convertedtext").text(inputed * 22);
		});
	});
</script>
<div class="row">


	<div class="col s12" style="margin-top: 10px;">


		<ul class="collection z-depth-2">
			<li class="collection-item" style="height: 50px;">Gallery</li>
		</ul>
		<ul class="collection z-depth-2" style="min-width: 700px;">
			<li class="collection-item">Balance : ${balance}</li>
		</ul>
		<input type="number" id="someinput" style="width: 45px" /> <span
			id="convertedtext"></span> <select id="currency"
			class="browser-default">
			<option value="UAH">UAH</option>
			<option value="USD">USD</option>
			<option value="RUB">RUB</option>
		</select> <br> <a href="#paymentModal" id="sendFriendRequestId2"
			data-userid="2"
			class="btn blue waves-effect waves-light send-friend-request modal-trigger">Refill</a>


	</div>
</div>

