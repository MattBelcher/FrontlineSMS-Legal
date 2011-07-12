var frontlinesms = this.frontlinesms || {};

<<<<<<< HEAD
frontlinesms.linkContacts = function() {
    $("#link-contact").hide();
    $("#link-contact").dialog({
=======
frontlinesms.linkContactToCase = function() {
   
    $("#link-contacts").dialog({
>>>>>>> 4cd41dd59e00d854a924fb929053b9f0610d1b63
        autoOpen: false,
        modal: true,
        buttons: {
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });

    

    $("#link-contact-button").click(function() {
        $("#link-contact").dialog("open");
        return false;
    });

    $(".contactLink").click(function() {
        $('#contacts').append('<tr><td>'+$(this).attr('id')+'</td><td>'+$(this).attr('id')+'</td></tr>');
        $("#link-contacts").dialog("close");
        return false;
    });
};

frontlinesms.updateSearchResults = function (results) {

    alert(results)

};
