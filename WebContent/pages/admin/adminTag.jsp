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
		$('#tblAdminTag').jtable({
			title : 'Tag List',
			actions : {
				listAction :   'adminTagRequest.do?action=list',
				createAction : 'adminTagRequest.do?action=create',
				updateAction : 'adminTagRequest.do?action=update',
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
					width : '60%',
					list : true,
					edit : true
				}
			}
		});
		$('#tblAdminTag').jtable('load');
	});
</script>

</head>
<body>
<div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">

		<h4>AEvent List</h4>
		<div id="tblAdminTag"></div>
	</div>
</body>
</html>