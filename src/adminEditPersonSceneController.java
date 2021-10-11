import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.collections.FXCollections;
import javafx.scene.control.TextFormatter;


public class adminEditPersonSceneController implements Initializable{

    Admin admin;
    person selectedPerson;

    @FXML
    TextField username,fullname,contactnum,password,reEnterPassword;

    @FXML
    ComboBox<String> userType;

    @FXML
    Button confirmButton,backButton;

    @FXML
    Label titleLabel,uuidLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userType.getItems().clear();
        userType.getItems().addAll("tenant","property owner");

        contactnum.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));

        username.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().equals(" ")) {
                change.setText("");
            }
            return change;
        }));
        
    }

    public void initialiseAdminInfo(Admin passedin){
        admin = passedin;
    }

    public void initialiseSelectedPersonInfo(person passedInSelectedPerson){
        selectedPerson = passedInSelectedPerson;
        username.setText(selectedPerson.getUsername());
        fullname.setText(selectedPerson.getFullName());
        contactnum.setText(selectedPerson.getPhoneNumber());
        password.setText(selectedPerson.getPassword());
        userType.setValue(selectedPerson.getUserType());

        titleLabel.setText("Editing " + selectedPerson.getFullName() + "'s Information: " );
        uuidLabel.setText("UUID: " + selectedPerson.getUUID().toString());

    }

    public void confirmButtonHandler() throws IOException{
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to edit this person details?",ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            String[] editPersonInfo_validation = {username.getText(),fullname.getText(),password.getText(),reEnterPassword.getText(),contactnum.getText()};
            if (Globals.LogicModel.editSelectedPersonDataValidation(editPersonInfo_validation)){
                String[] editPersonInfo_Write = {username.getText(),password.getText(),fullname.getText(),contactnum.getText(),userType.getValue()};
                Globals.LogicModel.editSelectedPersonData(selectedPerson,editPersonInfo_Write);
                (new Alert(AlertType.INFORMATION,"Account edited succesfully")).showAndWait();
                backButtonHandler();

            }
            else{
                
            }
        }

    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminAccountAdminstrationScene.fxml"));
        Parent root = loader.load();

        adminAccountAdminstrationController controller =  loader.getController();
        controller.initialiseAdminInfo(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
}