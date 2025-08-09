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
        for (int i = 0; i < prodotti.size(); i++) {
            if (prod.getId() == prodotti.get(i).getId()) {
                prodotti.get(i).setQuantitaDisponibile(prodotti.get(i).getQuantitaDisponibile() + 1);
                return true;
            }
        }
        return false;
    }
    
    // Restituisce indice del prodotto con quell'ID
    public int indice(int id) {
        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    
    // Rimuove prodotto dal carrello
    public void rimuovi(int id) {
        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).getId() == id) {
                prodotti.remove(i);
                break;
            }
        }
    }
    
    // Incrementa quantità di un prodotto specifico
    public void incrementaQuantita(int id) {
        for (ProductBean prodotto : prodotti) {
            if (prodotto.getId() == id) {
                prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() + 1);
                return;
            }
        }
    }
    
    // Decrementa quantità di un prodotto specifico
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
    
    public void deleteProduct(ProductBean prod) {
        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).getId() == prod.getId()) {
                prodotti.remove(i);
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