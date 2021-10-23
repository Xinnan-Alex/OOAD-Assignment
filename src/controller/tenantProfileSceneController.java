//PROFILE SETTING INTERFACE CONTROLLER
package controller;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import model.*;
import main.*;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

//settingSceneController class
public class tenantProfileSceneController implements Initializable{

    tenant loggedinPerson;

    @FXML
    Button changePassword,backButton,changeContactNumber;

    @FXML
    TextField tenantContactNumber, tenantPassword;

    @FXML
    Label fullnameLabel,usernameLabel,useridLabel,usertypeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tenantContactNumber.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));
        
    }
    
    public void setUserInfo(){
        fullnameLabel.setText(loggedinPerson.getFullName());
        usernameLabel.setText(loggedinPerson.getUsername());
        useridLabel.setText(loggedinPerson.getID());
        usertypeLabel.setText(loggedinPerson.getUserType());
        
    }


    public void backButtonHandler() throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        
        loader = new FXMLLoader(getClass().getResource("../resources/fxml/tenant/tenantHomepageScene.fxml"));

        Parent root = loader.load();
        
        tenantHomepageSceneController controller =  loader.getController();
  
        controller.initUserObejct(loggedinPerson);
        
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void changePassword() throws IOException{
        
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to change to this password?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            for(person p: Model.userInfo){
                if(p.getID().equals(loggedinPerson.getID())){
                    p.setPassword(tenantPassword.getText());
                    loggedinPerson.setPassword(tenantPassword.getText());
                }
            }
            Globals.LogicModel.writeToUserDataCSV();
            (new Alert(AlertType.INFORMATION,"Your password has successfully changed")).show();
        }
    }

    public void changeContactNumber() throws IOException{
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to change to this contact number?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            for(person p: Model.userInfo){
                if(p.getID().equals(loggedinPerson.getID())){
                    p.setPhonenumber(tenantContactNumber.getText());
                    loggedinPerson.setPassword(tenantContactNumber.getText());
                }
            }
            Globals.LogicModel.writeToUserDataCSV();
            (new Alert(AlertType.INFORMATION,"Your contact number has successfully changed")).show();
        }

    }


    public void initUserObejct(tenant passedIN){
        loggedinPerson = passedIN;
        setUserInfo();
    }


    

        
 
    }
