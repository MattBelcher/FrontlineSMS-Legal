var frontlinesms = this.frontlinesms || {};

frontlinesms.linkContacts = function() {
    $("#link-contacts").hide();
    $("#link-contacts").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });

    $("#search-button").click(function() {
        $.getJSON("/legalContact/search",{keyword: $("#contact-name-search").value}, frontlinesms.updateSearchResults);
        return false;
    });

    $("#link-contact-button").click(function() {
        $("#link-contacts").dialog("open");
        return false;
    });
};

frontlinesms.updateSearchResults = function (results) {

    alert(results)
        
};