<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <div id="logoutModal" class="modal"
	style="margin-left: 60%; width: 35%;">
	<!-- Show this window when user is logged -->

	<div class="modal-content">
		<img class="square"
			style="height: 120px; width: 120px; object-fit: cover"
			src="${sessionUser.avatar.path }" id="userphoto">
		
		<p style="margin-left: 40%;" id="helloMessageOnLogoutModal">Hello,
			${sessionUser.firstName} ${sessionUser.lastName}</p>

	</div>
	<div class="modal-footer">
		<a href="logout.do" class="light-blue btn waves-effect waves-light"
			style="margin-right: 10%;">Log out</a> <a href="userCabinet.do"
			class="light-blue btn waves-effect waves-light"
			style="margin-right: 23%;">Cabinet</a>
	</div>
</div> --%>

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
<fmt:bundle basename="locale.register.messages">

<div id="logoutModal" class="modal"
	style="margin-left: 67%; width: 23%;">
	<div class="col s12 m8 offset-m2 l6 offset-l3">
		<br>
		<div class="row valign-wrapper">
			<div class="col s6">
				<img src="${sessionUser.avatar.path }" alt="" class="responsive-img"
					id="userphoto">
				<!-- notice the "circle" class -->
			</div>
			<div class="col s10">
				<span class="black-text" id="helloMessageOnLogoutModal"> <fmt:message key="Hello" />,
					${sessionUser.firstName} ${sessionUser.lastName}</span>
			</div>

		</div>
	</div>
	<a href="eventsincabinet.do"
		class="light-blue btn waves-effect waves-light"
		style="margin-left: 5%;"> <fmt:message key="Cabinet" />  </a> <br> <br> <a
		href="logout.do" class="light-blue btn waves-effect waves-light"
		style="margin-left: 5%;"><fmt:message  key="Logout" /></a>
	<div class="modal-footer"></div>
</div>

</fmt:bundle>
