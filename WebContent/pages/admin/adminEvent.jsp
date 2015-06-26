 
<script type="text/javascript">
	$(document).ready(function() {
		$('#tblAdminEvent').jtable({
			title : 'Event List',
			actions : {
				listAction :   'adminEventRequest.do?action=list',
				createAction : 'adminEventRequest.do?action=create',
				updateAction : 'adminEventRequest.do?action=update',
				deleteAction : 'adminEventRequest.do?action=delete'
			},
			fields : {
				id : {
					title : 'id',
					width : '3%',
					key : true,
					list : true,
					edit : false				
					},
					
				name : {
					 title: 'name',
						display: function (data) {
	                     return '<a href="eventDetail.do?id=' + data.record.id + '">'+data.record.name+'</a>';
	                 }
				},
				description : {
					title : 'description',
					width : '67%',
					edit : true
				},
				dateFrom : {
					title : 'dateFrom',
					width : '67%',
					edit : true
				},
				dateTo : {
					title : 'dateTo',
					width : '67%',
					edit : true
				},
				moderatorId : {
					title : 'moderatorId',
					width : '67%',
					edit : true,
					visibility: 'hidden'
				},
				addressId : {
					title : 'addressId',
					width : '67%',
					edit : true,
					visibility: 'hidden'
				},
				status : {
					title : 'status',
					width : '67%',
					edit : true,
					options: { 'active': 'active', 'cancel': 'cancel' },
				},
				city: {
			        title: 'city',
			        display:function(data){
			                return data.record.address.city.name;
			        }
				},
				moderator: {
			        title: 'moderator1',
					display: function (data) {
                     return '<a href="userProfile.do?id=' + data.record.moderatorId + '">'+data.record.moderator.lastName+'</a>';
                 }
				}
			}
		});
		$('#tblAdminEvent').jtable('load');
	});
</script>

<div style="width: 100%; margin-right: 10%; margin-left: 10%; text-align: center;">

		<h4>AEvent List</h4>
		<div id="tblAdminEvent"></div>
	</div>