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
        var contactId = $(this).parent().find('td span.id:hidden').text();
        $(this).parent().remove();
        frontlinesms.removeLinkedContactFromCaseHiddenField(contactId);
        return false;
    });

    $(".contactLink").click(function() {
        var contactId = $(this).attr('id');
        if (!frontlinesms.checkIfCaseHasContactLinked(contactId)) {
            var relationship = prompt("Relationship to case:") || "";
            frontlinesms.addLinkedContactToCaseHiddenField(contactId, relationship);
            frontlinesms.addLinkedContactToCaseTable(contactId, relationship);
        }
        $("#link-contacts").dialog("close");
        return false;
    });
};

frontlinesms.addLinkedContactToCaseHiddenField = function(contactId, relationship) {
    var linkedContacts = $.parseJSON($('#case-linked-contacts').val()) || {};
    linkedContacts[contactId] = relationship;
    $('#case-linked-contacts').val(JSON.stringify(linkedContacts));
};

frontlinesms.removeLinkedContactFromCaseHiddenField = function(contactId) {
    var linkedContacts = $.parseJSON($('#case-linked-contacts').val()) || {};
    delete linkedContacts[contactId];

    $('#case-linked-contacts').val(JSON.stringify(linkedContacts));

};

frontlinesms.checkIfCaseHasContactLinked = function(contactId) {
    var linkedContacts = $.parseJSON($('#case-linked-contacts').val()) || {};
    return (contactId in linkedContacts);
};

frontlinesms.addLinkedContactToCaseTable = function(contactId, relationship) {
    var row = $('#contactsTable #' + contactId);
    var rowToAdd = $('<tr>').append(
        '<td>' +
            $(row).find('.contact-name').html() +
            '<span class="id" style="display:none;">' + contactId + '</span>' +
            '</td>' +
            '<td>' + $(row).find('.contact-number').html() + '</td>' +
            '<td>' +
            frontlinesms.encodeHTML(relationship) +
            '</td>' +
            '<td class="remove-contact-button">' +
            '<a href="">Remove</a>' +
            '</td>'
    );
    $('#contacts').append(rowToAdd);
}

frontlinesms.encodeHTML = function(value) {
    return $('<div/>').text(value).html();
};