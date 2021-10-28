//Admin ADDING PEROPERTY INTERFACE CONTROLLER (Admin Feature)
package controller.propertyowner;
import model.*;
import main.*;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//JAVAFX IMPORTS
import javafx.scene.control.TextFormatter;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;


//adminAddPropertySceneController class
public class propertyOwnerAddPropertySceneController implements Initializable{

    propertyOwner owner;

    @FXML
    ComboBox<String> propTypeComboBox,propHiddenStatusComboBox,propRentalStatusComboBox,propOwnerComboBox;
    
    @FXML
    ComboBox<Integer> propNumofRoomComboBox,propNumofBathroomComboBox;

    @FXML
    TextField propNameTextField,propSizeTextField,propOwnerContactNumTextField,propRentalRateTextField;

    @FXML
    Button confirmButton,clearButton,backButton;

    @FXML
    TextArea commentsTextBox;

    @FXML
    Label titleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titleLabel.setText("Adding a Property: Please complete the information below");

        //Setting the propTypeComboBox   "unspecified", and populate it with list of property type
        propTypeComboBox.getItems().clear();
        propTypeComboBox.setValue("Unspecified");
        propTypeComboBox.getItems().addAll(Globals.propertyType);

        //Setting the propHiddenStatusComboBox into a blank string, and populate it with "true" and "not false" hidden status choices
        propHiddenStatusComboBox.getItems().clear();
        propHiddenStatusComboBox.setValue("");
        propHiddenStatusComboBox.getItems().addAll("true","false");

        //Setting the propRentalStatusComboBox into a blank string, and populate it with "active" and "not Active" rental status choices
        propRentalStatusComboBox.getItems().clear();
        propRentalStatusComboBox.setValue("");
        propRentalStatusComboBox.getItems().addAll("active", "not Active");

        //Setting the propOwnerComboBox value into a blank string, and populate it with list of property owner
        propOwnerComboBox.getItems().clear();
        propOwnerComboBox.setValue("");
        propOwnerComboBox.getItems().addAll(Globals.LogicModel.getListofPropOwnerName());

        //Setting the propNumofRoomComboBox value to the default value "1", and populate it with the range of 100
        propNumofRoomComboBox.getItems().clear();
        propNumofRoomComboBox.setValue(1);
        propNumofRoomComboBox.getItems().setAll(
            IntStream.rangeClosed(1,100).boxed().collect(Collectors.toList())
        );  

        //Setting the propNumofBathroomComboBox value to the default value "1", and populate it with the range of 100
        propNumofBathroomComboBox.getItems().clear();
        propNumofBathroomComboBox.setValue(1);
        propNumofBathroomComboBox.getItems().setAll(
            IntStream.rangeClosed(1,100).boxed().collect(Collectors.toList())
        );  

        //Limitting propOwnerContactNumTextField to only accept numbers
        propOwnerContactNumTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));
        
        //Limitting propSizeTextField to only accept numbers
        propSizeTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));

        //Limitting propRentalRateTextField to only accept numbers
        propRentalRateTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));
        
    }

    public void propOwnerComboBoxHandler(ActionEvent e) {
        propOwnerContactNumTextField.setText(Globals.LogicModel.getUserContactNum(Globals.LogicModel.getUsername(propOwnerComboBox.getValue())));
    
    }

    public void confirmButtonHandler() throws IOException{
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to add in this property?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
             //Extract info from TextFields
            String[] propInfoList_tobeAdded = new String[]{propNameTextField.getText(),propSizeTextField.getText(),propRentalRateTextField.getText(),propTypeComboBox.getSelectionModel().getSelectedItem(),
                propOwnerComboBox.getValue(),propOwnerContactNumTextField.getText(),Integer.toString(propNumofRoomComboBox.getValue()),Integer.toString(propNumofBathroomComboBox.getValue()),
                commentsTextBox.getText(),propHiddenStatusComboBox.getValue(),propRentalStatusComboBox.getSelectionModel().getSelectedItem()};
            String[] propInfoList_TobeValided = {propHiddenStatusComboBox.getValue(),propOwnerComboBox.getValue()};

            //If property rental status = active means the property is active and there is no tenant = false
            //If property rental status = not active means the property is not active and there is tenant = true

            if(propHiddenStatusComboBox.getValue().isEmpty()){
                (new Alert(AlertType.ERROR,"Property Hidden Status can't be blank")).show();
            }
            else{
                if (Globals.LogicModel.addingPropertyValidation(propInfoList_TobeValided,propInfoList_tobeAdded)){

                    Property propertyToBeAdded = Globals.LogicModel.getPropertyObject(propInfoList_tobeAdded);
                    owner.addProperty(propertyToBeAdded);
                    
                    (new Alert(AlertType.CONFIRMATION,"Property added!")).showAndWait();
                    backButtonHandler();

                }
                else{
                    (new Alert(AlertType.ERROR,"Error occured please try again.")).show();
                }

            }
        }
       
    
    }

    public void clearButtonHandler(){

        propNameTextField.clear();
        propOwnerComboBox.setValue("");
        propOwnerContactNumTextField.clear();
        propRentalRateTextField.clear();
        propTypeComboBox.setValue("Unspecified");
        propHiddenStatusComboBox.setValue("");
        propRentalStatusComboBox.setValue("");

    }

    public void backButtonHandler() throws IOException{
        //CHANGE TO PROPERTYOWNERPROPERTYLISTING.FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminPropertyListing.fxml"));
        Parent root = loader.load();

        propertyOwnerPropertyListingController controller =  loader.getController();
        controller.initialiseOwnerInfo(owner);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void passedInOwnerObject(propertyOwner owner2){
        owner = owner2;
    }
}
