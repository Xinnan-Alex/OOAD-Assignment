//Create Admin Account's Scene Controller (Admin Feature)
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

//adminCreateAdminController class
public class adminCreateAdminController implements Initializable{
    private Admin admin;

    @FXML
    TextField adminUsername,adminPassword,adminReenterPassword,adminFullname;

    @FXML
    PasswordField adminSecretPhrase;

    @FXML
    Button confirmButton,backButton;

    //Method for passing in the admin object from the previous interface
    public void initialiseAdminInfo(Admin passedIn){
        admin = passedIn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminUsername.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().equals(" ")) {
                change.setText("");
            }
            return change;
        }));
    }

    public void confirmButtonHandler() throws IOException{
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to create this admin account?",ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            //Do something
            String admUsername = adminUsername.getText();
            String admPassword = adminPassword.getText();
            String admnFullname = adminFullname.getText();
            String admReEnterPassword = adminReenterPassword.getText();
            String admnSecretPhrase = adminSecretPhrase.getText();

            if(Globals.LogicModel.createAdminAccountValidation(admUsername, admPassword, admReEnterPassword, admnSecretPhrase, admnFullname)){
                person admin_created = admin.createAdminAccount(admUsername, admPassword, admnFullname);
                Globals.LogicModel.writeToUserDataCSV(admin_created);
                (new Alert(AlertType.CONFIRMATION,"Admin account: " + admUsername + " has been succesfully created!")).showAndWait();
                backButtonHandler();
            }
            else{

            }
        }
    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminHomepageScene.fxml"));
        Parent root = loader.load();

        adminHomepageSceneController controller =  loader.getController();
        controller.initUserObejct(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
}
