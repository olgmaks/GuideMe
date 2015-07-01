<%--User controls panel - left side of a page--%>
<div class="row" >
  <div class="col s12" style="margin-top:10px; ">
    <ul class="collection z-depth-2 " style="height: 100%;">
      <li class="collection-item"  >
        <div class="" align="center"  > <a href="userCabinet.do"> <img class="circle" style="height: 120px; width: 120px; object-fit: cover" src="${sessionUser.avatar.path}"></a></div><br>

        <a href="userCabinet.do"><button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;text-align: left;font-size: 100%;text-transform: capitalize">
            Events<i class="mdi-action-extension right" ></i></button></a>
        <a href="adminTag.do">
        	<button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
           	 	tag<i class="mdi-content-add-circle-outline right" ></i></button>
        </a>
        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
            Find event<i class="mdi-action-search right"></i></button>
        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
            Messages<i class="mdi-content-mail right"></i></button>
        <a href="userfriends.do"><button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
            Friends<i class="mdi-social-group right"></i></button></a>
        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
            Find friends<i class="mdi-social-group-add right"></i></button>
        <a href="adminuserservlet.do">
        <button class="btn light-blue waves-effect waves-light" type="submit" name="action" style="width: 100%;margin-top: 10px;text-align: left;font-size: 100%;text-transform: capitalize">
            users<i class="mdi-communication-comment right"></i></button>
      </a>
      </li>
    </ul>
  </div>
</div>