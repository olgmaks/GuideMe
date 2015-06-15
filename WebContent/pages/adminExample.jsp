<!DOCTYPE>
<html>
<head>
<title>Event List</title>
<!-- Include one of jTable styles. -->
<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<!-- Include jTable script file. -->
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
 
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
					title : 'name',
					width : '30%',
					edit : true
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
                     return '<a href="adminUserProfile.do?id=' + data.record.moderatorId + '">'+data.record.moderator.lastName+'</a>';
                 }
				}
			}
		});
		$('#tblAdminEvent').jtable('load');
	});
</script>

</head>
<body>
<div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">

		<h4>AEvent List</h4>
		<div id="tblAdminEvent"></div>
	</div>
</body>
</html>