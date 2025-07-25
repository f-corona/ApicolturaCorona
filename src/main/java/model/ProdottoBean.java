package model;


import java.io.Serializable;


public class ProdottoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // === Attributi principali ===
    private int idProdotto;
    private String nome;
    private String descrizione;
    private double prezzo;               // Prezzo netto (senza IVA)
    private int quantitaDisponibile;
    private String immagineURL;
    private int idCategoria;

    // === Attributo IVA dinamico (in percentuale, es: 10.0) ===
    private double iva;                  // Campo ora persistito anche nel database

    // === Costruttore vuoto ===
    public ProdottoBean() {
    }

    // === Costruttore completo ===
    public ProdottoBean(int idProdotto, String nome, String descrizione, double prezzo,
                        int quantitaDisponibile, String immagineURL, int idCategoria, double iva) {
        this.idProdotto = idProdotto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantitaDisponibile = quantitaDisponibile;
        this.immagineURL = immagineURL;
        this.idCategoria = idCategoria;
        this.iva = iva;
    }

    // === Getter e Setter ===
    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    // Prezzo netto (senza IVA)
    public double getPrezzoNetto() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    // Prezzo lordo (con IVA)
    public double getPrezzo() {
        return prezzo + (prezzo * (iva / 100));
    }

    public int getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void setQuantitaDisponibile(int quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public String getImmagineURL() {
        return immagineURL;
    }

    public void setImmagineURL(String immagineURL) {
        this.immagineURL = immagineURL;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public double getIVA() {
        return iva;
    }

    public void setIVA(double iva) {
        this.iva = iva;
    }

    // === toString ===
    @Override
    public String toString() {
        return "ProdottoBean{" +
                "idProdotto=" + idProdotto +
                ", nome='" + nome + '\'' +
                ", prezzoNetto=" + prezzo +
                ", IVA=" + iva +
                ", prezzoIvato=" + getPrezzo() +
                ", categoria=" + idCategoria +
                '}';
    }

}
