$(function() {
    $("#autocomplete-prductname").autocomplete({
        source: function( req, res ) {
            $.ajax({
                url: "https://maaya-challenge.appspot.com/suggest?" + encodeURIComponent(req.term),
                dataType: "json",
                success: function( data ) {
                    res(data);
                }
            });
        },
        autoFocus: true,
        delay: 100,
        minLength: 1 /**autocompleteするmin文字数*/
    });

});