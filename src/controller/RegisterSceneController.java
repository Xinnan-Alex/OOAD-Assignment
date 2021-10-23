//REGISTER INTERFACE CONTROLLER
package controller;
import main.*;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

//JAVAFX IMPORTS
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

//RegisterSceneController class
public class RegisterSceneController implements Initializable{

    private Stage stage;
    
    @FXML
    Button registerNextButton,registerBackButton;

    @FXML
    TextField registerUsername,registerFullname,registerContactNum,registerPass,registerRePass;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        registerContactNum.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));

        registerUsername.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().equals(" ")) {
                change.setText("");
            }
            return change;
        }));
        
    }

    public void registerBackButtonHandler() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/userRegisterSelectionScene.fxml"));
        stage = (Stage) registerBackButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void registerNextButtonHandler() throws IOException{
        String[] registerInfo_validation = {registerUsername.getText(),registerFullname.getText(),registerPass.getText(),registerRePass.getText(),registerContactNum.getText()};
        String[] registerInfo_write = {registerUsername.getText(),registerPass.getText(),registerFullname.getText(),registerContactNum.getText(),UUID.randomUUID().toString(),userRegisterSelectionSceneController.getUserType()};
        
        //check for existing username in csv file
        if (Globals.LogicModel.registerInfoValidation(registerInfo_validation)){
            Globals.LogicModel.writeToUserDataCSV(Globals.LogicModel.addUserInfo(registerInfo_write));

            Alert registerDoneAlert = new Alert(AlertType.INFORMATION,"Your account has been created successfully!");
            registerDoneAlert.show();

            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/loginScene.fxml"));
            stage = (Stage) registerNextButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        }

    }
    
}
