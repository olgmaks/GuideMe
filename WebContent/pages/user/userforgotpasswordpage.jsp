<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Cabinet</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
<title>Guide ME</title>
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
<script src="js/login.js"></script>
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


</head>
<body>
	<script type="text/javascript">
		function showDiv() {
			document.getElementById('sec').style.display = "block";
			document.getElementById("sub").style.display = "none";
			document.getElementById("inp").style.display = "none";
		}
		$(document).ready(function() {
			$.ajax({
				url : 'sendlinktoresetpass.do',
				type : 'GET',
				dataType : 'json',
				data : $("#sendlink").serialize(),
				success : function(data) {
					if (data.u != null) {
						showDiv();
					}
				}

			});
		});
	</script>
	<jsp:include page="/pages/header.jsp" />
	<jsp:include page="../home/loginmodal.jsp" />
	<jsp:include page="../home/logoutmodal.jsp" />
	<br>
	<br>

	<div>
		<h5 class="center-align">Please type your email</h5>
	</div>

	<div class="valign-wrapper">
		<br>

		<div class="row">
			<div class="row">
				<form class="col s12" action="sendlinktoresetpass.do" method="post"
					id="sendlink">
					<div class="row">
						<div class="input-field col s12" id="inp" style="display: block">
							<input id="email" type="email" class="validate" name="email"
								required> <label for="email">Email</label>
						</div>
					</div>
					<button class="btn waves-effect waves-light" type="submit"
						name="action" onclick="" id="sub" style="display: block">
						Email me a link to reset a password <i
							class="mdi-content-send right"></i>
					</button>
				</form>
			</div>
		</div>
	</div>

	<div class="progress" id="sec" style="display: none">
		<div class="indeterminate"></div>
	</div>
	<c:if test="${isWrong && isWrong != null }">
		<div>
			<h5 class="center-align">Wrong email</h5>
		</div>
	</c:if>
</body>
</html>


