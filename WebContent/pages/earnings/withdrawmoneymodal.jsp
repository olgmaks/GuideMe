
<!-- Modal Structure -->
<div id="withdrawmoneymodal" class="modal">
	<div class="modal-content">
		<h4>Money Withdraw</h4>
		<div class="row">
			<form class="col s12">
				<div class="row">
					<div class="input-field col s12">
						<input id="recipientnumber" type="text" class="validate">
						<label for="recipientnumber">Recipient's Web Money Account
							Number</label>
					</div>
					<div class="input-field col s12">
						<input id="amountofmoney" type="text" class="validate"
							value="${allNotWithDrawEarnings }"> <label
							for="amountofmoney">Amount of money</label>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn waves-effect waves-light" type="submit"
			name="action" id="payoutmoney">Payout</button>
		<a href="#!"
			class="modal-action modal-close waves-effect waves-green btn-flat ">Close</a>

	</div>

</div>