<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.table-wrapper {
	width: 100%;
	height: 450px;
	overflow: auto;
}
</style>
<br>
<h4>Mail Sender</h4>
<br>
<div class="blue lighten-5">
	<div class="row">
		<form class="col s12">
			<div class="row">
				<div class="input-field col s12">
					<textarea id="textarea1" class="materialize-textarea"
						placeholder="message"></textarea>

				</div>
			</div>
		</form>
	</div>

	<button id="sendmessagetousers" class="btn" type="submit" name="action">
		Send <i class="mdi-content-send right"></i>
	</button>
</div>
<br>
<br>
<script type="text/javascript">
	$(document).ready(function() {
		$('#sendmessagetousers').click(function() {
			var checkedUsers = [];
			$("input:checkbox[name=type]:checked").each(function() {
				checkedUsers.push($(this).attr("id"));
			});
			if (checkedUsers == "") {
				Materialize.toast("no one cheked", 1500);
			}
		});

		$('#checkallusers').click(function() {

			$("input:checkbox[name=type]").each(function() {
				$(this).attr("checked", "checked");
			});

		});
		$('#removecheckfromall').click(function() {
			$("input:checkbox[name=type]:checked").each(function() {
				$(this).removeAttr("checked");
			});

		});

	});
</script>
<div class="table-wrapper" id="divTableComments">
	<ul>
		<h4>
			<a id="checkallusers" style="margin-left: 4%;">all</a> <a
				id="removecheckfromall" style="margin-left: 4%;">remove</a>
		</h4>



		<br>
		<c:forEach items="${allmembersievent }" var="member">
			<li><input type="checkbox" name="type" class="filled-in"
				checked="none" id="${member.getUser().getId() }"
				<c:if test="${member.getUser().getEmail() ==null }">disabled="disabled"</c:if> />
				<label for="${member.getUser().getId()}"><span>${member.getUser().getFirstName()}
						${member.getUser().getLastName() }</span><br> <span>
						${member.getUser().getEmail() }</span></label></li>
		</c:forEach>
	</ul>
</div>
<br>