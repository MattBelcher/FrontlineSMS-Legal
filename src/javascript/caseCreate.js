var frontlinesms = this.frontlinesms ||  {};

frontlinesms.caseCancelConfirmAction = function() {
    $("#case-cancel-dialog").dialog({
        modal: true,
        buttons: [
            {
                text: "Yes",
                click: function() {
                    $(window.location).attr("href","/frontlinesms-legal");
                },
                id: "cancel-confirm-yes"
            },
            {
                text: "No",
                click: function() { $(this).dialog("close")},
                id: "cancel-confirm-no"
            }
        ]
    });
}