import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class adminEditPropertySceneController implements Initializable{

    Property propertyToBeEdited;
    Admin admin;

    @FXML
    ComboBox<String> propTypeComboBox,propHiddenStatusComboBox,propRentalStatusComboBox,propOwnerComboBox;

    @FXML
    TextField propNameTextField,propSizeTextField,propOwnerContactNumTextField,propRentalRateTextField;

    @FXML
    Button confirmButton,backButton;

    @FXML
    Label propertyID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        propTypeComboBox.getItems().addAll(Globals.propertyType);

        propHiddenStatusComboBox.getItems().addAll("true","false");

        propRentalStatusComboBox.getItems().addAll("active", "not Active");

        propOwnerComboBox.getItems().addAll(Globals.LogicModel.getListofPropOwnerName());

    }

    public void passPropertyToBeEdited(Property propertypassedin){
        propertyToBeEdited = propertypassedin;
        
        propNameTextField.setText(propertyToBeEdited.getProjectName());
        propSizeTextField.setText(Long.toString(propertyToBeEdited.getPropertySize()));
        propOwnerContactNumTextField.setText(propertyToBeEdited.getContactNum());
        propRentalRateTextField.setText(Long.toString(propertyToBeEdited.getRentalRate()));
        propTypeComboBox.setValue(propertyToBeEdited.getPropertyType());
        propHiddenStatusComboBox.setValue(Boolean.toString(propertyToBeEdited.getHiddenStatus()));
        propertyID.setText(Long.toString(propertyToBeEdited.getPropertyID()));
        
        String rentalStatus;
        if (propertyToBeEdited.getRentStatus()){
            rentalStatus = "active";
        }
        else{
            rentalStatus = "not Active";
        }

        propRentalStatusComboBox.setValue(rentalStatus);
        
        propOwnerComboBox.setValue(propertyToBeEdited.getPropertyOwner());
    }

    public void confirmButtonHandler(){

    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminPropertyListing.fxml"));
        Parent root = loader.load();

        adminPropertyListingController controller =  loader.getController();
        controller.initialiseAdminInfo(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void propOwnerComboBoxHandler(ActionEvent e) {
        propOwnerContactNumTextField.setText(Globals.LogicModel.getUserContactNum(Globals.LogicModel.getUsername(propOwnerComboBox.getValue())));
    
    }

    public void passedInAdminObject(Admin a){
        admin = a;
    }
    
}
