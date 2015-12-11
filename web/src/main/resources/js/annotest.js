/**
 * Created by jcchen on 15-12-10.
 */

jQuery(function($) {

    $('#getusers_btn').click(function() {

        $.post('/api/users').then(function(data) {
            console.log('success');
            console.log(data);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            console.log('error');
            console.log(jqXHR);
            console.log(textStatus);
        });
    });
});