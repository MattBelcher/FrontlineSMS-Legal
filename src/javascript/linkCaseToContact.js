var frontlinesms = this.frontlinesms || {};

frontlinesms.linkCaseToContact = function() {

    $("#link-case-dialog").dialog({
        autoOpen: false,
        modal: true,
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
};