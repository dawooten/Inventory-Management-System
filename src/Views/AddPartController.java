//Derek Wooten - WGU C482 PA

package Views;


import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Derek
 */
public class AddPartController implements Initializable {
    
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private ToggleGroup addPartToggleGroup;

    @FXML
    private RadioButton outSourcedRadio;

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
    private TextField companyTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private Label companyLabel;

    @FXML
    private Button cancelAddPartButton;

    @FXML
    private Button saveButton;

//Cancel Adding Part
    
    @FXML
    void cancelAddPart(MouseEvent event) throws IOException {
        
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

//Setting the companyLabel dependent on radio button selected    
    
    @FXML
    void setAddPartFields(ActionEvent event) {
        
        if(inHouseRadio.isSelected()){
            companyLabel.setText("Machine ID");
        }
        if(outSourcedRadio.isSelected()){
            companyLabel.setText("Company Name");
        }
    }

//Saving Part
    
    @FXML
    void saveAddPart(MouseEvent event) throws IOException {

        int id = Integer.parseInt(idTxt.getText());
        String name = nameTxt.getText();
        int stock = Integer.parseInt(countTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        
        int machineId = 0;
        String companyName = "";
        
        try {
            
            if (min > max) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR, "Max value less than Min value. Please make Max higher than Min.");
                alert2.showAndWait();
            }
        
            else {
                   
                if (addPartToggleGroup.getSelectedToggle().equals(inHouseRadio)) {
                machineId = Integer.parseInt(companyTxt.getText());
                Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));}
        
                else {
                companyName = companyTxt.getText();
                Inventory.addPart(new OutSourced(id, name, price, stock, min, max, companyName));
                }
            
            
                       
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
                }
        }
        
        catch(NumberFormatException abc){
            }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}
