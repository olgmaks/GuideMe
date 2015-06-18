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
   <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>

<body>
  <div id="topbar">
  <a href="http://designshack.net">Back to Design Shack</a>
  </div>
  <div id="w">
    <div id="content" >
      <div id="userphoto"><img  src="${user.avatar.path}" alt="default avatar" style="height: 120px; width: 120px; object-fit: cover"></div>
      <h1><c:out value="${requestScope.user['lastName']} "/><c:out value="${requestScope.user['firstName']}"/></h1> 
     
      <nav id="profiletabs">
      <div class="nav-wrapper">
        <ul>
          <li><a href="#bio">Bio</a></li>
          <li><a href="#activity">Activity</a></li>
          <li><a href="#friends">Friends</a></li>
          <li><a href="#settings">Settings</a></li>
        </ul>
        </div>
      </nav>

      <section id="bio">
       <p><span>E-mail Address</span><c:out value="${requestScope.user['email']} "/></p>  
       <p><span>First name</span><c:out value="${requestScope.user['firstName']} "/></p>  
       <p><span>last Name</span><c:out value="${requestScope.user['lastName']} "/></p>  
       <p><span>facebookId</span><c:out value="${requestScope.user['facebookId']} "/></p>  
       <p><span>vkID</span><c:out value="${requestScope.user['vkId']} "/></p>      
       <p><span>cell number</span><c:out value="${requestScope.user['cellNumber']} "/></p> 
       <p><span>user type</span><c:out value="${requestScope.user.userType['name']} "/></p>   
       <c:choose>
		    <c:when test="${not empty userLogined}">
		       <form action="adminUserRequest.do?action=commentUser" method="POST" >
		       <div class="row">
				        <div class="input-field col s6">
				        <input type="hidden" name = "userId" value ="${user.id}">
				        </div>
				        	<div class="input-field col s12">       
				       	 	<p><span>Comment</span>
				       	 	<input required type ="text" name="comment"></p>
				  		</div>
			   </div>
		   	<input type="submit" value="Submit">
			   </form>
			   <c:forEach items="${requestScope.commentUser}" var="cu">
	          <p class = "activity">${cu.comment} </p>
	      	</c:forEach>  
		 </c:when>
	</c:choose>

       
           
      </section>
      
      <section id="activity" class="hidden">
        <p>Most recent actions:</p>
        <c:forEach items="${requestScope.userActivity}" var="ua">
          <p class = "activity">${ua.activity}  ${ua.name}</p>
      	</c:forEach>
      </section>
      
      <section id="friends" class="hidden">
        <p>Friends list:</p>
        <ul id="friendslist" class="collection z-depth-2 " style="height: 100%;">
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
        <div class="row">        
        <form action="adminUserRequest.do?action=edit" method="POST" >
	       <div class="row">
		        <div class="input-field col s6">
		        <input type="hidden" name = "userId" value ="${user.id}">
		        <p><span>E-mail Address </span><input type ="text" value = "${requestScope.user.email}"></p>
		        </div>
		        	<div class="input-field col s6">       
		       	 	<p><span>Language </span><input type ="text" value = "${requestScope.user.firstName}"></p>
		  		</div>
		   </div>
        	<div class="row">
		        <div class="input-field col s6">
		        	<p><span>First name</span><input type ="text" value = "${requestScope.user.firstName}" name="firstName"></p>
		        </div>
		        	<div class="input-field col s6">       
		       	 	<p><span>Last name</span><input type ="text" value = "${requestScope.user.lastName}" name = "lastName"></p>
		  		</div>
		   </div>  
        <p><span>user Type</span> 
       			<select name="userTypeId" id = "userTypeId">
					<c:forEach items="${userType}" var="userType">
						<option value="${userType.id}">${userType.name}</option>
					</c:forEach>
				</select>
		</p>  
		<p><span>sex</span> 
       			<select name="sex" id = "sex">
							<option value="male">male</option>
							<option value="female">female</option>
				</select>
		</p>  
		<input type="submit" value="Submit">
		</form>
		</div>
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