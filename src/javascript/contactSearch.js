var frontlinesms = this.frontlinesms || {};

frontlinesms.contactSearchOnLoad = function() {
    frontlinesms.initializeSearchAutocomplete();
}

frontlinesms.initializeSearchAutocomplete = function() {
    $("#contactsTable").tableFilter({
        additionalFilterTriggers: [$("#contact-name-search")]
    });
    $("#contact-name-search").removeAttr("title");
    $('#contactsTable thead').remove()
}
