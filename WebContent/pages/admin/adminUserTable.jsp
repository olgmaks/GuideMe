 
<script type="text/javascript">
	$(document).ready(function() {
		$('#tblAdminUser').jtable({
			title : 'Event List',
			actions : {
				listAction :   'adminUserRequest.do?action=list',
				createAction : 'adminUserRequest.do?action=create',
				updateAction : 'adminUserRequest.do?action=update',
				deleteAction : 'adminUserRequest.do?action=delete'
			},
			fields : {
				Id : {
					title : 'id',
					width : '3%',
					key : true,
					list : true,
					edit : false				
					},
					
				firstName : {
					 title: 'lastName',
						display: function (data) {
	                     return '<a href="userProfile.do?id=' + data.record.Id + '">'+data.record.lastName+'</a>';
	                 }
				},
				lastName : {
					 title: 'firstName',
						display: function (data) {
	                     return '<a href="userProfile.do?id=' + data.record.Id + '">'+data.record.firstName+'</a>';
	                 }
				},
				addressId : {
					title : 'addressId',
					width : '67%',
					edit : true,
					visibility: 'hidden'
				},
				city: {
			        title: 'city',
			        display:function(data){
			                return data.record.address.city.name;
			        }
				}
			}
		});
		$('#tblAdminUser').jtable('load');
	});
</script>
<form action="UserDetailReport" method="get">
  <input type="submit" value="userReportBrowser">
</form>

<div style="width: 100%; margin-right: 10%; margin-left: 10%; text-align: center;">
		<h4>User List</h4>
		<div id="tblAdminUser"></div>
	</div>