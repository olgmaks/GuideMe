	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<!-- begin Localization -->

	<fmt:setLocale value="${sessionScope.sessionLanguage.locale}"/>
	<fmt:bundle basename="locale.register.messages">
	<!-- end Localization -->

<div class="row" style="width: 260px;">
  <div class="col s12" style="margin-top:10px;">
    <ul class="collection z-depth-2 ">
      <li class="collection-item">
        <fmt:message key="gal.Options"/>
      </li>
    </ul>
    <ul class="collection z-depth-2 ">
      <li class="collection-item">
      <div style="margin-left: 20%;">
<!--         <a class="waves-effect waves-light light-blue btn" href="uploaduserphotos">Upload photos</a> -->
        <a  href="upload.do?uploadtype=user" ><img src="img/upload-cloud.png" style="height: 160px; width: 160px;"/>
        <br><span> <fmt:message key="gal.Uploadphotos" />  </span>
        </a></div>
      </li>
    </ul>
  </div>
</div>

</fmt:bundle>

          