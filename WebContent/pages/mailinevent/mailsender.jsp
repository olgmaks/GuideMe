<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${allmembersievent }" var="member">
	<p>
		<input type="checkbox" class="filled-in" id="filled-in-box"
			checked="checked" /> <label for="filled-in-box">${member.getUser().getEmail() }</label>
	</p>

</c:forEach>