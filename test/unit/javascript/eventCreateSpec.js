describe('eventCreateCancelDialog', function () {
    beforeEach(function() {
        var tempHTML = '<div id="event-create-div">' +
                '<g:textField id="event-title" name="eventTitle" value="${params.eventTitle}"/><br><br>' +
                '<label>Date</label>' +
                '<g:textField name="dateFieldSelected" id="event-date" value="${params.dateFieldSelected}"/><br><br>' +
                '<label>Start time</label>' +
                '<g:textField name="startTimeField" id="event-start-time" value="${params.startTimeField}"/><br><br>' +
                '<label>End time</label>' +
                '<g:textField name="endTimeField" id="event-end-time" value="${params.endTimeField}"/><br><br>' +
                '<input type="submit" id="event-save" value="Save"/>' +
                '<button id="event-cancel" onclick="frontlinesms.eventCancelConfirmAction();return false;">Cancel</button>' +
                '<div id="event-cancel-dialog" title="Cancel event creation?" style="display: none;">' +
                '<p>Are you sure you want to cancel this event? Your event will not be saved?</p>' +
                '</div>' +
                '</div>'
        $(tempHTML).appendTo("#fixtures");

    });
    it('dialog should not pop up when cancel button is clicked with the form is empty', function () {
        spyOn(frontlinesms,"redirectToHomePage").andReturn("#");
        $("#event-cancel").click();
        expect($('#event-cancel-dialog:hidden').size()).toEqual(1);
    });

    it('dialog should pop up when cancel button is clicked with the form is not empty', function () {
        $('#event-title').val("asf");
        $("#event-cancel").click();
        expect($('#event-cancel-dialog:visible').size()).toEqual(1);
    });


    afterEach(function() {
        $("#event-create-div, #event-cancel-dialog").remove();
    })
});
