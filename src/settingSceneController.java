import java.io.IOException;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class settingSceneController {

    Model logicModel =  new Model();
    person loggedinPerson;

    @FXML
    Button changePassButton,backButton;

    @FXML
    Label fullnameLabel,usernameLabel,contactnumLabel,useridLabel,usertypeLabel;
    
    public void setUserInfo(person u){

        loggedinPerson = u;
        fullnameLabel.setText(loggedinPerson.getFullname());
        usernameLabel.setText(loggedinPerson.getUsername());
        contactnumLabel.setText(loggedinPerson.getPhonenumber());
        useridLabel.setText(loggedinPerson.getID());
        usertypeLabel.setText(loggedinPerson.getUsertype());
        
    }

    public void backButtonHandler() throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        
        loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminHomepageScene.fxml"));

        Parent root = loader.load();
        
        adminHomepageSceneController controller =  loader.getController();
        controller.displayName(loggedinPerson.getFullname());
        controller.displayID(loggedinPerson.getID());

        controller.initUserObejct(loggedinPerson.getUsername());
        
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void changePassButtonHandler() throws IOException{
        
    }
}
