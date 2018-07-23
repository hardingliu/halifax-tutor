'use strict';

$(document).ready(function () {
    var token = localStorage.getItem("token");
    var objToken = {'token': token};

    $(".remail").hide();
    $.ajax({
        url: location.origin + "/student/setting/access",
        data: JSON.stringify(objToken),
        contentType: "application/json",
        type: "POST",
        dataType: "text"
    }).done(function (data) {
        if (data === "UNAUTHORIZED") {
            alert("You do not have access to this page.");
            window.location.replace("../index.html");
        }
    }).fail(function (xhr, status, errorThrown) {
        //window.location.replace("../index.html");
    });

    $.ajax({
        url: location.origin + "/student/setting/activation",
        data: JSON.stringify(objToken),
        contentType: "application/json",
        type: "POST",
        dataType: "text"
    }).done(function (data) {
        if (data === "NOT_ACTIVE") {
            alert("Please, activate your account.");
            $(".remail").show();
        }
        else {
            $(".remail").hide();
        }
    }).fail(function (xhr, status, errorThrown) {
        //window.location.replace("../index.html");
        console.log("asdasd");
    });

    $("#logout").click(function (event) {
        event.preventDefault();
        localStorage.removeItem("token");
        window.location.replace("../index.html");
    });

    $("#Cpassword").submit(function (event) {
        event.preventDefault();

        if ($("#pwd").val() != $("#Rpwd").val()) {
            alert("The two password fields didn't match.")
            return;
        }

        let password = $("#pwd").val();
        var data = {
            'token': token,
            "password": password
        };

        $.ajax({
            url: location.origin + "/student/setting/password",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            //
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#Cemail").submit(function (event) {
        event.preventDefault();

        let email = $("#email").val();
        var data = {
            'token': token,
            "email": email
        };

        $.ajax({
            url: location.origin + "/student/setting/email",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#Cphone").submit(function (event) {
        event.preventDefault();

        let phone = $("#phone").val();
        var data = {
            'token': token,
            "phone": phone
        };

        $.ajax({
            url: location.origin + "/student/setting/phone",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#RemailBtn").submit(function (event) {
        event.preventDefault();

        var data = {
            'token': token
        }

        $.ajax({
            url: location.origin + "/student/setting/reactivation",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });
});