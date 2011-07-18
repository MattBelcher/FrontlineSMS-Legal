describe('caseEditCancelDialog', function () {
    beforeEach(function(){
        var tempHTML =
            '<input id="case-id" type="text" value="" change/>' +
            '<textarea id="case-description" type="text" value=""/>' +
            '<button id="case-update-cancel">Cancel</button>' +
            '<div id="case-update-cancel-dialog" title="Cancel Changes?" style="display: none;">' +
                '<p>Are you sure you do not want to save this case?</p>' +
            '</div>';
        $(tempHTML).appendTo("#fixtures");
        frontlinesms.showCaseOnLoad();
        spyOn(frontlinesms,"goHome").andReturn("#");
    });


    it('case cancel dialog should not appear if no changes have been made and cancel button is clicked', function () {
        $("#case-update-cancel").click();
        expect($('#case-update-cancel-dialog:visible').size()).toEqual(0);
    });

    it ('case cancel dialog should appear if changes are made and cancel button is clicked', function(){
        $('#case-id').change();
        $('#case-update-cancel').click();
        expect($('#case-update-cancel-dialog:visible').size()).toEqual(1);
    })

    afterEach(function(){
        $("#case-update-cancel-dialog, #case-cancel-dialog, #case-id, #case-description").remove();
    })


});