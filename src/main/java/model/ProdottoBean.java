package model;

import java.io.Serializable;

public class ProdottoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idProdotto;
    private String nome;
    private String descrizione;
    private double prezzo;
    private double iva; // NUOVO CAMPO
    private int quantitaDisponibile;
    private boolean cancellato; // NUOVO CAMPO
    private String immagineURL; // RINOMINATO
    private int idCategoria;

    public ProdottoBean() {
        // Costruttore vuoto
    }

    // GETTER E SETTER

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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public int getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void setQuantitaDisponibile(int quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public boolean isCancellato() {
        return cancellato;
    }

    public void setCancellato(boolean cancellato) {
        this.cancellato = cancellato;
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

    @Override
    public String toString() {
        return "ProdottoBean [idProdotto=" + idProdotto + ", nome=" + nome + ", descrizione=" + descrizione
                + ", prezzo=" + prezzo + ", iva=" + iva + ", quantitaDisponibile=" + quantitaDisponibile
                + ", cancellato=" + cancellato + ", immagineURL=" + immagineURL + ", idCategoria=" + idCategoria + "]";
    }
}