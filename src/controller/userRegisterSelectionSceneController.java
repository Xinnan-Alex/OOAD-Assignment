//USERTYPE REGISTRATION SELECTION INTERFACE CONTROLLER
package controller;

//JAVA IMPORTS
import java.io.IOException;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

//userRegisterSelectionSceneController class
public class userRegisterSelectionSceneController {
    private static String usertype;

    @FXML
    Button registerAgentButton,registerPropertyOwnerButton,registerTenantButton,backButton;

    public void registerPropertyOwnerButtonHandler() throws IOException{
        usertype = "property owner";
        
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/propertyowner/propOwnerRegisterScene.fxml"));
        Stage stage = (Stage) registerTenantButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

    public void registerTenantButtonHandler() throws IOException{
        usertype = "tenant";

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/tenant/tenantRegisterScene.fxml"));
        Stage stage = (Stage) registerTenantButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

    public static String getUserType(){
        return usertype;
    }

    public void backButtonHandler() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/loginScene.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
