var frontlinesms = this.frontlinesms || {};

frontlinesms.showCaseOnLoad = function() {
    $("#case-update-cancel").click(function() {
        frontlinesms.caseUpdateCancelConfirmAction();
        return false;
    });
}


frontlinesms.caseUpdateCancelConfirmAction = function() {

    if(($("#case-id").val().trim() == "") && ($("#case-description").val().trim() == "")) {
         $(window.location).attr("href" ,"/");
    }
    else {
    $("#case-cancel-dialog").dialog({
        modal: true,
        buttons: [
            {
                text: "Yes, discard",
                click: function() {
                    $(window.location).attr("href", "/");
                },
                id: "cancel-confirm-yes"
            },
            {
                text: "No",
                click: function() {
                    $(this).dialog("close");
                },
                id: "cancel-confirm-no"
            }
        ]
    });
    }
}