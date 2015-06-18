<%--
  Created by IntelliJ IDEA.
  User: OLEG
  Date: 14.06.2015
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Cabinet</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Guide ME</title>
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
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

<jsp:include page="../home/logoutmodal.jsp"/>
<jsp:include page="adminHeader.jsp"/>


<table>
    <tr>
        <td style=" width:20%; vertical-align: top;">
            <jsp:include page="adminLeftPanel.jsp"/>
        </td>


        <td style=" width:60%;vertical-align: top;">
			
		<div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">

		<h4>AEvent List</h4>
				<div id="tblAdminEvent"></div>
		</div>     
        </td>

    </tr>
</table>


</body>
</html>
