var frontlinesms = this.frontlinesms || {};

frontlinesms.linkCaseToContact = function() {

    $("#link-case-dialog").dialog({

        autoOpen: false,
        modal: true,
        width: 'auto',
        buttons: {
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });

    $("#link-case-button").click(function() {
        $("#link-case-dialog").dialog("open");
        return false;
    });

    $(".caseLink").click(function() {
        var caseId = $(this).attr('id');
        var relationship = prompt("Relationship to case:");
        if (!frontlinesms.checkIfContactHasCaseLinked(caseId)) {
            frontlinesms.addLinkedCaseToHiddenField(caseId, relationship);
            frontlinesms.addLinkedCaseToTable(caseId, relationship);


        }
        $("#link-case-dialog").dialog("close");
        return false;
    });

    $("td.remove-case-button").live('click', function() {
        var caseId = $(this).parent().find('td span.id:hidden').text();
        $(this).parent().remove();
        frontlinesms.removeLinkedCaseFromHiddenField(caseId);
        return false;
    });
};

frontlinesms.checkIfContactHasCaseLinked = function(caseId) {
    var linkedCases = $.parseJSON($('#contact-linked-cases').val()) || {};
//    return(typeof linkedCases[caseId]!= 'undefined');
    return (caseId in linkedCases);
};

frontlinesms.addLinkedCaseToHiddenField = function(caseId, relationship) {
    var linkedCases = $.parseJSON($('#contact-linked-cases').val()) || {};
    linkedCases[caseId] = relationship;
    $('#contact-linked-cases').val(JSON.stringify(linkedCases));
};

frontlinesms.addLinkedCaseToTable = function(caseId, relationship) {
    var row = $('#SearchResults #' + caseId);
    var rowToAdd = $('<tr>').append(
        '<td>' +
            $(row).find('.case-name').html() +
            '<span class="id" style="display:none;">' + caseId + '</span>' +
            '</td>' +
            '<td>' +
            frontlinesms.encodeHTML(relationship) +
            '</td>' +
            '<td class="remove-case-button">' +
            '<a href="">Remove</a>' +
            '</td>'
    );
    $('#cases').append(rowToAdd);
}

frontlinesms.removeLinkedCaseFromHiddenField = function(caseId) {
    var linkedCases = $.parseJSON($('#contact-linked-cases').val()) || {};
    delete linkedCases[caseId];

    $('#contact-linked-cases').val(JSON.stringify(linkedCases));

};

frontlinesms.encodeHTML = function(value) {
    return $('<div/>').text(value).html();
};