
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
   	<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
	<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
	<!-- Include jTable script file. -->
	<link rel="shortcut icon" href="http://designshack.net/favicon.ico">
  <link rel="icon" href="http://designshack.net/favicon.ico">
  <link rel="stylesheet" type="text/css" media="all" href="css/styleUserProfile.css">
  <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

  <link type="text/css" rel="stylesheet" href="css/jquery.ratings.css" />
    
    <script src="js/jquery.ratings.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function() {
    	
    	  $('#ratingEvent').ratings(5,'${mark}').bind('ratingchanged', function(event, data) {
    	      rate(data.rating)
    		  $('#example-rating-1').text(data.rating);
    	  });
    	});
    </script>
		<script type="text/javascript">
		function rate(mark){
			 $.ajax({
                url: "adminEventRequest.do?action=ratingEvent&mark=" + mark + "&eventId=" + '${event.id}',
                type: "post",
                success: function () {
                }
            });
		}
	
		</script>
  
</head>
<body>


<jsp:include page="adminHeader.jsp"/>


<table>
    <tr>
        <td style=" width:20%; vertical-align: top;">
            <jsp:include page="adminLeftPanel.jsp"/>
        </td>


    <td style=" width:80%;vertical-align: top;">
	<div id="w">
    <div id="content" class="clearfix">
    <div id="userphoto"><img  src="${event.avatar.path}" alt="default avatar" style="height: 120px; width: 120px; object-fit: cover"></div>
      <h2><c:out value="${requestScope.event.name} "/></h2> 
      
      
  <ul class="collection">
    <li class="collection-item avatar">
      <img src="${event.moderator.avatar.path}" alt="" class="circle">
      
      <span class="title"><a href="userProfile.do?id=${event.moderator.id}">Author: ${event.moderator.firstName} ${event.moderator.lastName}</a></span>
      
      <p>
      <i>${requestScope.moderatorMark}</i>
 <br>
      </p>
<%--       <a  class="secondary-content"><i class="material-icons">${m.status}</i></a> --%>
    </li>
    
  </ul>      
      
      
      <nav  id="profiletabs">
        <ul class="clearfix">
          <li><a href="#bio" class="sel">About</a></li>
          <li><a href="#photos">Fotos</a></li>
          <li><a href="#members">Members</a></li>
          
          <c:if test="${isAdmin}">
          <li><a href="#settings">Settings</a></li>
          </c:if>
        </ul>
      </nav>
      
      <section id="bio">
       <p><span><b>Description: </b> </span><c:out value="${event.description}"/></p>  
       <p><span><b>From: </b></span><c:out value="${event.dateFrom}"/></p>
       <p><span><b>To: </b></span><c:out value="${event.dateTo}"/></p>
       <p><span><b>Address: </b></span><c:out value="${event.address.address}"/></p>
       <p><span><b>City: </b></span><c:out value="${event.address.city.name}"/></p>
       <p><span><b>Average mark: </b></span><c:out value="${eventMark}"/></p> 
       <p><span><b>Total points: </b></span><c:out value="${eventPoints}"/></p> 
       <br>
       
       <c:if test="${isMember}">
       
       <p><span><b>Your mark: </b></span></p> 
         
       <c:choose>
		    <c:when test="${not empty userLogined}">
				  <div id="ratingEvent"></div> <br />
		       <form action="adminEventRequest.do?action=commentEvent" method="POST" >
		       <div class="row">
				        <div class="input-field col s6">
				        <input type="hidden" name = "eventId" value ="${event.id}">
				        </div>
				        	<div class="input-field col s12">       
				       	 	<p><span> <b> <i> Comment</i> </b>  </span>
				       	 	<input required type ="text" name="comment"></p>
				  		</div>
			   </div>
<!-- 		   	<input type="submit" value="Submit"> -->
		   	
			<button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 50%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
            Add comment<i class="mdi-content-add-circle-outline right"></i></button>		   	
			   </form>
			
	          
	      <ul class="collection" id="friendslist" >
      		
  			<c:forEach items="${requestScope.commentEvent}" var="ce">
    		<li class="collection-item avatar">
      
      		<img src="${ce.commentator.avatar.path}" alt="" class="circle">
      
      		<span class="title"><a href="userProfile.do?id=${ce.commentator.id}"> ${ce.commentator.firstName} ${ce.commentator.lastName}</a></span>
      		<p><br> ${ce.comment}
      		</p>
    
      
      		<c:if test="${ce.commentator.id == userLogined.id}"> 
       		<form id="deleteForm" action="deleteEventComment.do?id=${ce.id}&eventId=${event.id}" method="POST" > 
      
      		 <button type="submit"  class="secondary-content"><i class="material-icons">delete</i></button>
 
       	  </form> 
 
 	     </c:if>
    
    
    	</li>
    </c:forEach>      		
      		
      		
      	</ul>
		 </c:when>
	</c:choose>  
	</c:if>
	
	
	
	
      </section>
      
      <section id="photos" class="hidden">
        <p>Photos:</p>
        <ul id="photolist" class="clearfix">
          <c:forEach items="${photos}" var="p">
          		<li>   
          		<a href="">
          			<img src="${p.path}" style="height: 120px; width: 120px; object-fit: cover">>
          		</a>
          		</li>
      		</c:forEach>
      	</ul>
      </section>
      
      <section id="members" class="hidden">
        <p>Members:</p>

      	
  <ul class="collection">
  <c:forEach items="${requestScope.members}" var="m">
    <li class="collection-item avatar">
      <img src="${m.user.avatar.path}" alt="" class="circle">
      <span class="title"><a href="userProfile.do?id=${event.moderator.id}">Author: ${event.moderator.firstName} ${event.moderator.lastName}</a></span>
      <p>
      <c:if test="${m.bedCount > 0}">Can accept: ${m.bedCount} guest(s)</c:if>
      <c:if test="${m.bedCount < 0}">Need lodjing for: ${-m.bedCount} person(s)</c:if>
		
 <br>
         
      </p>
      <a  class="secondary-content"><i class="material-icons">${m.status}</i></a>
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
        </td>

    </tr>
</table>


</body>
</html>


