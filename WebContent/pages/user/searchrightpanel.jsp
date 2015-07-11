<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<script type="text/javascript" src="js/city-country-selecting.js" ></script>--%>
<div class="row">
    <div class="col s12" style="margin-top:10px;">
        <ul class="collection z-depth-2 ">
            <li class="collection-item" >
                Search options
            </li>
        </ul>
        <ul class="collection z-depth-2 ">
            <li class="collection-item">
                Options

                <div class="input-field" style="margin-top: 50px;">
                    <%--<div>--%>
                    <%--<input type="checkbox" class="filled-in" id="isGuide" checked="checked"/>--%>
                    <%--<label for="isGuide">Guide</label>--%>
                    <%--</div><div>--%>
                    <%--<input type="checkbox" class="filled-in" id="isUser" checked="checked"/>--%>
                    <%--<label for="isUser">User</label></div>--%>
                        <label for="user-type-select" style="margin-top: -35px;">User or Guide</label>
                        <select class="browser-default" style="height: 30px;" id="user-type-select">
                            <option value="all">All</option>
                            <option value="guide">Guides</option>
                            <option value="user">Users</option>
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
                    <label for="city-select" style="margin-top: -35px;">Choose country</label>
                    <select class="browser-default" style="height: 30px;" id="country-select-control">

                        <option value="all" data-name="">All</option>
                        <c:forEach items="${countryItems}" var="countryItem">
                            <option value="${countryItem.id}" data-name="${countryItem.name}">${countryItem.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-field" style="margin-top: 35px;">
                    <label for="city-select" style="margin-top: -35px;">Choose city</label>
                    <select class="browser-default" style="height: 30px;" id="city-select">
                        <option value="all">All</option>
                    </select>
                </div>



                <div class="input-field">
                    <form id="searchOptions">
                        <input type="hidden" name="userNameHidden" value="" id="userNameHidden">
                    <%--<input style="height: 30px;" placeholder="Interests" id="mySingleField" disabled="true" name="tags"/>--%>
                        <ul class="browser-default" id="singleFieldTags">
                        </ul>
                        <input  class="browser-default" id="visibleInput" type="submit" value="Submit">
                    </form>
                </div>
            </li>
        </ul>
    </div>
</div>

