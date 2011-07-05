var frontlinesms = this.frontlinesms || {};

frontlinesms.caseSearchOnLoad = function() {
    frontlinesms.initializeAutocomplete();
}

frontlinesms.initializeAutocomplete = function() {
    $("#SearchResults").tableFilter({
        additionalFilterTriggers: [$("#caseId")]
    });
    $("#SearchResults thead tr.filters").remove();
}