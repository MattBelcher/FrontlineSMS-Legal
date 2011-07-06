describe('caseCreateCancelDialog', function () {
    beforeEach(function(){
        var tempHTML =
            '<button id="case-cancel">Cancel</button>' +
            '<div id="case-cancel-dialog" title="Cancel case creation?" style="display: none;">' +
                '<p>Are you sure you do not want to save this case?</p>' +
            '</div>';
        $(tempHTML).appendTo("#fixtures");
        frontlinesms.caseCancelConfirmAction();
    });


    it('dialog pops up when cancel button is clicked', function () {
        $("#case-cancel").click();
        expect($('#case-cancel-dialog:visible').size()).toEqual(1);
    });

    it('dialog hides when no button is clicked on the dialog', function () {
        $("#case-cancel").click();
        $("#cancel-confirm-no").click();
        expect($('#case-cancel-dialog:hidden').size()).toEqual(1);
    });

    afterEach(function(){
        $("#case-cancel, #case-cancel-dialog").remove();
    })


});