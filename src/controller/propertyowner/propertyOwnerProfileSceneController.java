//PROFILE SETTING INTERFACE CONTROLLER
package controller.propertyowner;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.tenant.tenantHomepageSceneController;
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
public class propertyOwnerProfileSceneController implements Initializable{

    propertyOwner owner;

    @FXML
    Button changePassword,backButton,changeContactNumber;

    @FXML
    TextField propertyOwnerContactNumber, propertyOwnerPassword;

    @FXML
    Label fullnameLabel,usernameLabel,useridLabel,usertypeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyOwnerContactNumber.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));
        
    }
    
    public void setUserInfo(){
        fullnameLabel.setText(owner.getFullName());
        usernameLabel.setText(owner.getUsername());
        useridLabel.setText(owner.getID());
        usertypeLabel.setText(owner.getUserType());
        propertyOwnerContactNumber.setText(owner.getPhoneNumber());
    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/propertyowner/propertyownerHomepageScene.fxml"));
        Parent root = loader.load();

        propertyOwnerHomepageSceneController controller =  loader.getController();
        controller.initUserObejct(owner);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void changePassword() throws IOException{

        TextInputDialog passwordConfirmationdDialog = new TextInputDialog();
        passwordConfirmationdDialog.setHeaderText("Please input your password");
        passwordConfirmationdDialog.showAndWait();

        if(passwordConfirmationdDialog.getEditor().getText().equals(owner.getPassword())){
            Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to change to this password?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            confirmation_Alert.showAndWait();
    
            if (confirmation_Alert.getResult() == ButtonType.YES){
                for(person p: Model.userInfo){
                    if(p.getID().equals(owner.getID())){
                        p.setPassword(propertyOwnerPassword.getText());
                        owner.setPassword(propertyOwnerPassword.getText());
                    }
                }
                Globals.LogicModel.writeToUserDataCSV();
                (new Alert(AlertType.INFORMATION,"Your password has successfully changed")).show();
            }
            else{
                propertyOwnerPassword.clear();
            }
        }
        else{
            (new Alert(AlertType.ERROR,"Invalid Password, Please try again")).show();
            propertyOwnerPassword.clear();
        }
        
    }

    public void changeContactNumber() throws IOException{
        TextInputDialog passwordConfirmationdDialog = new TextInputDialog();
        passwordConfirmationdDialog.setHeaderText("Please input your password");
        passwordConfirmationdDialog.showAndWait();
        if(passwordConfirmationdDialog.getEditor().getText().equals(owner.getPassword())){
            Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to change to this contact number?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            confirmation_Alert.showAndWait();
            if (confirmation_Alert.getResult() == ButtonType.YES){
                if(Globals.LogicModel.ContactnumValidation(propertyOwnerContactNumber.getText())){
                    for(person p: Model.userInfo){
                        if(p.getID().equals(owner.getID())){
                            p.setPhonenumber(propertyOwnerContactNumber.getText());
                            owner.setPassword(propertyOwnerContactNumber.getText());
                        }
                    }
                    Globals.LogicModel.writeToUserDataCSV();
                    (new Alert(AlertType.INFORMATION,"Your contact number has successfully changed")).show();
                }
                else{
                    (new Alert(AlertType.ERROR,"Invalid Phone Number, Please try again")).show();
                    propertyOwnerContactNumber.setText(owner.getPhoneNumber());
                }
            }  
        }
        else{
            (new Alert(AlertType.ERROR,"Invalid Password, Please try again")).show();
        }
       

    }

    public void initUserObejct(propertyOwner passedIN){
        owner = passedIN;
        setUserInfo();
    }
 
}
