<%@ page language="java" contentType="text/html; charset=UTF-8"
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
  ${requestScope.user}
  <div id="w">
    <div id="content" class="clearfix">
      <div id="userphoto"><img  src="${user.avatar.path}" alt="default avatar" style="height: 120px; width: 120px; object-fit: cover"></div>
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
       <p><span>First name</span><c:out value="${requestScope.user['firstName']} "/></p>  
       <p><span>last Name</span><c:out value="${requestScope.user['lastName']} "/></p>  
       <p><span>facebookId</span><c:out value="${requestScope.user['facebookId']} "/></p>  
       <p><span>vkID</span><c:out value="${requestScope.user['vkId']} "/></p>      
       <p><span>cell number</span><c:out value="${requestScope.user['cellNumber']} "/></p> 
       <p><span>user type</span><c:out value="${requestScope.user.userType['name']} "/></p>   
             
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
          <c:forEach items="${requestScope.friends}" var="f">
          		<li>
          		${f.friend.lastName}   ${f.friend.firstName}
          		<a href="adminUserProfile.do?id=${f.friend.id}">
          			<img src="${f.friend.avatar.path}" width="22" height="22">
          		</a>
          		</li>
      		</c:forEach>
      	</ul>
      </section>
      <section id="settings" class="hidden">
        <p>Edit your user settings:</p>
        <form action="adminUserRequest.do?action=edit" method="POST" >
        <input type="text" name ="firstName"value ="фыыв">
        <input type="hidden" name = "userId" value ="${user.id}">
        <p class="setting"><span>E-mail Address </span><input type ="text" value = "${requestScope.user.email}"></p>       
        <p class="setting"><span>Language </span><input type ="text" value = "${requestScope.user.firstName}"></p>
        <p class="setting"><span>FirstName</span><input type ="text" value = "${requestScope.user.firstName}"name = "firstName1"></p>   
        <p class="setting"><span>Last name</span> <input type ="text" value = "${requestScope.user.lastName}" name = "lastName"></p>  
        <p class="setting"><span>user Type</span> 
       			<select name="userTypeId" id = "userTypeId">
					<c:forEach items="${userType}" var="userType">
						<option value="${userType.id}">${userType.name}</option>
					</c:forEach>
				</select>
		</p>  
		<p class="setting"><span>sex</span> 
       			<select name="sex" id = "sex">
							<option value="male">male</option>
							<option value="female">female</option>
				</select>
		</p>  
		<input type="submit" value="Submit">
		</form>
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