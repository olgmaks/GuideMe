
<div id="createservicemodal" class="modal"
	style="margin-right: 55%; width: 25%;">

	<div class="modal-content">
		<div class="row">
			<form class="col s12" method="post" action="addservice.do">
				<div class="row">
					<div class="row">
						<div class="input-field col s6">
							<input placeholder="" type="text" class="validate"
								required="required" name="name"> <label for="textarea1">Name</label>
						</div>
						<div class="input-field col s6">
							<input placeholder="" type="text" class="validate"
								name="description"> <label for="textarea1">Description</label>
						</div>
						<div class="input-field col s6">
							<input placeholder="" type="number" class="validate"
								required="required" step="any" name="price"> <label
								for="textarea1">Price</label>
						</div>

					</div>
				</div>
				<button class="btn waves-effect waves-light" type="submit"
					name="action">Add</button>
			</form>
		</div>
		<p style="margin-right: 40%;"></p>
	</div>
</div>