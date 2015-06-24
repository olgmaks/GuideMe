$(document)
		.ready(
				function() {
					$("#loginform")
							.submit(
									function() {
										$
												.ajax({
													url : 'login.do',
													type : 'GET',
													dataType : 'json',
													data : $("#loginform")
															.serialize(),
													success : function(data) {

														if (data.isValid) {
															console.log(data);
															$('#userphoto')
																	.attr(
																			"src",
																			data.sessionUser.avatar.path);
															$('#signinlabel')
																	.text(
																			data.sessionUser.email);
															$('#signinlabel')
																	.attr(
																			"href",
																			"#logoutModal");
															$('#guide-me-label')
																	.attr(
																			"href",
																			"userCabinet.do");
															$('#signInModal')
																	.closeModal();
															var helloMessage = 'Hello, '
																	+ data.sessionUser.firstName
																	+ ' '
																	+ data.sessionUser.lastName;
															console
																	.log(helloMessage);
															$(
																	'#helloMessageOnLogoutModal')
																	.text(
																			helloMessage);
															location.href = "http://localhost:8080/GuideMe/home.do";
														} else {
															$('#password').val(
																	'');
															$('#errorMessage')
																	.text(
																			'Email or/and password are not valid. Please try again');
														}
													}
												});
										return false;
									});
				});