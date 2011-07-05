var frontlinesms = this.frontlinesms || {};

frontlinesms.linkContacts = function() {
    $("#link-contacts").hide();
    $("#link-contacts").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function() {
                $(this).dialog("close");
            },
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });

    $("#link-contact-button").click(function() {
        $("#link-contacts").dialog("open");
        return false;
    });
};