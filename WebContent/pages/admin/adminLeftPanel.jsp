<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>

<%--User controls panel - left side of a page--%>
<fmt:setLocale value="${sessionScope.sessionLanguage.locale}" />
<fmt:bundle basename="locale.admin.admin">
<div class="row" >
  <div class="col s12" style="margin-top:10px; ">
    <ul class="collection z-depth-2 " style="height: 100%;">
      <li class="collection-item"  >

        <a href="admin.do"><button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;text-align: left;font-size: 100%;text-transform: capitalize">
            <fmt:message key="panel.event" />
<!--             <i class="mdi-action-extension right" ></i> -->
            </button>
            </a>
        <a href="adminuserservlet.do">
	        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
	             <fmt:message key="panel.user" />
<!-- 	            <i class="mdi-communication-comment right"></i> -->
	        </button>
      </a>
        <a href="adminTag.do">
        	<button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
           	    <fmt:message key="panel.tag" />
<!--             	 	<i class="mdi-content-add-circle-outline right" ></i> -->
		</button> 
        </a>
        <a href = "admincity.do">
	        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
	               <fmt:message key="panel.city" />
<!-- 	            <i class="mdi-action-search right"></i> -->
	        </button>
	    </a>
	    <a href = "admincountry.do">
        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
               <fmt:message key="panel.country" />
<!--             <i class="mdi-content-mail right"></i> -->
        </button>
        </a>
        
      <a href="adminlanguage.do">
	        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
	               <fmt:message key="panel.language" />
<!-- 	        <i class="mdi-communication-comment right"></i> -->
	        </button>
      </a>
        <a href="messageAdmin.do">
	        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
	             <fmt:message key="panel.message" />
<!-- 	        <i class="mdi-communication-comment right"></i> -->
	        </button>
      </a>
      </li>
    </ul>
  </div>
</div>
</fmt:bundle>