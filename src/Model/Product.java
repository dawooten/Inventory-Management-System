//Derek Wooten - WGU C482 PA

package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//New Product Class

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    public Product(int id, String name, double price, int stock, int min, int max) {
        
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    //Setters
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void setMin(int min) {
        this.min = min;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
    //Getters
        
    public int getId() {
        return this.id;
    }
   
    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    //Associated Parts

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public void addAssociatedPart(ObservableList<Part> ap) {
        this.associatedParts = ap;
    }
    
    public boolean deleteAssociatedPart(int partToRemove) {
        int i;
        for (i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i).getPartID() == partToRemove) {
                associatedParts.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void setAssociatedParts (Part part) {
        associatedParts.add(part);
    }
            

    public ObservableList<Part> getAllAssociatedParts() {
        return (ObservableList<Part>) associatedParts;
    }
    
//Search funtion for Product
    
    public static Product findProduct(String findName) {
        for(Product p:Inventory.getAllProducts()) {
            if(p.getName().contains(findName) || new Integer (p.getId()).toString().equals(findName))
                return p;
        }
        return null;
    }

    
}