<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
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
        $(document).ready(function () {
            $("#loginform").submit(function () {
                $.ajax({
                    url: 'login.do',
                    type: 'GET',
                    dataType: 'json',
                    data: $("#loginform").serialize(),
                    success: function (data) {

                        if (data.isValid) {
                            console.log(data);
                            $('#signinlabel').text(
                                    data.sessionUser.email);
                            $('#signinlabel').attr("href", "#logoutModal");
                            $('#guide-me-label').attr("href", "userCabinet.do");
                            $('#signInModal').closeModal();
                            var helloMessage = 'Hello, ' + data.sessionUser.firstName + ' ' + data.sessionUser.lastName;
                            console.log(helloMessage);
                            $('#helloMessageOnLogoutModal').text(helloMessage);
                        } else {
                            $('#password').val('');
                            $('#errorMessage').text('Email or/and password are not valid. Please try again');
                        }
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>

<jsp:include page="header.jsp" />
<jsp:include page="home/loginmodal.jsp"/>
<jsp:include page="home/logoutmodal.jsp"/>

<div class="row">
    <div class="col s12" style="margin-top: 20px;">
        <ul class="collection">
            <li class="collection-item" style="margin-left: 30%;">
                <div class="input-field col s6">
                    <i class="mdi-action-search prefix"></i> <input id="icon_prefix"
                                                                    type="text" class="ligth-blue"> <label
                        for="icon_prefix">Search</label>
                </div>

            </li>
        </ul>
    </div>


    <div class="col s6">This div is 6-columns wide</div>
    <div class="col s6">This div is 6-columns wide</div>
</div>
<div class="container" style="width: 80%; margin-left: 10%;">
    <div class="section">

        <p class="light">Events</p>

        <table>
            <thead>
            <tr>
                <th style="width: 33%; margin-left: 0%;">Recomended</th>
                <th style="width: 33%; margin-left: 33.3%;">Top rated</th>
                <th style="width: 33%; margin-left: 66.6%;">Popular</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
            </tr>
            <tr>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
            </tr>
            <tr>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
                <th>
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="img/guide1.jpg">
                        </div>
                        <div class="card-content">
									<span class="card-title activator grey-text text-darken-4">Event
										Name <i class="mdi-navigation-more-vert right"></i>
									</span>

                            <p>
                                <a href="#">Event details</a>
                            </p>
                        </div>
                        <div class="card-reveal">
									<span class="card-title grey-text text-darken-4">Card
										Title <i class="mdi-navigation-close right"></i>
									</span>

                            <p>Here is event description</p>
                        </div>
                    </div>
                </th>
            </tr>


            </tbody>
        </table>

    </div>
</div>

<div class="container">
    <div class="section">

        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text">
                        <i class="mdi-image-flash-on"></i>
                    </h2>
                    <h5 class="center">Speeds up development</h5>

                    <p class="light">We did most of the heavy lifting for you to
                        provide a default stylings that incorporate our custom
                        components. Additionally, we refined animations and transitions
                        to provide a smoother experience for developers.</p>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text">
                        <i class="mdi-social-group"></i>
                    </h2>
                    <h5 class="center">User Experience Focused</h5>

                    <p class="light">By utilizing elements and principles of
                        Material Design, we were able to create a framework that
                        incorporates components and animations that provide more feedback
                        to users. Additionally, a single underlying responsive system
                        across all platforms allow for a more unified user experience.</p>
                </div>
            </div>

            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text">
                        <i class="mdi-action-settings"></i>
                    </h2>
                    <h5 class="center">Easy to work with</h5>

                    <p class="light">We have provided detailed documentation as
                        well as specific code examples to help new users get started. We
                        are also always open to feedback and can answer any questions a
                        user may have about Materialize.</p>
                </div>
            </div>
        </div>

    </div>
</div>


<div class="container">
    <div class="section">

        <div class="row">
            <div class="col s12 center">
                <h3>
                    <i class="mdi-content-send brown-text"></i>
                </h3>
                <h4>Contact Us</h4>

                <p class="left-align light">Lorem ipsum dolor sit amet,
                    consectetur adipiscing elit. Nullam scelerisque id nunc nec
                    volutpat. Etiam pellentesque tristique arcu, non consequat magna
                    fermentum ac. Cras ut ultricies eros. Maecenas eros justo,
                    ullamcorper a sapien id, viverra ultrices eros. Morbi sem neque,
                    posuere et pretium eget, bibendum sollicitudin lacus. Aliquam
                    eleifend sollicitudin diam, eu mattis nisl maximus sed. Nulla
                    imperdiet semper molestie. Morbi massa odio, condimentum sed ipsum
                    ac, gravida ultrices erat. Nullam eget dignissim mauris, non
                    tristique erat. Vestibulum ante ipsum primis in faucibus orci
                    luctus et ultrices posuere cubilia Curae;</p>
            </div>
        </div>

    </div>
</div>



<div class="parallax-container valign-wrapper">
    <div class="section no-pad-bot">
        <div class="container">
            <div class="row center">
                <h5 class="header col s12 light">A modern responsive front-end
                    framework based on Material Design</h5>
            </div>
        </div>
    </div>
    <div class="parallax">
        <img src="background2.jpg" alt="Unsplashed background img 3">
    </div>
</div>

<footer class="page-footer teal">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Company Bio</h5>

                <p class="grey-text text-lighten-4">We are a team of college
                    students working on this project like it's our full time job. Any
                    amount would help support and continue development on this project
                    and is greatly appreciated.</p>


            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Settings</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Connect</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            Made by <a class="brown-text text-lighten-3"
                       href="http://materializecss.com">Materialize</a>
        </div>
    </div>
</footer>


<!--  Scripts-->

</body>
</html>
