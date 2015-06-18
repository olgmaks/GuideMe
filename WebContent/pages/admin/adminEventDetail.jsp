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
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>

<body>
  <div id="w">
    <div id="content" class="clearfix">
    <div id="userphoto"><img  src="${event.avatar.path}" alt="default avatar" style="height: 120px; width: 120px; object-fit: cover"></div>
      <h1><c:out value="${requestScope.event.name} "/></h1> 
      <nav id="profiletabs">
        <ul class="clearfix">
          <li><a href="#bio" class="sel">Bio</a></li>
          <li><a href="#photos">Fotos</a></li>
          <li><a href="#settings">Settings</a></li>
        </ul>
      </nav>
      
      <section id="bio">
       <p><span>description</span><c:out value="${event.description}"/></p>  
       <p><span>from date</span><c:out value="${event.dateFrom}"/></p>
       <p><span>to Date</span><c:out value="${event.dateTo}"/></p>
       <p><span>address</span><c:out value="${event.address.address}"/></p>
       <p><span>city</span><c:out value="${event.address.city.name}"/></p>     
      </section>
      
      <section id="photos" class="hidden">
        <p>Fhotos:</p>
        <ul id="photolist" class="clearfix">
          <c:forEach items="${photos}" var="p">
          		<li>
          		${p.path}   
          		<a href="">
          			<img src="${p.path}" style="height: 120px; width: 120px; object-fit: cover">>
          		</a>
          		</li>
      		</c:forEach>
      	</ul>
      </section>
      
<!--       <section id="settings" class="hidden"> -->
<!--         <p>Edit your user settings:</p> -->
<!--         <form action="AdminServletPost?action=edit" method="POST"> -->
<%--         <input type="hidden" name = "userId" value ="${user.id}"> --%>
<%--         <p class="setting"><span>E-mail Address </span><input type ="text" value = "${requestScope.user.email}"></p>        --%>
<%--         <p class="setting"><span>Language </span><input type ="text" value = "${requestScope.user.firstName}"></p> --%>
<%--         <p class="setting"><span>FirstName</span><input type ="text" value = "${requestScope.user.firstName}"name = "firstName"></p>    --%>
<%--         <p class="setting"><span>Last name</span> <input type ="text" value = "${requestScope.user.lastName}" name = "lastName"></p>   --%>
<!--         <p class="setting"><span>user Type</span>  -->
<!--        			<select name="userTypeId" id = "userTypeId"> -->
<%-- 					<c:forEach items="${userType}" var="userType"> --%>
<%-- 						<option value="${userType.id}">${userType.name}</option> --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
<!-- 		</p>   -->
<!-- 		<p class="setting"><span>sex</span>  -->
<!--        			<select name="sex" id = "sex"> -->
<!-- 							<option value="male">male</option> -->
<!-- 							<option value="female">female</option> -->
<!-- 				</select> -->
<!-- 		</p>   -->
<!-- 		<input type="submit" value="Submit"> -->
<!-- 		</form> -->
<!--       </section> -->
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