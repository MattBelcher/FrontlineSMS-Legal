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
<<<<<<< HEAD
        $('#frontlinesms.legal.contacts').append('<tr><td>'+$(this).attr('id')+'</td><td>'+$(this).attr('id')+'</td></tr>');
        $("#link-frontlinesms.legal.contacts").dialog("close");
=======
        var row = $('#contactsTable').find('#' + $(this).attr('id'));
        $('#contacts').append('<tr><td>'+$(row).find('.contact-name').text()+'</td><td>'+$(row).find('.contact-number').text()+'</td></tr>');
        $("#link-contacts").dialog("close");
>>>>>>> cee246d3577f783787c4cc4a3cfafe0ec305edd5
        return false;
    });
};

frontlinesms.updateSearchResults = function (results) {

    alert(results)

};