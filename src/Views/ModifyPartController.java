//Derek Wooten - WGU C482 PA

package Views;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
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
public class ModifyPartController implements Initializable {
    
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private ToggleGroup source;

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
    private Button cancelButton;

    @FXML
    private Button modifyPartSaveButton;

// Cancel Modifying Part

    @FXML
    void cancelModifyPart(MouseEvent event) throws IOException {
        
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

    @FXML
    void idDisabled(MouseEvent event) {

    }

//Save Modified Part    
    
    @FXML
    void saveModifyPart(MouseEvent event) throws IOException {

        int max = Integer.parseInt(maxTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        
        try {
            
            if (min > max) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR, "Max value less than Min value. Please make Max higher than Min.");
                alert2.showAndWait();
            }
        
            else {
                
            if(inHouseRadio.isSelected()) {
                if(update(Integer.parseInt(idTxt.getText()), new InHouse(Integer.parseInt(idTxt.getText()), nameTxt.getText(), Double.parseDouble(priceTxt.getText()), Integer.parseInt(countTxt.getText()), Integer.parseInt(minTxt.getText()), Integer.parseInt(maxTxt.getText()), Integer.parseInt(companyTxt.getText()))))
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Views/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
                }
            else if(outSourcedRadio.isSelected()) {
                if(update(Integer.parseInt(idTxt.getText()), new OutSourced(Integer.parseInt(idTxt.getText()), nameTxt.getText(), Double.parseDouble(priceTxt.getText()), Integer.parseInt(countTxt.getText()), Integer.parseInt(minTxt.getText()), Integer.parseInt(maxTxt.getText()), companyTxt.getText())))
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Views/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
                }
            }
        }
        catch(NumberFormatException abc){
            }
    }

//Setting Radio Button    
    
    @FXML
    void setAddPartFields(ActionEvent event) {
        
        if(inHouseRadio.isSelected()){
            companyLabel.setText("Machine ID");
        }
        if(outSourcedRadio.isSelected()){
            companyLabel.setText("Company Name");
        }
    }

//Get Parts from Table
    
    public void getPartsFromTable (Part part, int index) {   
        
    idTxt.setText(String.valueOf(part.getPartID()));
    nameTxt.setText(part.getName());
    countTxt.setText(String.valueOf(part.getStock()));
    priceTxt.setText(String.valueOf(part.getPrice()));
    maxTxt.setText(String.valueOf(part.getMax()));
    minTxt.setText(String.valueOf(part.getMin()));
    
    if(part instanceof InHouse) {
        InHouse x = (InHouse) part;
        companyTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
        companyLabel.setText("Machine ID");
        inHouseRadio.setSelected(true);
    }
    
    else if(part instanceof OutSourced) {
        OutSourced y = (OutSourced) part;
        companyTxt.setText(((OutSourced) part).getCompanyName());
        outSourcedRadio.setSelected(true);
    }
   
    }
 
   
    public boolean update(int id, Part part) {
        int index = Integer.parseInt(idTxt.getText()) - 1;
        
        for(Part parts : Inventory.getAllParts()) {
            if(part.getPartID() == id) {
                Inventory.getAllParts().set(index, part);
                return true;
            }
        }
        return false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }
        
}
