(function () {
    "use strict"
    let forms = document.querySelectorAll('.needs-validation')
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                let $form = $(this)
                if ($form.attr("action") === "#") {
                    event.preventDefault()
                    event.stopPropagation()

                    $(form).addClass("was-validated")
                    $("#username").removeClass("is-invalid")
                    $("#username ~ .invalid-tooltip").html("至少输入4位长度")

                    if (form.checkValidity()) {
                        register(this)
                    }
                }
            }, false)
        })
})()

function login(form) {
    let $form = $(form)
    $form.attr("action", "/login")
    $form.submit()
}

function register(form) {
    let url = "/user/register/one"
    let data = convertFormToJSON(form)
    data = JSON.stringify(data)
    $.post({
        url: url,
        data: data,
        contentType: "application/json"
    })
        .done(function (result) {
            if (result.violation.code === 0) {
                login(form)
            } else {
                if (result.violation.code === -101) {
                    $(form).removeClass("was-validated")
                    let username = $("#username")
                    username.addClass("is-invalid")
                    $("#username ~ .invalid-tooltip").html(result.violation.message)
                    $(window).scrollTop(username.offset().top)
                }
                console.log(result.violation.message)
            }
        })
        .fail(function (result) {
            console.log("网络或服务器异常 " + result.responseJSON.status)
        })
        .always(function () {
        })
}

function convertFormToJSON(form) {
    return $(form)
        .serializeArray()
        .reduce(function (json, {name, value}) {
            json[name] = value
            return json
        }, {})
}