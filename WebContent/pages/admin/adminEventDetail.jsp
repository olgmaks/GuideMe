<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin event</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Guide ME</title>
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
   	<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
	<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
	<!-- Include jTable script file. -->
	
  <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
  <link rel="shortcut icon" href="http://designshack.net/favicon.ico">
  <link rel="icon" href="http://designshack.net/favicon.ico">
  <link rel="stylesheet" type="text/css" media="all" href="css/styleUserProfile.css">
  
  <link href="css/star.css" rel="stylesheet" type="text/css" media="all"/>
   <script type="text/javascript" src="js/jquery-1.2.1.js"></script>
		<script type="text/javascript">
		function rate(mark){
			 $.ajax({
                url: "adminEventRequest.do?action=ratingEvent&mark=" + mark + "&eventId=" + '${event.id}',
                type: "post",
                dataType: "json",
                data: $("#cityId").serialize(),
                success: function (data) {
                }
            });
		}
		$(function(){
			$( ".star-rate li a" ).each(function(i){
				$(this).hover(function(){
					$("#remark").append("<div id=\"core\">"+$(this).html()+"</p>");
				},function(){
					$("#core").remove();
				});
			});
		});
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
      
       
       <c:choose>
		    <c:when test="${not empty userLogined}">
		    <div id ="stars-container">		
				<ul class='star-rate'>	  
					<li><a href='' title='' class="star-one" onclick="rate(1)">mmmm</a></li>
					<li><a href='' title='' class="star-two" onclick="rate(2)">try again</a></li>
					<li><a href='' title='' class="star-three" onclick="rate(3)">mmm not bad</a></li>  
					<li><a href='' title='' class="star-four" onclick="rate(4)">this is cool ya!</a></li>
					<li><a href='' title='' class="star-five" onclick="rate(5)">very good</a></li>
				</ul>
			</div>
		
		<div id ="remark"></div>
		       <form action="adminEventRequest.do?action=commentEvent" method="POST" >
		       <div class="row">
				        <div class="input-field col s6">
				        <input type="hidden" name = "eventId" value ="${event.id}">
				        </div>
				        	<div class="input-field col s12">       
				       	 	<p><span>Comment</span>
				       	 	<input required type ="text" name="comment"></p>
				  		</div>
			   </div>
		   	<input type="submit" value="Submit">
			   </form>
			
	          
	      <ul id="friendslist" >
            <c:forEach items="${requestScope.commentEvent}" var="ce">
          		${ce.commentator.lastName}   ${ce.commentator.firstName}
          		<li class="collection-item">
          		<a href="adminUserProfile.do?id=${ce.commentator.id}">
          			<img src="${ce.commentator.avatar.path}" style="height: 50px; width: 50px; object-fit: cover">
          		</a>
          		 ${ce.comment} 
          		</li>
      		</c:forEach>
      	</ul>
		 </c:when>
	</c:choose>  
      </section>
      
      <section id="photos" class="hidden">
        <p>Fhotos:</p>
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
