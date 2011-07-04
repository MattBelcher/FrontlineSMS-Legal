describe("tabs", function() {
   it("should use the page's active-tab element to select the right tab", function() {
       var fixture = "<span id=\"active-tab\" class=\"test\"></span>" +
           "<div class=\"tab abc\"></div><div class=\"tab test\"></div>";
       $(fixture).appendTo("#fixtures");

       frontlinesms.tabs();

       expect($(".tab.test").hasClass("selected")).toBeTruthy();
   });
});