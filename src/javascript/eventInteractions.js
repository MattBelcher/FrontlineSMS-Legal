var frontlinesms = this.frontlinesms || {};

frontlinesms.activateDatePicker = function() {
    $('#event-date').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'MM d, yy'
            });

}
frontlinesms.activateTimePicker = function() {
    $('#event-start-time').timeEntry({
                spinnerImage: ''
            });
    $('#event-end-time').timeEntry({
                spinnerImage: ''
            });
}

frontlinesms.redirectToHomePage = function() {
    return  "/"
}

frontlinesms.eventCancelConfirmAction = function() {
    if (frontlinesms.isPageEmpty()) {
       $(window.location).attr("href", frontlinesms.redirectToHomePage());
    }
    else {
        $("#event-cancel-dialog").dialog({
                    modal: true,
                    buttons: [
                        {
                            text: "Yes, Cancel This Event",
                            click: function() {
                                $(window.location).attr("href", "/");
                            },
                            id: "cancel-confirm-yes"
                        },
                        {
                            text: "No, Don't Cancel This Event",
                            click: function() {
                                $(this).dialog("close")
                            },
                            id: "cancel-confirm-no"
                        }
                    ]
                });
        $("#event-cancel-dialog").dialog('open');

    }
}

frontlinesms.isPageEmpty = function() {
    if (frontlinesms.elementIsEmpty($("#event-title")) && frontlinesms.elementIsEmpty($("#event-date")) &&
            frontlinesms.elementIsEmpty($("#event-start-time")) && frontlinesms.elementIsEmpty($("#event-end-time"))) {
        return true;
    }
    else {
        return false;
    }
}

frontlinesms.elementIsEmpty = function(element) {
    if ($(element).val() == "") {
        return true;
    }
    else {
        return false;
    }

}
