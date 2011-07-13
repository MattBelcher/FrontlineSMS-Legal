describe('caseCreateCancelDialog', function () {
    beforeEach(function(){
        var tempHTML =
            '<input id="case-id" type="text" value=""/>' +
            '<input id="case-description" type="text" value=""/>' +
            '<button id="case-cancel">Cancel</button>' +
            '<div id="case-cancel-dialog" title="Cancel case creation?" style="display: none;">' +
                '<p>Are you sure you do not want to save this case?</p>' +
            '</div>';
        $(tempHTML).appendTo("#fixtures");
        frontlinesms.createNewCaseOnLoad();
    });


    it('dialog pops up when cancel button is clicked with the form not empty', function () {
        $("#case-id").val("1234");
        $("#case-description").val("some description");
        $("#case-cancel").click();
        expect($('#case-cancel-dialog:visible').size()).toEqual(1);
    });

    it('dialog hides when no button is clicked on the dialog', function () {
        $("#case-id").val("1234");
        $("#case-description").val("some description");
        $("#case-cancel").click();
        $("#cancel-confirm-no").click();
        expect($('#case-cancel-dialog:hidden').size()).toEqual(1);
    });

    afterEach(function(){
        $("#case-cancel, #case-cancel-dialog, #case-id, #case-description").remove();
    })


});