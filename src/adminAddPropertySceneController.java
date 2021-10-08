import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextFormatter;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class adminAddPropertySceneController implements Initializable{

    Model logicModel =  new Model();

    @FXML
    ComboBox<String> propTypeComboBox,propHiddenStatusComboBox,propRentalStatusComboBox;

    @FXML
    TextField propNameTextField,propSizeTextField,PropOwnerTextField,propOwnerContactNumTextField,propRentalRateTextField;

    @FXML
    Button confirmButton,clearButton,backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propTypeComboBox.getItems().clear();
        propTypeComboBox.getItems().addAll("appartment","terress","flat");
        propHiddenStatusComboBox.getItems().clear();
        propHiddenStatusComboBox.getItems().addAll("true","false");
        propRentalStatusComboBox.getItems().clear();
        propRentalStatusComboBox.getItems().addAll("active", "not Active");

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


    public void confirmButtonHandler(){
        //Extract info from TextFields
        //propInfoList content 
        //      0          1           2           3           4           5            6           7           8
        //projectName,propertySize,rentalRate,propertyType, propertyOwner,contactNum,propertyID,hiddenStatus,rentStatus
        String[] propInfoList_tobeAdded = {propNameTextField.getText(),propSizeTextField.getText(),propRentalRateTextField.getText(),propTypeComboBox.getSelectionModel().getSelectedItem(),
                                            PropOwnerTextField.getText(),propOwnerContactNumTextField.getText(),"10002",propHiddenStatusComboBox.getSelectionModel().getSelectedItem(),
                                            propRentalStatusComboBox.getSelectionModel().getSelectedItem()};

        //If property rental status = active means the property is active and there is no tenant = false
        //If property rental status = not active means the property is not active and there is tenant = true
        if (propRentalStatusComboBox.getSelectionModel().getSelectedItem().equals("active")){
            propInfoList_tobeAdded[8] = "false";
        }
        else{
            propInfoList_tobeAdded[8] = "true";
        }

        for(int i=0;i<propInfoList_tobeAdded.length;i++){
            System.out.println(propInfoList_tobeAdded[i]);
        }
        

        if (logicModel.addingPropertyValidation(propInfoList_tobeAdded)){
            System.out.println("data is correct");
        }else{
            System.out.println("data is incorrect");
        }

    }

    public void clearButtonHandler(){

        propNameTextField.clear();
        PropOwnerTextField.clear();
        propOwnerContactNumTextField.clear();
        propRentalRateTextField.clear();
        propTypeComboBox.setValue(null);
        propHiddenStatusComboBox.setValue(null);
        propRentalStatusComboBox.setValue(null);

    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminPropertyListing.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
