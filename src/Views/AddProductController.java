//Derek Wooten - WGU C482 PA

package Views;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Derek
 */
public class AddProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    Product product;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField countTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private Button addProductSearchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableView<Part> partSearchTable;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partCountColumn;
    
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private Button addProductAddButton;

    @FXML
    private Button addProductDeleteButton;

    @FXML
    private TableView<Part> assocPartsTable;

    @FXML
    private TableColumn<Part, Integer> assocPartIDColumn;

    @FXML
    private TableColumn<Part, String> assocPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> assocPartCountColumn;
    
    @FXML
    private TableColumn<Part, Double> assocPartPriceColumn;

    @FXML
    private Button addProductSaveButton;

    @FXML
    private Button addProductCancelButton;

//Add Part
    
    @FXML
    void addPart(MouseEvent event) {

        Part thisPart = partSearchTable.getSelectionModel().getSelectedItem();
        product.setAssociatedParts(thisPart);
    }

//Cancel Add Product Window    
    
    @FXML
    void cancelAddProduct(MouseEvent event) throws IOException {
        
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        alert1.setTitle("Cancel");
        Optional<ButtonType> result = alert1.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        }
    }

//    @FXML
//    void clearField(MouseEvent event) {
//
//    }
//
//    @FXML
//    void clearTextField(MouseEvent event) {
//
//    }

//Delete Part    
    
    @FXML
    void deletePart(MouseEvent event) {
        
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?");
        alert2.setTitle("Delete");
        Optional<ButtonType> result = alert2.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

        Part part = assocPartsTable.getSelectionModel().getSelectedItem();
        product.getAllAssociatedParts().remove(assocPartsTable.getSelectionModel().getSelectedItem());
        }
        
    }

//Save Product    
    
    @FXML
    void saveAddProduct(MouseEvent event) throws IOException {

        for(Product product : Inventory.getAllProducts());
        
        int id = Integer.parseInt(idTxt.getText());
        String name = nameTxt.getText();
        int stock = Integer.parseInt(countTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        
        try {
            
            if (min > max) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR, "Max value less than Min value. Please make Max higher than Min.");
                alert2.showAndWait();
            }
            
            else {
            
            product.setId(id);
            product.setName(name);
            product.setStock(stock);
            product.setPrice(price);
            product.setMax(max);
            product.setMin(min);
            
            Inventory.addProduct(product);
            
             
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Views/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            }
        }
        catch(NumberFormatException e){}  
    }

//Search Box    
    
    @FXML
    void searchForPart(MouseEvent event) {

        String abc = searchTxt.getText();
        partSearchTable.getSelectionModel().select(Part.findPart(abc));
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partSearchTable.setItems(Inventory.getAllParts());
        
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partCountColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        product = new Product(0, null, 0.0, 0, 0, 0);
        assocPartsTable.setItems(product.getAllAssociatedParts());
        
        assocPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        assocPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartCountColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        
        
        
    } 
}
