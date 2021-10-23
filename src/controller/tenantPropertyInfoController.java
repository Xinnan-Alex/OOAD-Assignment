import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class tenantPropertyInfoController {

    tenant loggedinPerson;

    @FXML
    private Button backButton;

    public void backButtonHandler() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/tenant/tenantPropertyScene.fxml"));
        Parent root = fxmlLoader.load();
        
        tenantPropertyController controller = fxmlLoader.getController();
        controller.initUserObejct(loggedinPerson);
        
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500)); 
    }

     public void initUserObejct(tenant passedIN){
         loggedinPerson = passedIN;
     }
    
}
