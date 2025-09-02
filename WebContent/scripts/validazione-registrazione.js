function mostraErrore(idElemento, messaggio) {
    const elemento = document.getElementById(idElemento);
    if (elemento) {
        elemento.textContent = messaggio;
        elemento.style.display = messaggio ? "block" : "none";
    }
}

function validaEmail(email) {
    if (email === "") return "Campo obbligatorio";
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regex.test(email)) return "Campo non valido";
    return "";
}

function validaPassword(password) {
    if (password === "") return "Campo obbligatorio";
    if (password.length < 8) return "La password deve essere di almeno 8 caratteri";
    if (password.length > 50) return "La password deve essere di massimo 50 caratteri";
    const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%* #+=\(\)\^?&])[A-Za-z\d$@$!%* #+=\(\)\^?&]{8,50}$/;
    if (!regex.test(password)) return "La password deve contenere almeno una lettera, un numero e un carattere speciale";
    return "";
}

function validaConfermaPassword(password, confermaPassword) {
    if (confermaPassword === "") return "Riscrivi la password";
    if (password !== confermaPassword) return "Le password non coincidono";
    return "";
}

function validaNome(nome) {
    if (nome === "") return "Campo obbligatorio";
    const regex = /^[a-zA-ZÀÁÂÄÈÉÊËÌÍÎÏÒÓÔÖÙÚÛÜàáâäèéêëìíîïòóôöùúûü\s]+$/;
    if (nome.length < 2 || !regex.test(nome)) return  "Campo non valido";
    return "";
}

function validaTelefono(telefono) {
    if (telefono === "") return "";
    const regex = /^[0-9]{10}$/;
    if (!regex.test(telefono)) return "Campo non valido";
    return "";
}

function validaIndirizzo(indirizzo) {
    if (indirizzo === "") return "Campo obbligatorio";
    if (indirizzo.length < 5) return "Campo non valido";
    return "";
}

function validaCap(cap) {
    if (cap === "") return "Campo obbligatorio";
    const regex = /^[0-9]{5}$/;
    if (!regex.test(cap)) return "Campo non valido";
    return "";
}

function validaCitta(citta) {
    if (citta === "") return "Campoo obbligatorio";
    const regex = /^[a-zA-ZÀÁÂÄÈÉÊËÌÍÎÏÒÓÔÖÙÚÛÜàáâäèéêëìíîïòóôöùúûü\s]+$/;
    if (!regex.test(citta) || citta.length < 2) return "Campo non valido";
    return "";
}

function validaProvincia(provincia) {
    if (provincia === "") return "Campo obbligatorio";
    const provinceItaliane = [
        "AG", "AL", "AN", "AO", "AP", "AQ", "AR", "AT", "AV", "BA", "BG", "BI", "BL", "BN", "BO",
        "BR", "BS", "BT", "BZ", "CA", "CB", "CE", "CH", "CL", "CN", "CO", "CR", "CS", "CT", "CZ",
        "EN", "FC", "FE", "FG", "FI", "FM", "FR", "GE", "GO", "GR", "IM", "IS", "KR", "LC", "LE",
        "LI", "LO", "LT", "LU", "MB", "MC", "ME", "MI", "MN", "MO", "MS", "MT", "NA", "NO", "NU",
        "OR", "PA", "PC", "PD", "PE", "PG", "PI", "PN", "PO", "PR", "PT", "PU", "PV", "PZ", "RA",
        "RC", "RE", "RG", "RI", "RM", "RN", "RO", "SA", "SI", "SO", "SP", "SR", "SS", "SU", "SV",
        "TA", "TE", "TN", "TO", "TP", "TR", "TS", "TV", "UD", "VA", "VB", "VC", "VE", "VI", "VR",
        "VT", "VV"
    ];
    if (!provinceItaliane.includes(provincia)) return "Campo non valido";
    return "";
}

function validaForm(formId) {
    const form = document.getElementById(formId);
    if (!form) return false;

    let errori = 0;
    const inputs = form.querySelectorAll('input[required]');

    inputs.forEach(function(input) {
        let errore = "";
        const valore = input.value.trim();
        const tipo = input.type;
        const name = input.name;

        switch(tipo) {
            case "email":
                errore = validaEmail(valore);
                break;
            case "password":
                if (name === "confermaPassword") {
                    const password = form.querySelector('input[name="password"]').value;
                    errore = validaConfermaPassword(password, valore);
                } else {
                    errore = validaPassword(valore);
                }
                break;
            case "tel":
                errore = validaTelefono(valore);
                break;
            case "text":
                if (name === "nome" || name === "cognome") {
                    errore = validaNome(valore, name.charAt(0).toUpperCase() + name.slice(1));
                } else if (name === "cittaSpedizione") {
                    errore = validaCitta(valore);
                } else if (name === "capSpedizione") {
                    errore = validaCap(valore);
                } else if (name === "provinciaSpedizione") {
                    errore = validaProvincia(valore);
                } else if (name === "indirizzoSpedizione") {
                    errore = validaIndirizzo(valore);
                }
                break;
        }

        mostraErrore(`${name}-error`, errore);
        if (errore) errori++;
    });

    return errori === 0;
}
