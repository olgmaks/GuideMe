/**
 * Created by OLEG on 21.06.2015.
 */

$(function () {
    //var tags = ['c++', 'java', 'php', 'coldfusion', 'javascript', 'asp', 'ruby', 'python', 'c', 'scala', 'groovy', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];
    var tags = [];

    function prepareDefaultTags() {


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
            var tagName = ui.tagLabel;

            if ($.inArray(tagName, tags) === -1) {
                $(this).tagit('removeTagByLabel', tagName);
            } else {
                sendAjaxRequest(getSearchUserFilter());
            }
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

//$(document).ready(function () {
//    console.log('call city ajax');
//    $.ajax({
//        url: "getCitiesByCountry.do",
//        type: "post",
//        dataType: "json",
//        data: {cityRequestType: 'getAllCity'},
//        success: function (data) {
//            console.log('request success');
//            console.log(data);
//
//            var citySelect = $("#city-select");
//            citySelect.empty();
//            citySelect.append("<option value=''>All</option>");
//
//            $.each(data, function (counts, item) {
//                citySelect.append("<option value='" + item + "'>" + item + "</option>");
//            });
//        }
//    });
//});

//$(document).ready(function () {
//    $(".send-friend-request").click(function () {
//        alert('friend-request clicked');
//    });
//});

$(document).ready(function () {
    $("#country-select-control").change(function () {

        var country = $(this).val();

        console.log(country);

        var citySelect = $("#city-select");
        citySelect.val('');
        citySelect.empty();

        if (country === 'all') {


            citySelect.append("<option value='all'>" +  _("js.rpanel.All") + "</option>");

        } else {

            $.ajax({
                type: "post",
                url: 'getCitiesByCountry.do?value=' + country,
                success: function (cities) { //we're calling the response json array 'cities'
                    var citySelect = $("#city-select");
                    citySelect.empty();
                    citySelect.append("<option value='all'  data-name='all'>" + _("js.rpanel.All") + "</option>");
                    //console.log(cities);
                    $.each(cities, function (index, name) {
                        //console.log(index + " " + name);
                        citySelect.append("<option value='" + name + "'>" + name + "</option>");
                    });
                }
            });
        }
        sendAjaxRequest(getSearchUserFilter());

    });
});

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

$(document).ready(function () {
    updateAddFriendAnchors();
});

$(document).ready(function(){
    $("#search-submit-button").on('click',function(){
        sendAjaxRequest(getSearchUserFilter());
    });

});

function getSearchUserFilter() {
    var userNameInput = $("#userNameInput").val();
    var tags = $("#singleFieldTags").tagit("assignedTags").toString();
    var countryName = $("#country-select-control").find(":selected").data("name");
    var cityName = $("#city-select").val();
    var tagsMatches = 0;
    var searchRole = $("#user-type-select").val();

    if (searchRole === null) {
        searchRole = "all";
    }

    var searchUserFilter = {
        userNameInput: userNameInput,
        tags: tags,
        countryName: countryName,
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
    var points = user.points;
    var sendFriendRequestId = "sendFriendRequestId" + user.Id;
    var sendFriendRequestClass = "send-friend-request";
    
    return "<div class='card' id = 'card-" + userId + "' style='height: 150px; width: 47%; min-width: 300px; max-width:350px; float: left; margin-left: 10px;'>" +
        "<table><tr><td style='width: 120px; vertical-align: top;'>" +
        "<a href='userProfile.do?id="+userId+"'><img class='circle' style='height: 120px; width: 120px; object-fit: cover' src='" + pathToImage + "'></a></td><td><div>" +
        "<div style='height: 40px;'><a href='userProfile.do?id="+userId+"' class='black-text'>" + fName + " " + lName + "</a></div>" +
        "<div style='height: 20px;'> <span> "+ _("js.searchuser.Rate")  + ": "  + points + "</span></div>" +
        "<div style='height: 20px;'><span>" + cityName + "</span></div>" +
        "<div style='float: right; vertical-align: bottom; margin-bottom: 10px; margin-right: 10px;'>" +
        "<a href='#_' id='" + sendFriendRequestId + "' data-userid='" + userId + "' class='btn blue waves-effect waves-light " + sendFriendRequestClass + "'>" +
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
            data: {friendId: friendId},
            success: function () {
                console.log('friend request has been sent on server userId=' + friendId);
            }
        });

        var cardSelector = '#card-' + friendId;
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
            //console.log(data);

            var cardCollection = $("#inner-row");
            var resultsQuantity = $('#resultsQuantity');

            cardCollection.empty();
            resultsQuantity.text(data.length);
            $.each(data, function (counts, item) {
                //console.log(item);
                cardCollection.append(getCard(item));
            });

            updateAddFriendAnchors();
        }
    });


}