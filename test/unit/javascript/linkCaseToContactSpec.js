describe('linkCaseToContact', function () {
    beforeEach(function() {
        var tempHTML =
            '<div id="link-cases" title="Link Cases">' +

                '<button id="link-case-button">Link cases</button>' +
                '</div>';

        $(tempHTML).appendTo("#fixtures");
        frontlinesms.linkContactToCase();

    });

    afterEach(function() {
        $("#link-cases").remove();
    })


});