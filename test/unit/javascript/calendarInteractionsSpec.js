describe('calculateScheduleHeight', function () {
    it('uses the window height, header height and top/bottom padding to ', function () {
        $("<div id=\"header\"></div><div id=\"schedule\"></div>").appendTo("#fixtures");
        $("#header").css("height", 5);
        $("#schedule").css("padding", 10);
        var windowHeight = 500;

        expect(frontlinesms.calculateScheduleHeight(windowHeight)).toEqual(windowHeight - 10 - 10 - 5);
    });
});


