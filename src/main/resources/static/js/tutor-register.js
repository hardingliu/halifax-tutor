'use strict';

$(document).ready(function () {
    $("form").submit(function ( event ) {
        event.preventDefault();
        let email = $("#email").val();
        let password = $("#password").val();
        let phoneNumber = $("#phone-number").val();
        let firstName = $("#user-first-name").val();
        let lastName = $("#user-last-name").val();
        let creditCardNumber = $("#user-credit-card").val();
        let expireDate = $("#expire-date").val();
        let securityCode = $("#security-code").val();

        var tutorData = {
                    "email": email,
                    "password": password,
                    "phoneNumber": phoneNumber,
                    "firstName": firstName,
                    "lastName": lastName,
                    "creditCardNumber": creditCardNumber,
                    "expireDate": expireDate,
                    "securityCode": securityCode
        };

        $.ajax({
                    url: "https://csci5308group12devint.azurewebsites.net/tutor",
                    data: tutorData,
                    type: "POST",
                    dataType: "json",
                    ContentType: "application/json; charset=UTF-8"
        }).done(function (json) {

        }).fail(function (xhr, status, errorThrown) {

        });
    });
});