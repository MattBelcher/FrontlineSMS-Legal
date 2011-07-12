describe('show event details',function(){
    beforeEach(function(){
       var tempHTML =
                "<input type=\"button\" name=\"li\" id=\"test-dialog-button\"/>" +
                        "<div id=\"view-event\" title=\"Event Details\">" +
                        "<label>Title :</label>" +
                        "<label name=\"eventTitle\" id=\"event-title\"></label><br/>" +
                        "<label>Date :</label>" +
                        "<label name=\"eventDate\" id=\"event-date\"></label><br/>" +
                        "<label>Start Time :</label>" +
                        "<label name=\"eventStartTime\" id=\"event-start-time\"></label><br/>" +
                        "<label>End Time :</label>" +
                        "<label name=\"eventEndTime\" id=\"event-end-time\"></label><br/>" +
                        "<input type=\"button\" id=\"delete-event\" value=\"Delete\"/>" +
                        "<label name=\"eventId\" id=\"event-id\"></label>" +
                        "</div>";
        $(tempHTML).appendTo("#fixtures");
         $(tempHTML).appendTo("#fixtures");
        frontlinesms.eventDetails();
        function EventTemplate() {
            this.title = "test";
            this.start = new Date(2011, 6, 3, 0, 0, 0);
            this.end = new Date(2011, 6, 3, 15, 50, 0);
        }

        ;
        $('#test-dialog-button').click(function() {
            frontlinesms.displayEventDetails(new EventTemplate());
        });
    });

    it('should display event details in pop up',function(){
        $('#test-dialog-button').click();
        expect($('#event-title').text()).toEqual("test");
        expect($('#event-date').text()).toEqual("July 3,2011");
        expect($('#event-start-time').text()).toEqual("12:00AM");
        expect($('#event-end-time').text()).toEqual("03:50PM");
    });

     afterEach(function(){
               $("#view-event, #test-dialog-button").remove();
    });
});