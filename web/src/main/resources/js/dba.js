/**
 * Created by jcchen on 15-11-28.
 */

function init_csrf_token() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        console.log(header + ": " + token);
        xhr.setRequestHeader(header, token);
    });
}

function out(txt) {
    $("#msg").html(txt);
}

jQuery(function($) {
    init_csrf_token();

    $("#ajaxLogin").click(function() {
        $.post("/login", {
            username: $("#username").val(),
            password: $("#password").val()
        }).done(function(data, textStatus, jqXhr) {
            console.log("done: "+data);
            out("login success! "+JSON.stringify(data));
        }).fail(function(err) {
            console.log("fail");
            console.log(err);
            var rsp = err.responseJSON;
            out("login failed! [" + rsp.status+"][" + rsp.error + "]["+ rsp.message + "]");
        });
    });

    $("#ajaxLogout").click(function() {
        $.post("/logout", {})
            .done(function(data, textStatus, jqXhr) {
                console.log("done: "+data);
                out(data);
            }).fail(function(err) {
                console.log("logout failed.");
                console.log(err);
                out(err);
            });
    });
});