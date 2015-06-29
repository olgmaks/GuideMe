<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/south-street/jquery-ui.css" id="theme">
<link rel="stylesheet" href="//blueimp.github.io/Gallery/css/blueimp-gallery.min.css">

<div class="row">

	<div class="col s12" style="margin-top: 10px;">


		<ul class="collection z-depth-2">
			<li class="collection-item" style="height: 50px;">Gallery</li>
		</ul>

		<ul class="collection z-depth-2">
			<li class="collection-item">
 

				<div id="links">
					<c:forEach items="${photos}" var="photo">
					<a href="${photo.path}" title="${photo.path}" data-dialog> 
					<img src="${photo.path}" style="height: 160px; width: 160px; object-fit: cover" alt="${photo.path}"></a>
				</c:forEach>
				</div>

 
	<div id="blueimp-gallery-dialog" data-show="fade"  data-hide="fade" >
	    <!-- The Gallery widget  -->
	    <div class="blueimp-gallery blueimp-gallery-carousel blueimp-gallery-controls"  >
	        <div class="slides"></div>
	        <a class="prev"></a>
	        <a class="next"></a>
	        <a class="play-pause"></a>
	    </div>
	</div>
<!-- <script -->
<!-- 					src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
				<script	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>
				<script	src="//blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
				<script src="js/jquery.image-gallery.js"></script>
<!-- 				<script src="js/jquery.image-gallery.min.js"></script> 				
<!-- 				<script	src="js/demo.js"></script> --> 



			</li>
		</ul>

	</div>
</div>