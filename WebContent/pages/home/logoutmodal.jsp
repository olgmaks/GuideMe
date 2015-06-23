<div id="logoutModal" class="modal"
	style="margin-left: 60%; width: 35%;">
	<!-- Show this window when user is logged -->

	<div class="modal-content">
		<img class="square"
			style="height: 120px; width: 120px; object-fit: cover"
			src="${sessionUser.avatar.path}">
		<p style="margin-left: 40%;" id="helloMessageOnLogoutModal">Hello,
			${sessionUser.firstName} ${sessionUser.lastName}</p>

	</div>
	<div class="modal-footer">
		<a href="logout.do" class="light-blue btn waves-effect waves-light"
			style="margin-right: 10%;">Log out</a> <a href="userCabinet.do"
			class="light-blue btn waves-effect waves-light"
			style="margin-right: 23%;">Cabinet</a>
	</div>
</div>

