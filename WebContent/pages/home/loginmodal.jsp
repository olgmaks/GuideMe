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
				<a href="register.do" style="margin-left: 5%;">Registrate your
					self</a> <br> <font color="red" id="errorMessage"
					style="margin-left: 5%;"></font>
				<%@page import="java.net.URLEncoder"%>
				<p>
					<a
						href="http://www.facebook.com/dialog/oauth?client_id=1638969026318216&redirect_uri=http://localhost:8080/GuideMe/loginfb.do&scope=email"><img
						src="icons/facebook_logo.png" border="0" /></a>
				<p>
					<a
						href="https://oauth.vk.com/authorize?client_id=4955136&%20scope=41943044194304&redirect_uri=http://localhost:8080/GuideMe/loginvk.do&%20response_type=codev=5.34"><img
						src="icons/vk_logo.png" border="0" /></a>
			</div>
		</div>

		<div class="modal-footer">
			<button class="light-blue btn waves-effect waves-light" type="submit"
				name="action" style="margin-right: 33%;">
				Submit <i class="mdi-content-send right"></i>
			</button>
		</div>
	</form>
</div>

