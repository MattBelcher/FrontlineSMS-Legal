describe('linkCaseToContact', function () {
    beforeEach(function() {
        var tempHTML =
            '<div id="link-case-dialog" title="Link Cases">' +
                    '</div>' +
                    '<p>I am the Walrus</p>' +

                '<button id="link-case-button">Link cases</button>' ;
        $(tempHTML).appendTo("#fixtures");
        frontlinesms.linkContactToCase();

    });

    it('when link-cases button is clicked case search dialog is opened', function () {
        $("#link-case-button").click();
        expect($('#link-case-dialog:visible').size()).toEqual(1);
    });

    afterEach(function() {
        $("#link-cases").remove();
    })


});