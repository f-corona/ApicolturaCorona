function createXMLHttpRequest() {
    return new XMLHttpRequest();
}

function loadAjaxDoc(url, method, params, cFunction) {
    var request = createXMLHttpRequest();
    if (request) {
        request.onreadystatechange = function() {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    cFunction(this);
                } else {
                    alert("Errore nella richiesta");
                }
            }
        };
        
        if (method.toLowerCase() == "post") {
            request.open("POST", url, true);
            request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            request.setRequestHeader("x-requested-with", "XMLHttpRequest");
            request.send(params);
        }
    }
}

function aggiungiAlCarrello(idProdotto) {
    var params = "action=add&idProdotto=" + idProdotto;
    loadAjaxDoc("CarrelloServlet", "POST", params, handleCarrelloResponse);
}

function rimuoviDalCarrello(idProdotto) {
    var params = "action=remove&idProdotto=" + idProdotto;
    loadAjaxDoc("CarrelloServlet", "POST", params, handleCarrelloResponse);
}

function decrementaQuantita(idProdotto) {
    var params = "action=decrease&idProdotto=" + idProdotto;
    loadAjaxDoc("CarrelloServlet", "POST", params, handleCarrelloResponse);
}

function svuotaCarrello() {
    var params = "action=clear";
    loadAjaxDoc("CarrelloServlet", "POST", params, handleCarrelloResponse);
}

function handleCarrelloResponse(request) {
    var response = JSON.parse(request.responseText);

    if (response.status === "success") {
        aggiornaContatore(response.count);

        if (window.location.href.includes("carrello.jsp")) {
            location.reload();
        }
    }
}

function aggiornaContatore(count) {
    var carrelloLink = document.querySelector('a[href="carrello.jsp"]');
    if (carrelloLink) {
        if (count == 0) {
            carrelloLink.innerHTML = 'Carrello';
        } else {
            carrelloLink.innerHTML = 'Carrello (<span id="carrelloCounter">' + count + '</span>)';
        }
    }
}

function inizializzaContatore() {
    var params = "action=count";
    loadAjaxDoc("CarrelloServlet", "POST", params, function(request) {
        var response = JSON.parse(request.responseText);
        if (response.status === "success") {
            aggiornaContatore(response.count);
        }
    });
}

window.addEventListener('load', inizializzaContatore);