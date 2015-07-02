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

	<link rel="stylesheet" type="text/css" media="all" href="css/styleUserProfile.css">
	 
	 <script src="js/materialize.js"></script>
    <script src="js/init.js"></script>
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
	<!-- Include jTable script file. -->
	<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
	<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
	<script src="js/jquery.jtable.js" type="text/javascript"></script>
	  <link type="text/css" rel="stylesheet" href="css/jquery.ratings.css" />
    <script src="js/jquery.ratings.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $("#tabs").tabs();
        });
    </script>
</head>
<body>

<jsp:include page="../home/logoutmodal.jsp"/>
<jsp:include page="../user/usercabinetheader.jsp"/>

<table>
    <tr>

	
	
        <td style=" width:20%; vertical-align: top;">
<%--         <c:choose> --%>
<%--          <c:when test="${isAdmin}"> --%>
                    <jsp:include page="adminLeftPanel.jsp"/>
<%--             </c:when> --%>
<%--             <c:otherwise> --%>
<%--             	    <jsp:include page="../user/usercabinetpanelleft.jsp"/> --%>
<%--             </c:otherwise> --%>
<%--          </c:choose>    --%>
        
        </td>




        <td style=" width:80%;vertical-align: top;">
            <c:choose>

          
                <c:when test="${centralContent == 'adminEvent'}">
                    <jsp:include page="adminEvent.jsp"/>
                </c:when>

 				<c:when test="${centralContent == 'adminUser'}">
                    <jsp:include page="adminUserTable.jsp"/>
               </c:when>
               <c:when test="${centralContent == 'adminUserDetail'}">
                    <jsp:include page="adminUserDetail.jsp"/>
               </c:when>
                <c:when test="${centralContent == 'adminTag'}">
                    <jsp:include page="adminTag.jsp"/>
               </c:when>
               <c:when test="${centralContent == 'adminCity'}">
                    <jsp:include page="adminCityTable.jsp"/>
               </c:when>
               <c:when test="${centralContent == 'adminCountry'}">
                    <jsp:include page="adminCountryTable.jsp"/>
               </c:when>

            </c:choose>
        </td>

    </tr>
</table>


</body>
</html>








