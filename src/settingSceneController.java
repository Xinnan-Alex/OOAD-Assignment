//PROFILE SETTING INTERFACE CONTROLLER

//JAVA IMPORTS
import java.io.IOException;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

//settingSceneController class
public class settingSceneController {

    Admin loggedinPerson;

    @FXML
    Button changePassButton,backButton;

    @FXML
    Label fullnameLabel,usernameLabel,contactnumLabel,useridLabel,usertypeLabel;
    
    public void setUserInfo(Admin u){
        loggedinPerson = u;
        fullnameLabel.setText(loggedinPerson.getFullName());
        usernameLabel.setText(loggedinPerson.getUsername());
        contactnumLabel.setText(loggedinPerson.getPhoneNumber());
        useridLabel.setText(loggedinPerson.getID());
        usertypeLabel.setText(loggedinPerson.getUserType());
        
    }

    public void backButtonHandler() throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        
        loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminHomepageScene.fxml"));

        Parent root = loader.load();
        
        adminHomepageSceneController controller =  loader.getController();
        controller.displayName(loggedinPerson.getFullName());
        controller.displayID(loggedinPerson.getID());
        
        controller.initUserObejct(loggedinPerson);
        
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void changePassButtonHandler() throws IOException{
        
    }
}
