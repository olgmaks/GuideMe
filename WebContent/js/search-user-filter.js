/**
 * Created by OLEG on 21.06.2015.
 */

$(function () {
    //var tags = ['c++', 'java', 'php', 'coldfusion', 'javascript', 'asp', 'ruby', 'python', 'c', 'scala', 'groovy', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];

    function prepareDefaultTags() {
        var tags = [];

        $.ajax({
            url: "autocompleteUserTags.do",
            type: "post",
            dataType: "json",
            data: {getTags: "userSearchTags"},
            success: function (data) {
                console.log('response done');
                console.log(data);
                var index;
                for (index = 0; index < data.length; index++) {
                    //console.log(data[index]);
                    tags.push(data[index]);
                }
                console.log(tags);
            }
        });

        return tags;
    }


    $('#singleFieldTags').tagit({
        autocomplete: {
            minLength: 0,
            source: prepareDefaultTags()
        },
        singleField: true,
        afterTagAdded: function (event, ui) {
            sendAjaxRequest(getSearchUserFilter());
        },
        afterTagRemoved: function (event, ui) {
            sendAjaxRequest(getSearchUserFilter());
        }
    });

});


$(document).ready(function () {
    $("#userNameInput").keyup(function () {
        sendAjaxRequest(getSearchUserFilter());
    });
});

$(document).ready(function () {
    console.log('call city ajax');
    $.ajax({
        url: "getCitiesByCountry.do",
        type: "post",
        dataType: "json",
        data: {cityRequestType: 'getAllCity'},
        success: function (data) {
            console.log('request success');
            console.log(data);

            var citySelect = $("#city-select");
            citySelect.empty();
            citySelect.append("<option value=''>City</option>");

            $.each(data, function (counts, item) {
                citySelect.append("<option value='" + item + "'>" + item + "</option>");
            });
        }
    });
});

//$(document).ready(function () {
//    $(".send-friend-request").click(function () {
//        alert('friend-request clicked');
//    });
//});

$(document).ready(function () {
    $("#city-select").change(function () {
        sendAjaxRequest(getSearchUserFilter());
    });
});

$(document).ready(function () {
    $("#user-type-select").change(function () {
        sendAjaxRequest(getSearchUserFilter());
    });
});

$(document).ready(function(){
    updateAddFriendAnchors();
});

function getSearchUserFilter() {
    var userNameInput = $("#userNameInput").val();
    var tags = $("#singleFieldTags").tagit("assignedTags").toString();
    var cityName = $("#city-select").val();
    var tagsMatches = 0;
    var searchRole = $("#user-type-select").val();

    if (searchRole === null) {
        searchRole = "all";
    }

    var searchUserFilter = {
        userNameInput: userNameInput,
        tags: tags,
        cityName: cityName,
        tagsMatches: tagsMatches,
        searchRole: searchRole
    };
    console.log(searchUserFilter);
    return searchUserFilter;
}


function getCard(user) {
    var userId = user.Id;
    var pathToImage = user.avatar.path;
    var fName = user.firstName;
    var lName = user.lastName;
    var cityName = user.address.city.name;
    var sendFriendRequestId = "sendFriendRequestId" + user.Id;
    var sendFriendRequestClass = "send-friend-request";

    return "<div class='card' id = 'card-"+userId+"' style='height: 150px; width: 330px; float: left; margin-left: 10px;'>" +
        "<table><tr><td style='width: 120px; vertical-align: top;'>" +
        "<img class='circle' style='height: 120px; width: 120px; object-fit: cover' src='" + pathToImage + "'></td><td><div>" +
        "<div style='height: 40px;'><a href='#_' class='black-text'>" + fName + " " + lName + "</a></div>" +
        "<div style='height: 40px;'><br><span>" + cityName + "</span></div>" +
        "<div style='float: right; vertical-align: bottom; margin-bottom: 10px; margin-right: 10px;'>" +
        "<a href='#_' id='" + sendFriendRequestId + "' data-userid='"+userId+"' class='btn blue waves-effect waves-light " + sendFriendRequestClass + "'>" +
        "ADD</a></div></div></td></tr></table>" +
        "</div>";
}

function updateAddFriendAnchors() {
    $(".send-friend-request").click(function () {
        var friendId = $(this).data('userid');
        $.ajax({
            url: "sendfriendrequest.do",
            type: "post",
            dataType: "json",
            data: {friendId:friendId},
            success: function () {
                console.log('friend request has been sent on server userId='+friendId);
            }
        });

        var cardSelector = '#card-'+friendId;
        console.log(cardSelector);
        $(cardSelector).remove();
    });
}

function sendAjaxRequest(searchUserFilter) {
    console.log('ajax request preparation');

    var sendFriendRequestClass = "send-friend-request";

    $.ajax({
        url: "searchuserfilter.do",
        type: "post",
        dataType: "json",
        data: getSearchUserFilter(),
        success: function (data) {
            console.log(data);

            var cardCollection = $("#inner-row");
            cardCollection.empty();

            $.each(data, function (counts, item) {
                console.log(item);
                cardCollection.append(getCard(item));
            });

            updateAddFriendAnchors();
        }
    });


}