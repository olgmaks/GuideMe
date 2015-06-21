<div id="signInModal" class="modal"
	style="margin-left: 60%; width: 35%;">
	<!-- Show this window when user not logged -->
	<form id="loginform">
		<div class="modal-content">
			<p style="margin-left: 40%;">Login form</p>

			<div class="row">
				<div class="row">
					<div class="input-field col s12">
						<input id="email" type="email" name="email" class="validate">
						<label for="email">Email</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<input id="password" type="password" name="password"
							class="validate"> <label for="password">Password</label>
					</div>
				</div>
				<button class="btn waves-effect waves-light teal accent-4"
					type="submit" name="action" style="margin-left: 5%;">
					Submit <i class="mdi-content-send right"></i>
				</button>


				<br> <br> <a href="register.do" style="margin-left: 5%;">Registrate
					your self</a> <br> <a href="userforgotpassword.do"
					style="margin-left: 5%;"> <span
					class="blue-text text-lighten-3">Forgot a password?</span>
				</a> <br> <font color="red" id="errorMessage"
					style="margin-left: 5%;"></font> <br> <span
					class="black-text text" style="margin-left: 5%;">Sign via :</span>
				<br> <a
					href="http://www.facebook.com/dialog/oauth?client_id=1638969026318216&redirect_uri=http://localhost:8080/GuideMe/loginfb.do&scope=email"
					style="margin-left: 5%;"><img src="icons/facebook_logo.png"
					border="0" /></a> <a
					href="https://oauth.vk.com/authorize?client_id=4955136&%20scope=41943044194304&redirect_uri=http://localhost:8080/GuideMe/loginvk.do&%20response_type=codev=5.34"><img
					src="icons/vk_logo.png" border="0" /></a> <br> <br>
			</div>
		</div>
	</form>
</div>



