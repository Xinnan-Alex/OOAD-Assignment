//ADMIN EDIT PROPERTY INTERFACE CONTROLLER (Admin Feature)
package controller.admin;
import model.*;
import main.*;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

//adminEditPropertySceneController class
public class adminEditPropertySceneController implements Initializable{

    Property propertyToBeEdited;
    Admin admin;

    @FXML
    ComboBox<String> propTypeComboBox,propHiddenStatusComboBox,propRentalStatusComboBox,propOwnerComboBox;

    @FXML
    ComboBox<Integer> propNumofRoomComboBox,propNumofBathroomComboBox;

    @FXML
    TextField propNameTextField,propSizeTextField,propOwnerContactNumTextField,propRentalRateTextField;

    @FXML
    Button confirmButton,backButton;

    @FXML
    TextArea commentsTextBox;

    @FXML
    Label titleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        propTypeComboBox.getItems().addAll(Globals.propertyType);

        propHiddenStatusComboBox.getItems().addAll("true","false");

        propRentalStatusComboBox.getItems().addAll("active", "not Active");

        propOwnerComboBox.getItems().addAll(Globals.LogicModel.getListofPropOwnerName());

        propOwnerContactNumTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));
        
        propSizeTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));

        
        propRentalRateTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));

    }

    public void passPropertyToBeEdited(Property propertypassedin){
        propertyToBeEdited = propertypassedin;
        
        propNameTextField.setText(propertyToBeEdited.getProjectName());
        propSizeTextField.setText(Long.toString(propertyToBeEdited.getPropertySize()));
        propOwnerContactNumTextField.setText(propertyToBeEdited.getContactNum());
        propRentalRateTextField.setText(Long.toString(propertyToBeEdited.getRentalRate()));
        propNumofRoomComboBox.setValue(propertyToBeEdited.getNumofRoom());
        propNumofBathroomComboBox.setValue(propertyToBeEdited.getNumofBathroom());
        propTypeComboBox.setValue(propertyToBeEdited.getPropertyType());
        propHiddenStatusComboBox.setValue(Boolean.toString(propertyToBeEdited.getHiddenStatus()));
        commentsTextBox.setText(propertyToBeEdited.getFacilities());

        titleLabel.setText("Editing a Property with the ID:" + Long.toString(propertyToBeEdited.getPropertyID()));
        
        String rentalStatus = propertyToBeEdited.getRentStatus();

        propRentalStatusComboBox.setValue(rentalStatus);
        
        propOwnerComboBox.setValue(propertyToBeEdited.getPropertyOwner());
    }

    public void confirmButtonHandler() throws IOException{

        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to update in this property?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            String[] selectedPropertyData_Edited = new String[]{propNameTextField.getText(),propSizeTextField.getText(),propRentalRateTextField.getText(),propTypeComboBox.getSelectionModel().getSelectedItem(),
                propOwnerComboBox.getValue(),propOwnerContactNumTextField.getText(),propHiddenStatusComboBox.getValue(),
                propRentalStatusComboBox.getSelectionModel().getSelectedItem()};

            String[] selectedPropertyData_TobeValided  = {propHiddenStatusComboBox.getValue(),propOwnerComboBox.getValue()};

            admin.editSelectedPropertyData(propertyToBeEdited,selectedPropertyData_Edited,selectedPropertyData_TobeValided);

            (new Alert(AlertType.CONFIRMATION,"Property edited!")).showAndWait();
            backButtonHandler();
        }

    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminPropertyListing.fxml"));
        Parent root = loader.load();

        adminPropertyListingController controller =  loader.getController();
        controller.initialiseAdminInfo(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void propOwnerComboBoxHandler(ActionEvent e) {
        propOwnerContactNumTextField.setText(Globals.LogicModel.getUserContactNum(Globals.LogicModel.getUsername(propOwnerComboBox.getValue(),"property owner")));
    
    }

    //Passing in admin object from previous interface(Admin Feature)
    public void passedInAdminObject(Admin a){
        admin = a;
    }
    
}
