<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>

<link rel="stylesheet"	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/south-street/jquery-ui.css" 	id="theme">
<link rel="stylesheet"	href="//blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
<!--     <script	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->



	<script	src="//blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<%----%>
	<script src="js/jquery.image-gallery.js"></script>


	<!-- begin Localization -->
	<jsp:include page="../localization.jsp"/>
	<ct:showLocale basename="locale.profile.messages" from = "userProfile.do?id=${requestScope.profileId}" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.profile.messages">
	<!-- end Localization -->	
   
  <style>
  nav {
     color: #fff;
     background-color: #6699FF;
     width: 100%;
     height: 56px;
     line-height: 56px; 
   }  
   
	.table-wrapper {
	width: 100%;
	height: 450px;
	overflow: auto;
	}
  
  </style>
   
   <script type="text/javascript">
    $(document).ready(function() {
    	
    	  $('#example-1').ratings(5,'${mark}').bind('ratingchanged', function(event, data) {
    	      rate(data.rating)
    		  $('#example-rating-1').text(data.rating);
    	  });
    	});
    </script>
	
	<script type="text/javascript">
		function rate(mark){
			 $.ajax({
                url: "adminUserRequest.do?action=ratingUser&mark=" + mark + "&id=" + "${user.id}",
                type: "post",
                success: function () {
                }
            });
		}
		

	
		</script>
		
		<title> <fmt:message key="profile.title" /></title>
		
	</fmt:bundle>
</head>

<body>
<fmt:bundle basename="locale.profile.messages">


<%--  <jsp:include page="adminHeader.jsp" /> --%>

  <div id="w">
    <div id="content" >
      <div id="userphoto"><img  src="${user.avatar.path}" alt="default avatar" style="height: 120px; width: 120px; object-fit: cover"></div>
      <h1><c:out value="${requestScope.user['firstName']} "/><c:out value="${requestScope.user['lastName']}"/></h1> 
     
     <c:forEach items="${profile.tagList}" var="tagElem">
     <a href='home.do?tag=${tagElem}'>#${tagElem}</a> 
     </c:forEach>							
							
	<br><br>
	
     <c:if test="${requestScope.requestedId != null}">
	<ul class="collection">
			<li class="collection-item">
			
			<span style="margin-right: 10px;"><fmt:message key="Acceptrequest"  />  </span>
			<a href="userProfileFriends.do?operation=accept&id=${requestScope.requestedId}&profile=${requestScope.profileId}" 
			class="btn-floating light-blue acceptfriendrequest"
			data-id=""> <i	class="mdi-navigation-check"></i>
			</a>
   		    
   		    <span style="margin-right: 10px;"><fmt:message key="Discardrequest"  /></span>
			<a href="userProfileFriends.do?operation=discard&id=${requestScope.requestedId}&profile=${requestScope.profileId}" 
			class="btn-floating light-blue removefriend"
			data-id=""> <i
			class="mdi-navigation-close"></i>
			</a>
			
			</li>
	</ul>	
	</c:if> 
	
     <c:if test="${requestScope.requesterId != null}">
	<ul class="collection">
			<li class="collection-item">
			
   		    <span style="margin-right: 10px;"><fmt:message key="CancelRequest"  /></span>
			<a href="userProfileFriends.do?operation=callback&id=${requestScope.requesterId}&profile=${requestScope.profileId}" 
			class="btn-floating light-blue removefriend"
			data-id=""> <i
			class="mdi-navigation-close"></i>
			</a>
			
			</li>
	</ul>	
	</c:if> 
	
     <c:if test="${requestScope.noRelation}">
	<ul class="collection">
			<li class="collection-item">
			
   		    <span style="margin-right: 10px;"><fmt:message key="AddFriend"  /> </span>
			<a href="userProfileFriends.do?operation=add&id=${requestScope.profileId}&profile=${requestScope.profileId}" 
			class="btn-floating light-blue removefriend"
			data-id=""> <i
			class="mdi-navigation-check"></i>
			</a>
			
			</li>
	</ul>	
	</c:if>
	
     <c:if test="${requestScope.isFriend}">
	<ul class="collection">
			<li class="collection-item">
			
   		    <span style="margin-right: 10px;"><fmt:message key="Removefriend"/> </span>
			<a href="userProfileFriends.do?operation=remove&id=${requestScope.friendId}&profile=${requestScope.profileId}" 
			class="btn-floating light-blue removefriend"
			data-id=""> <i
			class="mdi-navigation-close"></i>
			</a>
			
			</li>
	</ul>	
	</c:if>	      
      
      <nav id="profiletabs">
      <div class="nav-wrapper">
        <ul>
          <li><a href="#bio"><fmt:message key="profile.Info" /></a></li>
		  <li><a href="#photos"> <fmt:message key="profile.Photos"/> </a></li>          
          <c:if test="${isAdmin}">
          <li><a href="#activity"><fmt:message key="profile.Activity"/> </a></li>
          </c:if>
<!--           <li><a href="#friends">Friends</a></li> -->
          
          <li><a href="#newfriends"> <fmt:message key="profile.Friends" /> </a></li>
          
          <li><a href="#moderatorEvents"> <fmt:message key="profile.Author" /></a></li>
          
          <li><a href="#memberEvents"> <fmt:message key="profile.Member" /></a></li>
          
<!--           <li><a href="#settings">Settings</a></li> -->
          
        </ul>
        </div>
      </nav>
      <section id="bio">
       <c:if test="${isAdmin || isFriend}">
       <p><span><b>E-mail: </b> </span><c:out value="${requestScope.user['email']} "/></p>  
       </c:if>
       
       <p><span><b><fmt:message key="profile.Firstname" />: </b></span><c:out value="${requestScope.user['firstName']} "/></p>  
       <p><span><b><fmt:message key="profile.LastName" />: </b></span><c:out value="${requestScope.user['lastName']} "/></p>  
       
       <p><span><b><fmt:message key="profile.Gender" /> : </b></span> <fmt:message key = "${requestScope.user['sex']}" /> </p> 
       
       <c:if test="${isAdmin}">
       <p><span><b>Facebook id: </b></span><c:out value="${requestScope.user['facebookId']} "/></p>  
       <p><span><b>VK id: </b></span><c:out value="${requestScope.user['vkId']} "/></p>   
       </c:if>   
       
       <c:if test="${isAdmin || isFriend}">
       <p><span><b> <fmt:message key="profile.CellNumber"/>: </b></span><c:out value="${requestScope.user['cellNumber']} "/></p> 
       </c:if>
       
       <p><span><b> <fmt:message key="profile.Usertype"/>: </b></span><c:out value="${requestScope.user.userType['name']} "/></p>   
       
       <p><span><b> <fmt:message key="profile.Languages"/>: </b></span><c:out value="${requestScope.langs} "/></p>   
       
       <p><span><b> <fmt:message key="profile.Averagemark" /> : </b></span><c:out value="${requestScope.averMark} "/></p>
       
       <p><span><b><fmt:message key="profile.Totalpoints" />: </b></span><c:out value="${requestScope.total} "/></p>
       
       <c:choose>
		    <c:when test="${not empty userLogined}">
		      <c:if test="${isFriend}">
		      <div id="example-1"></div> <br />
		       
		       
		       <form action="adminUserRequest.do?action=commentUser" method="POST" >
		       <div class="row">
				        <div class="input-field col s6">
				        <input type="hidden" name = "userId" value ="${user.id}">
				        </div>
				        	
				        <div class="input-field col s12">       
				       	<p><span><b> <fmt:message key="Comment" /> </b></span>
				       	<input required type ="text" name="comment"></p>
				  		</div>
				  		
			   </div>
				<button class="btn light-blue waves-effect waves-light"
					type="submit" name="action"
					style="width: 50%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
					<fmt:message key="Addcomment" /> <i class="mdi-content-add-circle-outline right"></i>
				</button>		   	   
		   	   
		   	   </form>
			   
			   </c:if>
			   
<%-- 			   <c:forEach items="${requestScope.commentUser}" var="cu"> --%>
<%-- 	          <p class = "activity">${cu.comment} </p> --%>
<%-- 	      	   </c:forEach>  --%>
	      	   
	      	   <div class="table-wrapper" id = "divTableMessages">
				<ul class="collection" id="commentslist">

					<c:forEach items="${requestScope.commentUser}" var="ce">
					<li class="collection-item avatar"><img style="object-fit: cover"
					src="${ce.commentator.avatar.path}" alt="" class="circle">

					<span class="title"><a
					href="userProfile.do?id=${ce.commentator.id}">
				    ${ce.commentator.firstName} ${ce.commentator.lastName}</a></span>
					<p>
					${ce.comment}
					</p> <c:if test="${ce.commentator.id == userLogined.id}">
					
					<form id="deleteForm"
					action="deleteUserComment.do?id=${ce.id}&userId=${profileId}"
					method="POST">

							<button type="submit" class="secondary-content">
							<i class="material-icons"> <fmt:message  key="delete" /> </i>
							</button>

					</form>

					</c:if></li>
					</c:forEach>
				</ul>	
				</div>      	   
	      	   
	      	   
	      	    
		 </c:when>
	</c:choose>

       
           
      </section>
      
      <section id="activity" class="hidden">
        <p>Most recent actions:</p>
        <ul class="collection z-depth-2 ">
        <c:forEach items="${requestScope.userActivity}" var="ua">
        <c:choose>
		    <c:when test="${ua.act=='user'}">    
		    	<li class="collection-item">
		    	${ua.activity}      		
          		<a href="adminUserProfile.do?id=${ua.idAct}">
         			  ${ua.name}
          		</a>
          		</li>
          	</c:when>
          	 <c:when test="${ua.act=='event'}">  
          	 		<li class="collection-item">
          	 		${ua.activity}        		
          		<a href="eventDetail.do?id=${ua.idAct}">
         			  ${ua.name}
          		</a>
          		</li>
          	</c:when>
         </c:choose>
      	</c:forEach>
      	</ul>
      </section>
      
<!--       <section id="friends" class="hidden"> -->
<!--         <p>Friends list:</p> -->
<!--         <ul id="friendslist" class="collection z-depth-2 " style="height: 100%;"> -->
<%--           <c:forEach items="${requestScope.friends}" var="f"> --%>
<!--           		<li> -->
<%--           		${f.friend.lastName}   ${f.friend.firstName} --%>
<%--           		<a href="adminUserProfile.do?id=${f.friend.id}"> --%>
<%--           			<img src="${f.friend.avatar.path}" width="22" height="22"> --%>
<!--           		</a> -->
<!--           		</li> -->
<%--       		</c:forEach> --%>
<!--       	</ul> -->
<!--       </section> -->


		<section id="photos" class="hidden">
			<div id="blueimp-gallery-dialog" data-show="fade"
				data-hide="fade">
								<!-- The Gallery widget  -->
								<div
									class="blueimp-gallery blueimp-gallery-carousel blueimp-gallery-controls">
									<div class="slides"></div>
									<a class="prev"></a> <a class="next"></a> <a class="play-pause"></a>
								</div>
							</div>
							<p>
								 <fmt:message key="Photos" /> : 
<!-- 								<a href="upload.do?uploadtype=event"> <span -->
<!-- 									style="float: right;">Upload new photo</span></a> -->
									
							</p>
							<!--         <ul id="photolist" class="clearfix"> -->
							<%--           <c:forEach items="${photos}" var="p"> --%>
							<!--           		<li>    -->
							<!--           		<a href=""> -->
							<%--           			<img src="${p.path}" style="height: 120px; width: 120px; object-fit: cover">> --%>
							<!--           		</a> -->
							<!--           		</li> -->
							<%--       		</c:forEach> --%>
							<div id="links">
								<c:forEach items="${photos}" var="photo">
									<a href="${photo.path}" title="${photo.path}" data-dialog>
										<img src="${photo.path}"
										style="height: 160px; width: 160px; object-fit: cover"
										alt="${photo.path}">
									</a>
								</c:forEach>
			</div>
							<!--       	</ul> -->
	</section>      
      
	  <section id="newfriends" class="hidden">
			<p> <fmt:message key="Friends" />:</p>


			<ul class="collection" id="collectionResults">
			<c:forEach items="${requestScope.friends}" var="m">
				<li class="collection-item avatar" id="userFriendCard${m.friend.id}"><img src="${m.friend.avatar.path}" alt="" class="circle"> 
				<span class="title"><a href="userProfile.do?id=${m.friend.id}">
													${m.friend.firstName} ${m.friend.lastName}</a></span>
													
				<p>
				<i> <fmt:message key="profile.Averagemark"/>: ${Math.round(m.rate)} Total points: ${Math.round(m.points)} </i> <br>
				</p> <%--       <a  class="secondary-content"><i class="material-icons">${m.status}</i></a> --%>													
													
				<form id="userFriendFormWithId${m.friend.id}">
				<input type="hidden" id="userFriendId" class="userFriendId"
											name="userFriendId" value="${m.friend.id}">
				</form>			
				</li>
						
			</c:forEach>
			</ul>

		</section>    
		
	  <section id="moderatorEvents" class="hidden">
			<p> <fmt:message key="profile.Author" />:</p>


			<ul class="collection" id="collectionResults">
			<c:forEach items="${requestScope.moderatorEvents}" var="m">
				<li class="collection-item avatar" id="userFriendCard${m.id}"><img src="${m.avatar.path}" alt="" class="circle"> 
				<span class="title"><a href="eventDetail.do?id=${m.id}">
												<c:out value="${m.name}"/></a></span>
													
				<p>
				<fmt:message key="Date"/>: <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${m.dateFrom}" /> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${m.dateTo}" /> 
				</p>
				
				</li>
						
			</c:forEach>
			</ul>

		</section>
		
		
	  <section id="memberEvents" class="hidden">
			<p> <fmt:message  key="profile.Member" />:</p>


			<ul class="collection" id="collectionResults">
			<c:forEach items="${requestScope.memberEvents}" var="m">
				<li class="collection-item avatar" id="userFriendCard${m.id}"><img src="${m.avatar.path}" alt="" class="circle"> 
				<span class="title"><a href="eventDetail.do?id=${m.id}">
												<c:out value="${m.name}"/></a></span>
													
				<p>
				<fmt:message key="Date" /> : <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${m.dateFrom}" /> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${m.dateTo}" /> 
				</p>
				
				</li>
						
			</c:forEach>
			</ul>

		</section>         
      
      
<!--       <section id="settings" class="hidden"> -->
<!--         <p>Edit your user settings:</p> -->
<!--         <div class="row">         -->
<!--         <form action="adminUserRequest.do?action=edit" method="POST" > -->
<!-- 	       <div class="row"> -->
<!-- 		        <div class="input-field col s6"> -->
<%-- 		        <input type="hidden" name = "userId" value ="${user.id}"> --%>
<%-- 		        <p><span>E-mail Address </span><input type ="text" value = "${requestScope.user.email}"></p> --%>
<!-- 		        </div> -->
<!-- 		        	<div class="input-field col s6">        -->
<%-- 		       	 	<p><span>Language </span><input type ="text" value = "${requestScope.user.firstName}"></p> --%>
<!-- 		  		</div> -->
<!-- 		   </div> -->
<!--         	<div class="row"> -->
<!-- 		        <div class="input-field col s6"> -->
<%-- 		        	<p><span>First name</span><input type ="text" value = "${requestScope.user.firstName}" name="firstName"></p> --%>
<!-- 		        </div> -->
<!-- 		        	<div class="input-field col s6">        -->
<%-- 		       	 	<p><span>Last name</span><input type ="text" value = "${requestScope.user.lastName}" name = "lastName"></p> --%>
<!-- 		  		</div> -->
<!-- 		   </div>   -->
<!--         <p><span>user Type</span>  -->
<!--        			<select name="userTypeId" id = "userTypeId"> -->
<%-- 					<c:forEach items="${userType}" var="userType"> --%>
<%-- 						<option value="${userType.id}">${userType.name}</option> --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
<!-- 		</p>   -->
<!-- 		<p><span>sex</span>  -->
<!--        			<select name="sex" id = "sex"> -->
<!-- 							<option value="male">male</option> -->
<!-- 							<option value="female">female</option> -->
<!-- 				</select> -->
<!-- 		</p>   -->
<!-- 		<input type="submit" value="Submit"> -->
<!-- 		</form> -->
<!-- 		</div> -->
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
</fmt:bundle>
</body>
