/**
 * Created by jcchen on 15-12-8.
 */

jQuery(function($) {

    // csrf
    function init_csrf_token() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            console.log(header + ": " + token);
            xhr.setRequestHeader(header, token);
        });
    }
    init_csrf_token();

    // file upload
    var files;

    $("input[type=file]").on("change", function(event) {
        files = event.target.files;
    });

    $("#ajaxUpload").on("click", function() {
        console.log(files.length);
        var data = new FormData();
        data.append("name", $("#name").val());
        data.append("file", files[0]);

        //$.each(files, function(key, value) {
        //    data.append(key, value);
        //});

        $.ajax({
            url: '/upload',
            type: 'post',
            data: data,
            cache: false,
            //dataType: 'json',
            processData: false,
            contentType: false,
            success: function(data, textStatus, jqXHR) {
                console.log(data);
                console.log(textStatus);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log('ERRORS: '+textStatus);
            }
        });
    });
});