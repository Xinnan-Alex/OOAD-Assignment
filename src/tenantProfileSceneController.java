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
public class tenantProfileSceneController {

    tenant loggedinPerson;

    @FXML
    Button changePassButton,backButton;

    @FXML
    Label fullnameLabel,usernameLabel,contactnumLabel,useridLabel,usertypeLabel;
    
    public void setUserInfo(){
        fullnameLabel.setText(loggedinPerson.getFullName());
        usernameLabel.setText(loggedinPerson.getUsername());
        contactnumLabel.setText(loggedinPerson.getPhoneNumber());
        useridLabel.setText(loggedinPerson.getID());
        usertypeLabel.setText(loggedinPerson.getUserType());
        
    }


    public void backButtonHandler() throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        
        loader = new FXMLLoader(getClass().getResource("resources/fxml/tenant/tenantHomepageScene.fxml"));

        Parent root = loader.load();
        
        tenantHomepageSceneController controller =  loader.getController();
  
        controller.initUserObejct(loggedinPerson);
        
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void changePassButtonHandler() throws IOException{
        
    }


    public void initUserObejct(tenant passedIN){
        loggedinPerson = passedIN;
        setUserInfo();
    }


    // public void backButtonHandler() throws IOException{
        
    //     Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/tenant/tenantHomepageScene.fxml"));

    //     Stage window = (Stage)backButton.getScene().getWindow();
    //     window.setScene(new Scene(root, 750, 500)); 

        
        //adminHomepageSceneController controller =  loader.getController();
        // controller.displayName(loggedinPerson.getFullName());
        // controller.displayID(loggedinPerson.getID());
        
        // controller.initUserObejct(loggedinPerson);
        
 
    }
