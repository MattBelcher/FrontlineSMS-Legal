var frontlinesms = this.frontlinesms ||  {};

frontlinesms.createNewCaseOnLoad = function() {
    $("#case-cancel").click(function(){
        frontlinesms.caseCancelConfirmAction();
        return false;
    });
}

frontlinesms.caseCancelConfirmAction = function() {
    $("#case-cancel-dialog").dialog({
        modal: true,
        buttons: [
            {
                text: "Yes",
                click: function() {
                    $(window.location).attr("href","/");
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