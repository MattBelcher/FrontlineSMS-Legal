var frontlinesms = this.frontlinesms || {};

frontlinesms.caseSearchOnLoad = function() {
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }

    frontlinesms.initializeAutocomplete();
}

frontlinesms.initializeAutocomplete = function() {
    $("#SearchResults").tableFilter({
        additionalFilterTriggers: [$("#caseId")]
    });
    $("#SearchResults thead tr.filters").remove();
    $("#caseId").removeAttr("title");
}
