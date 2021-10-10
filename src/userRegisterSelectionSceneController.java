import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class userRegisterSelectionSceneController {
    private Stage stage;
    private static String usertype;

    @FXML
    Button registerAgentButton,registerPropertyOwnerButton,registerTenantButton;

    public void registerPropertyOwnerButtonHandler() throws IOException{
        usertype = "property owner";
        
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/property owner/propOwnerRegisterScene.fxml"));
        stage = (Stage) registerTenantButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

    public void registerTenantButtonHandler() throws IOException{
        usertype = "tenant";

        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/tenant/tenantRegisterScene.fxml"));
        stage = (Stage) registerTenantButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

    public static String getUserType(){
        return usertype;
    }
}
