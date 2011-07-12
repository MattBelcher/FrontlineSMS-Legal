var frontlinesms = this.frontlinesms || {};

frontlinesms.linkContactToCase = function() {
   
    $("#link-frontlinesms.legal.contacts").dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });

    

    $("#link-contact-button").click(function() {
        $("#link-frontlinesms.legal.contacts").dialog("open");
        return false;
    });

    $(".contactLink").click(function() {
        $('#frontlinesms.legal.contacts').append('<tr><td>'+$(this).attr('id')+'</td><td>'+$(this).attr('id')+'</td></tr>');
        $("#link-frontlinesms.legal.contacts").dialog("close");
        return false;
    });
};

frontlinesms.updateSearchResults = function (results) {

    alert(results)

};