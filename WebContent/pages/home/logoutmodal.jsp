<div id="logoutModal" class="modal"
	style="margin-left: 60%; width: 35%;">
	<!-- Show this window when user is logged -->
	<div class="modal-content">
		<p style="margin-left: 40%;" id="helloMessageOnLogoutModal">Hello,
			${sessionUser.firstName} ${sessionUser.lastName}</p>
	</div>
	<div class="modal-footer">
		<a href="logout.do" class="light-blue btn waves-effect waves-light"
			style="margin-right: 10%;">Log out</a> <a href="logout.do"
			class="light-blue btn waves-effect waves-light"
			style="margin-right: 33%;">Log out</a>
	</div>
</div>

