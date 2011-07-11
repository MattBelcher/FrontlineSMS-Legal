describe('calculateScheduleHeight', function () {
    beforeEach(function () {
        $('#fixture').remove();
        $('body').append("<div id=\"header\"></div><div id=\"schedule\"></div>");
        $('body').append("<div id=\"view-event\" title=\"Event Details\">\
    <label>Title :</label>\
    <label name=\"eventTitle\" id=\"event-title\"></label><br/>\
    <label>Date :</label>\
    <label name=\"eventDate\" id=\"event-date\"></label><br/>\
    <input type=\"button\" id=\"delete-event\" value=\"Delete\"/>\
    <label name=\"eventId\" id=\"event-id\"></label>");
        $("#header").css("height", 5);
        $("#schedule").css("padding", 10);
        $('#schedule').fullCalendar({
                    events: [
                        {
                            title: 'All Day Event',
                            start: new Date(y, m, 1)
                        }
                    ]
                });

        frontlinesms.calendarInteractions();
        frontlinesms.eventDetails();
        $('#event-id').hide();
    });

    it('uses the window height, header height and top/bottom padding to ', function () {
        $("#header").css("height", 5);
        $("#schedule").css("padding", 10);
        var windowHeight = 500;

        expect(frontlinesms.calculateScheduleHeight(windowHeight)).toEqual(windowHeight - 10 - 10 - 5);
    });

    it('show pop-up dialog on clicking an event', function() {


        span[class = 'fc-event-title'].first().click();
        expect($('#view-event').is(':visible'));
    });
    it('show cancel confirm dialog on clicking delete button', function() {

        span[class = 'fc-event-title'].first().click();
        oldEventSize = $("span[class='fc-event-title']'").size()
        $('#delete-event').click()
        expect($('#event-cancel-dialog').is(':visible'));

        $('#cancel-confirm-no').click()
        expect($('#view-event').is(':visible'));
        expect(oldEventSize == $("span[class='fc-event-title']'").size())

        $('#delete-event').click()
        $('#cancel-confirm-yes').click()
        expect($('#view-event').is(':visible'));
        expect(oldEventSize == $("span[class='fc-event-title']'").size() + 1)


    });

    it('should not delete event on No button in cancel confirm dialog'),function() {
        span[class = 'fc-event-title'].first().click();
        oldEventSize = $("span[class='fc-event-title']'").size()
        $('#delete-event').click()
        expect($('#event-cancel-dialog').is(':visible'));

        $('#cancel-confirm-no').click()
        expect($('#view-event').is(':visible'));
        expect(oldEventSize == $("span[class='fc-event-title']'").size())

    }

    it('should delete event on No button in cancel confirm dialog'),function() {
        span[class = 'fc-event-title'].first().click();
        oldEventSize = $("span[class='fc-event-title']'").size()
        $('#delete-event').click()
        expect($('#event-cancel-dialog').is(':visible'));

        $('#delete-event').click()
        $('#cancel-confirm-yes').click()
        expect($('#view-event').is(':visible'));
        expect(oldEventSize == $("span[class='fc-event-title']'").size() + 1)

    }
});