Progetto TSW
NB: Il sito andrà mostrato in funzione nell’ambiente di produzione, non in quello di sviluppo!

~~completato~~
*work in progress*

1. Il cliente (che sia registrato o non) deve poter inserire prodotti nel carrello, variarne la quantità, rimuoverli dal carrello e svuotare il carrello
2. Il cliente deve potersi registrare, fare il login e logout
3. Il cliente registrato deve poter effettuare l’ordine dei prodotti nel carrello (specificando le informazioni necessarie per la spedizione e il pagamento)
    Una volta confermato l'ordine va svuotato il carrello e deve essere possibile per il cliente registrato visualizzare l'ordine nell'elenco degli ordini da lui effettuati
4. Va prevista la figura dell'amministratore e delle pagine a lui dedicate, accessibili solo dopo autenticazione (è quindi necessario consentire il login e logout dell’amministratore) 
	L’amministratore deve poter inserire, modificare, visualizzare, e cancellare elementi del catalogo
	Visualizzare gli ordini complessivi, dalla data x alla data y, e per cliente
5. Il sito deve essere dinamico, responsive, con persistenza dei dati	
6. ~~Usare il DataSource o DriveManager per connettersi al DB~~
7. *Usare il pattern DAO per incapsulare la logica di persistenza*
8. *Usare il pattern architetturale MVC*
	~~Creare almeno due package: uno per le servlet, chiamato “control”, ed uno per le classi di modello, chiamato “model”~~
    Il codice HTML viene creato esclusivamente dalle JSP (non dalle Servlet)
9. Usare il token nella sessione per il controllo degli accessi nelle servlet e nelle pagine jsp 
	Assicurarsi di aver implementare correttamente il controllo degli accessi!
10. Gestire le sessioni per memorizzare lo stato del carrello
    Si salva l'ordine nel DB dopo l'acquisto
11. Includere nelle pagine JSP l'header/footer (e.g., con la direttiva JSP include)
12. Usare AJAX per scambiare alcune informazioni con il server
13. Controllare i dati immessi nelle form con JavaScript
	I dati delle form vengono inviati al server solo se corretti
	~~Usare le espressioni regolari, laddove necessario, per validare i campi delle form~~
	Fornire i messaggi di errore sia quando l’utente termina l’inserimento di un dato (e.g., change event) sia quando preme il pulsante di submit (e.g., submit event)
	Visualizzare i messaggi di errore modificando il DOM (non usare gli alert!)
14. Separare il codice html/jsp dal codice CSS (usare i fogli di stile esterni) e dal codice JavaScript (usare gli script esterni) e organizzare opportunamente le risorse Web 
	I file JavaScript nella cartella «scripts», le immagini (statiche) in «images» e i fogli di stile in «styles»
15. *Strutturare correttamente il database, ovvero:*
    *Se viene modificato il prezzo di un prodotto dopo l’acquisto di un utente, l'ordine del cliente mantiene il prezzo di acquisto (non il nuovo prezzo)*
    *Se l'amministratore cancella un prodotto, non deve scomparire dagli ordini effettuati di nessun cliente*
16. Usare correttamente Git (ovvero fare commit spesso!)
17. A corredo del progetto va presentato il documento di website design (assicurarsi che sia consistente col sito web presentato)
    Essendo un documento di design, la sua compilazione dovrebbe precedere lo sviluppo del sito web 