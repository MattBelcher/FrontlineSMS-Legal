var frontlinesms = this.frontlinesms || {};

frontlinesms.createNewContactOnLoad = function() {
    $("#contact-save").click(function() {
        frontlinesms.contactCreateWithoutNameConfirmAction();
        return false;
    });
};

frontlinesms.contactCreateWithoutNameConfirmAction = function() {
    var ajaxDefaults = {
        dataType: 'json',
        cache: false
    };

    if (($("#contact-name").val().trim() == "")) {
        $("#contact-save-no-name-dialog").dialog({
                    modal: true,
                    buttons: [
                        {
                            text: "Yes",
                            click: function() {
                              $(this).dialog("close");
                              $("#contact-save-form").submit();

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
    else {
        $("#contact-save-form").submit();
    }
};


