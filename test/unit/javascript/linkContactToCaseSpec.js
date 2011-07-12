describe('linkContactToCase', function () {
    beforeEach(function() {
        var tempHTML =
            '<div id="link-contacts" title="Link Contacts">' +

                '<table id="contactsTable"> <tbody>' +
                '<tr class="contactLink" id="fabio">' +
                '<td  class="contact-name"> <a href="#" >fabio</a> </td>' +
                '<td class="contact-number"> <a href="#">99999</a> </td>' +
                '</tr> </tbody></table> </div>' +

                '<table name="contacts" id="contacts">' +
                '<tr>' +
                '<th>Contact Name</th>' +
                '<th>Phone</th>' +
                '<th>Involvement</th>' +
                '</tr> </table>' +

                '<button id="link-contact-button">Link contacts</button>';

        $(tempHTML).appendTo("#fixtures");
        frontlinesms.linkContactToCase();

    });

    it('when link-contacts button is clicked contacts dialog is opened', function () {
        $("#link-contact-button").click();
        expect($('#link-contacts:visible').size()).toEqual(1);
    });

    it('when a contact is clicked on link contact dialog box it should appear on show case page table', function() {
        spyOn(window, 'prompt').andReturn('Client');
        $("#link-contact-button").click();
        $("#fabio").click();
        expect($('#contacts tr > td:contains(fabio) + td:contains(99999) + td:contains(Client)').length).toEqual(1);
    });

    afterEach(function() {
        $("#link-contacts, .contactLink, #contacts, #link-contact-button").remove();
    })


});