<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#someinput")
								.change(
										function() {
											var inputed = $(this).val();
											var good = 1;
											if (inputed < 0) {
												var good = 0;
												Materialize
														.toast(
																"Can't be less than null",
																2000);
												$("#someinput").val(0);
												$("#convertedtextdollars")
														.text(0);
											} else if (isNaN(inputed)) {
												var good = 0;
												Materialize.toast(
														"must be number", 2000);
												$("#someinput").val(0);
												$("#convertedtextdollars")
														.text(0);
											} else {
												$("#convertedtextdollars")
														.text(
																inputed
																		* 0.5
																		+ " dol");
												$("#convertedtexthrn").text(
														inputed * 11 + " hrn");
												$("#convertedtextrub").text(
														inputed * 26 + " rub");
												var rubsum = inputed * 26;
												$("#hrefto")
														.attr(
																"src",
																"https://money.yandex.ru/embed/shop.xml?account=${account}&quickpay=shop&payment-type-choice=on&writer=seller&targets=${eventNamePay}&targets-hint=&default-sum="
																		+ rubsum
																		+ "&button-text=01&comment=on&hint=&successURL=http%3A%2F%2Flocalhost%3A8080%2FGuideMe%2FaddEvent.do")
											}

										});
					});
</script>
<div style="margin-right: 5%;" class="row">


	<div class="col s12" style="margin-top: 10px;">


		<%-- 		<ul class="collection z-depth-2">
			<li class="collection-item" style="height: 50px;">Gallery</li>
		</ul>
		<ul class="collection z-depth-2" style="min-width: 700px;">
			<li class="collection-item">Balance : ${balance}</li>
		</ul> --%>
		<!-- <input type="number" id="someinput" style="width: 45px" /> <span
			id="convertedtext"></span> <select id="currency"
			class="browser-default">
			<option selected="selected" value="UAH">UAH</option>
			<option value="USD">USD</option>
			<option value="RUB">RUB</option>
		</select> <br> --->
		<input type="text" id="someinput" /> <span id="convertedtextdollars"></span>
		<p>
			<span id="convertedtexthrn" /></span><p>
		 <span id="convertedtextrub" /></span><p>
			<a href="#paymentModal" id="sendFriendRequestId2" data-userid="2"
				class="btn blue waves-effect waves-light
		send-friend-request modal-trigger">Refill</a>
	</div>
</div>



<div id="paymentModal" class="modal"
	style="margin-left: 60%; width: 37%;">
	<!-- Show this window when user not logged -->
	<form id="paymentform">
		<div class="modal-content">
			<iframe id="hrefto" frameborder="0" allowtransparency="true"
				scrolling="no"
				src="https://money.yandex.ru/embed/shop.xml?account=${account}&quickpay=shop&payment-type-choice=on&writer=seller&targets=${eventNamePay}&targets-hint=&default-sum=15&button-text=01&comment=on&hint=&successURL=http%3A%2F%2Flocalhost%3A8080%2FGuideMe%2FaddEvent.do"
				width="450" height="253"></iframe>
		</div>
	</form>
</div>