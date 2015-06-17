<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>

<title>New event</title>
</head>
<body>



	<!-- <input type="image"	src="img/guide1.jpg"><br> -->



	<div>
		<form action="submitEvent.do" method="post">

			<div class="file-field input-field">

				<div class="btn">
					<span>addEventpage.download_image</span> <input type="file"
						accept="image/*" name="image" />
				</div>
			</div>
			<br> <br> <br>


			<div>

				addEventpage.eventName: <input type="text" id="eventName"
					name="eventName" /> <span id="eventNameMessage"></span><br>


				<div class="input-field col s12">
					<select id="country" name="country" class="browser-default">

						<option value="" disabled selected>addEventpage.country:</option>
						<c:forEach var="country" items="${countryList }">
							<option value="${country.id}">${country.name}</option>

						</c:forEach>
					</select> <select id="city" name="city" class="browser-default">
						<option value="" disabled selected>addEventpage.city:</option>
						<c:forEach var="city" items="${cityList }">

							<option value="${city.id}">${city.name}</option>
						</c:forEach>
					</select> addEventpage.street:
				</div>
				<br> addEventpage.dateFrom: <input type="date"
					class="datepicker" id="dateFrom" name="dateFrom" /> <span
					id="dateFromMessage"></span> addEventpage.dateTo: <input
					type="date" class="datepicker" id="dateTo" name="dateTo" /> <span
					id="dateToMessage"></span> <br>

				<%@ include file="time.jsp"%>

				<div class="row">

					<div class="row">
						<div class="input-field col s12">
							<textarea id="description" name="description" rows="10" cols="45"
								maxlength="400" class="materialize-textarea"></textarea>
							<label for="description">addEventpage.Description:</label>
						</div>
					</div>

				</div>
				addEventpage.Partisipant_limit: <input type="number" name="limit"
					min="1"> <br> addEventpage.addition: <input
					type="file" accept="audio/*,video/*,image/*" name="image" multiple>



				
				<br>
				<button type="submit">addEventpage.Add_event</button>
			</div>
		</form>
	</div>

</body>
</html>