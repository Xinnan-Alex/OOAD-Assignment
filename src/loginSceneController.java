//LOGIN INTERFACE CONTROLLER

//JAVA IMPORT
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private Stage stage;
    public Model logicModel = new Model(); 

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
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/userRegisterSelectionScene.fxml"));
        stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    public void loginButtonHandler() throws IOException{
        //logicModel.loadUserInfo();
        String loginUsernameInput = loginUsername.getText();
        String loginPassInput = loginPass.getText(); 
        

        if (logicModel.loginStatus(loginUsernameInput,loginPassInput)){

            Alert loginSuccessfuAlert = new Alert(AlertType.INFORMATION,"Login successfully");
            loginSuccessfuAlert.showAndWait();

            FXMLLoader loader = new FXMLLoader();
            if(logicModel.getUsertype(loginUsernameInput).equals("tenant")){
                loader = new FXMLLoader(getClass().getResource("resources/fxml/tenant/tenantHomepageScene.fxml"));
                Parent root = loader.load();

                stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));

            }
            else if(logicModel.getUsertype(loginUsernameInput).equals("admin")){
                person user = logicModel.getPersonObeject(loginUsernameInput);

                Admin admin = new Admin(user);

                loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminHomepageScene.fxml"));
                Parent root = loader.load();

                adminHomepageSceneController controller =  loader.getController();
                controller.initUserObejct(admin);
                
                stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));

            }
            else if(logicModel.getUsertype(loginUsernameInput).equals("property owner")){
                loader = new FXMLLoader(getClass().getResource("resources/fxml/property owner/propertyownerHomepageScene.fxml"));
            }
            
            
        }
        else{
            Alert usernameIncorrect = new Alert(AlertType.ERROR,"Invalid login details please try again");
            usernameIncorrect.show();
        }


        
    }


    
}
