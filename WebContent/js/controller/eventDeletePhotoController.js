$(document).ready(function() {
	$(".delete-event-photo").on('click', function() {
		var deleteHref = $(this).attr('data-value');
		$('#confirmDeleteModal').openModal();
		$('#modalDeletePhotoAnchor').attr('data-value',deleteHref);
	});
});

$(document).ready(function() {
	$(document).on('click', '#modalDeletePhotoAnchor', function() {
		var location = $(this).attr('data-value');
		$.ajax({
			url:'deleteEventPhoto.do',
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
