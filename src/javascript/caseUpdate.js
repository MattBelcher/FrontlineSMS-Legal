var frontlinesms = this.frontlinesms || {};

frontlinesms.showCaseOnLoad = function() {
    modified = false
    $('input').change(function() {
        modified = true;
    });
    $('textarea').change(function(){
        modified = true;
    })
    $("#case-update-cancel").click(function() {
        frontlinesms.caseUpdateCancelConfirmAction(modified);
        return false;
    });
};


frontlinesms.caseUpdateCancelConfirmAction = function(setter) {

    if (!setter) {
        frontlinesms.goHome();
    }
    else {

        $("#case-update-cancel-dialog").dialog({
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
};

frontlinesms.goHome = function(){
    $(window.location).attr("href", "/");
};