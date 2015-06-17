<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Guide ME</title>
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.js"></script>
    <script src="js/init.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.modal-trigger').leanModal();
        });
    </script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).ready(function () {


            $("#registerform").submit(function () {
                $("#firstName").change();
                $("#lastName").change();
                $("#email").change();
                $("#password").change();
                $("#confirm").change();
                $("#sex").change();
                $("#cellNumber").change();


                $.ajax({
                    url: "registervalidator.do",
                    type: "post",
                    dataType: "json",
                    data: $("#cityId").serialize(),
                    success: function (data) {
                        $("#cityIdMessage").text(data.valid);

                    }
                });


                $.ajax({

                    url: 'submitRegister.do',
                    type: 'post',
                    dataType: 'json',
                    data: $("#registerform").serialize(),
                    success: function (data) {
                        alert('Ok!!!');


//                             if (data.isValid) {
//                                 console.log(data);
//                                 $('#signinlabel').text(
//                                         data.sessionUser.email);
//                                 $('#signinlabel').attr("href", "#logoutModal");
//                                 $('#signInModal').closeModal();
//                                 var helloMessage = 'Hello, ' + data.sessionUser.firstName + ' ' + data.sessionUser.lastName;
//                                 console.log(helloMessage);
//                                 $('#helloMessageOnLogoutModal').text(helloMessage);
//                             } else {
//                                 $('#password').val('');
//                                 $('#errorMessage').text('Email or/and password are not valid. Please try again');
//                             }


                    }
                });
                return false;
            });


            $("#firstName").change(function () {
                $.ajax({
                    url: "registervalidator.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        $("#firstNameMessage").text(data.valid);

                    }
                });
            });


            $("#lastName").change(function () {
                $.ajax({
                    url: "registervalidator.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        $("#lastNameMessage").text(data.valid);

                    }
                });
            });

//             $("#email").change(function() {
//                 $.get("ValidateUsernameServlet", $(this).serialize(), function(data) {
//                     $("#emailMessage").text(data.valid);
//                 });
//             });

            $("#email").change(function () {
                $.ajax({
                    url: "registervalidator.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        $("#emailMessage").text(data.valid);

                    }
                });
            });

            $("#password").change(function () {
                $.ajax({
                    url: "registervalidator.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        $("#passwordMessage").text(data.valid);

                    }
                });
            });

            $('#confirm').change(function () {
                var pass = $('#password').val();
                var conf = $('#confirm').val();

                $.ajax({
                    type: "post",
                    url: 'confirmValidator.do?password=' + pass + '&confirm=' + conf,
                    success: function (data) { //we're calling the response json array 'cities'

                        $("#confirmMessage").text(data.valid);

                    }
                });

            });

            $("#sex").change(function () {

                $.ajax({
                    url: "registervalidator.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        $("#sexMessage").text(data.valid);

                    }
                });
            });

            $("#cellNumber").change(function () {
                $.ajax({
                    url: "registervalidator.do",
                    type: "post",
                    dataType: "json",
                    data: $(this).serialize(),
                    success: function (data) {
                        $("#cellNumberMessage").text(data.valid);

                    }
                });
            });

        });

    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#cellNumber").click(function () {
                if ($(this).val() == "") {
                    $(this).val("+380");
                }
            });
        });
    </script>

</head>
<body>
<jsp:include page="header.jsp"/>
<td style=" width:60%;vertical-align: top;">
    <div class="row" style="margin-top: 15px;">
        <div class="col s12" style="width: 50%; margin-left: 25%">
            <ul class="collection z-depth-2 ">
                <li class="collection-item">
                    <div class="row">
                        <p>Registration form</p>

                        <form class="col s12" action="submitRegister.do" method="post" id="registerform"
                              name="registerform">
                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="firstName">First Name</label>
                                    <input type="text" id="firstName" name="firstName"/>
                                    <span id="firstNameMessage"></span>
                                </div>
                            </div>


                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="lastName">Last Name</label>
                                    <input type="text" id="lastName" name="lastName"/>
                                    <span id="lastNameMessage"></span>
                                </div>
                            </div>


                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="email">e-mail</label>
                                    <input type="text" id="email" name="email"/>
                                    <span id="emailMessage"></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="password">Password</label>
                                    <input type="password" id="password" name="password"/>
                                    <span id="passwordMessage"></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="confirm">Confirm password</label>
                                    <input type="password" id="confirm" name="confirm"/>
                                    <span id="confirmMessage"></span>
                                </div>
                            </div>

                            <div class="row">
                                <label>Sex</label>

                                <div class="input-field col s12">
                                    <select id="sex" name="sex" class="browser-default">
                                        <option selected value="choose">Choose sex</option>
                                        <option value="male">male</option>
                                        <option value="female">female</option>
                                    </select>
                                    <span id="sexMessage"></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="cellNumber">Cell</label>
                                    <input type="text" id="cellNumber" name="cellNumber" value="+380" length="13"/>
                                    <span id="cellNumberMessage"></span>
                                </div>
                            </div>


                            <div class="row">
                                <label>Address</label>

                                <div>
                                    <c:forEach items="${requestScope.languageList}" var="lang">

                                        ${lang.name} :
                                        <table style="width: 100%;">
                                            <tr>

                                                <td style="width:30%;">
                                                    <label>Country</label>
                                                    <select id="countryByLang_${lang.id}" class="browser-default">
                                                        <option selected value="choose" disabled selected>
                                                            loginpage.country.choose
                                                        </option>

                                                        <c:forEach items="${requestScope.countryList}" var="country">
                                                            <c:if test="${country.localId == lang.id}">
                                                                <option value="${country.id}">${country.name}</option>
                                                            </c:if>
                                                        </c:forEach>

                                                        <script>
                                                            $('#countryByLang_${lang.id}').change(function () {

                                                                var destination = $('#cityByLang_${lang.id}').val();
                                                                var selectedValue = $(this).val();


                                                                //change other countries
                                                                $.ajax({
                                                                    type: "post",
                                                                    url: 'getLocalCountryAnalogs.do?value=' + selectedValue,
                                                                    success: function (originCountryId) { //we're calling the response json array 'cities'

                                                                        $.each(originCountryId, function (countryId, country) { //here we're doing a foeach loop round each city with id as the key and city as the value

                                                                            //$(countryId).val(country);

                                                                            $('#countryByLang_' + countryId).val(country);

                                                                            //fill city list
                                                                            $('#cityByLang_' + countryId + " > option").remove(); //first of all clear select items

                                                                            $.ajax({
                                                                                type: "post",
                                                                                url: 'getCitiesByCountry.do?value=' + country,
                                                                                success: function (originCityId) { //we're calling the response json array 'cities'

                                                                                    var optFirst = $('<option selected />'); // here we're creating a new select option with for each city
                                                                                    optFirst.val('choose');
                                                                                    $('#cityId').val('choose');

                                                                                    optFirst.text('loginpage.city.choose');
                                                                                    $('#cityByLang_' + countryId).append(optFirst);

                                                                                    $.each(originCityId, function (id, city) { //here we're doing a foeach loop round each city with id as the key and city as the value


                                                                                        var opt = $('<option />'); // here we're creating a new select option with for each city
                                                                                        opt.val(id);
                                                                                        opt.text(city);

                                                                                        $('#cityByLang_' + countryId).append(opt); //here we will append these new select options to a dropdown with the id 'cities'
                                                                                    });
                                                                                }
                                                                            });


                                                                        });
                                                                    }
                                                                });

                                                            });
                                                        </script>

                                                    </select>
                                                </td>


                                                <td style="width:30%;">
                                                    <label>City</label>
                                                    <select id="cityByLang_${lang.id}" class="browser-default">
                                                        <option selected value="choose">
                                                            loginpage.city.choosecountryfirst
                                                        </option>
                                                    </select>
                                                    <script>
                                                        $('#cityByLang_${lang.id}').change(function () {

                                                            var selectedValue = $(this).val();

                                                            $('#cityId').val(selectedValue);

                                                            //change other cities
                                                            $.ajax({
                                                                type: "post",
                                                                url: 'getLocalCityAnalogs.do?value=' + selectedValue,
                                                                success: function (originCityId) { //we're calling the response json array 'cities'

                                                                    $.each(originCityId, function (cityId, city) { //here we're doing a foeach loop round each city with id as the key and city as the value

                                                                        $('#cityByLang_' + cityId).val(city);

                                                                    });
                                                                }
                                                            });

                                                        });
                                                    </script>
                                                </td>

                                                <td style="width:40%;">
                                                    <div class="input-field">
                                                        <label for="addressByLang_${lang.id}">Adress</label>
                                                        <input type="text" id="addressByLang_${lang.id}"
                                                               name="addressByLang_${lang.id}"/>
                                                        <span id="addressByLangMessage_${lang.id}"></span>
                                                    </div>

                                                    <script>
                                                        $(document).ready(function () {
                                                            $("#addressByLang_${lang.id}").change(function () {
                                                                $.ajax({
                                                                    url: "registerAddressValidator.do",
                                                                    type: "post",
                                                                    dataType: "json",
                                                                    data: $(this).serialize(),
                                                                    success: function (data) {
                                                                        $("#addressByLangMessage_${lang.id}").text(data.valid);

                                                                    }
                                                                });
                                                            });
                                                        });

                                                    </script>
                                                </td>
                                            </tr>
                                        </table>

                                    </c:forEach>

                                </div>
                                <input type="hidden" id="cityId" name="cityId"/> <span id="cityIdMessage"></span>

                                <br>

                                <p>
                                    <input type="checkbox" id="isGuide" name="isGuide"/>
                                    <label for="isGuide">Guide</label>
                                </p>
                                <br>
                                <button class="light-blue btn waves-effect waves-light" type="submit"
                                        name="action" style="margin-left: auto; margin-right: auto; width: 33%;">
                                    Submit <i class="mdi-content-send right"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>