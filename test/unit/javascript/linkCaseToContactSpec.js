describe('linkCaseToContact', function () {
    beforeEach(function() {
        var tempHTML =
            '<input type="hidden" id="contact-linked-cases" value=""/>' +
                '<div id="link-case-dialog" title="Link Cases">' +
                '<table id="SearchResults">' +
                '<thead></thead>' +
                '<tbody>' +
                '<tr class="caseLink" id="case1">' +
                '<td class="case-name">case1</td>' +
                '<td>active</td>' +
                '</tr>' +
                '<tr class="caseLink" id="case2">' +
                ' <td class="case-name">case2</td>' +
                '<td>active</td>' +
                '</tr>' +
                '</tbody>' +
                '</table>' +
                '</div>' +

                '<table id="cases">' +
                '</table>' +

                '<button id="link-case-button">Link cases</button>';
        $(tempHTML).appendTo("#fixtures");
        frontlinesms.linkCaseToContact();

    });


    it('when remove button is clicked the appropriate case is removed', function() {
        spyOn(window, 'prompt').andReturn('client');
        $("#link-case-button").click();
        $("#case1").click();
        var secondRowSelector = "table#linked-cases tr:first";
        $(secondRowSelector + " td.remove-case-button").click();
        expect($(secondRowSelector + ":contains('case1')").size()).toEqual(0);
    })
    it('when remove button is clicked the appropriate case is removed from hidden form field', function() {
        spyOn(window, 'prompt').andReturn('client');
        $("#link-case-button").click();
        $("#case1").click();
        $("#link-case-button").click();
        $("#case2").click();
        var secondRowSelector = "table#cases tr:nth-child(2)";
        $(secondRowSelector + " td.remove-case-button").click();
        expect($('#contact-linked-cases').val()).toEqual("{\"case1\":\"client\"}");
    })

    it('when link-cases button is clicked case search dialog is opened', function () {
        spyOn(window, 'prompt').andReturn('client');
        $("#link-case-button").click();
        expect($('#link-case-dialog:visible').size()).toEqual(1);
    });

    afterEach(function() {
        $('body#fixtures > *').not(".jasmine_reporter").not('script').remove()
    })


});