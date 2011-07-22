describe('caseEditCancelDialog', function () {
    beforeEach(function(){
        var tempHTML =
            '<div id="test-div">' +
            '<input id="case-id" type="text" value="" change/>' +
            '<textarea id="case-description" type="text" value=""/>' +
            '<button id="case-update-cancel">Cancel</button>' +
            '<div id="case-update-cancel-dialog" title="Cancel Changes?" style="display: none;">' +
                '<p>Are you sure you do not want to save this case?</p>' +
            '</div>' +
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
        $('body#fixtures > *').not(".jasmine_reporter").not('script').remove()
    })


});

describe('submit button', function(){
    beforeEach(function(){
        var tempHTML =
            '<div id="test-div">' +
            '<input id="case-id" type="text" value="" />' +
                    '<table id="test-table"><tr class="contactLink" id="contact-link"></tr>' +
                    '<tr><td class="remove-contact-button"></td></tr></table>' +
            '<button id="case-update" disabled="disabled">Submit</button>' +
                '</div>';
        $(tempHTML).appendTo("#fixtures");
        frontlinesms.showCaseOnLoad();
    });

    it ('should be enabled if changes have been to the id', function(){
        $('#case-id').change();
        expect($('#case-update').attr('disabled')).toBeUndefined();
    })

    it ('should be enabled if contacts have been linked', function(){
        $('.contactLink').click()
        expect($('#case-update').attr('disabled')).toBeUndefined();
    })

    it ('should be enabled if contacts have been removed', function(){
        $('.remove-contact-button').click()
        expect($('#case-update').attr('disabled')).toBeUndefined();
    })

    afterEach(function(){
        $('body#fixtures > *').not(".jasmine_reporter").not('script').remove()
    })

})                                                             ;