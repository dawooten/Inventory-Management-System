//Derek Wooten - WGU C482 PA

package Views;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ModifyProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    Product product;
    int selectedItem;
    Product theProduct;

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
    private Button modifyProductSearchButton;

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
    private Button modifyProductAddButton;

    @FXML
    private Button modifyProductDeleteButton;

    @FXML
    private Button modifyProductCancelButton;

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
    private Button modifyProductSaveButton;
    
//Add Part
    
    @FXML
    void addPart(MouseEvent event) {

        Part thisPart = partSearchTable.getSelectionModel().getSelectedItem();
        theProduct.setAssociatedParts(thisPart);
        
    }

//Cancel Modifying Product    
    
    @FXML
    void cancelModify(MouseEvent event) throws IOException {
        
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

    @FXML
    void clearTextField(MouseEvent event) {

    }

//Delete Part 
    
    @FXML
    void deletePart(MouseEvent event) {

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        alert1.setTitle("Cancel");
        Optional<ButtonType> result = alert1.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
                   
        theProduct.getAllAssociatedParts().remove(assocPartsTable.getSelectionModel().getSelectedItem());
        }
    }

//Search Box    
    
    @FXML
    void modifyProductSearch(MouseEvent event) {

        String abc = searchTxt.getText();
        partSearchTable.getSelectionModel().select(Part.findPart(abc));
    }

//Save Product   
    
    @FXML
    void saveProduct(MouseEvent event) throws IOException {

        int id = Integer.parseInt(idTxt.getText());
        String name = nameTxt.getText();
        int stock = Integer.parseInt(countTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
       
        ObservableList<Part> associatedParts = FXCollections.observableArrayList();
        associatedParts.addAll(assocPartsTable.getItems());
        
        try {
            
            if (min > max) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR, "Max value less than Min value. Please make Max higher than Min.");
                alert2.showAndWait();
            }
            else {
        
        Product changeProduct = new Product(id, name, price, stock, min, max);
        changeProduct.addAssociatedPart(associatedParts);
        
//        Inventory.getAllProducts().set(selectedItem, changeProduct);

        Inventory.getAllProducts().remove(theProduct);
        Inventory.getAllProducts().add(changeProduct);
            
//            product.setId(id);
//            product.setName(name);
//            product.setStock(stock);
//            product.setPrice(price);
//            product.setMax(max);
//            product.setMin(min);
            
//            Inventory.addProduct(product);
            
             
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Views/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            }
        }
                catch(NumberFormatException abc){
            }
    }

//Get Products from Table
    
    public void getProductsFromTable (Product product, int index) {
        
        theProduct = product;
        selectedItem = index;
        
        if (product instanceof Product) {
            Product newProduct = (Product) product;
            
            this.idTxt.setText(Integer.toString(newProduct.getId()));
            this.nameTxt.setText(newProduct.getName());
            this.countTxt.setText(Integer.toString(newProduct.getStock()));
            this.priceTxt.setText(Double.toString(newProduct.getPrice()));
            this.maxTxt.setText(Integer.toString(newProduct.getMax()));
            this.minTxt.setText(Integer.toString(newProduct.getMin()));
            assocPartsTable.setItems(newProduct.getAllAssociatedParts());
        }
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
