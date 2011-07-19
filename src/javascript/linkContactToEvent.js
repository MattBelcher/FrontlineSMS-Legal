var frontlinesms = this.frontlinesms || {};

frontlinesms.linkContactToEvent = function() {

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
        var contactId = $(this).attr('id');
        if (!frontlinesms.checkIfEventHasContactLinked(contactId)) {
            frontlinesms.addLinkedContactIdToHiddenField(contactId);
            frontlinesms.addLinkedContactToTable(contactId);


        }
        $("#link-contacts").dialog("close");
        return false;
    });

    $("td.remove-contact-button").live('click', function() {
        var contactId = $(this).parent().find('td span.id:hidden').text();
        $(this).parent().remove();
        frontlinesms.removeLinkedContactIdFromHiddenField(contactId);
        return false;
    });

};


frontlinesms.addLinkedContactIdToHiddenField = function(contactId) {
    var contactIds = $("#event-linked-contacts").val().split(",");
    contactIds = (contactIds.length == 1 && contactIds[0] == "") ? [] : contactIds;
    contactIds.push(contactId);
    $("#event-linked-contacts").val(contactIds.join(","));
}

frontlinesms.removeLinkedContactIdFromHiddenField = function(contactId) {
    var contactIds = $("#event-linked-contacts").val().split(",");
    if( contactIds.indexOf(contactId) > -1 )
        contactIds.splice(contactIds.indexOf(contactId),1);
    $("#event-linked-contacts").val(contactIds.join(","));
}

frontlinesms.addLinkedContactToTable = function(contactId) {
    var row = $('#contactsTable').find('#' + contactId);
    var rowToAdd = $('<tr>').append(
            '<td>' +
                $(row).find('.contact-name').text() +
                '<span class="id" style="display:none;">' + contactId + '</span>' +
            '</td>' +
            '<td>' +
                $(row).find('.contact-number').text() +
            '</td>' +
            '<td class="remove-contact-button">' +
                '<a href="">Remove</a>' +
            '</td>'
    );
    $('#contacts').append(rowToAdd);

}

frontlinesms.checkIfEventHasContactLinked = function(contactId) {
    var contactIds = $("#event-linked-contacts").val().split(",");
    return (contactIds.indexOf(contactId) > -1);
};
