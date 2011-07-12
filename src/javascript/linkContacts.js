var frontlinesms = this.frontlinesms || {};

frontlinesms.linkContacts = function() {
    $("#link-contact").hide();
    $("#link-contact").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });

    $("#link-contact-button").click(function() {
        $("#link-contact").dialog("open");
        return false;
    });
};

