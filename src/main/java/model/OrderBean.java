package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.math.BigDecimal;

public class OrderBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idOrdine;
    private int idUtente;
    private Timestamp dataOrdine;
    private String stato;
    private BigDecimal totale;
    private ArrayList<ProductBean> prodotti;
    
    public OrderBean() {
        super();
    }
    
    public int getIdOrdine() {
        return idOrdine;
    }
    
    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }
    
    public int getIdUtente() {
        return idUtente;
    }
    
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
    
    public Timestamp getDataOrdine() {
        return dataOrdine;
    }
    
    public void setDataOrdine(Timestamp dataOrdine) {
        this.dataOrdine = dataOrdine;
    }
    
    public String getStato() {
        return stato;
    }
    
    public void setStato(String stato) {
        this.stato = stato;
    }
    
    public BigDecimal getTotale() {
        return totale;
    }
    
    public void setTotale(BigDecimal totale) {
        this.totale = totale;
    }
    
    public ArrayList<ProductBean> getProdotti() {
        return prodotti;
    }
    
    public void setProdotti(ArrayList<ProductBean> prodotti) {
        this.prodotti = prodotti;
    }
}