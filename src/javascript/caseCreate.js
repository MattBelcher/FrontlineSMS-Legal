frontlinesms = {}

frontlinesms.cancelConfirmAction = function() {
    $("#case-cancel-dialog").dialog({
        modal: true,
        buttons: [
            {
                text: "Yes",
                click: function() {

                    $(this).dialog("close")
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