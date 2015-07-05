$(document).ready(function () {
    //var tags = ['c++', 'java', 'php', 'coldfusion', 'javascript', 'asp', 'ruby', 'python', 'c', 'scala', 'groovy', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];

    var tags = [];

    var existingTags = [];

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
                console.log('tags : ' + tags);
            }
        });

        return tags;
    }

    $("#userInterestsTags").tagit({
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
                    addUserInterestTag(tagName);
                appendTagInterestOnPage(tagName);
            }

        },
        afterTagRemoved: function (event, ui) {
            var tagName = ui.tagLabel;
            console.log(tagName);

            if ($.inArray(tagName, tags) !== -1) {
                deleteUserInterestTag(tagName);
            }

            removeTagInterestFromPage(tagName);
        }
    });

});

$(document).ready(function () {

    $.ajax({
        url: "cabinetTagsController.do",
        type: "post",
        dataType: "json",
        data: {command: "getAllUserTags"},
        success: function (tags) {
            console.log('user tags : ');
            //printTags(tags);
            for (index = 0; index < tags.length; index++) {
                var tagName = tags[index].name;
                $('#userInterestsTags').tagit('createTag', tagName);

                //$('#userInterestsTags').append('<li>'+tags[index].name+'</li>');
            }

            console.log('completed get all user tags loop');

        }
    });


});


//Languages
$(document).ready(function () {
    var languages = [];

    function prepareDefaultLanguages() {

        $.ajax({
            url: "cabinetTagsController.do",
            type: "post",
            dataType: "json",
            data: {command: "getAllActiveLanguages"},
            success: function (langs) {
                console.log('languages : ' + langs);
                $.each(langs, function (index, item) {
                    languages.push(item.name);
                });
                console.log(languages);
            }
        });

        return languages;
    }

    $("#userLanguagesTags").tagit({
        autocomplete: {

            minLength: 0,
            source: prepareDefaultLanguages()
        },
        singleField: true,

        afterTagAdded: function (event, ui) {
            var tagName = ui.tagLabel;

            if ($.inArray(tagName, languages) === -1) {
                //console.log('not in array : position = '+$.inArray(tagName, languages));
                $(this).tagit('removeTagByLabel', tagName);
            } else {
                console.log(tagName);
                appendTagLanguageOnPage(tagName);
                addUserLanguage(tagName);
            }
        },
        afterTagRemoved: function (event, ui) {
            var tagName = ui.tagLabel;
            console.log(tagName);

            if ($.inArray(tagName, languages) !== -1) {
                removeUserLanguage(tagName);
            }

            removeTagLanguageFromPage(tagName);
        }
    });

});

$(document).ready(function () {

    $.ajax({
        url: "cabinetTagsController.do",
        type: "post",
        dataType: "json",
        data: {command: "getAllUserLanguages"},
        success: function (tags) {
            console.log('user tags : ');
            //printTags(tags);
            for (index = 0; index < tags.length; index++) {
                var tagName = tags[index].name;
                $('#userLanguagesTags').tagit('createTag', tagName);

                //$('#userInterestsTags').append('<li>'+tags[index].name+'</li>');
            }

            console.log('completed get all user language tags loop');

        }
    });

});

function appendTagInterestOnPage(tagLabel) {
    $("#userInterestsTagReferences").
        append(' <a href="home.do?tag=' + tagLabel + '" id="userInterestsTagReferences-' + tagLabel + '">#' + tagLabel + '</a>');
}

function removeTagInterestFromPage(tagLabel) {
    var selector = '#' + 'userInterestsTagReferences-' + tagLabel;
    $(selector).remove();
}

function addUserInterestTag(currentTag) {
    $.ajax({
        url: "cabinetTagsController.do",
        type: "post",
        dataType: "json",
        data: {command: "addUserInterestTag", value: currentTag},
        success: function (data) {
            console.log(data);
        }
    });
}

function deleteUserInterestTag(currentTag) {
    $.ajax({
        url: "cabinetTagsController.do",
        type: "post",
        dataType: "json",
        data: {command: "deleteUserInterestTag", value: currentTag},
        success: function (data) {
            console.log(data);
        }
    });
}


function appendTagLanguageOnPage(tagLanguageLabel) {
    console.log('appending : ' + tagLanguageLabel);
    $("#userLanguagesTagReferences")
        .append(' <span id = "userLanguagesTagReferences-' + tagLanguageLabel + '" >' + '#' + tagLanguageLabel + '</span>');
}

function removeTagLanguageFromPage(tagLanguageLabel) {
    console.log('removing : ' + tagLanguageLabel);
    var selector = '#' + 'userLanguagesTagReferences-' + tagLanguageLabel;
    $(selector).remove();
}

function addUserLanguage(currentLanguage) {
    $.ajax({
        url: "cabinetTagsController.do",
        type: "post",
        dataType: "json",
        data: {command: "addUserLanguage", value: currentLanguage},
        success: function (data) {
            console.log(data);
        }
    });
}

function removeUserLanguage(currentLanguage) {
    $.ajax({
        url: "cabinetTagsController.do",
        type: "post",
        dataType: "json",
        data: {command: "deleteUserLanguage", value: currentLanguage},
        success: function (data) {
            console.log(data);
        }
    });
}