<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link >

<link rel="StyleSheet" href="css/dataTables.css" type="text/css" media="all" />

<script src="js/dataTables.js"></script>
<script>
    $(document).ready(function(){
    $('#commentUserTable').DataTable();
});
</script>
<div class="row">
    <div class="col s12" style="margin-top:10px;">
        <ul class="collection z-depth-2">
            <li class="collection-item" style="height : 50px;">
                Comments from other users
            </li>
        </ul>
        <ul class="collection z-depth-2">
            <li class="collection-item" style="">
                <table id="commentUserTable" class="display" cellspacing="0" width="90%">
                    <thead>
                    <tr>
                        <th hidden>id</th>
                        <th></th>
                        <th>Name</th>
                        <th>Comment</th>
                        <th>respond</th>
                        <th>delete</th>
                    </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="userComment" items="${userComments}">
                            <tr style="font-size:12px; font-weight: bold;">
                            <th hidden>id</th>
                            <th><a href="userProfile.do?id=${userComment.commentator.id}"><img src="${userComment.commentator.avatar.path}" style="height: 80px; width: 80px; object-fit: cover"></a></th>
                            <th><a href="userProfile.do?id=${userComment.commentator.id}">${userComment.commentator.firstName}
                           ${userComment.commentator.lastName}</a><br>${userComment.date}</th>
                                <th> ${userComment.comment}</th>
                            <th>respond</th>
                            <th>delete</th>
                    </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </li>
        </ul>
    </div>
</div>
