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
        if( !frontlinesms.checkIfEventHasContactLinked(contactId) ){
            frontlinesms.addLinkedContactIdToHiddenField(contactId);
            frontlinesms.addLinkedContactToTable(contactId);


        }
        return false;
    });
};
frontlinesms.addLinkedContactIdToHiddenField= function(contactId){
    var contactIds= $("#event-linked-contacts").val().split(",");
    contactIds = (contactIds.length == 1 && contactIds[0] == "") ? [] : contactIds;
    contactIds.push(contactId);
    $("#event-linked-contacts").val(contactIds.join(","));
}

frontlinesms.addLinkedContactToTable=function(contactId){
     var row = $('#contactsTable').find('#' + contactId);
        $('#contacts').append('<tr><td>' + $(row).find('.contact-name').text() + '</td><td>' + $(row).find('.contact-number').text() + '</td></tr>');
        $("#link-contacts").dialog("close");

}

frontlinesms.checkIfEventHasContactLinked = function(contactId) {
    var contactIds= $("#event-linked-contacts").val().split(",");
    return (contactIds.indexOf(contactId)>-1);
};
