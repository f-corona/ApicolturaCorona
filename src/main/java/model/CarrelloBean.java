package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CarrelloBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private ArrayList<ProductBean> prodotti;
    
    public CarrelloBean() {
        prodotti = new ArrayList<ProductBean>();
    }
    
    public double sommaPrezzo() {
        double somma = 0;
        for (int i = 0; i < prodotti.size(); i++) {
            somma = somma + prodotti.get(i).getPrezzoConIva() * prodotti.get(i).getQuantitaDisponibile();
        }
        return somma;
    }
    
    public boolean isPresente(ProductBean prod) {
        for (int i = 0; i < prodotti.size(); i++) {
            if (prod.getId() == prodotti.get(i).getId()) {
                return true;
            }
        }
        return false;
    }
    
    public void rimuovi(int id) {
        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).getId() == id) {
                prodotti.remove(i);
                break;
            }
        }
    }
    
    public void incrementaQuantita(int id) {
        for (ProductBean prodotto : prodotti) {
            if (prodotto.getId() == id) {
                prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() + 1);
                return;
            }
        }
    }
    
    public void decrementaQuantita(int id) {
        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).getId() == id) {
                ProductBean prodotto = prodotti.get(i);
                if (prodotto.getQuantitaDisponibile() > 1) {
                    prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() - 1);
                } else {
                    prodotti.remove(i);
                }
                return;
            }
        }
    }
    
    public void addProduct(ProductBean prod) {
        prodotti.add(prod);
    }
    
    public void svuotaCarrello() {
        prodotti.clear();
    }
    
    public ArrayList<ProductBean> getProdotti() {
        return prodotti;
    }
    
    public boolean isEmpty() {
        return prodotti.isEmpty();
    }
    
    public int getQuantitaTotale() {
        int totale = 0;
        for (ProductBean prodotto : prodotti) {
            totale += prodotto.getQuantitaDisponibile();
        }
        return totale;
    }
}