describe('calculate', function () {
    var counter = 0

    it('can add a number', function () {
        counter = counter + 2;
        expect(counter).toEqual(2);
    });

    it('can multiply a number', function () {
        counter = counter * 5;
        expect(counter).toEqual(10);
    });
});