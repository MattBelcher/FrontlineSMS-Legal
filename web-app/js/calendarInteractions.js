frontlinesms = {};

frontlinesms.calculateScheduleHeight = function () {
    var windowHeight = $(window).height();
    var headerHeight = $("#header").outerHeight();
    var schedulePadding = parseInt($("#schedule").css('padding-top')) + parseInt($("#schedule").css('padding-bottom'));
    return windowHeight - headerHeight - schedulePadding;
};

frontlinesms.calendarInteractions = function() {
    $('#schedule').fullCalendar({
        height: frontlinesms.calculateScheduleHeight(),
        events: [
            {
                title: 'Appointment',
                start: 'Wed, 29 Jun 2011 13:00:00 EST'
            },
            {
                title: 'Another Appointment',
                start: 'Wed, 29 Jun 2011 13:00:00 EST'
            },
            {
                title: 'Past Appointment',
                start: 'Wed, 16 Jun 2011 13:00:00 EST'
            }
        ],
        windowResize: function () {
            $("#schedule").fullCalendar("option", "height", frontlinesms.calculateScheduleHeight())
        }
    })
}
