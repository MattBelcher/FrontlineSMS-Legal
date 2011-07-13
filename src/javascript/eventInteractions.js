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

frontlinesms.eventCancelConfirmAction = function() {
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
}