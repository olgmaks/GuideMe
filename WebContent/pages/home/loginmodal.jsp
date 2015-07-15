<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>
<div id="signInModal" class="modal"
	style="margin-left: 55%; width: 40%;">
	<!-- Show this window when user not logged -->
	<form id="loginform">
		<div class="modal-content" >
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
				<font color="red" id="errorMessage"
					  style="margin-left: 5%;"></font>
				<button class="btn waves-effect waves-light light-blue accent-4"
					type="submit" name="action" style="width:50%; margin-left: 25%;">
					Sing IN <i class="mdi-content-send right"></i>
				</button>


				<br> <br>


				<a href="register.do" style="margin-left: 5%;">Registrate
					your self</a> <br> <a href="userforgotpassword.do"
					style="margin-left: 5%;"> <span
					class="blue-text text-lighten-3">Forgot a password?</span>
				</a>
				<div style="float: right; width: 150px;">
					 <span
					class="black-text text" style="margin-left: 5%;">Sign via :</span>
				<br> <a
					href="http://www.facebook.com/dialog/oauth?client_id=1638969026318216&redirect_uri=http://localhost:8080/GuideMe/loginfb.do&scope=email"
					style="margin-left: 5%;"><img src="icons/new-facebok-icon.png" style="width: 50px;height: 50px;"
					border="0" /></a> <a
					href="https://oauth.vk.com/authorize?client_id=4955136&%20scope=41943044194304&redirect_uri=http://localhost:8080/GuideMe/loginvk.do&%20response_type=codev=5.34"><img
					src="icons/new-vk-icon.png" border="0" style="width: 50px;height: 50px;"/></a> <br> <br>
						</div>
				</div>
		</div>
	</form>
</div>



