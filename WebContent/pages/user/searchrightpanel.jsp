<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>

	<!-- begin Localization -->
	<jsp:include page="../localization.jsp"/>
	<ct:showLocale basename="locale.searchuser.messages" from = "searchuser.do" />

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.searchuser.messages">
	<!-- end Localization -->
	

<%--<script type="text/javascript" src="js/city-country-selecting.js" ></script>--%>
<div class="row">
    <div class="col s12" style="margin-top:10px;">
        <ul class="collection z-depth-2 ">
            <li class="collection-item" >
                <fmt:message key="rpanel.Searchoptions" /> 
            </li>
        </ul>
        <ul class="collection z-depth-2 ">
            <li class="collection-item">
                <fmt:message key="rpanel.options" />  

                <div class="input-field" style="margin-top: 50px;">
                    <%--<div>--%>
                    <%--<input type="checkbox" class="filled-in" id="isGuide" checked="checked"/>--%>
                    <%--<label for="isGuide">Guide</label>--%>
                    <%--</div><div>--%>
                    <%--<input type="checkbox" class="filled-in" id="isUser" checked="checked"/>--%>
                    <%--<label for="isUser">User</label></div>--%>
                        <label for="user-type-select" style="margin-top: -35px;"><fmt:message key="rpanel.UserorGuide"/> </label>
                        <select class="browser-default" style="height: 30px;" id="user-type-select">
                            <option value="all"><fmt:message key="rpanel.All"/> </option>
                            <option value="guide"><fmt:message key="rpanel.Guides"/> </option>
                            <option value="user"><fmt:message key="rpanel.Users"/> </option>
                        </select>
                </div>

                <%--<div class="ranges" style="margin-top:30px;">--%>
                    <%--<div class="input-field">--%>
                    <%--<label for="minage" style="margin-top: -20px;">Min age : </label>--%>
                    <%--<p class="range-field">--%>
                        <%--<input type="range" id="minage" min="0" max="100"/>--%>
                    <%--</p>--%>
                    <%--</div><div class="input-field">--%>
                    <%--<label for="maxage" style="margin-top: -20px;">Max age : </label>--%>
                    <%--<p class="range-field">--%>
                        <%--<input type="range" id="maxage" min="0" max="100"/>--%>
                    <%--</p></div>--%>
                <%--</div>--%>

                <%--<div class="input-large" style="margin-top: 35px;">--%>
                    <%--&lt;%&ndash;<label for="userLocation" style="margin-top: -35px;">Location</label>&ndash;%&gt;--%>
                    <%--<input name="location" id="userLocation" placeholder="Location">--%>
                <%--</div>--%>


                <div class="input-field" style="margin-top: 35px;">
                    <label for="city-select" style="margin-top: -35px;"><fmt:message key="rpanel.Choosecountry"/> </label>
                    <select class="browser-default" style="height: 30px;" id="country-select-control">

                        <option value="all" data-name=""> <fmt:message key="rpanel.All"/> </option>
                        <c:forEach items="${countryItems}" var="countryItem">
                            <option value="${countryItem.id}" data-name="${countryItem.name}">${countryItem.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-field" style="margin-top: 35px;">
                    <label for="city-select" style="margin-top: -35px;"><fmt:message key="rpanel.Choosecity"/></label>
                    <select class="browser-default" style="height: 30px;" id="city-select">
                        <option value="all"><fmt:message key="rpanel.All"/></option>
                    </select>
                </div>



                <div class="input-field">
                    <form id="searchOptions">
                        <input type="hidden" name="userNameHidden" value="" id="userNameHidden">
                    <%--<input style="height: 30px;" placeholder="Interests" id="mySingleField" disabled="true" name="tags"/>--%>
                        <ul class="browser-default" id="singleFieldTags">
                        </ul>

                    </form>
                    <br>
                    <a href="#_" id="search-submit-button" style="margin-left: 10%" class="btn light-blue"><i class="mdi-action-search right"></i><fmt:message key="rpanel.Search"/></a>
                </div>
            </li>
        </ul>
    </div>
</div>

</fmt:bundle>

