'use strict';

$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();
        let email = $("#email").val();

        var studentData = {
            "email": email
        };


        if (document.getElementById('student').checked){
            $.ajax({

                url: "http://localhost:8080/studentforgotpassword",
                data: JSON.stringify(studentData),
                contentType: "application/json",
                type: "POST",
                dataType: "text"
            }).done(function (text) {
                if (text === "verification email sent") {
                    alert("Verification email has been sent!");
                } else {
                    alert("Something went wrong: " + text);
                }
            }).fail(function (xhr, status, errorThrown) {

            });
        }
        else{
            $.ajax({
                url: "http://localhost:8080/tutorforgotpassword",
                data: JSON.stringify(studentData),
                contentType: "application/json",
                type: "POST",
                dataType: "text"
            }).done(function (text) {
                if (text === "verification email sent") {
                    alert("Verification email has been sent!");
                } else {
                    alert("Something went wrong: " + text);
                }
            }).fail(function (xhr, status, errorThrown) {

            });
        }


    });

});
