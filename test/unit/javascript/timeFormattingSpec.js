describe('formattingTimeString', function () {
    it('should return formatted string as hh:mm(AM/PM)', function () {
        expect(frontlinesms.getFormattedTimeString(12,7)).toEqual("12:07PM");
        expect(frontlinesms.getFormattedTimeString(0,30)).toEqual("12:30AM");
        expect(frontlinesms.getFormattedTimeString(23,0)).toEqual("11:00PM");
        expect(frontlinesms.getFormattedTimeString(14,59)).toEqual("02:59PM");
        expect(frontlinesms.getFormattedTimeString(7,25)).toEqual("07:25AM");

    });
});