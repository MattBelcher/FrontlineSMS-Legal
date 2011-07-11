var frontlinesms = this.frontlinesms || {};

frontlinesms.calculateScheduleHeight = function (windowHeight) {
    var headerHeight = $("#header").outerHeight();
    var schedulePadding = parseInt($("#schedule").css('padding-top')) + parseInt($("#schedule").css('padding-bottom'));
    return windowHeight - headerHeight - schedulePadding;
};

frontlinesms.calendarInteractions = function() {
    $('#schedule').fullCalendar({
                theme: true,
                height: frontlinesms.calculateScheduleHeight($(window).height()),
                events: {
                    url:'fetchEvents',
                    type: 'POST',
                    dataType: 'json'
                },
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,basicWeek,basicDay'
                },
                defaultView: 'month',
                eventColor: "rgb(100,100,100)",
                eventClick: function(calEvent, jsEvent, view) {
                    $("#event-title").text(calEvent.title);

                    $("#event-date").text(calEvent.start.toString());
                    $('#event-id').text(calEvent.id);
                    $("#view-event").dialog("open");

                },
                windowResize: function () {
                    $("#schedule").fullCalendar("option", "height", frontlinesms.calculateScheduleHeight($(window).height()))
                }
            })

    var ajaxDefaults = {
        dataType: 'json',
        cache: false
    };
    $("#delete-event").click(function() {
        $("#event-cancel-dialog").dialog({
                    modal: true,
                    buttons: [
                        {
                            text: "Yes",
                            click: function() {

                                $("#view-event").dialog("close");
                                $.ajax("deleteEvent/" + $('#event-id').text(), ajaxDefaults);
                                $('#schedule').fullCalendar('removeEvents', $('#event-id').text())
                                $(this).dialog("close");
                                return true;
                            },
                            id: "cancel-confirm-yes"
                        },
                        {
                            text: "No",
                            click: function() {
                                $(this).dialog("close");
                                return false;
                            },
                            id: "cancel-confirm-no"
                        }
                    ]
                });

    });


}
