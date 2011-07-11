var frontlinesms = this.frontlinesms || {};

frontlinesms.eventDetails = function() {
    $("#view-event").hide();
    $("#view-event").dialog({
        autoOpen: false
       /// modal: true
    });

    $("#link-contact-button").click(function() {
        $("#view-event").dialog("open");
        return false;
    });


};

