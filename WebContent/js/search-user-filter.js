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
            sendAjaxRequest(getSearchOptions());
        },
        afterTagRemoved: function (event, ui) {
            sendAjaxRequest(getSearchOptions());
        }
    });

});


function getSearchOptions() {
    var userNameInput = $("#userNameInput").val();
    var tags = $("[name = 'tags']").val();
    var searchOptions = {from: "userSearchOptions", userNameInput: userNameInput, tags: tags};
    console.log(userNameInput);
    console.log(tags);
    return searchOptions;
}


function sendAjaxRequest(searchOptions) {
    $.ajax({
        url: "searchuserfilter.do",
        type: "post",
        dataType: "json",
        data: searchOptions,
        //data: $("#searchOptions").serialize(),
        success: function () {
            console.log('request success');
        }
    });
}

$(document).ready(function () {
    $("#userNameInput").keyup(function () {

        //var userNameHidden = $("#userNameHidden");
        //se
        //userNameHidden.val(userNameInput);
        //console.log(userNameHidden.val());
        sendAjaxRequest(getSearchOptions());
    });
});
