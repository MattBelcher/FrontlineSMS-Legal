var frontlinesms = this.frontlinesms || {};

frontlinesms.tabs = function () {
    var selectedTab = $("#active-tab").attr('class');
    $(".tab." + selectedTab).addClass("selected");
};