package model;

import java.io.Serializable;

public class ProductBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private double iva;
    private int quantitaDisponibile;
    private boolean cancellato;
    private String immagineURL;
    private int idCategoria;
    
    public ProductBean() {}
    
    public ProductBean(int id, String nome, String descrizione, double prezzo, 
                      double iva, int quantitaDisponibile, boolean cancellato, 
                      String immagineURL, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.iva = iva;
        this.quantitaDisponibile = quantitaDisponibile;
        this.cancellato = cancellato;
        this.immagineURL = immagineURL;
        this.idCategoria = idCategoria;
    }
    
    // Getter e setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
    
    public double getPrezzoConIva() {
        return prezzo + (prezzo * (iva / 100));
    }
}