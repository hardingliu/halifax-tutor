'use strict';

$(document).ready(function () {
    var token = localStorage.getItem("token");
    var objToken = {'token': token};

    if (token === null) {
        alert("Please login. Redirecting to login page.");
        location.href = "../index.html";
    }

    $.ajax({
        url: location.origin + "/admin/setting/access",
        data: JSON.stringify(objToken),
        contentType: "application/json",
        type: "POST",
        dataType: "text"
    }).done(function (data) {
        if (data === "UNAUTHORIZED") {
            alert("You do not have access to this page.");
            window.location.replace("../index.html");
        }
        if (data === "ERROR") {
            window.location.replace("/exception-page.html");
        }
    }).fail(function (xhr, status, errorThrown) {
        //window.location.replace("../index.html");
    });

    $("#logout").click(function () {
        localStorage.removeItem("token");
        alert("Logging out. Redirecting to login page.");
        location.href = "../index.html";
    });

    $("#Cpassword").submit(function (event) {
        event.preventDefault();

        if($("#pwd").val() != $("#Rpwd").val()){
            alert("The two password fields didn't match.")
            return;
        }
        if ($("#pwd").val().length < 8){
            alert("Passwords must contain at least 8 characters");
            return;
        }
        if ($("#pwd").val().toLowerCase() == $("#pwd").val()){
            alert("Passwords must contain at least one capital letter");
            return;
        }

        let password = $("#pwd").val();
        var data = {
            'token': token,
            "password": password
        };

        $.ajax({
            url: location.origin + "/admin/setting/password",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "ERROR") {
                window.location.replace("/exception-page.html");
            }
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });


    $("#Cprice").submit(function (event) {
        event.preventDefault();
        let priceOne = $("#priceOne").val();
        let priceTwo = $("#priceTwo").val();
        let priceThree = $("#priceThree").val();
        let priceFour = $("#priceFour").val();

        var data = {
            'token': token,
            'priceOne': priceOne,
            'priceTwo': priceTwo,
            'priceThree': priceThree,
            'priceFour': priceFour
        };

        $.ajax({
            url: location.origin + "/admin/setting/price",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "ERROR") {
                window.location.replace("/exception-page.html");
            }
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#banStudent").submit(function (event) {
        event.preventDefault();
        let studentID = $("#SbanID").val();

        var data = {
            'token': token,
            'studentID': studentID
        };

        $.ajax({
            url: location.origin + "/admin/setting/ban/student",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "ERROR") {
                window.location.replace("/exception-page.html");
            }
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#unbanStudent").submit(function (event) {
        event.preventDefault();
        let studentID = $("#SunbanID").val();

        var data = {
            'token': token,
            'studentID': studentID
        };

        $.ajax({
            url: location.origin + "/admin/setting/unban/student",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "ERROR") {
                window.location.replace("/exception-page.html");
            }
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });


    $("#banTutor").submit(function (event) {
        event.preventDefault();
        let tutorID = $("#TbanID").val();

        var data = {
            'token': token,
            'tutorID': tutorID
        };

        $.ajax({
            url: location.origin + "/admin/setting/ban/tutor",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "ERROR") {
                window.location.replace("/exception-page.html");
            }
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#unbanTutor").submit(function (event) {
        event.preventDefault();
        let tutorID = $("#TunbanID").val();

        var data = {
            'token': token,
            'tutorID': tutorID
        };

        $.ajax({
            url: location.origin + "/admin/setting/unban/tutor",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "ERROR") {
                window.location.replace("/exception-page.html");
            }
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#findStudent").submit(function (event) {
        event.preventDefault();
        let email = $("#SEmail").val();

        var data = {
            'token': token,
            'email': email
        };

        $.ajax({
            url: location.origin + "/admin/setting/find/student",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (data) {
            if(data.studentID < 0){
                $("#studentReviews").html("<p>Student Email Not Found</p>");
            }
            else{
                $("#studentReviews").html("");
                var result =    "<table><tr><td>ID: </td><td>" + data.studentID + "</td></tr>" +
                    "<tr><td>Email: </td><td>" + data.studentEmail + "</td></tr>" +
                    "<tr><td>ReviewID</td><td>Review</td></tr>";

                if(data.reviews != null){
                    for (var i = 0; i < data.reviews.length; i++) {
                        result += "<tr><td>" + data.reviews[i].reviewID + "</td>" +
                            "<td>" + data.reviews[i].reviewText + "</td></tr>";
                    }
                }
                result += "</table>";
                $("#studentReviews").html(result);
            }
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#findTutor").submit(function (event) {
        event.preventDefault();
        let email = $("#TEmail").val();

        var data = {
            'token': token,
            'email': email
        };

        $.ajax({
            url: location.origin + "/admin/setting/find/tutor",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (data) {
            if(data.tutorID < 0){
                $("#tutorReviews").html("<tr><td>Tutor Email Not Found</td></tr>");
            }
            else{
                $("#tutorReviews").html("");
                var result =    "<table><tr><td>ID: </td><td>" + data.tutorID + "</td></tr>" +
                    "<tr><td>Email: </td><td>" + data.tutorEmail + "</td></tr>" +
                    "<tr><td>ReviewID</td><td>Review</td></tr>";

                if(data.reviews != null){
                    for (var i = 0; i < data.reviews.length; i++) {
                        result += "<tr><td>" + data.reviews[i].reviewID + "</td>" +
                            "<td>" + data.reviews[i].reviewText + "</td></tr>";
                    }
                }
                result += "</table>";
                $("#tutorReviews").html(result);
            }
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#deleteReview").submit(function (event) {
        event.preventDefault();
        let reviewID = $("#ReviewID").val();

        var data = {
            'token': token,
            'reviewID': reviewID
        };

        $.ajax({
            url: location.origin + "/admin/setting/review/delete",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "ERROR") {
                window.location.replace("/exception-page.html");
            }
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

});