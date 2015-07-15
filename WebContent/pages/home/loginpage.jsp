<%--
  Created by IntelliJ IDEA.
  User: OLEG
  Date: 14.07.2015
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login page</title>

  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>

  <!-- begin Localization -->
  <jsp:include page="../localization.jsp"/>
  <ct:showLocale basename="locale.home.messages"  from = "loginPage.do" />

  <fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
  <fmt:bundle basename="locale.home.messages"/>
    <!-- end Localization -->

  <script type="text/javascript">
    $(document).ready(function () {
      window.onload = function () {



        //       		$("#searchform").submit();
//             	var countryVal = $("#selCountryId").val();
//             	countryVal = "choose";

      }
    });


  </script>

  <script type="text/javascript">

    $(document).ready(function () {
      $('.modal-trigger').leanModal();
    });
  </script>
  <script type="text/javascript">
    $(document).ready(function () {
      $("#loginform").submit(function () {



        $.ajax({
          url: 'login.do',
          type: 'post',
          dataType: 'json',
          data: $("#loginform").serialize(),
          success: function (data) {

            if (data.isValid) {
//              console.log(data);
              $('#errorMessage').text('');

              window.location.href = "http://localhost:8080/GuideMe/userCabinet.do";
            } else {

              $('#password').val('');
              $('#errorMessage').text('invalid text, please try again');

            }
          }
        });


        //window.location.href = "home.do";
        //window.location.reload(true);
        //alert('Logged!');
//                 Materialize.toast('Hi!', 3000,'',function(){
//                 	window.location.href = "home.do";
//                 	window.location.reload(true);
//                 });

        //$("#homeform").submit();

        return false;
      });
    });
  </script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
<fmt:bundle basename="locale.cabinet.messages">

<div class="collection z-depth-5" style="width: 50%; margin-left: 25%; margin-top: 50px;">
<form id="loginform" method="post">

    <br>
    <span style="margin-left: 40%; font-size: 22px;" ><fmt:message key="login.helloLogin"/></span>
    <br>  <br>  <br>
     <div class="row" style="width: 80%; margin-left: 10%" id="row-to-be-replaced">
      <div class="row">
        <div class="input-field col s12">
          <input id="email" type="email" name="email" class="validate">
          <label for="email"><fmt:message key="login.email"/></label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="password" type="password" name="password"
                 class="validate"> <label for="password"><fmt:message key="login.password"/></label>
        </div>
      </div>
       <font color="red" id="errorMessage"
             style="margin-left: 5%;"></font>
      <button class="btn waves-effect waves-light light-blue accent-4"
              type="submit" name="action" style="width:50%; margin-left: 25%;">
        <fmt:message key="login.signIn"/> <i class="mdi-content-send right"></i>
      </button>


      <br> <br>

      <a href="register.do" style="margin-left: 5%;"><fmt:message key="login.registrate"/>
         </a> <br> <a href="userforgotpassword.do"
                              style="margin-left: 5%;"> <span
            class="blue-text text-lighten-3"><fmt:message key="login.forgotPassword"/></span>
    </a>
      <div style="float: right; width: 150px;">
					 <span
                             class="black-text text" style="margin-left: 5%;"><fmt:message key="login.signVia"/></span>
        <br> <a
              href="http://www.facebook.com/dialog/oauth?client_id=1638969026318216&redirect_uri=http://localhost:8080/GuideMe/loginfb.do&scope=email"
              style="margin-left: 5%;"><img src="icons/new-facebok-icon.png" style="width: 50px;height: 50px;"
                                            border="0" /></a> <a
              href="https://oauth.vk.com/authorize?client_id=4955136&%20scope=41943044194304&redirect_uri=http://localhost:8080/GuideMe/loginvk.do&%20response_type=codev=5.34"><img
              src="icons/new-vk-icon.png" border="0" style="width: 50px;height: 50px;"/></a> <br> <br>
      </div>


  </div>
</form>
</div>
</fmt:bundle>
</body>
</html>
