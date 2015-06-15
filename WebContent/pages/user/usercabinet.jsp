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
</head>
<body>

<jsp:include page="../home/logoutmodal.jsp"/>
<jsp:include page="usercabinetheader.jsp"/>


<table>
    <tr >
        <td style=" width:20%; vertical-align: top;">

            <%--User controls panel - left side of a page--%>
            <div class="row" >
                <div class="col s12" style="margin-top:10px; ">
                    <ul class="collection z-depth-2 " style="height: 100%;">
                        <li class="collection-item"  >
                <div class="" align="center"  >  <img class="circle" style="height: 120px; width: 120px; object-fit: cover" src="${userPhoto.path}"></div><br>
                <h7>${sessionUser.firstName} ${sessionUser.lastName}</h7><br><br>

                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;text-align: left;font-size: 100%;text-transform: capitalize">Events<i class="mdi-action-extension right" ></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">New event<i class="mdi-content-add-circle-outline right" ></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Find event<i class="mdi-action-search right"></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Messages<i class="mdi-content-mail right"></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Friends<i class="mdi-social-group right"></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Find friends<i class="mdi-social-group-add right"></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Comments<i class="mdi-communication-comment right"></i></button>

                </li>
                </ul>
            </div>
            </div>

        </td>
        <td style=" width:60%;vertical-align: top;">
            <div class="row" >
                <div class="col s12" style="margin-top:10px;">
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" >
                            User modification panel
                        </li>
                    </ul>
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" >
                            <ul class="collection z-depth-2 ">
                                <c:forEach var="userInEvent" items="${userInEvents}">
                                <li class="collection-item" >
                                    <table>
                                        <tr>
                                        <td width="20%;">
                                <img class="circle" style="height: 120px; width: 120px; object-fit: cover" src="${userPhoto.path}">
                                        </td>
                                        <td width="70%;">
                                            ${userInEvent.event.name}<br><br>
                                            ${userInEvent.event.description}<br><br>
                                            ${userInEvent.status}<br>
                                        </td>
                                        <td width="10%;">
                                            <a class="btn-floating btn-large waves-effect waves-light green" style="transform: scaleY(0.7) scaleX(0.7)"><i class="mdi-image-remove-red-eye"></i></a>
                                            <br><a class="btn-floating btn-large waves-effect waves-light red" style="transform: scaleY(0.7) scaleX(0.7)"><i class="mdi-content-remove"></i></a>
                                        </td>
                                        </tr>
                                    </table>
                                </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </td>
        <td style=" width:20%;vertical-align: top;">
            <div class="row" >
                <div class="col s12" style="margin-top:10px;">
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" >
                            Recommended
                        </li>
                    </ul>
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" >
                            Events
                        </li>
                    </ul>
                </div>
            </div>
        </td>
    </tr>
</table>



</body>
</html>
