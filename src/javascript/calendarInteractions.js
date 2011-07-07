var frontlinesms = this.frontlinesms || {};

frontlinesms.calculateScheduleHeight = function (windowHeight) {
    var headerHeight = $("#header").outerHeight();
    var schedulePadding = parseInt($("#schedule").css('padding-top')) + parseInt($("#schedule").css('padding-bottom'));
    return windowHeight - headerHeight - schedulePadding;
};

frontlinesms.calendarInteractions = function() {
    $('#schedule').fullCalendar({
        height: frontlinesms.calculateScheduleHeight($(window).height()),
        events: {
            url:'fetchEvents',
            type: 'POST',
            dataType: 'json'
        },
        eventColor: "rgb(100,100,100)",

        windowResize: function () {
            $("#schedule").fullCalendar("option", "height", frontlinesms.calculateScheduleHeight($(window).height()))
        }
    })
}
