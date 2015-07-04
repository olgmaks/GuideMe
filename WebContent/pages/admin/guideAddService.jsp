
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>

<script type="text/javascript">
	$(document).ready(function() {
		$('#dropdowndef').click(function() {
			var selectvalue = $('#dropdowndef').val();
			$.ajax({
				url : 'addServiceToGuideEvent.do',
				type : 'GET',
				data : {
					select : selectvalue
				},
				success : function(data) {
					$('#desc').val(data.description);
					$('#price').val(data.price);
				}
			});
		});
		$('#sub').click(function() {
			Materialize.toast('<span>Item Deleted</span><a class=&quot;btn-flat yellow-text&quot; href=&quot;#!&quot;>Undo<a>', 1000);
		});
		
		
	});
</script>
<select id="dropdowndef" style="width: 20%;" class="browser-default">
	<c:forEach items="${listOfServices }" var="service">
		<option value='${service.getId() }'>${ service.getName()}</option>
	</c:forEach>
</select>

desciption
<input type="text" style="width: 50%;" id="desc" />
price
<input type="number" min="0" style="width: 25%;" id="price" />
date from
<input type="date" style="width: 25%;" id="price" />
date to
<input type="date" style="width: 25%;" id="price" />
<br>

<br>
<button class="btn waves-effect waves-light" type="submit" id="sub"
	name="action">Add To Event</button>



<%-- addServiceToGuideEvent.do?serviceid=${service.getId() } --%>