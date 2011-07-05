var frontlinesms = this.frontlinesms || {};

frontlinesms.linkContacts = function() {
    $("#link-contacts").hide();
    $("#link-contacts").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function() {
                console.log("saved");
                $(this).dialog("close");
            },
            "Cancel": function() {
                console.log("cancel");
                $(this).dialog("close");
            }
        }
    });

    $("#link-contact-button").click(function() {
        $("#link-contacts").dialog("open");
    });
};