package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nome;
    private String descrizione;
    private BigDecimal prezzo;
    private BigDecimal iva;
    private int quantitaDisponibile;
    private boolean cancellato;
    private String immagineURL;
    private int idCategoria;
    
    public ProductBean() {}
    
    public ProductBean(int id, String nome, String descrizione, BigDecimal prezzo, 
                      BigDecimal iva, int quantitaDisponibile, boolean cancellato, 
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
    
    public BigDecimal getPrezzo() {
        return prezzo;
    }
    
    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
    
    public BigDecimal getIva() {
        return iva;
    }
    public void setIva(BigDecimal iva) {
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
    
    public BigDecimal getPrezzoConIva() {
        if (prezzo == null || iva == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal moltiplicatore = iva.divide(new BigDecimal("100"))
                                      .add(BigDecimal.ONE);
        return prezzo.multiply(moltiplicatore)
                     .setScale(2, RoundingMode.HALF_UP);
    }
}