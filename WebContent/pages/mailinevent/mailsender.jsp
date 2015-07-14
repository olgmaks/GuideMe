<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><script
	type="text/javascript">
	$(document).ready(function() {
		$('#sendmessagetousers').click(function() {
			var checkedUsers = [];
			$("input:checkbox[name=type]:checked").each(function() {
				checkedUsers.push($(this).attr("id"));
			});
			if (checkedUsers == "") {
				Materialize.toast("no one cheked", 1500);
			} else if ($("#textmessagetousers").val() == "") {
				Materialize.toast("message cannot be empty", 1500);
			} else {
				$.ajax({
					url : 'sendEmail.do',
					type : 'POST',
					data : {
						textmessagetosendval : $("#textmessagetousers").val(),
						usercheckedtosendval : JSON.stringify(checkedUsers)
					},
					success : function(data) {
						Materialize.toast("Message Sent", 1500);
						$("#textmessagetousers").val("");
						$("input:checkbox[name=type]:checked").each(function() {
							$(this).prop("checked", false);
						});
					}

				});

			}

		});

		$('#checkallusers').click(function() {

			$("input:checkbox[name=type]").each(function() {
				if (typeof ($(this).attr("disabled")) == typeof undefined) {
					$(this).prop("checked", true);

				}
			});

		});

		$('#removecheckfromall').click(function() {
			$("input:checkbox[name=type]:checked").each(function() {
				$(this).prop("checked", false);
			});

		});

	});
</script>
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
					<textarea id="textmessagetousers" class="materialize-textarea"
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

<div class="table-wrapper" id="divTableComments">
	<ul>
		<button id="checkallusers" style="margin-left: 4%;"
			class="btn transparent" type="submit" name="action">
			<span class="blue-text text-darken-2"> all</span>
		</button>
		<button id="removecheckfromall" style="margin-left: 4%;"
			class="btn transparent" type="submit" name="action">
			<span class="blue-text text-darken-2"> remove</span>
		</button>


		<br>


		<br>
		<c:forEach items="${allmembersievent }" var="member">
			<li><input type="checkbox" name="type" class="filled-in"
				id="${member.getUser().getId() }"
				<c:if test="${member.getUser().getEmail() ==null }">disabled="disabled"</c:if> />
				<label for="${member.getUser().getId()}"><span>${member.getUser().getFirstName()}
						${member.getUser().getLastName() }</span><br> <span>
						${member.getUser().getEmail() }</span></label></li>
		</c:forEach>
	</ul>
</div>
<br>
