package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CarrelloBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private ArrayList<ProductBean> prodotti;
    
    public CarrelloBean() {
        prodotti = new ArrayList<ProductBean>();
    }
    
    // Somma prezzo totale carrello (con IVA)
    public double sommaPrezzo() {
        double somma = 0;
        for (int i = 0; i < prodotti.size(); i++) {
            somma = somma + prodotti.get(i).getPrezzoConIva() * prodotti.get(i).getQuantitaDisponibile();
        }
        return somma;
    }
    
    // Vede se il prodotto è presente nel carrello
    public boolean isPresente(ProductBean prod) {
        int i = 0;
        boolean trovato = false;
        
        while (i < prodotti.size() && !trovato) {
            if (prod.getId() == prodotti.get(i).getId()) {
                incrementaQuantita(prod);
                trovato = true;
            }
            i++;
        }
        return trovato;
    }
    
    // Restituisce indice del prodotto con quell'ID
    public int indice(int id) {
        int i = 0;
        int trovato = 0;
        
        while (i < this.getProdotti().size()) {
            if (this.getProdotti().get(i).getId() == id) {
                trovato = i;
            }
            i++;
        }
        return trovato;
    }
    
    // Rimuove prodotto dal carrello
    public void rimuovi(int id) {
        int i = 0;
        while (i < this.prodotti.size()) {
            if (this.getProdotti().get(i).getId() == id) {
                this.getProdotti().remove(i);
            }
            i++;
        }
    }
    
   
    public void incrementaQuantita(ProductBean prod) {
        prod.setQuantitaDisponibile(prod.getQuantitaDisponibile() + 1);
    }
    

    public void decrementaQuantita(ProductBean prod) {
        prod.setQuantitaDisponibile(prod.getQuantitaDisponibile() - 1);
    }
    

    public void addProduct(ProductBean prod) {
        prodotti.add(prod);
    }
    
   
    public void deleteProduct(ProductBean prod) {
        for (ProductBean prodotto1 : prodotti) {
            if (prodotto1.getId() == prod.getId()) {
                prodotti.remove(prodotto1);
                break;
            }
        }
    }

    public void svuotaCarrello() {
        prodotti.clear();
    }
    
    
    public ProductBean getItemIndex(int i) {
        return prodotti.get(i);
    }
    
    public int lengthCarrello() {
        return prodotti.size();
    }
    
    public ArrayList<ProductBean> getProdotti() {
        return prodotti;
    }
    
    public boolean isEmpty() {
        return prodotti.isEmpty();
    }
}