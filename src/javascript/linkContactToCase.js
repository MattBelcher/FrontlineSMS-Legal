var frontlinesms = this.frontlinesms || {};

frontlinesms.linkContactToCase = function() {

    $("#link-contacts").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });


    $("#link-contact-button").click(function() {
        $("#link-contacts").dialog("open");
        return false;
    });

    $(".contactLink").click(function() {
        var involvement = prompt('INVOLVEMENT', '');
        if (!involvement) {
            involvement = "";
        }
        var row = $('#contactsTable').find('#' + $(this).attr('id'));
        $('#contacts').append('<tr><td>' + $(row).find('.contact-name').text() + '</td><td>' + $(row).find('.contact-number').text() + '</td><td>' + involvement + '</td></tr>');
        $("#link-contacts").dialog("close");
        return false;
    });
};

frontlinesms.updateSearchResults = function (results) {

    alert(results)

};