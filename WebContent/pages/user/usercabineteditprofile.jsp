<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>
<link>

<link rel="StyleSheet" href="css/dataTables.css" type="text/css" media="all"/>
<script src="js/moment.js"></script>
<%--<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>--%>

	<!-- begin Localization -->
	<jsp:include page="..//localization.jsp"/>
	<ct:showLocale basename="locale.register.messages" from = "editProfile.do" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.register.messages">
	<!-- end Localization -->

	<title> <fmt:message key="register.title" /></title>

<script>
	$(document).ready(function () {
		
        $("#registerform").submit(function () {
        	
            $("#firstName").change();
            $("#lastName").change();
            //$("#email").change();
            //$("#password").change();
            //$("#confirm").change();
            $("#sex").change();
            $("#cellNumber").change();


            $.ajax({
                url: "registervalidator.do",
                type: "post",
                dataType: "json",
                data: $("#cityId").serialize(),
                success: function (data) {
                    
                	if(data.valid.indexOf(".ok") >= 0)
                    	$("#cityIdMessage").attr('class','green-text');
                    else
                    	$("#cityIdMessage").attr('class','red-text');
                    
                    $("#cityIdMessage").text(_(data.valid) );                       

                }
            });


            $.ajax({

                url: 'submitEditProfile.do',
                type: 'post',
                dataType: 'json',
                data: $("#registerform").serialize(),
                success: function (data) {
                         if (data.isValid) {

                        	 Materialize.toast(_('js.editsuccess') , 1000,'',function(){   
                             	window.location.href = "userCabinet.do";
                             });

                         } else{
                        	 
                        	 Materialize.toast(_('js.editfail') , 1000,'',function(){});                            	 
                         }

                }
            });
            return false;
        });		
	
        $("#firstName").change(function () {
            $.ajax({
                url: "registervalidator.do",
                type: "post",
                dataType: "json",
                data: $(this).serialize(),
                success: function (data) {
                    //$("#firstNameMessage").text(data.valid);
                    
                    if(data.valid.indexOf(".ok") >= 0)
                    	$("#firstNameMessage").attr('class','green-text');
                    else
                    	$("#firstNameMessage").attr('class','red-text');
                    
                    $("#firstNameMessage").text(_(data.valid) );

                }
            });
        });
        
        $("#lastName").change(function () {
            $.ajax({
                url: "registervalidator.do",
                type: "post",
                dataType: "json",
                data: $(this).serialize(),
                success: function (data) {

                    if(data.valid.indexOf(".ok") >= 0)
                    	$("#lastNameMessage").attr('class','green-text');
                    else
                    	$("#lastNameMessage").attr('class','red-text');
                    $("#lastNameMessage").text(_(data.valid) );

                }
            });
        });        
		
        
        $("#email").change(function () {
            $.ajax({
                url: "registervalidator.do",
                type: "post",
                dataType: "json",
                data: $(this).serialize(),
                success: function (data) {
                	
                    if(data.valid.indexOf(".ok") >= 0)
                    	$("#emailMessage").attr('class','green-text');
                    else
                    	$("#emailMessage").attr('class','red-text');
                    $("#emailMessage").text(_(data.valid) );

                }
            });
        });
        
        $("#password").change(function () {
            $.ajax({
                url: "registervalidator.do",
                type: "post",
                dataType: "json",
                data: $(this).serialize(),
                success: function (data) {
                	
                    if(data.valid.indexOf(".ok") >= 0)
                    	$("#passwordMessage").attr('class','green-text');
                    else
                    	$("#passwordMessage").attr('class','red-text');
                    $("#passwordMessage").text(_(data.valid) );
                    

                }
            });
        });
        
        
        $('#confirm').change(function () {
            var pass = $('#password').val();
            var conf = $('#confirm').val();

            $.ajax({
                type: "post",
                url: 'confirmValidator.do?password=' + pass + '&confirm=' + conf,
                success: function (data) { //we're calling the response json array 'cities'

                    if(data.valid.indexOf(".ok") >= 0)
                    	$("#confirmMessage").attr('class','green-text');
                    else
                    	$("#confirmMessage").attr('class','red-text');
                    $("#confirmMessage").text(_(data.valid) );
                    

                }
            });

        });
        
        $("#sex").change(function () {

            $.ajax({
                url: "registervalidator.do",
                type: "post",
                dataType: "json",
                data: $(this).serialize(),
                success: function (data) {
                	

                    if(data.valid.indexOf(".ok") >= 0)
                    	$("#sexMessage").attr('class','green-text');
                    else
                    	$("#sexMessage").attr('class','red-text');
                    $("#sexMessage").text(_(data.valid) );


                }
            });
        });
        
        $("#cellNumber").change(function () {
            $.ajax({
                url: "registervalidator.do",
                type: "post",
                dataType: "json",
                data: $(this).serialize(),
                success: function (data) {
                    if(data.valid.indexOf(".ok") >= 0)
                    	$("#cellNumberMessage").attr('class','green-text');
                    else
                    	$("#cellNumberMessage").attr('class','red-text');
                    $("#cellNumberMessage").text(_(data.valid) );                        

                }
            });
        });        
        
        
	});

</script>



<div class="row">
    <div class="col s12" style="margin-top:10px;">

		<ul class="collection z-depth-2 ">
	
				<li class="collection-item">
	
					<div class="row">
						<p><fmt:message key="register.RegistrationForm"/></p>
	
                        <form class="col s12" action="submitRegister.do" method="post" id="registerform"
                              name="registerform">
                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="firstName"><fmt:message key="register.FirstName"/></label>
                                    <input type="text" id="firstName" name="firstName" value="${user.firstName}"  />
                                    <span id="firstNameMessage" ></span>
                                </div>
                            </div>	
						
                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="lastName"><fmt:message key="register.LastName"/></label>
                                    <input type="text" id="lastName" name="lastName" value="${user.lastName}"  />
                                    <span id="lastNameMessage"></span>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="email"><fmt:message key="register.email"/></label>
                                    
<%--                                      ${fbVkUser} --%>
                                    
                                    <input type="text" id="email" name="email" value="${user.email}" ${fbVkUser} />
                                    <span id="emailMessage"></span>
                                </div>
                            </div>

<%-- 							<c:if test="${fbVkUser.length() > 0}"> --%>
							
<!--                             <div class="row"> -->
<!--                                 <div class="input-field col s12"> -->
<%--                                     <label for="password"><fmt:message key="register.password"/></label> --%>
<!--                                     <input type="password" id="password" name="password"/> -->
<!--                                     <span id="passwordMessage"></span> -->
<!--                                 </div> -->
<!--                             </div> -->

<!--                             <div class="row"> -->
<!--                                 <div class="input-field col s12"> -->
<%--                                     <label for="confirm"><fmt:message key="register.confirm"/></label> --%>
<!--                                     <input type="password" id="confirm" name="confirm"/> -->
<!--                                     <span id="confirmMessage"></span> -->
<!--                                 </div> -->
<!--                             </div> -->
                            
<%--                             </c:if> --%>

                            <div class="row">
                                <label><fmt:message key="register.sex"/></label>

                                <div class="input-field col s12">
                                    <select id="sex" name="sex" class="browser-default">
                                        <option selected value="choose"><fmt:message key="register.ChooseSex"/></option>
                                        <option ${selMale}   value="male"><fmt:message key="register.male"/></option>
                                        <option ${selFemale}   value="female"> <fmt:message key="register.female"/></option>
                                    </select>
                                    <span id="sexMessage"></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="cellNumber"><fmt:message key="register.Cell"/> </label>
                                    <input type="text" id="cellNumber" name="cellNumber" value="${user.cellNumber}" />
                                    <span id="cellNumberMessage"></span>
                                </div>
                            </div>
                            
                            
                                                    <div class="row">
                                <label><b><fmt:message key="register.AddressInFewLangs"/></b></label>

                                <div>
                                <ul class="collapsible" data-collapsible="expandable">
                                    <c:forEach items="${requestScope.languageList}" var="lang">

                                        
                                        <li>
                                        
                                        
                                        <c:if test="${lang.id != sessionScope.sessionLanguage.id}">
                                        <div class="collapsible-header"><fmt:message key="register.language"/>: <span class="blue-text"><u>${lang.name}</u></span></div>
                                        </c:if>
                                        
                                        <c:if test="${lang.id == sessionScope.sessionLanguage.id}">
                                        <div class="collapsible-header active"><fmt:message key="register.language"/>: <span class="blue-text"><u>${lang.name}</u></span></div>
                                        </c:if>
                                        
                                        <div class="collapsible-body">
                                        <table style="width: 100%;">
                                            <tr>

                                                <td style="width:30%;">
                                                    <label><fmt:message key="register.Country"/></label>
                                                    
                                                 
                                                    
                                                    <select id="countryByLang_${lang.id}" class="browser-default">
                                                    
                                                        <option selected value="choose" disabled selected>
                                                            <fmt:message key="register.ChooseCountry"/>
                                                        </option>

															
															
                                                        <c:forEach items="${requestScope.countryList}" var="country">
                                                            
                                                            <c:if test="${country.localId == lang.id}">
                                                            	
                                                            	<c:set var="strId" value="${lang.id.toString()}" />
                                                            
                                                            	<c:if test="${selectedCountryMap[strId].equals(country.id.toString()) }">
                                                            	<option selected value="${country.id}">${country.name}</option>
                                                            	</c:if>
                                                            	
                                                            	<c:if test="${!selectedCountryMap[strId].equals(country.id.toString())}">
                                                            	<option value="${country.id}">${country.name}</option>
                                                            	</c:if>                                                            

                                                            </c:if>
                                                        </c:forEach>

                                                        <script>
                                                            $('#countryByLang_${lang.id}').change(function () {

                                                                var destination = $('#cityByLang_${lang.id}').val();
                                                                var selectedValue = $(this).val();


                                                                //change other countries
                                                                $.ajax({
                                                                    type: "post",
                                                                    url: 'getLocalCountryAnalogs.do?value=' + selectedValue,
                                                                    success: function (originCountryId) { //we're calling the response json array 'cities'

                                                                        $.each(originCountryId, function (countryId, country) { //here we're doing a foeach loop round each city with id as the key and city as the value

                                                                            //$(countryId).val(country);

                                                                            $('#countryByLang_' + countryId).val(country);

                                                                            //fill city list
                                                                            $('#cityByLang_' + countryId + " > option").remove(); //first of all clear select items

                                                                            $.ajax({
                                                                                type: "post",
                                                                                url: 'getCitiesByCountry.do?value=' + country,
                                                                                success: function (originCityId) { //we're calling the response json array 'cities'

                                                                                    var optFirst = $('<option selected />'); // here we're creating a new select option with for each city
                                                                                    optFirst.val('choose');
                                                                                    $('#cityId').val('choose');

                                                                                    optFirst.text(_('js.citychoose'));
                                                                                    $('#cityByLang_' + countryId).append(optFirst);

                                                                                    $.each(originCityId, function (id, city) { //here we're doing a foeach loop round each city with id as the key and city as the value


                                                                                        var opt = $('<option />'); // here we're creating a new select option with for each city
                                                                                        opt.val(id);
                                                                                        opt.text(city);

                                                                                        $('#cityByLang_' + countryId).append(opt); //here we will append these new select options to a dropdown with the id 'cities'
                                                                                    });
                                                                                }
                                                                            });


                                                                        });
                                                                    }
                                                                });

                                                            });
                                                        </script>

                                                    </select>
                                                </td>


                                                <td style="width:30%;">
                                                    <label><fmt:message key="register.City"/></label>
                                                    
                                                    <c:set var="strId" value="${lang.id.toString()}" />
                                                    <c:if test="${selectedCountryMap[strId].equals('choose')}">
                                                    
                                                    	<select id="cityByLang_${lang.id}" class="browser-default">
                                                        	<option selected value="choose">
                                                            	<fmt:message key="register.choosecountryfirst"/>  
                                                       		 </option>
                                                    </select>
                                                    </c:if>
                                                    
                                                    <c:if test="${!selectedCountryMap[strId].equals('choose')}">
                                                    
                                                    	<select id="cityByLang_${lang.id}" class="browser-default">
                                                        	<option value="choose">
                                                            	<fmt:message key="js.citychoose"/>  
                                                       		 </option>
                                                    		<c:forEach items="${initalCities[strId]}" var="c">
 																
 																<c:if test="${selectedCityMap[strId].equals(c.id.toString()) }">
 																	<option selected value="${c.id}"><c:out value="${c.name}"/></option>
 																</c:if>
 		
  																<c:if test="${!selectedCityMap[strId].equals(c.id.toString()) }">
 																	<option value="${c.id}"><c:out value="${c.name}"/></option>
 																</c:if>
 																                                                       		                                                    	
                                                    		</c:forEach>
                                                    	</select>
                                                    </c:if>
                                                    
                                                    
                                                    <script>
                                                        $('#cityByLang_${lang.id}').change(function () {

                                                            var selectedValue = $(this).val();

                                                            $('#cityId').val(selectedValue);

                                                            //change other cities
                                                            $.ajax({
                                                                type: "post",
                                                                url: 'getLocalCityAnalogs.do?value=' + selectedValue,
                                                                success: function (originCityId) { //we're calling the response json array 'cities'

                                                                    $.each(originCityId, function (cityId, city) { //here we're doing a foeach loop round each city with id as the key and city as the value

                                                                        $('#cityByLang_' + cityId).val(city);

                                                                    });
                                                                }
                                                            });

                                                        });
                                                    </script>
                                                </td>

                                                <td style="width:40%;">
                                                    <div class="input-field">
                                                        <label for="addressByLang_${lang.id}"><fmt:message key="register.Adress"/></label>
                                                        
                                                        <c:set var="strId" value="${lang.id.toString()}" />
                                                        
                                                        <input type="text" id="addressByLang_${lang.id}"
                                                               name="addressByLang_${lang.id}" value="${selectedAddressMap[strId]}"  />
                                                        <span id="addressByLangMessage_${lang.id}"></span>
                                                    </div>

                                                    <script>
                                                        $(document).ready(function () {
                                                            $("#addressByLang_${lang.id}").change(function () {
                                                                $.ajax({
                                                                    url: "registerAddressValidator.do",
                                                                    type: "post",
                                                                    dataType: "json",
                                                                    data: $(this).serialize(),
                                                                    success: function (data) {
                                                                        $("#addressByLangMessage_${lang.id}").text(_(data.valid));
                                                                        
                                                                    	if(data.valid.indexOf(".ok") >= 0)
                                                                        	$("#addressByLangMessage_${lang.id}").attr('class','green-text');
                                                                        else
                                                                        	$("#addressByLangMessage_${lang.id}").attr('class','red-text');                                                                        

                                                                    }
                                                                });
                                                            });
                                                        });

                                                    </script>
                                                </td>
                                            </tr>
                                        </table>
										</div>
										</li>
                                    </c:forEach>

                                </ul>
                                </div>
                                
                                
                                
                                
                                <input type="hidden" id="cityId" name="cityId"  value="${currCityId} " /> <span id="cityIdMessage"></span>

                                <br>

<!--                                 <p> -->
<!--                                     <input type="checkbox" id="isGuide" name="isGuide"/> -->
<%--                                     <label for="isGuide"><fmt:message key="register.Guide"/> </label> --%>
<!--                                 </p> -->
                                
                                
                                <br>
                                
                                <button class="light-blue btn waves-effect waves-light" type="submit"
                                        name="action" style="margin-left: auto; margin-right: auto; width: 33%;">
                                    <fmt:message key="register.Save"/> <i class="mdi-content-send right"></i>
                                </button>
                            </div>    
                            						
						
						</form>
						
					</div>
	
				</li>
	
			</ul>
	
    </div>
</div>
</fmt:bundle>

