var frontlinesms = this.frontlinesms || {};

frontlinesms.tabs = function () {
    var selectedTab = $("#active-tab").attr('class');
    if (selectedTab) {
        $(".tab." + selectedTab).addClass("selected");
    }
};