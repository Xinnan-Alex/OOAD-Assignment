//PROFILE SETTING INTERFACE CONTROLLER
package controller.admin;

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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

//settingSceneController class
public class adminProfileSceneController implements Initializable{

    Admin loggedinPerson;

    @FXML
    Button changePassword,backButton,changeContactNumber;

    @FXML
    TextField adminContactNumber, adminPassword;

    @FXML
    Label fullnameLabel,usernameLabel,useridLabel,usertypeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminContactNumber.setTextFormatter(new TextFormatter<>(c -> {
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
        
        loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminHomepageScene.fxml"));

        Parent root = loader.load();
        
        adminHomepageSceneController controller =  loader.getController();
  
        controller.initUserObejct(loggedinPerson);
        
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void changePassword() throws IOException{
        
        TextInputDialog passwordConfirmationdDialog = new TextInputDialog();
        passwordConfirmationdDialog.setHeaderText("Please input your password");
        passwordConfirmationdDialog.showAndWait();

        if(passwordConfirmationdDialog.getEditor().getText().equals(loggedinPerson.getPassword())){
            Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to change to this password?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            confirmation_Alert.showAndWait();
    
            if (confirmation_Alert.getResult() == ButtonType.YES){
                for(person p: Model.userInfo){
                    if(p.getID().equals(loggedinPerson.getID())){
                        p.setPassword(adminPassword.getText());
                        loggedinPerson.setPassword(adminPassword.getText());
                    }
                }
                Globals.LogicModel.writeToUserDataCSV();
                (new Alert(AlertType.INFORMATION,"Your password has successfully changed")).show();
            }
            else{
                adminPassword.clear();
            }
        }
        else{
            (new Alert(AlertType.ERROR,"Invalid Password, Please try again")).show();
            adminPassword.clear();
        }
    }

    public void changeContactNumber() throws IOException{
        TextInputDialog passwordConfirmationdDialog = new TextInputDialog();
        passwordConfirmationdDialog.setHeaderText("Please input your password");
        passwordConfirmationdDialog.showAndWait();
        if(passwordConfirmationdDialog.getEditor().getText().equals(loggedinPerson.getPassword())){
            Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to change to this contact number?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            confirmation_Alert.showAndWait();
            if (confirmation_Alert.getResult() == ButtonType.YES){
                if(Globals.LogicModel.ContactnumValidation(adminContactNumber.getText())){
                    for(person p: Model.userInfo){
                        if(p.getID().equals(loggedinPerson.getID())){
                            p.setPhonenumber(adminContactNumber.getText());
                            loggedinPerson.setPassword(adminContactNumber.getText());
                        }
                    }
                    Globals.LogicModel.writeToUserDataCSV();
                    (new Alert(AlertType.INFORMATION,"Your contact number has successfully changed")).show();
                }
                else{
                    (new Alert(AlertType.ERROR,"Invalid Phone Number, Please try again")).show();
                    adminContactNumber.setText(loggedinPerson.getPhoneNumber());
                }
            }  
        }
        else{
            (new Alert(AlertType.ERROR,"Invalid Password, Please try again")).show();
        }
       
    }

    public void initUserObejct(Admin passedIN){
        loggedinPerson = passedIN;
        setUserInfo();
    }
 
}
