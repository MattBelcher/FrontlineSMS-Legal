describe('linkContactToCase', function () {
    beforeEach(function() {
        var tempHTML =
            '<div id="link-contacts" title="Link Contacts">'+
            '<a href="#" class="contactLink" id="fabio">fabio</a>' +
            '<a href="#" class="contactLink" id="99999">99999</a>' +

                '<table name="contacts" id="contacts">' +
                '<tr>' +
                '<th>Contact Name</th>' +
                '<th>Phone</th>' +
                '<th>Contact Type</th>' +
                '</tr>'+
                '</div>' +
            '<button id="link-contact-button">Link contacts</button>';
        $(tempHTML).appendTo("#fixtures");
        frontlinesms.linkContactToCase();
    });

     it('when link-contacts button is clicked contacts dialog is opened', function () {
        $("#link-contact-button").click();
        expect($('#link-contacts:visible').size()).toEqual(1);
    });


    it('when contact is clicked dialog box should disappear', function () {
        $("#fabio").click();
        expect($('#link-contacts:visible').size()).toEqual(0);
    });



    afterEach(function() {
      $("#link-contacts, .contactLink, #contacts, #link-contact-button").remove();
    })


});