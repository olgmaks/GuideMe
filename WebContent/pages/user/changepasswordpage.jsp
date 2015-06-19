<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.modal-trigger').leanModal();
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#tabs").tabs();
	});
</script>
<title>Forgot Password page</title>
</head>
<body>
	<jsp:include page="/pages/header.jsp" />
	<jsp:include page="../home/loginmodal.jsp" /><br>
	<br>
	<br>
	<br>
	<div class="valign-wrapper">
		<br>
		<div class="row">
			<div class="row">
				<form class="col s12" action="changepassword.do" method="post">
					<div class="row">
						<div class="input-field col s12">
							<input id="password" type="password" class="validate"> <label
								for="password">Password</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<input id="password" type="password" class="validate"> <label
								for="password">Repeat Password</label>
						</div>
					</div>
					<button class="btn waves-effect waves-light" type="submit"
						name="action">
						Change <i class="mdi-content-send right"></i>
					</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>