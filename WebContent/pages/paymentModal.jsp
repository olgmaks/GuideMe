

<div id="paymentModal" class="modal"
	style="margin-left: 60%; width: 37%;">
	<!-- Show this window when user not logged -->
	<form id="paymentform">
		<div class="modal-content">
			<iframe frameborder="0" allowtransparency="true" scrolling="no"
				src="https://money.yandex.ru/embed/shop.xml?account=${account}&quickpay=shop&payment-type-choice=on&writer=seller&targets=${eventNamePay}&targets-hint=&default-sum=15&button-text=01&comment=on&hint=&successURL=http%3A%2F%2Flocalhost%3A8080%2FGuideMe%2FaddEvent.do"
				width="450" height="253"></iframe>
		</div>
	</form>
</div>



