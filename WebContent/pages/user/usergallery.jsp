<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet"	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/south-street/jquery-ui.css" 	id="theme">
<link rel="stylesheet"	href="//blueimp.github.io/Gallery/css/blueimp-gallery.min.css">

	<!-- begin Localization -->
	<ct:showLocale basename="locale.register.messages" from = "usergallery.do" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.register.messages">
	<!-- end Localization -->
	

  <script type="text/javascript">
 	$(document).ready(function() {
 		$(".delete-user-photo").on('click', function() {
 		var deleteHref = $(this).attr('data-value');
  			$('#confirmDeleteModal').openModal();
			$('#modalDeletePhotoAnchor').attr('data-value',deleteHref);
 		});
 	});

	$(document).ready(function() {
		$(document).on('click', '#modalDeletePhotoAnchor', function() {
			var location = $(this).attr('data-value');
			$.ajax({
				url:'deleteUserPhoto.do',
				type: "post",
				dataType: "json",
				data:{location:location},
				complete:function(){
					$('#blueimp-gallery-dialog').dialog('close');
					var titleSelector = '[title=' +"'"+ location+"'"+']';
					$(titleSelector).remove();
				}
			});
		});
	});
  </script>
<div class="row">

	<div class="col s12" style="margin-top: 10px;">


		<ul class="collection z-depth-2">
			<li class="collection-item" style="height: 50px;"> <fmt:message key="gal.Gallery"/></li>
		</ul>

		<ul class="collection z-depth-2" style="min-width: 700px;">
			<li class="collection-item">


				<div id="links">
					<c:forEach items="${photos}" var="photo">
						<a href="${photo.path}" title="${photo.path}" id="userPhoto-${photo.id}" data-dialog> <img
							src="${photo.path}"
							style="height: 160px; width: 160px; object-fit: cover"
							alt="${photo.path}"></a>
					</c:forEach>
				</div>


				<div id="blueimp-gallery-dialog" data-show="fade" data-hide="fade">
					<!-- The Gallery widget  -->
					<div
						class="blueimp-gallery blueimp-gallery-carousel blueimp-gallery-controls">
						<div class="slides"></div>
						<a class="prev"></a> <a class="next"></a> <a class="play-pause"></a>
						<a class=""><fmt:message key="gal.Setasavatar"/></a>
					</div>
					<div style="visibility: hidden" id="footer">
						<a class="update-user-avatar" href="updateUserAvatar.do" data-url="updateUserAvatar.do" id="updateAvatar">
						<img src="icons/update-avatar-icon.png" style="width: 30px; height: 30px;">
							<fmt:message key="gal.Setonavatar"/></a>
						<a class="delete-user-photo" data-value="" href="#_" id="deletePhoto" style="float: right">
						<img src="icons/delete-photo-icon.png" style="width: 30px; height: 30px;" />
							<fmt:message key="gal.Deletefromgallery"/></a>
					</div>
				</div> 
				
				
				<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
				<script
					src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>
				<script
					src="//blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
				<script src="js/jquery.image-gallery.js"></script>
				 <!-- <script src="js/jquery.image-gallery.min.js"></script> 				
<!-- 				<script	src="js/demo.js"></script> -->



			</li>
		</ul>

	</div>
</div>

  <div id="confirmDeleteModal" class="modal" style="width: 25%">
    <div class="modal-content">
      <h4><fmt:message key="gal.Deletephoto"/></h4>
      <p><fmt:message key="gal.Areyousure"/> ?</p>
    </div>
    <div class="modal-footer">
      <a href="#_" id = "modalDeletePhotoAnchor" data-value="" class=" modal-action modal-close waves-effect waves-green btn-flat"><fmt:message key="gal.Yesdeleteit"/></a>
    </div>
  </div>
  
  </fmt:bundle>