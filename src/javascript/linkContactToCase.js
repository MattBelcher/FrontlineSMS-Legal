var frontlinesms = this.frontlinesms || {};

frontlinesms.linkContactToCase = function() {
    $('#linked-contact-ids').val("");
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

        contactIdList = $('#linked-contact-ids').val().split(',');
        if (contactIdList.indexOf($(this).attr('id')) > -1) {
            $("#link-contacts").dialog("close");
            return false;
        }
        else {
            var involvement = prompt('INVOLVEMENT', '');
            if (!involvement) {
                involvement = "";
            }
            invlovementList = $('#involvement-list').val().split(',');
            invlovementList.push(involvement);
            $('#involvement-list').val(invlovementList);
            contactIdList.push($(this).attr('id'));
            $('#linked-contact-ids').val(contactIdList);
            var row = $('#contactsTable').find('#' + $(this).attr('id'));
            $('#contacts').append('<tr><td>' + $(row).find('.contact-name').text() + '</td><td>' + $(row).find('.contact-number').text() + '</td><td>' + involvement + '</td></tr>');
            $("#link-contacts").dialog("close");
            return false;
        }
    });
};
