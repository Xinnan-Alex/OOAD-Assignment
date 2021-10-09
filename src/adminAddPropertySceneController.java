import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextFormatter;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class adminAddPropertySceneController implements Initializable{

    Admin admin;

    @FXML
    ComboBox<String> propTypeComboBox,propHiddenStatusComboBox,propRentalStatusComboBox,propOwnerComboBox;

    @FXML
    TextField propNameTextField,propSizeTextField,propOwnerContactNumTextField,propRentalRateTextField;

    @FXML
    Button confirmButton,clearButton,backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propTypeComboBox.getItems().clear();
        propTypeComboBox.getItems().addAll(Globals.propertyType);
        propHiddenStatusComboBox.getItems().clear();
        propHiddenStatusComboBox.getItems().addAll("true","false");
        propRentalStatusComboBox.getItems().clear();
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

    public void propOwnerComboBoxHandler(ActionEvent e) {
        propOwnerContactNumTextField.setText(Globals.LogicModel.getUserContactNum(Globals.LogicModel.getUsername(propOwnerComboBox.getValue())));
    
    }

    public void confirmButtonHandler() throws IOException{
        //Extract info from TextFields
        //propInfoList content 
        //      0          1           2           3           4           5            6           7           8
        //projectName,propertySize,rentalRate,propertyType, propertyOwner,contactNum,propertyID,hiddenStatus,rentStatus
        String[] propInfoList_tobeAdded = new String[]{propNameTextField.getText(),propSizeTextField.getText(),propRentalRateTextField.getText(),propTypeComboBox.getValue(),
                    propOwnerComboBox.getValue(),propOwnerContactNumTextField.getText(),propHiddenStatusComboBox.getValue(),
                    propRentalStatusComboBox.getSelectionModel().getSelectedItem()};
        String[] propInfoList_TobeValided = {propHiddenStatusComboBox.getValue(),propOwnerComboBox.getValue()};

        //If property rental status = active means the property is active and there is no tenant = false
        //If property rental status = not active means the property is not active and there is tenant = true

        if(propHiddenStatusComboBox.getValue() == null){
            (new Alert(AlertType.ERROR,"Property Hidden Status can't be blank")).show();
        }
        else{
            if (Globals.LogicModel.addingPropertyValidation(propInfoList_TobeValided,propInfoList_tobeAdded)){

                for (int i=0;i<propInfoList_tobeAdded.length;i++){
                    System.out.print(propInfoList_tobeAdded[i]);
                    System.out.print(",");
                }
                System.out.println();

                Property propertyToBeAdded = Globals.LogicModel.getPropertyObject(propInfoList_tobeAdded);

                Globals.LogicModel.WriteToPropertyListCsv(propertyToBeAdded);
            }
            else{
                System.out.println("data is incorrect");
            }

        }
    
    }

    public void clearButtonHandler(){

        propNameTextField.clear();
        propOwnerComboBox.setValue(null);
        propOwnerContactNumTextField.clear();
        propRentalRateTextField.clear();
        propTypeComboBox.setValue(null);
        propHiddenStatusComboBox.setValue(null);
        propRentalStatusComboBox.setValue(null);

    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminPropertyListing.fxml"));
        Parent root = loader.load();

        adminPropertyListingController controller =  loader.getController();
        controller.initialiseAdminInfo(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void passedInAdminObject(Admin a){
        admin = a;
    }
}
