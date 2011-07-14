describe('linkContactToEvent', function () {
    beforeEach(function() {
        var tempHTML =
            '<input type="hidden" id="event-linked-contacts" value=""/>' +
            '<div id="link-contacts" title="Link Contacts">' +

                '<table id="contactsTable"> <tbody>' +
                '<tr class="contactLink" id="fabio">' +
                '<td  class="contact-name"> <a href="#" >fabio</a> </td>' +
                '<td class="contact-number"> <a href="#">99999</a> </td>' +
                '</tr>' +
                 '<tr class="contactLink" id="dahlia">' +
                '<td  class="contact-name"> <a href="#" >dahlia</a> </td>' +
                '<td class="contact-number"> <a href="#">33333</a> </td>' +
                '</tr>'+
                '</tbody></table> ' +
            '</div>' +

                '<table name="contacts" id="contacts">' +
                '<tr>' +
                '<th>Contact Name</th>' +
                '<th>Phone</th>' +
                '<th style="display: none;"></th>' +
                '</tr> </table>' +

                '<button id="link-contact-button">Link contacts</button>';

        $(tempHTML).appendTo("#fixtures");
        frontlinesms.linkContactToEvent();

    });

    it('when remove button is clicked the appropriate contact is removed', function(){
        $("#link-contact-button").click();
        $("#fabio").click();
        var secondRowSelector = "table#contacts tr:nth-child(2)";
        $(secondRowSelector + " td.remove-contact-button").click();
        expect($(secondRowSelector + ":contains('fabio')").size()).toEqual(0);
    })

    it('when link-contacts button is clicked contacts dialog is opened', function () {
        $("#link-contact-button").click();
        expect($('#link-contacts:visible').size()).toEqual(1);
    });

    it('when a contact is clicked on link contact dialog box it should appear on show case page table', function() {
        $("#link-contact-button").click();
        $("#fabio").click();
        expect($('#contacts tr > td:contains(fabio) + td:contains(99999)').length).toEqual(1);
    });

    it('when a contact is clicked on link contact dialog box it\'s id should appear in the hidden field', function() {
        $("#link-contact-button").click();
        $("#fabio").click();
        expect($('#event-linked-contacts').val()).toEqual("fabio");
    });
    it('when two contacts are clicked on link contact dialog box their ids should appear in the hidden field', function() {
        $("#link-contact-button").click();
        $("#fabio").click();
        $("#link-contact-button").click();
        $("#dahlia").click();
        expect($('#event-linked-contacts').val()).toEqual("fabio,dahlia");
    });



    afterEach(function() {
        $("#link-contacts, .contactLink, #contacts, #link-contact-button, #event-linked-contacts").remove();
    })


});