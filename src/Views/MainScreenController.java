//Derek Wooten - WGU C482 PA

package Views;

import Model.Inventory;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Model.Part;
import Model.Product;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Derek
 */
public class MainScreenController implements Initializable {
    
    Stage stage;
    Parent scene;

       
    @FXML
    private AnchorPane MainScreen;

    @FXML
    private Label mainTitleLabel;

    @FXML
    private Button exitMainButton;

    @FXML
    private TextField partSearchBox;

    @FXML
    private Button addPartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partCountColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private Button searchPartButton;

    @FXML
    private TextField productSearchBox;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Button addProductButton;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productCountColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private Button searchProductButton;

//Add Part Window
    
    @FXML
    void addPart(MouseEvent event) throws IOException {
                   
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();  
    }

//Add Product Window    
    
    @FXML
    void addProduct(MouseEvent event) throws IOException {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

//Delete Part button
    
    @FXML
    void deletePart(MouseEvent event) {
        
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Part?");
        alert1.setTitle("Delete");
        Optional<ButtonType> result = alert1.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
        Part part = partsTable.getSelectionModel().getSelectedItem();
        ObservableList<Part> allParts, singlePart;
            allParts = partsTable.getItems();
            singlePart = partsTable.getSelectionModel().getSelectedItems();
            singlePart.forEach(allParts::remove); 
        }
    }

//Delete Product button    
    
    @FXML
    void deleteProduct(MouseEvent event) {
        
        
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Product?");
        alert2.setTitle("Delete");
        Optional<ButtonType> result = alert2.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            
        Product product = productsTable.getSelectionModel().getSelectedItem();
        ObservableList<Product> allProducts, singlePart;
            allProducts = productsTable.getItems();
            singlePart = productsTable.getSelectionModel().getSelectedItems();
            singlePart.forEach(allProducts::remove);
        }
    }

//Exit Program    
    
    @FXML
    void exitProgramButton(MouseEvent event) {
        
        Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        alert3.setTitle("Exit");
        Optional<ButtonType> result = alert3.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
        System.exit(0);
        }
    }

//Modify Part Window    
    
    @FXML
    void modifyPart(MouseEvent event) throws IOException {
        
        Stage stage;
        Parent root;
        
        stage = (Stage) modifyPartButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ModifyPart.fxml"));
        root = loader.load();
        ModifyPartController controller = loader.getController();
        Part part = partsTable.getSelectionModel().getSelectedItem();
        int index = partsTable.getSelectionModel().getSelectedIndex();
        
        if (part != null) {
            
            controller.getPartsFromTable(part, index);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//Modify Product Window
    
    @FXML
    void modifyProduct(MouseEvent event) throws IOException {
        
        Stage stage;
        Parent root;
        
        stage = (Stage) modifyProductButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ModifyProduct.fxml"));
        root = loader.load();
        ModifyProductController controller = loader.getController();
        Product product = productsTable.getSelectionModel().getSelectedItem();
        int index = productsTable.getSelectionModel().getSelectedIndex();
        
        if (product != null) {
            
            controller.getProductsFromTable(product, index);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//Search Part ID    
    
    @FXML
    void searchForPart(MouseEvent event) {                                  
        
        String abc = partSearchBox.getText();
        partsTable.getSelectionModel().select(Part.findPart(abc));
        
    }

//Search Product ID    
    
    @FXML
    void searchForProduct(MouseEvent event) {

        String xyz = productSearchBox.getText();
        productsTable.getSelectionModel().select(Product.findProduct(xyz));
        
    }

// Load Tables with Inventory from Parts and Product 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        partsTable.setItems(Inventory.getAllParts());
        
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partCountColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productsTable.setItems(Inventory.getAllProducts());
        
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCountColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}