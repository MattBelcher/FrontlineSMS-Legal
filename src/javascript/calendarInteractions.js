var frontlinesms = this.frontlinesms || {};

frontlinesms.calculateScheduleHeight = function (windowHeight) {
    var headerHeight = $("#header").outerHeight();
    var schedulePadding = parseInt($("#schedule").css('padding-top')) + parseInt($("#schedule").css('padding-bottom'));
    return windowHeight - headerHeight - schedulePadding;
};
frontlinesms.displayEventDetails = function(calEvent) {
    $("#event-title").text(calEvent.title);
    $('#event-start-time').text(frontlinesms.getFormattedTimeString(calEvent.start.getHours(), calEvent.start.getMinutes()));
    if(calEvent.end!=null)
        $('#event-end-time').text(frontlinesms.getFormattedTimeString(calEvent.end.getHours(), calEvent.end.getMinutes()));
    else
         $('#event-end-time').text($('#event-start-time').text());
    $("#event-date").text($.datepicker.formatDate("MM d,yy", calEvent.start));
    $('#event-id').text(calEvent.id);
    $.ajax({
        url: "fetchEventContacts",
        type: "POST",
        data : {
            eventId: calEvent.id
        },
        dataType: 'json',
        error: function (data){
            console.log("Failed to get linked contacts for event.");
        },
        success : function(data){
            frontlinesms.constructContactsTable(data)
        }
    });

    $("#view-event").dialog("open");
};

frontlinesms.constructContactsTable = function(data) {
    $('#event-contacts-table tbody *').remove();
    for (var i = 0; i < data.length; i++) {
        var newRow =
            '<tr class="event-contact">' +
                '<td>' + data[i]["name"] + '</td>' +
                '<td>' + data[i]["primaryMobile"] + '</td>' +
                '</tr>';
        $('#event-contacts-table tbody').append(newRow);
    }
};

frontlinesms.getFormattedTimeString = function(hr, min) {
    formattedString = "";
    var isAM;
    if (hr > 12) {
        isAM = false;
        hr -= 12;
    }
    else if (hr == 0) {
        hr = 12;
        isAM = true;
    }
    else if (hr == 12) {
        isAM = false;
    }
    else {
        isAM = true;
    }

    if (hr < 10) {
        formattedString += "0";
    }
    formattedString += hr + ":";
    if (min < 10) {
        formattedString += "0";
    }
    formattedString += min;
    formattedString += isAM ? "AM" : "PM";
    return formattedString;
}
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
                    right: 'month,agendaWeek,agendaDay'
                },
                defaultView: 'month',
                timeFormat: 'hh:mm tt',
                allDayDefault: false,
                eventColor: "rgb(0,162,232)",
                eventClick: function(calEvent, jsEvent, view) {
                    frontlinesms.displayEventDetails(calEvent);


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
