//ADMIN ADD PERSON INTERFACE CONTROLLER (Admin Feature)
package controller.admin;
import model.*;
import main.*;

//JAVA IMPORT
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

//adminAddPersonSceneController class
public class adminAddPersonSceneController implements Initializable{
    Admin admin;

    @FXML
    TextField username,fullname,contactnum,password,reEnterPassword;

    @FXML
    Button confirmButton,backButton;

    @FXML
    ComboBox<String> userType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactnum.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));

        userType.getItems().clear();
        userType.setValue("tenant");
        userType.getItems().addAll("tenant","property owner");
        
    }

    public void initialiseAdminInfo(Admin passedIn){
        admin = passedIn;
    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/admin/adminAccountAdminstrationScene.fxml"));
        Parent root = loader.load();

        adminAccountAdminstrationController controller =  loader.getController();
        controller.initialiseAdminInfo(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    public void confirmButtonHandler() throws IOException{
        
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to add this person details?",ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            String[] addPersonInfo_validation = {username.getText(),fullname.getText(),password.getText(),reEnterPassword.getText(),contactnum.getText()};
            if (Globals.LogicModel.registerInfoValidation(addPersonInfo_validation)){
                String[] addPersonInfo_Write = {username.getText(),password.getText(),fullname.getText(),contactnum.getText(),UUID.randomUUID().toString(),userType.getValue()};
                admin.addPerson(addPersonInfo_Write);
                (new Alert(AlertType.INFORMATION,"Account created succesfully")).showAndWait();
                backButtonHandler();

            }
            else{
                (new Alert(AlertType.ERROR,"Invalid information inputted, please try again!")).showAndWait();
            }
            
        }
    }

}
