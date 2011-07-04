var frontlinesms = this.frontlinesms || {};

frontlinesms.calculateScheduleHeight = function (windowHeight) {
    var headerHeight = $("#header").outerHeight();
    console.log(windowHeight);
    var schedulePadding = parseInt($("#schedule").css('padding-top')) + parseInt($("#schedule").css('padding-bottom'));
    console.log(headerHeight);
    console.log(schedulePadding);
    return windowHeight - headerHeight - schedulePadding;
};

frontlinesms.calendarInteractions = function() {
    $('#schedule').fullCalendar({
        height: frontlinesms.calculateScheduleHeight($(window).height()),
        events: [
            {
                title: 'Appointment',
                start: 'Wed, 29 Jul 2011 13:00:00 EST'
            },
            {
                title: 'Another Appointment',
                start: 'Wed, 29 Jul 2011 13:00:00 EST'
            },
            {
                title: 'Past Appointment',
                start: 'Wed, 16 Jul 2011 13:00:00 EST'
            }
        ],
        windowResize: function () {
            $("#schedule").fullCalendar("option", "height", frontlinesms.calculateScheduleHeight($(window).height()))
        }
    })
}
