//Derek Wooten - WGU C482 PA

package Model;

//New Part Class

public abstract class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    
    public Part(int id, String name, double price, int stock, int min, int max) {
        
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
    
    public int getPartID() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return price;
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
    
//Used for search functions
    
    public static Part findPart(String findName) {
        for(Part p:Inventory.getAllParts()){
            if(p.getName().contains(findName) || new Integer (p.getPartID()).toString().equals(findName))
                return p;
        }
        return null;
    }
}