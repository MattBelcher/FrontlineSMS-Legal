frontlinesms = {};

frontlinesms.activateDatePicker = function() {
    $('#event-date').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'MM d, yy'
            });

}

frontlinesms.eventCancelConfirmAction = function() {
    $("#event-cancel-dialog").dialog({
                modal: true,
                buttons: [
                    {
                        text: "Yes, Cancel",
                        click: function() {
                            $(window.location).attr("href", "/frontlinesms-legal");
                        },
                        id: "cancel-confirm-yes"
                    },
                    {
                        text: "No",
                        click: function() {
                            $(this).dialog("close")
                        },
                        id: "cancel-confirm-no"
                    }
                ]
            });
}