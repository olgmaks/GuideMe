<%--
  Created by IntelliJ IDEA.
  User: OLEG
  Date: 14.06.2015
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Cabinet</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Guide ME</title>
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.js"></script>
    <script src="js/init.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.modal-trigger').leanModal();
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#tabs").tabs();
        });
    </script>
</head>
<body>

<jsp:include page="../home/logoutmodal.jsp"/>
<jsp:include page="usercabinetheader.jsp"/>


<table>
    <tr>



        <td style=" width:20%; vertical-align: top;">
            <jsp:include page="usercabinetpanelleft.jsp"/>
        </td>




        <td style=" width:60%;vertical-align: top;">
            <c:choose>

                <c:when test="${centralContent == 'central'}">
                    <jsp:include page="usercabinetpanelcentral.jsp"/>
                </c:when>

                <c:when test="${centralContent == 'friends'}">
                    <jsp:include page="userfriends.jsp"/>
                </c:when>

            </c:choose>
        </td>



        <td style=" width:20%;vertical-align: top;">
            <jsp:include page="usercabinetpanelright.jsp"/>
        </td>




    </tr>
</table>


</body>
</html>
