var frontlinesms = this.frontlinesms || {};

frontlinesms.createNewContactOnLoad = function() {
    $("#contact-save").click(function() {
        frontlinesms.contactCreateWithoutNameConfirmAction();
        return false;
    });
};

frontlinesms.contactCreateWithoutNameConfirmAction = function() {
    if (($("#contact-name").val().trim() == "")) {
        $("#contact-save-no-name-dialog").dialog({
                    modal: true,
                    buttons: [
                        {
                            text: "Yes",
                            click: function() {
                            },
                            id: "save-confirm-yes"
                        },
                        {
                            text: "No",
                            click: function() {
                                $(this).dialog("close");
                            },
                            id: "save-confirm-no"
                        }
                    ]
                });
    }
};


