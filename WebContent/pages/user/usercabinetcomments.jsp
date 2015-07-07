<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link>

<link rel="StyleSheet" href="css/dataTables.css" type="text/css" media="all"/>
<script src="js/moment.js"></script>
<script src="js/dataTables.js"></script>
<%--<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>--%>
<script>
    $(document).ready(function () {

        $('#commentUserTable').DataTable({
            "bSort": false
        });

        $('.respond-button').on('click', function () {

                $('#messageRespond').val("");

            $('#answerOnCommentWindow').openModal();
        });


        $('#sendRespondOnComment').on('click', function () {

            var textValue = $('#messageRespond').val();

            var respond = {command: 'respondOnComment', respondText: textValue};

            $.ajax({
                url: 'userCabinetCommentController.do',
                type: "post",
                dataType: "json",
                data: respond,
                success: function (data) {
//                    alert(data.id + data.date + data.Commentator + data.user + data.comment);
                    var t = $('#commentUserTable').DataTable();
                    t.row.add([
                        "<a href='userProfile.do?id=" + data.Commentator.Id + "'><img class='circle' src='" + data.Commentator.avatar.path + "'style='height: 50px; width: 50px; object-fit: cover'></a>",
                        "<a href='userProfile.do?id=" + data.Commentator.Id + "'>" + data.Commentator.firstName + " " + data.Commentator.lastName + "</a><br>" + moment(data.date).format('YYYY-MM-DD hh:mm:ss.S'),
                        data.comment.replace(/</g, '&lt'),
                        "<a href='#_' class='delete-comment-button' data-id='" + data.id + "'><img src='icons/delete-photo-icon.png' style='width: 30px;height: 30px'></a>"

                    ]).draw();
//                    $('#commentUserTable tr:last').after(getTableRow(data.id,data.date,data.Commentator,data.user,data.comment));
                    $('#answerOnCommentWindow').closeModal();
                }
            });
        });

    });

    $(document).on('click', '.delete-comment-button', function () {

        var tableRow = $(this).closest('tr');

        var deleteRequest= {command:'deleteComment', commentId : $(this).attr('data-id')};

        $.ajax({
            url: 'userCabinetCommentController.do',
            type: "post",
            dataType: "json",
            data: deleteRequest,
            success: function (data) {
                tableRow.remove();
            }
        });
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
                <table id="commentUserTable" class="display" cellspacing="0" width="90%"
                       style="table-layout: fixed;font-size:12px;width: 90%">
                    <thead>
                    <tr>
                        <th style="width:11%;"></th>
                        <th style="width:18%; min-width: 100px;">Name</th>
                        <th style="width:61%;">Comment</th>
                        <th style="width:10%;"></th>
                    </tr>
                    </thead>
                    <tbody style="word-wrap: break-word;">

                    <c:forEach var="userComment" items="${userComments}">
                        <tr>
                            <td><a href="userProfile.do?id=${userComment.commentator.id}">
                                <img class="circle"
                                     src="${userComment.commentator.avatar.path}"
                                     style="height: 50px; width: 50px; object-fit: cover"></a></td>
                            <td>
                                <a href="userProfile.do?id=${userComment.commentator.id}">${userComment.commentator.firstName}
                                        ${userComment.commentator.lastName}</a><br>${userComment.date}</td>
                            <td><c:out value="${userComment.comment}"/></td>
                            <td>
                                <c:if test="${userComment.user.id!=userComment.commentator.id}">
                                <a href="#_" class="respond-button" data-id="${userComment.id}">
                                    <img src="icons/respond-icon.png" style="width: 30px;height: 30px"></a></td>
                            </c:if>
                            <c:if test="${userComment.user.id==userComment.commentator.id}">
                                <a href="#_" class="delete-comment-button" data-id="${userComment.id}">
                                    <img src="icons/delete-photo-icon.png" style="width: 30px;height: 30px"></a></td>
                            </c:if>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </li>
        </ul>
    </div>
</div>

<div id="answerOnCommentWindow" class="modal" style="height: 320px;">
    <div class="modal-content">
        <h4>Provide your answer</h4>

        <div class="row" style="margin-left: 10%">
            <div class="input-field col s6" style="width: 80%">
                <%--<i class="material-icons prefix">mode_edit</i>--%>
                <textarea id="messageRespond" class="materialize-textarea" length="120"></textarea>
                <label for="messageRespond">Your respond</label>
            </div>
        </div>


    </div>
    <div class="modal-footer">
        <a href="#_" id="sendRespondOnComment" style="margin-left: 40%;">Send
            <img src="icons/send-message-icon.png" style="width: 60px;height: 60px;vertical-align:middle"></a>
    </div>
</div>