var frontlinesms = this.frontlinesms || {};

frontlinesms.buildContactsRow = function (row, involvement) {
    $('#contacts').append('<tr id=' + $(this).attr('id') + '><td>' + $(row).find('.contact-name').text() + '</td><td>' + $(row).find('.contact-number').text() + '</td><td>' + involvement + '</td><td class="remove-contact-button">Remove</td></tr>');
}
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

    $(".remove-contact-button").live('click', function() {
        contactId = $(this).parent().attr('id');
        $(this).parent().remove();
        frontlinesms.removeLinkedContactIdAndInvolvementFromHiddenFieldOfCasePage(contactId);
        return false;
    });

    frontlinesms.removeLinkedContactIdAndInvolvementFromHiddenFieldOfCasePage = function(contactId) {
        contactIds = $("#linked-contact-ids").val().split(",");
        involvements = $("#involvement-list").val().split(',');
        if (contactIds.indexOf(contactId) > -1) {
            contactIds.splice(contactIds.indexOf(contactId), 1);
            involvements.splice(involvements.indexOf(contactId), 1);
        }
        $("#linked-contact-ids").val(contactIds);
        $("#involvement-list").val(involvements);
    }


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

            involvementList = $('#involvement-list').val().split(',');
            involvementList.push(involvement);
            $('#involvement-list').val(involvementList);
            contactIdList.push($(this).attr('id'));
            $('#linked-contact-ids').val(contactIdList);
            var row = $('#contactsTable').find('#' + $(this).attr('id'));
            frontlinesms.buildContactsRow(row, involvement);
            $("#link-contacts").dialog("close");
            return false;
        }
    });
};
