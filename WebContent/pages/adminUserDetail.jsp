<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>User Profile with Content Tabs - Design Shack Demo</title>
  <link rel="shortcut icon" href="http://designshack.net/favicon.ico">
  <link rel="icon" href="http://designshack.net/favicon.ico">
  <link rel="stylesheet" type="text/css" media="all" href="css/styleUserProfile.css">
  <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>

<body>
  <div id="topbar">
  <a href="http://designshack.net">Back to Design Shack</a>
  </div>
  
  <div id="w">
    <div id="content" class="clearfix">
      <div id="userphoto"><img src="img/avatar.png" alt="default avatar"></div>
      <h1><c:out value="${requestScope.user['lastName']} "/><c:out value="${requestScope.user['firstName']}"/></h1> 
      <nav id="profiletabs">
        <ul class="clearfix">
          <li><a href="#bio" class="sel">Bio</a></li>
          <li><a href="#activity">Activity</a></li>
          <li><a href="#friends">Friends</a></li>
          <li><a href="#settings">Settings</a></li>
        </ul>
      </nav>
      
      <section id="bio">
      <p><span>E-mail Address</span><c:out value="${requestScope.user['email']} "/></p>      
       <h2>email   <c:out value="${requestScope.user['email']} "/></h2>
       <h2>userType   <c:out value="${requestScope.user.userType['name']} "/></h2>
       <h2>cell Number    <c:out value="${requestScope.user['cellNumber']} "/></h2>
       <h2>facebookId   <c:out value="${requestScope.user['facebookId']} "/></h2>
       <h2>vkId   <c:out value="${requestScope.user['vkId']} "/></h2>
       <br>
      </section>
      
      <section id="activity" class="hidden">
        <p>Most recent actions:</p>
        <c:forEach items="${requestScope.userActivity}" var="ua">
          <p class = "activity">${ua.activity}  ${ua.name}</p>
      	</c:forEach>
      </section>
      
      <section id="friends" class="hidden">
        <p>Friends list:</p>
        <ul id="friendslist" class="clearfix">
          <c:forEach items="${requestScope.friends}" var="friends">
          		${friends.lastName} ${friends.firstName}
          		<li><a href="adminUserProfile.do?id=${friends.id}">
          			<img src="img/avatar.png" width="22" height="22"> 
          		${friends.lastName} ${friends.firstName}</a></li>
      		</c:forEach>
      	</ul>
      </section>
      
      <section id="settings" class="hidden">
        <p>Edit your user settings:</p>
        <p class="setting"><span>E-mail Address <img src="img/edit.png" alt="*Edit*"></span><c:out value="${requestScope.user['email']} "/></p>       
        <p class="setting"><span>Language <img src="img/edit.png" alt="*Edit*"></span><c:out value="${requestScope.user['email']} "/></p>
        <p class="setting"><span>Profile Status <img src="img/edit.png" alt="*Edit*"></span> Public</p>
        <p class="setting"><span>Update Frequency <img src="img/edit.png" alt="*Edit*"></span> Weekly</p>   
        <p class="setting"><span>FirstName<img src="img/edit.png" alt="*Edit*"></span>${requestScope.user['firstName']} </p>   
        <p class="setting"><span>Last name<img src="img/edit.png" alt="*Edit*"></span> ${requestScope.user['lastName']} </p>    
        <p class="setting"><span>Connected Accounts <img src="img/edit.png" alt="*Edit*"></span> None</p>
      </section>
    </div><!-- @end #content -->
  </div><!-- @end #w -->
<script type="text/javascript">
$(function(){
  $('#profiletabs ul li a').on('click', function(e){
    e.preventDefault();
    var newcontent = $(this).attr('href');
    
    $('#profiletabs ul li a').removeClass('sel');
    $(this).addClass('sel');
    
    $('#content section').each(function(){
      if(!$(this).hasClass('hidden')) { $(this).addClass('hidden'); }
    });
    
    $(newcontent).removeClass('hidden');
  });
});
</script>
</body>
</html>