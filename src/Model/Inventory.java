//Derek Wooten - WGU C482 PA

package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//New Inventory Class

public class Inventory {

//Lists Start
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
     
//Add Part Method
    
    public static void addPart(Part newPart) {
        allParts.add(newPart);
          
    }
    
//Add Product Method
    
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
        
    }
    
//Delete Part Method
    
    public static boolean deletePart(int selectedPart) {
        for(Part p : allParts) {
            if (p.getPartID() == selectedPart) {
                allParts.remove(p);
                return true;
            }
        }
        return false;
    }
    
    //Getters
    public static ObservableList<Part> getAllParts() {
        return allParts;
    
    }
    
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    
    }
}