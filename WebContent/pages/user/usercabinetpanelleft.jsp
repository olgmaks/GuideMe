<%--User controls panel - left side of a page--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="js/left-panel-tag-controller.js" type="text/javascript"></script>

<script>
	$(document).ready(function() {
		$('#modalInterests').click(function() {
			$('#modalInterestsWindow').openModal();
			$('.ui-autocomplete').css('z-index', '1001');
		});
		$('#modalLanguages').click(function() {
			$('#modalLanguagesWindow').openModal();
			$('.ui-autocomplete').css('z-index', '1001');
		});
	});
</script>
<div class="row">
	<div class="col s12" style="margin-top: 10px; width: 260px;">
		<ul class="collection z-depth-2 " style="height: 100%;">
			<li class="collection-item">
				<div class="" align="center">
					<a href="userCabinet.do"> <!-- 					<img class="responsive-img" -->
						<!-- 						style="height: 40%; width: 40%; object-fit: cover" -->
						<%-- 						src="${sessionUser.avatar.path}"> --%> <img
						class="circle"
						style="height: 120px; width: 120px; object-fit: cover"
						src="${sessionUser.avatar.path}">
					</a>
				</div> <a href="editProfile.do" style="float: right;"> <img
					class="circle" style="height: 40px; width: 40px;"
					src="icons/edit-profile.png"></a> <br> <h7>${sessionUser.firstName}
				${sessionUser.lastName}</h7> <br> <br> <a
				href="eventsincabinet.do">
					<button class="btn light-blue waves-effect waves-light"
						type="submit" name="action"
						style="width: 100%; text-align: left; font-size: 100%; text-transform: capitalize">
						Events<i class="mdi-action-extension right"></i>
					</button>
			</a> <a href="addEvent.do">
					<button class="btn light-blue waves-effect waves-light"
						type="submit" name="action"
						style="width: 100%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
						New event<i class="mdi-content-add-circle-outline right"></i>
					</button>
			</a> <a href="home.do">
					<button class="btn light-blue waves-effect waves-light"
						type="submit" name="action"
						style="width: 100%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
						Find event<i class="mdi-action-search right"></i>
					</button>
			</a> <a href="usermessages.do">
					<button class="btn light-blue waves-effect waves-light"
						type="submit" name="action"
						style="width: 100%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
						Messages<i class="mdi-content-mail right"></i>
					</button>
			</a> <a href="userfriends.do">
					<button class="btn light-blue waves-effect waves-light"
						type="submit" name="action"
						style="width: 100%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
						Friends<i class="mdi-social-group right"></i>
					</button>
			</a> <a href="searchuser.do">
					<button class="btn light-blue waves-effect waves-light"
						type="submit" name="action"
						style="width: 100%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
						Find friends<i class="mdi-social-group-add right"></i>
					</button>
			</a> <a href="userCabinetComments.do">
					<button class="btn light-blue waves-effect waves-light"
						type="submit" name="action"
						style="width: 100%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
						Comments<i class="mdi-communication-comment right"></i>
					</button>
			</a> <a href="usergallery.do"><button
						class="btn light-blue waves-effect waves-light" type="submit"
						name="action"
						style="width: 100%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
						Gallery<i class="mdi-communication-comment right"></i>
					</button></a> <c:if test="${sessionUser.userTypeId == 3}">
					<a href="guideservices.do">
						<button class="btn light-blue waves-effect waves-light"
							type="submit" name="action"
							style="width: 100%; margin-top: 10px; text-align: left; font-size: 100%; text-transform: capitalize">
							Services</button>
					</a>
				</c:if>

				<h6>
					<a href="#_" id="modalLanguages">Languages</a>:
					<div id="userLanguagesTagReferences" style="width: 200px;"></div>
				</h6>
				<h6>
					<a href="#_" id="modalInterests">Interests</a>:
					<div id="userInterestsTagReferences" style="width: 200px;"></div>
				</h6>
			</li>
		</ul>
	</div>
</div>
<jsp:include page="modal/userintereststags.jsp" />
<jsp:include page="modal/userlanguages.jsp" />




