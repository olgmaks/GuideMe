<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<!-- <meta charset="utf-8"> -->
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_600.font.js"></script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
	<script type="text/javascript" src="js/html5.js"></script>
<![endif]-->
</head>
<body id="page1">
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '1606321409631462',
				xfbml : true,
				version : 'v2.3'
			});
		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	<div class="fb-like" data-share="true" data-width="450"
		data-show-faces="true"></div>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/ru_RU/sdk.js#xfbml=1&version=v2.3&appId=1606321409631462";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<div class="fb-login-button" data-size="xlarge" data-show-faces="true"
		data-auto-logout-link="true"></div>
	<div class="extra">
		<div class="main">
			<!-- header -->
			<header>
				<div class="wrapper">
					<h1>
						<a href="index.html" id="logo">Around the World</a>
					</h1>
					<div class="right">
						<div class="wrapper">
							<form id="search" action="" method="post">
								<div class="bg">
									<input type="submit" class="submit" value=""> <input
										type="text" class="input">
								</div>
							</form>
						</div>
						<div class="wrapper">
							<nav>
								<ul id="top_nav">
									<li><a href="#">Register</a></li>
									<li><a href="#">Log In</a></li>
									<li><a href="#">Help</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
				<nav>
					<ul id="menu">
						<li><a href="index.html" class="nav1">Home</a></li>
						<li><a href="About.html" class="nav2">About Us </a></li>
						<li><a href="Tours.html" class="nav3">Our Tours</a></li>
						<li><a href="Destinations.html" class="nav4">Destinations</a></li>
						<li class="end"><a href="Contacts.html" class="nav5">Contacts</a></li>
					</ul>
				</nav>
				<article class="col1">
					<ul class="tabs">
						<li><a href="#" class="active">Flight</a></li>
						<li><a href="#">Hotel</a></li>
						<li><a href="#">Car</a></li>
						<li class="end"><a href="#">Cruise</a></li>
					</ul>
					<div class="tabs_cont">
						<form id="form_1" action="" method="post">
							<div class="bg">
								<div class="wrapper">
									<div class="radio">
										<input type="radio" name="name1" checked>Round trip
									</div>
									<div class="radio">
										<input type="radio" name="name1">One way
									</div>
								</div>
								<a href="#">Multiple destinations</a>
								<div class="wrapper">
									<input type="text" class="input">From
								</div>
								<div class="wrapper">
									<input type="text" class="input">To
								</div>
								<div class="wrapper check_box">
									<input type="checkbox" checked><a href="#">Search
										nearby airports</a>
								</div>
								<div class="wrapper">
									<input type="text" class="input input2" value="04/11/2010"
										onblur="if(this.value=='') this.value='04/11/2010'"
										onfocus="if(this.value =='04/11/2010' ) this.value=''">Depart
									(mm/dd/yy)
								</div>
								<div class="wrapper pad_bot1">
									<input type="text" class="input input2" value="04/11/2010"
										onblur="if(this.value=='') this.value='04/11/2010'"
										onfocus="if(this.value =='04/11/2010' ) this.value=''">Return
									(mm/dd/yy)
								</div>
								<div class="wrapper">
									<div class="radio">
										<input type="radio" name="name2" checked>Economy cabin
									</div>
									<div class="radio end">
										<input type="radio" name="name2">Business
									</div>
								</div>
								<div class="wrapper pad_bot1">
									<a href="#" class="button"
										onclick="document.getElementById('form_1').submit()">Search</a>
									Audlts <select><option>1</option></select>
								</div>
							</div>
						</form>
					</div>
				</article>
				<article class="col1 pad_left1">
					<div class="text">
						<img src="images/text1.jpg" alt="">
						<h2>The Best Offers</h2>
						<p>Sed ut perspiciatis unde omnis iste natus error sit
							voluptatem accusantium doloremque laudantium, totam rem aperiam,
							eaque ipsa quae ab illo inventore.</p>
						<a href="#" class="button">Read More</a>
					</div>
				</article>
				<div class="img">
					<img src="images/img.jpg" alt="">
				</div>
			</header>

			<div class="ic">More Website Templates at TemplateMonster.com!</div>
			<!-- / header -->
			<!-- content -->
			<section id="content">
				<article class="col1">
					<h3>Hot Travel</h3>
					<div class="pad">
						<div class="wrapper under">
							<figure class="left marg_right1">
								<img src="images/page1_img1.jpg" alt="">
							</figure>
							<p class="pad_bot2">
								<strong>Italy<br>Holidays
								</strong>
							</p>
							<p class="pad_bot2">Lorem ipsum dolor sit amet, consect etuer
								adipiscing.</p>
							<a href="#" class="marker_1"></a>
						</div>
						<div class="wrapper under">
							<figure class="left marg_right1">
								<img src="images/page1_img2.jpg" alt="">
							</figure>
							<p class="pad_bot2">
								<strong>Philippines<br>Travel
								</strong>
							</p>
							<p class="pad_bot2">Lorem ipsum dolor sit amet, consect etuer
								adipiscing.</p>
							<a href="#" class="marker_1"></a>
						</div>
						<div class="wrapper">
							<figure class="left marg_right1">
								<img src="images/page1_img3.jpg" alt="">
							</figure>
							<p class="pad_bot2">
								<strong>Cruise<br>Holidays
								</strong>
							</p>
							<p class="pad_bot2">Lorem ipsum dolor sit amet, consect etuer
								adipiscing.</p>
							<a href="#" class="marker_1"></a>
						</div>
					</div>
				</article>
				<article class="col2 pad_left1">
					<h2>Popular Places</h2>
					<div class="wrapper under">
						<figure class="left marg_right1">
							<img src="images/page1_img4.jpg" alt="">
						</figure>
						<p class="pad_bot2">
							<strong>Hotel du Havre</strong>
						</p>
						<p class="pad_bot2">Sed ut perspiciatis unde omnis iste natus
							error sit voluptatem accusantium doloremque laudantium, totam rem
							aperiam, eaque ipsa quae ab illo inventore veritatis et quasi
							architecto beatae vitae dicta sunt explicabo.</p>
						<p class="pad_bot2">
							<strong>Nemo enim ipsam voluptatem</strong> quia voluptas sit
							aspernatur aut odit aut fugit, sed quia consequuntur magni
							dolores eos qui ratione voluptatem sequi nesciunt. Neque porro
							quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur.
						</p>
						<a href="#" class="marker_2"></a>
					</div>
					<div class="wrapper">
						<figure class="left marg_right1">
							<img src="images/page1_img5.jpg" alt="">
						</figure>
						<p class="pad_bot2">
							<strong>Hotel Vacance</strong>
						</p>
						<p class="pad_bot2">At vero eos et accusamus et iusto odio
							dignissimos ducimus qui blanditiis praesentium voluptatum
							deleniti atque corrupti quos dolores et quas molestias excepturi
							sint occaecati cupiditate non provident, similique sunt in culpa.</p>
						<p class="pad_bot2">Et harum quidem rerum facilis est et
							expedita distinctio. Nam libero tempore, cum soluta nobis est
							eligendi optio cumque nihil impedit quo minus id quod maxime
							placeat facere possimus, omnis voluptas assumenda.</p>
						<a href="#" class="marker_2"></a>
					</div>
				</article>
			</section>
			<!-- / content -->
		</div>
		<div class="block"></div>
	</div>
	<div class="body1">
		<div class="main">
			<!-- footer -->
			<footer>
				<a rel="nofollow" href="http://www.html5xcss3.com/" target="_blank">Free
					HTML5 Templates</a> designed by TemplateMonster.com<br>
			</footer>
			<!-- / footer -->
		</div>
	</div>
	<script type="text/javascript">
		Cufon.now();
	</script>
</body>
</html>