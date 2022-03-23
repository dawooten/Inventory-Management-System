//Derek Wooten - WGU C482 PA

package Main;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Derek
 */
public class InventoryProgram extends Application {

//Launch Program with preset inventory
    
    public InventoryProgram(){
        
        Part part1 = new InHouse(1, "Keyboard", 59.99, 5, 1, 50, 100);
        Part part2 = new InHouse(2, "RAM", 139.99, 7, 1, 50, 101);
        Part part3 = new InHouse(3, "Monitor", 259.99, 2, 1, 50, 103);
        Part part4 = new OutSourced(4, "Mouse", 49.99, 4, 1, 50, "Corsair");
        Part part5 = new OutSourced(5, "Mat", 7.99, 10, 1, 50, "Logitech");
        
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        
        Product product1 = new Product(1, "Desktop", 999.99, 2, 1, 100);
        Product product2 = new Product(2, "Laptop", 1299.99, 4, 1, 30);
        Product product3 = new Product(3, "iPad", 1999.99, 11, 1, 25);
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

//Load Main Screen
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/MainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}