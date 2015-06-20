<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Cabinet</title>


<!-- tag start -->
    
    <link rel="StyleSheet" href="css/ui-lightness/jquery-ui-1.9.2.custom.min.css" type="text/css" media="all"/>
    <link rel="StyleSheet" href="css/jquery.tagedit.css" type="text/css" media="all"/>
    
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery.autoGrowInput.js"></script>
    <script type="text/javascript" src="js/jquery.tagedit.js"></script>
    
<!-- tag end     -->
    
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<!--     <script src="http://code.jquery.com/jquery-latest.min.js"></script> -->
<!--     <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script> -->
    <script src="js/materialize.js"></script>
    <script src="js/init.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.modal-trigger').leanModal();
        });
    </script>


    
    <script type="text/javascript">
	$(function() {
		
		// Edit only
		$( "#tagform-editonly" ).find('input.tag').tagedit({
			autocompleteURL: 'autocompleteUserTags.do',
			autocompleteOptions: {minLength: 0},
			allowEdit: false,
			allowAdd: false
	
		});
	});
	
	$(function() {
		
		// Edit only
		$( "#tagform-editonlyLang" ).find('input.tag').tagedit({
			autocompleteURL: 'langAutocompleteUserTags.do',
			autocompleteOptions: {minLength: 0},
			allowEdit: false,
			allowAdd: false
	
		});
	});	
	</script>

	<script>
	$(document).ready(function() {
		
		$("#tagform-editonly").submit(function () {
			
        	$.ajax({
            	
        		
                url: 'submitUserTags.do',
                type: 'post',
                dataType: 'json',
                data: $("#tagform-editonly").serialize(),
                success: function (data) {
                	alert(data.valid);
                    
                }
            });
            return false;
       	 });
	 });
	
	$(document).ready(function() {
		
		$("#tagform-editonlyLang").submit(function () {
			
        	$.ajax({
            	
        		
                url: 'langSubmitUserTags.do',
                type: 'post',
                dataType: 'json',
                data: $("#tagform-editonlyLang").serialize(),
                success: function (data) {
                	alert(data.valid);
                    
                }
            });
            return false;
       	 });
	 });
	
	</script>

</head>
<body>
	<jsp:include page="../home/logoutmodal.jsp"/>
	<jsp:include page="usercabinetheader.jsp"/>
	
	
<table>
    <tr >
        <td style=" width:20%; vertical-align: top;">

            <%--User controls panel - left side of a page--%>
            <div class="row" >
                <div class="col s12" style="margin-top:10px; ">
                    <ul class="collection z-depth-2 " style="height: 100%;">
                        <li class="collection-item"  >
                <div class="" align="center"  >  <img class="circle" style="height: 120px; width: 120px; object-fit: cover" src="${userPhoto.path}"></div><br>
                <h7>${sessionUser.firstName} ${sessionUser.lastName} Points: ${requestScope.userCalc.calculate()}</h7><br><br>

                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;text-align: left;font-size: 100%;text-transform: capitalize">Events<i class="mdi-action-extension right" ></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">New event<i class="mdi-content-add-circle-outline right" ></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Find event<i class="mdi-action-search right"></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Messages<i class="mdi-content-mail right"></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Friends<i class="mdi-social-group right"></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Find friends<i class="mdi-social-group-add right"></i></button>
                <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">Comments<i class="mdi-communication-comment right"></i></button>

                </li>
                </ul>
            </div>
            </div>

        </td>
        <td style=" width:60%;vertical-align: top;">
            <div class="row" >
                <div class="col s12" style="margin-top:10px;">
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" >
                            
                            
    					<form action="submitUserTags.do" method="post" id="tagform-editonly" >
							<p>
							    <input type="text" name="tag[]" value="" class="tag"/>
							    
   								<c:forEach items="${requestScope.tags}" var = "tag">
   									<input type="text" name="tag[3-a]" value="${tag.name}" class="tag" />
   								</c:forEach>
   											
								<input type="submit" name="save" value="Save"/>
							</p>
    					</form>                            
                            
                            
                            
                        </li>
                        
                        <li class="collection-item" >
    					<form action="langSubmitUserTags .do" method="post" id="tagform-editonlyLang" >
							<p>
							    <input type="text" name="tag[]" value="" class="tag"/>
							    
   								<c:forEach items="${requestScope.langs}" var = "lang">
   									<input type="text" name="tag[3-a]" value="${lang.name}" class="tag" />
   								</c:forEach>
   											
								<input type="submit" name="save" value="Save"/>
							</p>
    					</form>                            
                        </li>                        
                    </ul>
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" >
                            <ul class="collection z-depth-2 ">
                                <c:forEach var="userInEvent" items="${userInEvents}">
                                <li class="collection-item" >
                                    <table>
                                        <tr>
                                        <td width="20%;">

                                <img class="" style="height: 120px; width: 120px; object-fit: cover" src="${eventPhotosPathMap.get(userInEvent.eventId)}">
                                        </td>
                                        <td width="70%;">
                                            ${userInEvent.event.name}<br><br>
                                            ${userInEvent.event.description}<br><br>
                                            ${userInEvent.status}<br>
                                        </td>
                                        <td width="10%;">
                                            <a class="btn-floating btn-large waves-effect waves-light green" style="transform: scaleY(0.7) scaleX(0.7)"><i class="mdi-image-remove-red-eye"></i></a>
                                            <br><a class="btn-floating btn-large waves-effect waves-light red" style="transform: scaleY(0.7) scaleX(0.7)"><i class="mdi-content-remove"></i></a>
                                        </td>
                                        </tr>
                                    </table>
                                </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </td>
        <td style=" width:20%;vertical-align: top;">
            <div class="row" >
                <div class="col s12" style="margin-top:10px;">
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" >
                            Recommended
                        </li>
                    </ul>
                    <ul class="collection z-depth-2 ">
                        <li class="collection-item" >
                            Events
                        </li>
                    </ul>
                </div>
            </div>
        </td>
    </tr>
</table>




</body>
</html>