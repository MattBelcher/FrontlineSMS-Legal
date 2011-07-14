describe("Contact Create Dialog", function(){
    beforeEach(function(){
        var tempHTML =
            '<input id="contact-name" type="text" value=""/>' +
            '<input id="contact-primary-mobile" type="text" value=""/>' +
            '<button id="contact-save">Save</button>' +
            '<div id="contact-save-no-name-dialog" title="Save Without Name?" style="display: none;">' +
                '<p>Do you want to save this Contact without a Contact Name?</p>' +
            '</div>';
        $(tempHTML).appendTo("#fixtures");
        frontlinesms.createNewContactOnLoad();
    });
    it("Dialog Appears if contact saved with number but without name", function(){
        $("#contact-primary-mobile").val("8675309");
        $("#contact-name").val("");
        $("#contact-save").click();
        expect($('#contact-save-no-name-dialog:visible').size()).toEqual(1);
    });

    it("Dialog should not appear if contact is saved without number AND without name", function(){
        $("#contact-primary-mobile").val("");
        $("#contact-name").val("");
        $("#contact-save").click();
        expect($('#contact-save-no-name-dialog:visible').size()).toEqual(0);
    })

       afterEach(function(){
               $("#contact-name, #contact-primary-mobile, #contact-save, #contact-save-no-name-dialog").remove();
    });

});