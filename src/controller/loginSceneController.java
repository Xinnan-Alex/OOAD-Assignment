//LOGIN INTERFACE CONTROLLER
package controller;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.admin.adminHomepageSceneController;
import controller.propertyowner.propertyOwnerHomepageSceneController;
import controller.tenant.tenantHomepageSceneController;
import model.*;
import main.*;

//JAVAFX IMPORTS
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.TextFormatter;

//loginSceneController class
public class loginSceneController implements Initializable{

    @FXML
    Button registerButton,loginButton;
    @FXML
    TextField loginUsername,loginPass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginUsername.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().equals(" ")) {
                change.setText("");
            }
            return change;
        }));
        
    }


    public void registerButtonHandler() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/userRegisterSelectionScene.fxml"));
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    public void loginButtonHandler() throws IOException{
        //Globals.LogicModel.loadUserInfo();
        String loginUsernameInput = loginUsername.getText();
        String loginPassInput = loginPass.getText(); 
        

        if (Globals.LogicModel.loginStatus(loginUsernameInput,loginPassInput)){

            Alert loginSuccessfuAlert = new Alert(AlertType.INFORMATION,"Login successfully");
            loginSuccessfuAlert.showAndWait();

            FXMLLoader loader = new FXMLLoader();
            if(Globals.LogicModel.getUsertype(loginUsernameInput).equals("tenant")){
                loader = new FXMLLoader(getClass().getResource("../resources/fxml/tenant/tenantHomepageScene.fxml"));
                Parent root = loader.load();
                person user = Globals.LogicModel.getPersonObject(loginUsernameInput);

                Tenant initTenant = new Tenant(user);
                tenantHomepageSceneController controller =  loader.getController();
                controller.initUserObejct(initTenant);
               

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));

            }
            else if(Globals.LogicModel.getUsertype(loginUsernameInput).equals("admin")){
                person user = Globals.LogicModel.getPersonObject(loginUsernameInput);

                Admin admin = new Admin(user);

                loader = new FXMLLoader(getClass().getResource("../resources/fxml/admin/adminHomepageScene.fxml"));
                Parent root = loader.load();

                adminHomepageSceneController controller =  loader.getController();
                controller.initUserObejct(admin);
                
                
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));

            }
            else if(Globals.LogicModel.getUsertype(loginUsernameInput).equals("property owner")){
                loader = new FXMLLoader(getClass().getResource("../resources/fxml/propertyowner/propertyownerHomepageScene.fxml"));
                Parent root = loader.load();

                person user = Globals.LogicModel.getPersonObject(loginUsernameInput);

                propertyOwner initPropertyOwner = new propertyOwner(user);
                propertyOwnerHomepageSceneController controller =  loader.getController();
                controller.initUserObejct(initPropertyOwner);

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            }
            
            
        }
        else{
            Alert usernameIncorrect = new Alert(AlertType.ERROR,"Invalid login details please try again");
            usernameIncorrect.show();
        }  
    }
    
}
