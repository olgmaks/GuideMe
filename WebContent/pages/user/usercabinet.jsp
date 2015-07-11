<%--
  Created by IntelliJ IDEA.
  User: OLEG
  Date: 14.06.2015
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
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

<%--Tag it css--%>
<link href="css/jquery.tagit.css" rel="stylesheet" type="text/css">
<link href="css/tagit.ui-zendesk.css" rel="stylesheet" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>

<%--Tag it Java script--%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="js/tag-it.js" type="text/javascript" charset="utf-8"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$(".modal-trigger").leanModal();
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#tabs").tabs();
	});
</script>



</head>
<body onload="connect();" onunload="disconnect();">
	<jsp:include page="../home/logoutmodal.jsp" />
	<jsp:include page="usercabinetheader.jsp" />

	<%--<jsp:include page="fileupload.jsp"/>--%>

	<table>
		<tr style="width: 100%;">


			<td style="width: 20%; vertical-align: top;"><jsp:include
					page="usercabinetpanelleft.jsp" /></td>


			<td style="width: 60%; vertical-align: top;"><c:choose>

					<c:when test="${centralContent == 'central'}">
						<jsp:include page="usercabinetpanelcentral.jsp" />
					</c:when>

					<c:when test="${centralContent == 'friends'}">
						<jsp:include page="userfriends.jsp" />
					</c:when>

					<c:when test="${centralContent == 'fileupload'}">
						<jsp:include page="fileupload.jsp" />
					</c:when>

					<c:when test="${centralContent == 'usergallery'}">
						<jsp:include page="usergallery.jsp" />
					</c:when>

					<c:when test="${centralContent == 'searchuser'}">
						<jsp:include page="searchuser.jsp" />
					</c:when>

					<c:when test="${centralContent == 'guideservices'}">
						<jsp:include page="../guide/guideservices.jsp" />
					</c:when>
					<c:when test="${centralContent == 'eventsincabinet' }">
						<jsp:include page="../eventsincabinet/rightpanel.jsp" />
					</c:when>
					<c:when test="${centralContent == 'usermessages' }">
						<jsp:include page="usermessages.jsp" />
					</c:when>
					<c:when test="${centralContent == 'moderatorevent' }">
						<jsp:include page="../eventsincabinet/moderatorevents.jsp" />
					</c:when>
					<c:when test="${centralContent == 'memberevent' }">
						<jsp:include page="../eventsincabinet/memberevents.jsp" />
					</c:when>
					<c:when test="${centralContent == 'userCabinetComments' }">
						<jsp:include page="usercabinetcomments.jsp" />
					</c:when>
				
					<c:when test="${centralContent == 'userCabinetEditProfile' }">
						<jsp:include page="usercabineteditprofile.jsp" />
					</c:when>
					
				</c:choose></td>


			<td style="width: 20%; vertical-align: top;"><c:choose>

					<c:when test="${centralContent == 'searchuser'}">
						<jsp:include page="searchrightpanel.jsp" />
					</c:when>
					<c:when
						test="${centralContent == 'eventsincabinet' || centralContent == 'moderatorevent' ||  centralContent == 'memberevent'}">

					</c:when>
					<c:when test="${centralContent == 'usergallery'}">
						<jsp:include page="usergallerypanelright.jsp" />
					</c:when>
					<c:when test="${centralContent == 'usermessages' }">
						<jsp:include page="rightpanelmessageuser.jsp" /> -
					</c:when>
					<c:when test="${centralContent == 'wallet' }">
						<jsp:include page="wallet.jsp" /> -
					</c:when>
					<c:otherwise>
						<jsp:include page="usercabinetpanelright.jsp" />
					</c:otherwise>
				</c:choose></td>


		</tr>
	</table>


</body>
</html>
