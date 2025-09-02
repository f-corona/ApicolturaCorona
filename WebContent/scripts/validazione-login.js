function mostraErroreLogin(idElemento, messaggio) {
    const elemento = document.getElementById(idElemento);
    if (elemento) {
        elemento.textContent = messaggio;
        elemento.style.display = messaggio ? "block" : "none";
    }
}

function validaEmailLogin(email) {
    if (email === "") return "Inserisci l'email";
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regex.test(email)) return "Email non valida";
    return "";
}

function validaPasswordLogin(password) {
    if (password === "") return "Inserisci la password";
    return "";
}

document.addEventListener('DOMContentLoaded', function() {
    const emailField = document.getElementById("email");
    const passwordField = document.getElementById("password");
    const loginForm = document.getElementById("form-login");

    if (emailField) {
        emailField.addEventListener("change", function() {
            mostraErroreLogin("email-error", validaEmailLogin(this.value.trim()));
        });
    }

    if (passwordField) {
        passwordField.addEventListener("change", function() {
            mostraErroreLogin("password-error", validaPasswordLogin(this.value));
        });
    }

    if (loginForm) {
        loginForm.addEventListener("submit", function(e) {
            const email = document.getElementById("email").value.trim();
            const password = document.getElementById("password").value;

            const erroreEmail = validaEmailLogin(email);
            const errorePassword = validaPasswordLogin(password);

            mostraErroreLogin("email-error", erroreEmail);
            mostraErroreLogin("password-error", errorePassword);

            if (erroreEmail || errorePassword) {
                e.preventDefault();
            }
        });
    }
});
